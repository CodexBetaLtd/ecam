package com.neolith.focus.service.admin.usersite.testCase;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.notNullValue;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neolith.focus.common.TestCase;
import com.neolith.focus.dao.admin.UserSiteDao;
import com.neolith.focus.dto.admin.cmmssetting.UserSiteDTO;
import com.neolith.focus.model.admin.UserSite;
import com.neolith.focus.result.admin.UserSiteResult;
import com.neolith.focus.service.admin.api.UserSiteService;
import com.neolith.focus.service.admin.usersite.UserSiteData;

@Component
public class UserSiteServiceSaveTestCase extends TestCase {

	protected final String RESULT_IS_ERROR = "isError";

	@Autowired
	public UserSiteService userSiteService;

	@Autowired
	public UserSiteDao userSiteDao;

	UserSiteDTO userSiteDTO = UserSiteData.getInstance().getUserSiteDTO();

	UserSiteResult resultDetail;

	UserSite userSite = new UserSite();

	private boolean isError;

	@Override
	protected void checkActualResults() throws Exception {
		setActualResult(RESULT_IS_ERROR, isError);
	}

	@Override
	protected void executeTest() throws Exception {
		try {
			Assert.assertNotNull("UserDTO Is NULL.",userSiteDTO);
			resultDetail = userSiteService.save(userSiteDTO);
			testSetUserSiteGroup(resultDetail);
			//testSaveOrUpdate(userSiteDTO,userSite);
			userSiteDao.delete(userSite.getId());
		} catch (Exception e) {
			isError = true;
		}
	}

	protected void testSetUserSiteGroup(UserSiteResult resultDetail) {
		//Assert.assertNotNull("User Site DTO is null.", userSiteDTO);
		userSite =  userSiteDao.findOne(resultDetail.getIntegerList().get(0));
		//Assert.assertThat(userSite.getUserSiteGroups(), is(not(empty())));
		Assert.assertThat(userSite.getUserSiteGroups(), both(not(empty())).and(notNullValue()));
		Assert.assertEquals("User site Should Be Equal", userSiteDTO.getSiteSiteId(), userSite.getSite().getId());
		Assert.assertEquals("User site group count Should Be Equal", userSiteDTO.getSiteAssignedUserGroups().size(), userSite.getUserSiteGroups().size());

	}



}
