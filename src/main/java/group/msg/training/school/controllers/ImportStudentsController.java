package group.msg.training.school.controllers;

import group.msg.training.school.dtos.InboundStudent;
import group.msg.training.school.services.StartOfYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ImportStudentsController {
    private final StartOfYearService startOfYearService;

    @Autowired
    public ImportStudentsController(StartOfYearService startOfYearService) {
        this.startOfYearService = startOfYearService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/groups/{groupId}/students")
    public void importStudentsIntoGroup(@PathVariable int groupId, @RequestBody List<InboundStudent> students) {
        startOfYearService.createGroupStudents(groupId, students);
    }

}
