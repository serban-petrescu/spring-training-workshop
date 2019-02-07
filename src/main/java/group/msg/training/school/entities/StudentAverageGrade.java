package group.msg.training.school.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class StudentAverageGrade {
    @Id
    private int studentId;
	private String studentName;
    private int groupId;
    private float average;
}
