package com.neolith.focus.controller.inventory.stockAdjustment;

import com.neolith.focus.constants.ResultStatus;
import com.neolith.focus.constants.inventory.StockAdjustmentStatus;
import com.neolith.focus.dto.inventory.stockAdjuestment.StockAdjustmentDTO;
import com.neolith.focus.result.inventory.StockAdjustmentResult;
import com.neolith.focus.service.inventory.api.StockAdjustmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping(StockAdjustmentController.REQUEST_MAPPING_URL)
public class StockAdjustmentController {

    public static final String REQUEST_MAPPING_URL = "/stockAdjustment";

    @Autowired
    private StockAdjustmentService stockAdjustmentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return "inventory/stockAdjustment/home-view";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, @ModelAttribute("success") final ArrayList<String> success, @ModelAttribute("error") final ArrayList<String> error) {
        model.addAttribute("success", success);
        model.addAttribute("error", error);
        return "inventory/stockAdjustment/home-view";
    }

    /*********************************************************************
     * Stock Adjustment Modal Views
     *********************************************************************/

    @RequestMapping(value = "/itemView", method = RequestMethod.GET)
    public String getItemView(Model model) {
        return "general/modal/dt-modal-item";
    }

    @RequestMapping(value = "/stockView", method = RequestMethod.GET)
    public String getStockView(Model model) {
        return "general/modal/dt-modal-stock";
    }

    @RequestMapping(value = "/warehouseView", method = RequestMethod.GET)
    public String getWarehouseView(Model model) {
        return "general/modal/dt-modal-warehouse";
    }


    /*********************************************************************
     * Stock Adjustment Functions
     *********************************************************************/

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model, RedirectAttributes ra) {
        try {
            setCommonData(model, stockAdjustmentService.newStockAdjustment());
            return "inventory/stockAdjustment/add-view";
        } catch (Exception e) {
            ra.addFlashAttribute("error", new ArrayList<String>().add("Error While Loading Initial Data."));
            return "inventory/stockAdjustment/add-view";
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("stockAdjustment") StockAdjustmentDTO dto, Model model) throws Exception {
        StockAdjustmentResult result;
        if ((dto.getId() != null) && (dto.getId() > 0)) {
            result = stockAdjustmentService.update(dto);
        } else {
            result = stockAdjustmentService.save(dto);
        }
        if (result.getStatus().equals(ResultStatus.ERROR)) {
            model.addAttribute("error", result.getErrorList());
        } else {
            model.addAttribute("success", result.getMsgList());
        }
        setCommonData(model, result.getDtoEntity());
        return "inventory/stockAdjustment/add-view";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editForm(Integer id, Model model, RedirectAttributes ra) {
        try {
            setCommonData(model, stockAdjustmentService.findById(id));
            return "inventory/stockAdjustment/add-view";
        } catch (Exception e) {
            ra.addFlashAttribute("error", new ArrayList<String>().add("Error occurred. Please Try again."));
            return "inventory/stockAdjustment/add-view";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Integer id, Model model, RedirectAttributes ra) {
        StockAdjustmentResult result = stockAdjustmentService.delete(id);
        if (result.getStatus().equals(ResultStatus.ERROR)) {
            ra.addFlashAttribute("error", result.getErrorList());
        } else {
            ra.addFlashAttribute("success", result.getMsgList());
        }
        return "inventory/stockAdjustment/add-view";
    }


    @RequestMapping(value = "/statusChange", method = RequestMethod.GET)
    public String aodStatusChange(Integer id, StockAdjustmentStatus stockAdjustmentStatus, Model model, RedirectAttributes ra) throws Exception {
        StockAdjustmentResult result = null;
        if ((id != null) && (id > 0)) {
            try {
                result = stockAdjustmentService.statusChange(id, stockAdjustmentStatus);
                if (result.getStatus().equals(ResultStatus.ERROR)) {
                    //                    model.addAttribute("error", result.getErrorListLine());
                } else {
                    model.addAttribute("success", new ArrayList<String>().add("Successfully Changed the Stock Adjustment Status"));
                }
            } catch (Exception ex) {
                model.addAttribute("error", new ArrayList<String>().add("Error! Cannot Change Stock Adjustment Status...!  " + ex.getMessage()));
            }
        } else {
            model.addAttribute("error", new ArrayList<String>().add("Please Save/Select the Stock Adjustment first"));
        }
        setCommonData(model, result.getDtoEntity());
        return "inventory/stockAdjustment/add-view";
    }

    private void setCommonData(Model model, StockAdjustmentDTO stockAdjustmentDTO) {
        model.addAttribute("stockAdjustment", stockAdjustmentDTO);
    }


}
