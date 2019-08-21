package com.neolith.focus.controller.biz.supplier;

import com.neolith.focus.constants.ResultStatus;
import com.neolith.focus.dto.biz.supplier.SupplierDTO;
import com.neolith.focus.result.biz.SupplierResult;
import com.neolith.focus.service.admin.api.CountryService;
import com.neolith.focus.service.admin.api.CurrencyService;
import com.neolith.focus.service.biz.api.BusinessClassificationService;
import com.neolith.focus.service.biz.api.BusinessService;
import com.neolith.focus.service.biz.api.BusinessTypeService;
import com.neolith.focus.service.biz.api.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

import javax.validation.Valid;

@Controller
@RequestMapping(SupplierController.REQUEST_MAPPING_URL)
public class SupplierController {

	public static final String REQUEST_MAPPING_URL = "/supplier";

    @Autowired
    private SupplierService supplierService;
    
    @Autowired
    private BusinessClassificationService businessClassificationService;
    
    @Autowired
    private BusinessTypeService businessTypeService;
    
    @Autowired
    private CurrencyService currencyService;
    
    @Autowired
    private CountryService countryService;
    
    @Autowired
    private BusinessService businessService;
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, @ModelAttribute("success") final ArrayList<String> success, @ModelAttribute("error") final ArrayList<String> error) {
		model.addAttribute("success", success);
		model.addAttribute("error", error);
		return "biz/supplier/home-view";
	} 
    
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addForm(Model model, RedirectAttributes ra) {
        SupplierResult result;
        try {
            result = supplierService.newSupplier();
            setCommonData(model, result.getDtoEntity());
            return "biz/supplier/add-view";
		} catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute("error", "Error While Loading Initial Data.");
            return "redirect:/supplier/";
        }
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editForm(Integer id, Model model, RedirectAttributes ra) {
        SupplierResult result;
        try {
            result = supplierService.findById(id);
            setCommonData(model, result.getDtoEntity());
            return "biz/supplier/add-view";
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute("error", "Error occurred. Please Try again.");
            return "redirect:/supplier/";
        }
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("supplier") @Valid SupplierDTO supplierDTO, Model model, RedirectAttributes ra) {
        SupplierResult result;
        try {
            if (supplierDTO.getId() != null && supplierDTO.getId() > 0) {
                result = supplierService.update(supplierDTO);
            } else {
                result = supplierService.save(supplierDTO);
            }
            if (result.getStatus().equals(ResultStatus.ERROR)) {
                model.addAttribute("error", result.getErrorList());
            } else {
                model.addAttribute("success", result.getMsgList());
            }
            setCommonData(model, supplierDTO);
            return "biz/supplier/add-view";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/supplier/add";
        }
    } 

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Integer id, Model model, RedirectAttributes ra) {
        SupplierResult result;
        try {
            result = supplierService.delete(id);
            if (result.getStatus().equals(ResultStatus.ERROR)) {
                ra.addFlashAttribute("error", result.getErrorList());
            } else {
                ra.addFlashAttribute("success", result.getMsgList());
            }
            return "redirect:/supplier/";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/supplier/";
        }

    } 

    private void setCommonData(Model model, SupplierDTO supplierDTO) {
        model.addAttribute("supplier", supplierDTO);
        model.addAttribute("businessClassifications", businessClassificationService.findAll());
		model.addAttribute("businessTypes", businessTypeService.findAll());
		model.addAttribute("currencyList", currencyService.findAll());
		model.addAttribute("countryList", countryService.findAll());
        model.addAttribute("businesses", businessService.findAllActualBusinessByLevel());
//		model.addAttribute("sites", assetService.findSiteByBusinessId(supplierDTO.getBusinessId(), AssetCategoryType.LOCATIONS_OR_FACILITIES));
    }


}
