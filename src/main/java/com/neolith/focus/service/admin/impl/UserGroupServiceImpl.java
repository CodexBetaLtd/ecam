package com.neolith.focus.service.admin.impl;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.neolith.focus.constants.Menu;
import com.neolith.focus.constants.Page;
import com.neolith.focus.constants.SubMenu;
import com.neolith.focus.dao.admin.UserGroupDao;
import com.neolith.focus.dao.biz.BusinessDao;
import com.neolith.focus.dto.admin.UserGroupDTO;
import com.neolith.focus.mappers.admin.UserGroupMapper;
import com.neolith.focus.model.admin.UserGroup;
import com.neolith.focus.model.admin.UserGroupMenu;
import com.neolith.focus.model.admin.UserGroupPage;
import com.neolith.focus.model.app.AppMenu;
import com.neolith.focus.model.biz.business.Business;
import com.neolith.focus.model.biz.business.BusinessApp;
import com.neolith.focus.repository.FocusDataTablesInput;
import com.neolith.focus.result.admin.UserGroupResult;
import com.neolith.focus.service.admin.api.UserGroupService;
import com.neolith.focus.util.AuthenticationUtil;
import com.neolith.focus.util.search.admin.UserGroupSearchPropertyMapper;
import com.neolith.focus.util.type.GenericCheckBox;

@Service
public class UserGroupServiceImpl implements UserGroupService {

	@Autowired
	private UserGroupDao userGroupDao;

	@Autowired
	private BusinessDao businessDao;

	@Override
	public DataTablesOutput<UserGroupDTO> findAll(FocusDataTablesInput input) throws Exception {
		DataTablesOutput<UserGroup> domainOut;
		
		UserGroupSearchPropertyMapper.getInstance().generateDataTableInput(input);
		
		if(AuthenticationUtil.isAuthUserAdminLevel()){
			domainOut = userGroupDao.findAll(input);
		} else {
			Specification<UserGroup> specification = (root, query, cb) -> cb.equal(root.get("business"), AuthenticationUtil.getLoginUserBusiness());
			domainOut = userGroupDao.findAll(input, specification);
		}
		DataTablesOutput<UserGroupDTO> out = UserGroupMapper.getInstance().domainToDTODataTablesOutput(domainOut);
		return out;
	}

	@Override
	public List<UserGroupDTO> findAll() throws Exception {
		List<UserGroup> domainOut;
		if(AuthenticationUtil.isAuthUserAdminLevel()){
			domainOut = (List<UserGroup>) userGroupDao.findAll();
		} else {
			Specification<UserGroup> specification = (root, query, cb) -> cb.equal(root.get("business"), AuthenticationUtil.getLoginUserBusiness());
			domainOut = (List<UserGroup>) userGroupDao.findAll(specification);
		}  
		return UserGroupMapper.getInstance().domainToDTOList(domainOut);
	}

	@Override
	public UserGroupDTO findById(Integer id) throws Exception {
		UserGroup domain = userGroupDao.findById(id);
		if (domain != null) {
			return UserGroupMapper.getInstance().domainToDtoWithPermission(domain);
		}
		return null;
	}

	@Override
	public UserGroupResult delete(Integer id) {
		UserGroupResult result = new UserGroupResult(null, null);
		try {
			userGroupDao.delete(id);
			result.setResultStatusSuccess();
			result.addToMessageList("User Group Deleted Successfully.");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.setResultStatusError();
			result.addToErrorList("Error Occured While Deleting.");
		}
		return result;
	}

