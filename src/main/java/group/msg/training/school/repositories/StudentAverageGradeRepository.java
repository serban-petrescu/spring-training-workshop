package group.msg.training.school.repositories;

import group.msg.training.school.entities.StudentAverageGrade;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface StudentAverageGradeRepository extends Repository<StudentAverageGrade, Integer> {

    List<StudentAverageGrade> findByGroupId(int groupId);

}
