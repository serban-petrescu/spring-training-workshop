package group.msg.training.school.mappers;

import group.msg.training.school.dtos.OutboundGrade;
import group.msg.training.school.entities.Grade;

public class GradeMapper {

	private GradeMapper() {
		super();
	}

	public static OutboundGrade toOutbound(Grade grade) {
		OutboundGrade outboundGrade = new OutboundGrade();
		outboundGrade.setId(grade.getId());
		outboundGrade.setDate(grade.getDate());
		outboundGrade.setScore(grade.getScore());
		return outboundGrade;
	}
}
