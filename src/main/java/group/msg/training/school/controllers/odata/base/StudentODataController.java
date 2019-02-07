package group.msg.training.school.controllers.odata.base;

import com.sap.cloud.sdk.service.prov.api.request.CreateRequest;
import com.sap.cloud.sdk.service.prov.api.request.OperationRequest;
import com.sap.cloud.sdk.service.prov.api.request.QueryRequest;
import com.sap.cloud.sdk.service.prov.api.response.CreateResponse;
import com.sap.cloud.sdk.service.prov.api.response.OperationResponse;
import com.sap.cloud.sdk.service.prov.api.response.QueryResponse;
import group.msg.training.school.controllers.odata.base.model.ODataStudent;
import group.msg.training.school.dtos.OutboundStudent;
import group.msg.training.school.services.BrowsingService;
import group.msg.training.school.services.LaboratoryService;
import group.msg.training.school.services.StartOfYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentODataController {
	private final StartOfYearService startOfYearService;
	private final BrowsingService browsingService;
	private final LaboratoryService laboratoryService;

	public QueryResponse getStudentsFromGroup(QueryRequest request) {
		Integer groupId = (Integer) request.getSourceKeys().get("id");
		List<OutboundStudent> students = browsingService.listStudentsForGroup(groupId);
		return QueryResponse.setSuccess().setData(students.stream()
				.map(s -> ODataStudent.ofOutboundAndGroupId(s, groupId))
				.collect(Collectors.toList())).response();
	}

	public CreateResponse addStudentToGroup(CreateRequest request) {
		ODataStudent odata = request.getDataAs(ODataStudent.class);
		OutboundStudent outbound = startOfYearService.addStudentToGroup(odata.getGroupId(), odata.toInbound());
		return CreateResponse.setSuccess().setData(ODataStudent.ofOutboundAndGroupId(outbound, odata.getGroupId()))
				.response();
	}

	public OperationResponse getStudentsForHomeworkCheck(OperationRequest request) {
		Integer groupId = (Integer) request.getParameters().get("groupId");
		Integer count = (Integer) request.getParameters().getOrDefault("count", 5);
		List<OutboundStudent> students = laboratoryService.getStudentsForHomeworkCheck(groupId, count);
		return OperationResponse.setSuccess().setData(students.stream()
				.map(s -> ODataStudent.ofOutboundAndGroupId(s, groupId))
				.collect(Collectors.toList())).response();
	}
}
