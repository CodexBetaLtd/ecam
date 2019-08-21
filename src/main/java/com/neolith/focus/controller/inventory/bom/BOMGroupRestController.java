package com.neolith.focus.controller.inventory.bom;

import com.neolith.focus.dto.inventory.bom.BOMGroupDTO;
import com.neolith.focus.repository.FocusDataTablesInput;
import com.neolith.focus.service.inventory.api.BOMGroupService;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(BOMGroupRestController.REQUEST_MAPPING_URL)
public class BOMGroupRestController {

    public static final String REQUEST_MAPPING_URL = "restapi/bomgroup";

    @Autowired
    private BOMGroupService bomGroupService;

    @RequestMapping(value = "/tabledata", method = RequestMethod.GET)
    public DataTablesOutput<BOMGroupDTO> findBOMGroups(@Valid FocusDataTablesInput input) {
    	try {
    		return bomGroupService.findAll(input);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    @RequestMapping(value = "/bomgroup-by-business-tabledata", method = RequestMethod.GET)
    public DataTablesOutput<BOMGroupDTO> findBOMGroupsByBusiness(@Valid FocusDataTablesInput input, @Valid Integer bizId) {
        try {
            return bomGroupService.findBOMGroupsByBusiness(input, bizId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
