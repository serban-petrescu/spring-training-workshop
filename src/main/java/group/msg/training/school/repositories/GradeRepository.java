package group.msg.training.school.repositories;

import group.msg.training.school.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Integer> {

}
