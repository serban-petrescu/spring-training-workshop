package group.msg.training.school.controllers;

import group.msg.training.school.entities.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImportStudentsController {

    @PostMapping(value = "/students/import", consumes = "text/csv", produces = "application/json")
    public List<Student> csvToJson(@RequestBody List<Student> students) {
        return students;
    }

}
