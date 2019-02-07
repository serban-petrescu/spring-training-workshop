package group.msg.training.school.controllers.odata.v2;

import com.sap.cloud.sdk.service.prov.api.annotations.Function;
import com.sap.cloud.sdk.service.prov.api.request.OperationRequest;
import com.sap.cloud.sdk.service.prov.api.response.OperationResponse;
import group.msg.training.school.controllers.odata.base.RandomODataController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RandomODataV2Controller {
	private final RandomODataController base;

	@Function(serviceName = "service", Name = "GetRandomIntegers")
	public OperationResponse getRandomIntegers(OperationRequest request) {
		return base.getRandomIntegers(request);
	}
}
