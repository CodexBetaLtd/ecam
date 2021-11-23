package com.codex.ecam.controller.budget;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codex.ecam.constants.AssetCategoryType;
import com.codex.ecam.constants.ResultStatus;
import com.codex.ecam.constants.budget.BudgetType;
import com.codex.ecam.dto.budget.BudgetDTO;
import com.codex.ecam.result.budget.BudgetResult;
import com.codex.ecam.service.asset.api.AssetService;
import com.codex.ecam.service.biz.api.BusinessService;
import com.codex.ecam.service.budget.api.BudgetService;

@Controller
@RequestMapping(BudgetController.REQUEST_MAPPING_URL)
public class BudgetController {

	public static final String REQUEST_MAPPING_URL = "/budget";

	@Autowired
	private BudgetService budgetService;

	@Autowired
	private BusinessService businessService;

	@Autowired
	private AssetService assetService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {

		return "budget/home-view";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String indexMachine(Model model, @ModelAttribute("success") final ArrayList<String> success, @ModelAttribute("error") final ArrayList<String> error) {
		model.addAttribute("success", success);
		model.addAttribute("error", error);
		return "budget/home-view";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Integer id, Model model, RedirectAttributes ra) {
		try {
			final BudgetResult budget = budgetService.findById(id);
			setCommonData(model, budget.getDtoEntity());
			return "budget/add-view";
		} catch (final Exception e) {
			e.printStackTrace();
			ra.addFlashAttribute("error", new ArrayList<String>().add("Error occured. Please Try again."));
			return "redirect:/budget";
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Integer id, Model model, RedirectAttributes ra) {
		try {
			BudgetResult result = budgetService.delete(id);
			if (result.getStatus().equals(ResultStatus.ERROR)) {
				ra.addFlashAttribute("error", result.getErrorList());
			} else {
				ra.addFlashAttribute("success", result.getMsgList());
			}
		} catch (Exception e) {
			e.printStackTrace();
			ra.addFlashAttribute("error", e.getMessage());
		}

		return "redirect:/budget";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addForm(Model model, RedirectAttributes ra) {
		try {
			setCommonData(model, new BudgetDTO());
			return "budget/add-view";
		} catch (final Exception e) {
			ra.addFlashAttribute("error", new ArrayList<String>().add("Error While Loading Initial Data."));
			return "redirect:/budget";
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editForm(Integer id, Model model, RedirectAttributes ra) {
		try {
			final BudgetResult budget = budgetService.findById(id);
			setCommonData(model, budget.getDtoEntity());
			return "budget/add-view";
		} catch (final Exception e) {
			e.printStackTrace();
			ra.addFlashAttribute("error", new ArrayList<String>().add("Error occured. Please Try again."));
			return "redirect:/budget";
		}
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("budget") @Valid BudgetDTO budgetDTO, Model model) {
		try {
			final BudgetResult result = budgetService.save(budgetDTO);
			budgetDTO = budgetService.findById(budgetDTO.getBudgetId()).getDtoEntity();
			if (result.getStatus().equals(ResultStatus.ERROR)) {
				model.addAttribute("error", result.getErrorList());
			} else {
				model.addAttribute("success", result.getMsgList());
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
		}
		setCommonData(model, budgetDTO);
		return "budget/add-view";
	}

	@RequestMapping(value = "/delete-multiple", method = RequestMethod.GET)
	public String deleteMultipleFacility(Integer ids[], Model model) {

		deleteMultiple(ids, model);

		return "/home-view";
	}

	private void deleteMultiple(Integer[] ids, Model model) {
		try {
			final BudgetResult result = budgetService.deleteMultiple(ids);
			if (result.getStatus().equals(ResultStatus.ERROR)) {
				model.addAttribute("error", result.getErrorList().get(0));
			} else {
				model.addAttribute("success", result.getMsgList().get(0));
			}
		} catch (final DataIntegrityViolationException e) {
			model.addAttribute("error", "Budget already assigned. Please remove from where assigned and try again.");
		}  catch (final Exception e) {
			model.addAttribute("error", e.getMessage());
		}
	}

	private void setCommonData(Model model, BudgetDTO dto) {
		model.addAttribute("budget", dto);
		model.addAttribute("businesses", businessService.findAllActualBusinessByLevel());
		model.addAttribute("sites", assetService.findSiteByBusinessId(dto.getBudgetBusinessId(), AssetCategoryType.LOCATIONS_OR_FACILITIES));
		model.addAttribute("budgetTypes", BudgetType.getBudgetTypes());

	}

}
