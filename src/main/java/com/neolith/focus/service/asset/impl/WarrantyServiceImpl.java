package com.neolith.focus.service.asset.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neolith.focus.dao.admin.MeterReadingUnitDao;
import com.neolith.focus.dao.biz.BusinessDao;
import com.neolith.focus.dto.asset.WarrantyDTO;
import com.neolith.focus.mappers.asset.WarrantyMapper;
import com.neolith.focus.model.asset.Asset;
import com.neolith.focus.model.asset.Warranty;
import com.neolith.focus.service.asset.api.WarrantyService;

@Service
public class WarrantyServiceImpl implements WarrantyService {

	@Autowired
	private MeterReadingUnitDao meterReadingUnitDao;

	@Autowired
	private BusinessDao businessDao;

	@Override
	public void setWarranties(List<WarrantyDTO> warrantyDTOs, Asset asset) throws Exception {
		Set<Warranty> warranties = new HashSet<>();
		if ((warrantyDTOs != null) && (warrantyDTOs.size() > 0)) {
			Set<Warranty> currentWarranties = asset.getWarranties();
			for (WarrantyDTO warrantyDTO : warrantyDTOs) {
				Warranty warranty = getCurrentWarranty(currentWarranties, warrantyDTO);
				updateWarranty(warrantyDTO, warranty, asset);
				warranties.add(warranty);
			}
		}
		asset.setWarranties(warranties);
	}

	private Warranty getCurrentWarranty(Set<Warranty> currentWarranties, WarrantyDTO warrantyDTO) {
		Warranty warranty;

		if ((currentWarranties != null) && (currentWarranties.size() > 0)) {
			Optional<Warranty> optionalWarranty = currentWarranties.stream().filter((x) -> x.getId() == warrantyDTO.getWarrantyId()).findAny();
			if (optionalWarranty.isPresent()) {
				warranty = optionalWarranty.get();
			} else {
				warranty = new Warranty();
			}
		} else {
			warranty = new Warranty();
		}
		return warranty;
	}

	private void updateWarranty(WarrantyDTO dto, Warranty domain, Asset asset) throws Exception {

		WarrantyMapper.getInstance().dtoToDomain(dto, domain);
		domain.setAsset(asset);

		if ((dto.getWarrantyProviderId() != null) && (dto.getWarrantyProviderId() > 0)) {
			domain.setProvider(businessDao.findById(dto.getWarrantyProviderId()));
		}
		if ((dto.getWarrantyMeterReadingUnitId() != null) && (dto.getWarrantyMeterReadingUnitId() > 0)) {
			domain.setMeterReadingUnit(meterReadingUnitDao.findById(dto.getWarrantyMeterReadingUnitId()));
		}
	}

}
