package group.msg.training.school.controllers.odata.v4;

import com.sap.cloud.sdk.service.prov.api.operations.Create;
import com.sap.cloud.sdk.service.prov.api.operations.Query;
import com.sap.cloud.sdk.service.prov.api.request.CreateRequest;
import com.sap.cloud.sdk.service.prov.api.request.QueryRequest;
import com.sap.cloud.sdk.service.prov.api.response.CreateResponse;
import com.sap.cloud.sdk.service.prov.api.response.QueryResponse;
import group.msg.training.school.config.ODataInstanceProvider;
import group.msg.training.school.controllers.odata.base.GroupODataController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupODataV4Controller {
	private final GroupODataController base;

	public GroupODataV4Controller() {
		this.base = ODataInstanceProvider.getInstanceTyped(GroupODataController.class);
	}

	@Query(serviceName = "srv", entity = "Groups")
	public QueryResponse getGroups(QueryRequest request) {
		return base.getGroups(request);
	}

	@Create(serviceName = "srv", entity = "Groups")
	public CreateResponse createEmptyGroup(CreateRequest request) {
		return base.createEmptyGroup(request);
	}
}
