package group.msg.training.school.controllers.odata.base;

import com.sap.cloud.sdk.service.prov.api.request.CreateRequest;
import com.sap.cloud.sdk.service.prov.api.request.QueryRequest;
import com.sap.cloud.sdk.service.prov.api.response.CreateResponse;
import com.sap.cloud.sdk.service.prov.api.response.QueryResponse;
import group.msg.training.school.controllers.odata.base.model.ODataGroup;
import group.msg.training.school.dtos.InboundGroup;
import group.msg.training.school.dtos.OutboundGroup;
import group.msg.training.school.services.BrowsingService;
import group.msg.training.school.services.StartOfYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GroupODataController {
	private final StartOfYearService startOfYearService;
	private final BrowsingService browsingService;

	public QueryResponse getGroups(QueryRequest request) {
		return QueryResponse.setSuccess().setData(browsingService.listGroups().stream()
				.map(ODataGroup::ofOutbound)
				.collect(Collectors.toList())).response();
	}

	public CreateResponse createEmptyGroup(CreateRequest request) {
		InboundGroup inbound = request.getDataAs(ODataGroup.class).toInbound();
		OutboundGroup outbound = startOfYearService.createEmptyGroup(inbound);
		return CreateResponse.setSuccess().setData(ODataGroup.ofOutbound(outbound)).response();
	}
}
