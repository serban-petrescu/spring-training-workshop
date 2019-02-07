package group.msg.training.school.controllers.odata.base.model;

import com.sap.cloud.sdk.service.prov.api.annotations.Key;
import group.msg.training.school.dtos.InboundGrade;
import group.msg.training.school.dtos.OutboundGrade;
import lombok.Data;

import java.time.ZoneOffset;
import java.util.Date;

@Data
public class ODataGrade {
	@Key
	private int id;
	private int studentId;
	private Date date;
	private int score;

	public static ODataGrade ofOutboundAndStudentId(OutboundGrade outbound, int studentId) {
		ODataGrade oDataGrade = new ODataGrade();
		oDataGrade.setId(outbound.getId());
		oDataGrade.setStudentId(studentId);
		if (outbound.getDate() != null) {
			oDataGrade.setDate(Date.from(outbound.getDate().atStartOfDay().toInstant(ZoneOffset.UTC)));
		}
		oDataGrade.setScore(outbound.getScore());
		return oDataGrade;
	}

	public InboundGrade toInbound() {
		InboundGrade inboundGrade = new InboundGrade();
		inboundGrade.setScore(this.getScore());
		return inboundGrade;
	}
}
