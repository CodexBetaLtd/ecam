package com.neolith.focus.mappers.purchasing;

import com.neolith.focus.dto.inventory.rfq.RFQDTO;
import com.neolith.focus.dto.inventory.rfq.RFQFileDTO;
import com.neolith.focus.mappers.GenericMapper;
import com.neolith.focus.model.inventory.rfq.RFQ;
import com.neolith.focus.model.inventory.rfq.RFQFile;
import com.neolith.focus.model.inventory.rfq.RFQItem;

public class RFQMapper extends GenericMapper<RFQ, RFQDTO> {

	private static RFQMapper instance = null;

	private RFQMapper() {
	}

	public static RFQMapper getInstance() {
		if (instance == null) {
			instance = new RFQMapper();
		}
		return instance;
	}

	public RFQDTO domainToDto(RFQ domain) throws Exception {
		RFQDTO dto = new RFQDTO();
		dto.setId(domain.getId());
		dto.setCode(domain.getCode());
		dto.setRfqStatus(domain.getRfqStatus());
		dto.setStatusName(domain.getRfqStatus().getName());

		dto.setExpectedDeliveryDate(domain.getDateExpectedDelivery());
		dto.setRequiredResponseDate(domain.getDateRequiredResponse());
		dto.setSentDate(domain.getDateSent());
		dto.setMessageSubject(domain.getMessageSubject());
		dto.setMessageContent(domain.getMessageContent());
		dto.setQuoteReferenceNumber(domain.getQuoteReferenceNumber());				
		
		dto.setSupplierAddress(domain.getSupplierAddress());		
		dto.setSupplierCity(domain.getSupplierCity());
		dto.setSupplierPostalCode(domain.getSupplierPostalCode());
		dto.setSupplierProvince(domain.getSupplierProvince());		
		
		dto.setShipToAddress(domain.getShippingAddress());
		dto.setShippingCity(domain.getSupplierCity());
		dto.setShippingPostalCode(domain.getShippingPostalCode());
		dto.setShippingProvince(domain.getSupplierProvince());
		
		if ( domain.getSupplierBusiness() != null) {
			dto.setSupplierId(domain.getSupplierBusiness().getId());
			dto.setSupplierName(domain.getSupplierBusiness().getName());
		}
		if ( domain.getBusiness() != null) {
			dto.setBusinessId(domain.getBusiness().getId());
		}
		
		if (domain.getShipTo() != null) {
			dto.setShipToId(domain.getShipTo().getId());
		}
		
		if ( domain.getShippingCountry() != null ) {
			dto.setShipToCountry(domain.getShippingCountry().getId());
		}	
		
		if ( domain.getSupplierCountry() != null ) {
			dto.setSupplierCountry(domain.getSupplierCountry().getId());
		}

		dto.setVersion(domain.getVersion());
		dto.setIsDeleted(domain.getIsDeleted());

		setRFQItems(domain, dto);
		setRFQFile(domain, dto);

		return dto;
	}

	private void setRFQItems(RFQ domain, RFQDTO dto) throws Exception {

		for (RFQItem item : domain.getRfqItems()) {
			dto.getItems().add(RFQItemMapper.getInstance().domainToDto(item));
		}

	}
	
	private void setRFQFile(RFQ domain, RFQDTO dto) throws Exception {
		if (domain.getRfqFiles().size() > 0) {
			for (RFQFile rfqFile :domain.getRfqFiles()) {
				RFQFileDTO rfqFileDTO=new RFQFileDTO();
				rfqFileDTO.setId(rfqFile.getId());
				rfqFileDTO.setItemDescription(rfqFile.getItemDescription());
				rfqFileDTO.setVersion(rfqFile.getVersion());
				rfqFileDTO.setFileLocation(rfqFile.getFileLocation());
				rfqFileDTO.setFileType(rfqFile.getFileType());
				rfqFileDTO.setFileDate(rfqFile.getFileDate());
				dto.getRfqFileDTOs().add(rfqFileDTO);

			}
		}
	}

	public void dtoToDomain(RFQDTO dto, RFQ domain) throws Exception {
		domain.setId(dto.getId());
		domain.setCode(dto.getCode());

		domain.setDateExpectedDelivery(dto.getExpectedDeliveryDate());
		domain.setDateRequiredResponse(dto.getRequiredResponseDate());
		domain.setDateSent(dto.getSentDate());
		domain.setMessageSubject(dto.getMessageSubject());
		domain.setMessageContent(dto.getMessageContent());
		domain.setMessageSubject(dto.getMessageSubject());
		domain.setQuoteReferenceNumber(dto.getQuoteReferenceNumber());
		domain.setRfqStatus(dto.getRfqStatus());

		domain.setSupplierAddress(dto.getSupplierAddress());
		domain.setSupplierCity(dto.getSupplierCity());
		domain.setSupplierPostalCode(dto.getSupplierPostalCode());
		domain.setSupplierProvince(dto.getSupplierProvince());

		domain.setShippingAddress(dto.getShipToAddress());
		domain.setShippingCity(dto.getShippingCity());
		domain.setShippingPostalCode(dto.getShippingPostalCode());
		domain.setShippingProvince(dto.getShippingProvince());

		domain.setBillingAddress(dto.getBillToAddress());
		domain.setBillingCity(dto.getBillingCity());
		domain.setBillingPostalCode(dto.getBillingPostalCode());
		domain.setBillingProvince(dto.getBillingProvince());

		domain.setVersion(dto.getVersion());
		domain.setIsDeleted(dto.getIsDeleted());

	}

    @Override
    public RFQDTO domainToDtoForDataTable(RFQ domain) throws Exception {
    	RFQDTO dto = new RFQDTO();
		dto.setId(domain.getId());
		dto.setCode(domain.getCode());		
		dto.setStatusName(domain.getRfqStatus().getName());
		if ( domain.getSupplierBusiness() != null) {
			dto.setSupplierId(domain.getSupplierBusiness().getId());
			dto.setSupplierName(domain.getSupplierBusiness().getName());
		}
        return dto;
    }

}
