package group.msg.training.school.controllers.odata.v4;

import com.sap.cloud.sdk.service.prov.api.annotations.Function;
import com.sap.cloud.sdk.service.prov.api.request.OperationRequest;
import com.sap.cloud.sdk.service.prov.api.response.OperationResponse;
import group.msg.training.school.config.ODataInstanceProvider;
import group.msg.training.school.controllers.odata.base.RandomODataController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RandomODataV4Controller {
	private final RandomODataController base;

	public RandomODataV4Controller() {
		this.base = ODataInstanceProvider.getInstanceTyped(RandomODataController.class);
	}

	@Function(serviceName = "srv", Name = "GetRandomIntegers")
	public OperationResponse getRandomIntegers(OperationRequest request) {
		return base.getRandomIntegers(request);
	}
}
