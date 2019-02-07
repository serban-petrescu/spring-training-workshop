package group.msg.training.school.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OutboundGrade {
	private int id;
	private LocalDate date;
	private int score;
}
