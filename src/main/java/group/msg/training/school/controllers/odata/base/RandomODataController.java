package group.msg.training.school.controllers.odata.base;

import com.sap.cloud.sdk.service.prov.api.request.OperationRequest;
import com.sap.cloud.sdk.service.prov.api.response.OperationResponse;
import group.msg.training.school.util.RandomNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RandomODataController {
	private final RandomNumberGenerator generator;

	public OperationResponse getRandomIntegers(OperationRequest request) {
		Integer min = (Integer) request.getParameters().get("min");
		Integer max = (Integer) request.getParameters().get("max");
		Integer count = (Integer) request.getParameters().get("count");
		Set<Integer> integers = generator.generateIntegers(count, min, max);
		return OperationResponse.setSuccess().setPrimitiveData(new LinkedList<>(integers)).response();
	}

}