	@Override
	public UserGroupResult save(UserGroupDTO dto) throws Exception {
		UserGroupResult result = createUserGroupResult(dto);
		try {
			saveOrUpdate(result);
			result.addToMessageList(getMessageByAction(dto));
		} catch (ObjectOptimisticLockingFailureException e) {
			result.setResultStatusError();
			result.addToErrorList("User Group Already updated. Please Reload User Group.");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.setResultStatusError();
			result.addToErrorList(ex.getMessage());
		}

		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private void saveOrUpdate(UserGroupResult result) throws Exception {
		removeMenuAndPagePermissions(result);
		UserGroupMapper.getInstance().dtoToDomain(result.getDtoEntity(), result.getDomainEntity());
		setBusiness(result);
		userGroupDao.save(result.getDomainEntity());
		result.updateDtoIdAndVersion();
	}

	private void removeMenuAndPagePermissions(UserGroupResult result) {
		if ( (result.getDomainEntity().getId() != null) && (result.getDomainEntity().getId() > 0) ) {
			result.getDomainEntity().setMenuList(new HashSet<UserGroupMenu>());
			if ( result.getDtoEntity().getPage() != null ) {
				if ( (result.getDomainEntity().getPageList() != null) && (result.getDomainEntity().getPageList().size() > 0) ) {
					Optional<UserGroupPage> optionalUserGroupPage = result.getDomainEntity().getPageList().stream().filter((x) -> x.getPage().equals(result.getDtoEntity().getPage())).findAny();
					if (optionalUserGroupPage.isPresent()) {
						result.getDomainEntity().getPageList().remove(optionalUserGroupPage.get());
					}
				}
			}
		}
	}

	private UserGroupResult createUserGroupResult(UserGroupDTO dto) {
		UserGroupResult result;
		if ((dto.getId() != null) && (dto.getId() > 0)) {
			result = new UserGroupResult(userGroupDao.findOne(dto.getId()), dto);
		} else {
			result = new UserGroupResult(new UserGroup(), dto);
		}

		return result;
	}

	private String getMessageByAction(UserGroupDTO dto) {
		if (dto.getId() == null) {
			return "User Group Added Successfully.";
		} else {
			return "User Group Updated Successfully.";
		}
	}

	private void setBusiness(UserGroupResult result) {
		if ((result.getDtoEntity() != null) && (result.getDtoEntity().getBusinessId() != null)) {
			result.getDomainEntity().setBusiness(businessDao.findOne(result.getDtoEntity().getBusinessId()));
		}
	}

	@Override
	public List<GenericCheckBox<Menu, SubMenu>> getMenuPermissions() {
		List<GenericCheckBox<Menu, SubMenu>> menuPermissionlist = new ArrayList<GenericCheckBox<Menu, SubMenu>>();
		Menu[] menuList = findMenuByBusiness();
		for (Menu menu : menuList) {
			GenericCheckBox<Menu, SubMenu> cBox = new GenericCheckBox<Menu, SubMenu>(menu, null, false);
			List<SubMenu> subMenuList = SubMenu.getSubMenuByMenu(menu);
			List<GenericCheckBox<Menu, SubMenu>> sMenulist = new ArrayList<GenericCheckBox<Menu, SubMenu>>();
			for (SubMenu sMenu : subMenuList) {
				sMenulist.add(new GenericCheckBox<Menu, SubMenu>(null, sMenu, false));
			}
			cBox.setChildList(sMenulist);
			menuPermissionlist.add(cBox);
		}
		return menuPermissionlist;
	}

	private Menu[] findMenuByBusiness() {
		if (AuthenticationUtil.isAuthUserAdminLevel()) {
			return Menu.values();
		} else {
			List<Menu> menus = new ArrayList<>();
			Business business = businessDao.findById(AuthenticationUtil.getLoginUserBusiness().getId());
			for ( BusinessApp app  : business.getBusinessApps() ) {
				for ( AppMenu appMenu : app.getApp().getAppMenus() ) {
					menus.add(appMenu.getMenu());
				}
			}
			return menus.toArray(new Menu[menus.size()]);
		}
	}

	@Override
	public List<Page> findPageListByBusiness() {
		if (AuthenticationUtil.isAuthUserAdminLevel()) {
			return Page.getPageList();
		} else {
			List<Page> pages = new ArrayList<>();

			Business business = businessDao.findById(AuthenticationUtil.getLoginUserBusiness().getId());
			for ( BusinessApp app  : business.getBusinessApps() ) {
				for ( AppMenu appMenu : app.getApp().getAppMenus() ) {
					List<SubMenu> submenus = SubMenu.getSubMenuByMenu(appMenu.getMenu());
					for ( SubMenu subMenu : submenus) {
						pages.addAll(Page.findPageBySubMenu(subMenu));
					}
				}
			}

			return pages;
		}
	}

}
