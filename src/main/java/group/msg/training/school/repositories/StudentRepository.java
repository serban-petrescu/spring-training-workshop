package group.msg.training.school.repositories;

import group.msg.training.school.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {
}
