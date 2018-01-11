package group.msg.training.school.controllers;

import group.msg.training.school.entities.StudentAverageGrade;
import group.msg.training.school.repositories.StudentAverageGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/averages")
public class AverageGradeController {
    private final StudentAverageGradeRepository studentAverageGradeRepository;

    @Autowired
    public AverageGradeController(StudentAverageGradeRepository studentAverageGradeRepository) {
        this.studentAverageGradeRepository = studentAverageGradeRepository;
    }

    @GetMapping(value = "/for-group/{groupId}")
    public List<StudentAverageGrade> findAveragesForGroup(@PathVariable int groupId) {
        return studentAverageGradeRepository.findByGroupId(groupId);
    }
}
