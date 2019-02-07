package group.msg.training.school.controllers.odata.v2;

import com.sap.cloud.sdk.service.prov.api.annotations.Function;
import com.sap.cloud.sdk.service.prov.api.operations.Create;
import com.sap.cloud.sdk.service.prov.api.operations.Query;
import com.sap.cloud.sdk.service.prov.api.request.CreateRequest;
import com.sap.cloud.sdk.service.prov.api.request.OperationRequest;
import com.sap.cloud.sdk.service.prov.api.request.QueryRequest;
import com.sap.cloud.sdk.service.prov.api.response.CreateResponse;
import com.sap.cloud.sdk.service.prov.api.response.OperationResponse;
import com.sap.cloud.sdk.service.prov.api.response.QueryResponse;
import group.msg.training.school.controllers.odata.base.StudentODataController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentODataV2Controller {
	private final StudentODataController base;

	@Query(serviceName = "service", entity = "Students", sourceEntity = "Groups")
	public QueryResponse getStudentsFromGroup(QueryRequest request) {
		return base.getStudentsFromGroup(request);
	}

	@Create(serviceName = "service", entity = "Students")
	public CreateResponse addStudentToGroup(CreateRequest request) {
		return base.addStudentToGroup(request);
	}

	@Function(serviceName = "service", Name = "GetStudentsForHomeworkCheck")
	public OperationResponse getStudentsForHomeworkCheck(OperationRequest request) {
		return base.getStudentsForHomeworkCheck(request);
	}
}
