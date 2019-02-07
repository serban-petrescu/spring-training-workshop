package group.msg.training.school.controllers.odata.v2;

import com.sap.cloud.sdk.service.prov.api.operations.Create;
import com.sap.cloud.sdk.service.prov.api.operations.Query;
import com.sap.cloud.sdk.service.prov.api.request.CreateRequest;
import com.sap.cloud.sdk.service.prov.api.request.QueryRequest;
import com.sap.cloud.sdk.service.prov.api.response.CreateResponse;
import com.sap.cloud.sdk.service.prov.api.response.QueryResponse;
import group.msg.training.school.controllers.odata.base.GroupODataController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupODataV2Controller {
	private final GroupODataController base;

	@Query(serviceName = "service", entity = "Groups")
	public QueryResponse getGroups(QueryRequest request) {
		return base.getGroups(request);
	}

	@Create(serviceName = "service", entity = "Groups")
	public CreateResponse createEmptyGroup(CreateRequest request) {
		return base.createEmptyGroup(request);
	}
}
