package group.msg.training.school.controllers.odata.base.model;

import com.sap.cloud.sdk.service.prov.api.annotations.Key;
import group.msg.training.school.dtos.InboundGroup;
import group.msg.training.school.dtos.OutboundGroup;
import lombok.Data;

@Data
public class ODataGroup {
	@Key
	private int id;
	private int year;
	private String name;
	private String specialization;

	public static ODataGroup ofOutbound(OutboundGroup outboundGroup) {
		ODataGroup oDataGroup = new ODataGroup();
		oDataGroup.setId(outboundGroup.getId());
		oDataGroup.setYear(outboundGroup.getYear());
		oDataGroup.setName(outboundGroup.getName());
		oDataGroup.setSpecialization(outboundGroup.getSpecialization());
		return oDataGroup;
	}

	public InboundGroup toInbound() {
		InboundGroup inboundGroup = new InboundGroup();
		inboundGroup.setYear(this.getYear());
		inboundGroup.setName(this.getName());
		inboundGroup.setSpecialization(this.getSpecialization());
		return inboundGroup;
	}
}
