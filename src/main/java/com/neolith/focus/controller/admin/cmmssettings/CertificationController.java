package com.neolith.focus.controller.admin.cmmssettings;

import com.neolith.focus.constants.ResultStatus;
import com.neolith.focus.dto.admin.CertificationDTO;
import com.neolith.focus.result.admin.CertificationResult;
import com.neolith.focus.service.admin.api.CertificationService;
import com.neolith.focus.service.biz.api.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping(CertificationController.REQUEST_MAPPING_URL)
public class CertificationController {

	public static final String REQUEST_MAPPING_URL = "/certification";

	@Autowired
	private CertificationService certificationService;

	@Autowired
	private BusinessService businessService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addForm(Model model) {
		setCommonData(model, new CertificationDTO());
		return "admin/cmmssetting/lookuptable/certification/add-view";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String findByIDCertification(Integer id, Model model) {
		try {
			CertificationDTO certificationDTO = certificationService.findById(id);
			setCommonData(model, certificationDTO);
			return "admin/cmmssetting/lookuptable/certification/add-view";
		} catch (Exception e) {
            model.addAttribute("error", new ArrayList<String>().add(e.getMessage()));
            return "redirect:/cmmssettings/";
        }
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.POST, RequestMethod.GET })
	public String deleteCertification(Integer id, Model model, RedirectAttributes ra) {

		CertificationResult result = certificationService.delete(id);

		if (result.getStatus().equals(ResultStatus.ERROR)) {
            model.addAttribute("error", result.getErrorList());
        } else {
            model.addAttribute("success", result.getMsgList());
        }

		setCommonData(model, new CertificationDTO());

		return "admin/cmmssetting/lookuptable/certification/add-view";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveOrUpdate(@ModelAttribute("certification") CertificationDTO certificationDTO, Model model, String module) throws Exception {

		CertificationResult result = certificationService.save(certificationDTO);

		if (result.getStatus().equals(ResultStatus.ERROR)) {
            model.addAttribute("error", result.getErrorList());
        } else {
            model.addAttribute("success", result.getMsgList());
        }
		
		setCommonData(model, certificationDTO);
		if(module != null && "user".equals(module)){
			return "admin/user/modal/usercertificationtype/certification-type-add-modal";
		} else { 
			return "admin/cmmssetting/lookuptable/certification/add-view";
		}
	}

	private void setCommonData(Model model, CertificationDTO certificationDTO) {
		model.addAttribute("certification", certificationDTO);
		model.addAttribute("businesses", businessService.findAllActualBusinessByLevel());
	}

}
