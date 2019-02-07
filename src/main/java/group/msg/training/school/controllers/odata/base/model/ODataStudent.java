package group.msg.training.school.controllers.odata.base.model;

import com.sap.cloud.sdk.service.prov.api.annotations.Key;
import group.msg.training.school.dtos.InboundStudent;
import group.msg.training.school.dtos.OutboundStudent;
import lombok.Data;

@Data
public class ODataStudent {
	@Key
	private int id;
	private int groupId;
	private String name;
	private String number;

	public static ODataStudent ofOutboundAndGroupId(OutboundStudent student, int groupId) {
		ODataStudent oDataStudent = new ODataStudent();
		oDataStudent.setId(student.getId());
		oDataStudent.setGroupId(groupId);
		oDataStudent.setName(student.getName());
		oDataStudent.setNumber(student.getNumber());
		return oDataStudent;
	}

	public InboundStudent toInbound() {
		InboundStudent inboundStudent = new InboundStudent();
		inboundStudent.setName(this.getName());
		inboundStudent.setNumber(this.getNumber());
		return inboundStudent;
	}
}
