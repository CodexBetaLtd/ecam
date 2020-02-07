package com.codex.ecam.service.inventory.api;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.multipart.MultipartFile;

import com.codex.ecam.constants.PurchaseOrderStatus;
import com.codex.ecam.dto.inventory.purchaseOrder.PurchaseOrderDTO;
import com.codex.ecam.repository.FocusDataTablesInput;
import com.codex.ecam.result.inventory.MRNResult;
import com.codex.ecam.result.purchasing.PurchaseOrderResult;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public interface PurchaseOrderService {

	DataTablesOutput<PurchaseOrderDTO> findAll(FocusDataTablesInput dataTablesInput) throws Exception;

	PurchaseOrderDTO findById(Integer id) throws Exception;
	
	PurchaseOrderDTO statusChange(Integer id,PurchaseOrderStatus status);
	
	PurchaseOrderDTO createPurchaseOrderFromRFQItems(String rfqItemIds);
	
	PurchaseOrderDTO createPurchaseOrderFromRFQItems(List<Integer> rfqItemIds);

	PurchaseOrderResult update(PurchaseOrderDTO purchaseOrder);

	PurchaseOrderResult save(PurchaseOrderDTO purchaseOrder);

	PurchaseOrderResult delete(Integer id);

	void purchaseOrderFileDownload(Integer id, HttpServletResponse response) throws IOException;

	String purchaseOrderFileUpload(MultipartFile fileData, String refId);

	void purchaseOrderFileDelete(Integer id);
    MRNResult generatePoFromMrn(String ids, Integer mrnId);


}
