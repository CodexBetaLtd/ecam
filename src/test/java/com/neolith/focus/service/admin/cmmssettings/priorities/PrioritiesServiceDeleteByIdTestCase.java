package com.neolith.focus.service.admin.cmmssettings.priorities;

import com.neolith.focus.common.TestCase;
import com.neolith.focus.dto.admin.PriorityDTO;
import com.neolith.focus.result.admin.PriorityResult;
import com.neolith.focus.service.admin.api.PriorityService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Component
public class PrioritiesServiceDeleteByIdTestCase extends TestCase{
	

	@Autowired
    private PriorityService prioritiesService;

    protected final String RESULT_IS_ERROR = "isError";

	private boolean isError;

	protected void checkActualResults() throws Exception {
		setActualResult(RESULT_IS_ERROR, isError);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED ,rollbackFor = Exception.class)
	protected void executeTest() throws Exception {
		try {

            PriorityDTO prioritiesDTO = PrioritiesDummyData.getInstance().getDummyDTOPrioritiesOne();

            PriorityResult expectedId = prioritiesService.save(prioritiesDTO);
            prioritiesService.delete(expectedId.getDtoEntity().getId());

            Assert.assertNull("priorities is not null.", prioritiesService.findById(expectedId.getDtoEntity().getId()));

		} catch (Exception e) {
			isError = true;
		}
	}

}
