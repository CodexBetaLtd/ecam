package com.neolith.focus.service.admin.cmmssettings.meterreadingunits;

import com.neolith.focus.common.TestCase;
import com.neolith.focus.dto.admin.MeterReadingUnitDTO;
import com.neolith.focus.result.admin.MeterReadingUnitResult;
import com.neolith.focus.service.admin.api.MeterReadingUnitService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Component
public class MeaterReadingUnitsServiceCreateTestCase extends TestCase{

	@Autowired
    private MeterReadingUnitService meaterReadingUnitsService;

    // conditions
    protected final String CONDITION_SAVE_ENTITY = "entity";

	protected final String RESULT_IS_ERROR = "isError";

	private boolean isError;

	protected void checkActualResults() throws Exception {
		setActualResult(RESULT_IS_ERROR, isError);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED ,rollbackFor = Exception.class)
	protected void executeTest() throws Exception {
        MeterReadingUnitResult expectedId = null;
        try {
            MeterReadingUnitDTO meterReadingUnitsDTO = (MeterReadingUnitDTO) getTestCondition(CONDITION_SAVE_ENTITY);
            expectedId = meaterReadingUnitsService.save(meterReadingUnitsDTO);

            Assert.assertNotNull("Meater Reading Units is null.", meaterReadingUnitsService.findById(expectedId.getDtoEntity().getId()));

        } catch (Exception e) {
            isError = true;
		} finally {
            meaterReadingUnitsService.delete(expectedId.getDtoEntity().getId());
        }
    }

}
