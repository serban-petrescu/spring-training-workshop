package group.msg.training.school.controllers.odata.base;

import com.sap.cloud.sdk.service.prov.api.request.CreateRequest;
import com.sap.cloud.sdk.service.prov.api.request.OperationRequest;
import com.sap.cloud.sdk.service.prov.api.request.QueryRequest;
import com.sap.cloud.sdk.service.prov.api.response.CreateResponse;
import com.sap.cloud.sdk.service.prov.api.response.OperationResponse;
import com.sap.cloud.sdk.service.prov.api.response.QueryResponse;
import group.msg.training.school.controllers.odata.base.model.ODataGrade;
import group.msg.training.school.dtos.OutboundGrade;
import group.msg.training.school.entities.StudentAverageGrade;
import group.msg.training.school.services.BrowsingService;
import group.msg.training.school.services.EndOfYearService;
import group.msg.training.school.services.LaboratoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GradeODataController {
	private final LaboratoryService laboratoryService;
	private final BrowsingService browsingService;
	private final EndOfYearService endOfYearService;

	public QueryResponse getGradesOfStudent(QueryRequest request) {
		Integer studentId = (Integer) request.getSourceKeys().get("id");
		List<OutboundGrade> grades = browsingService.listGradesForStudent(studentId);
		return QueryResponse.setSuccess().setData(grades.stream()
				.map(g -> ODataGrade.ofOutboundAndStudentId(g, studentId))
				.collect(Collectors.toList())).response();
	}

	public CreateResponse addGradeToStudent(CreateRequest request) {
		ODataGrade odata = request.getDataAs(ODataGrade.class);
		OutboundGrade outbound = laboratoryService.gradeStudent(odata.getStudentId(), odata.toInbound());
		return CreateResponse.setSuccess().setData(ODataGrade.ofOutboundAndStudentId(outbound, odata.getStudentId()))
				.response();
	}

	public OperationResponse getAverageGradesPerGroup(OperationRequest request) {
		Integer groupId = (Integer) request.getParameters().get("groupId");
		List<StudentAverageGrade> grades = endOfYearService.getAverageGradesForGroup(groupId);
		return OperationResponse.setSuccess().setComplexData(grades.stream()
				.map(this::toDataMap)
				.collect(Collectors.toList())).response();
	}

	private Map<String, Object> toDataMap(StudentAverageGrade grade) {
		Map<String, Object> data = new HashMap<>();
		data.put("groupId", grade.getGroupId());
		data.put("studentId", grade.getStudentId());
		data.put("studentName", grade.getStudentName());
		data.put("average", grade.getAverage());
		return data;
	}
}
