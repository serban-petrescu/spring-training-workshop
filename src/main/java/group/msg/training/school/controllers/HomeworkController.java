package group.msg.training.school.controllers;

import group.msg.training.school.dtos.OutboundStudent;
import group.msg.training.school.services.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeworkController {
    private final LaboratoryService laboratoryService;

    @Autowired
    public HomeworkController(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }

    @GetMapping("/group/{groupId}/homework")
    public List<OutboundStudent> getRandomStudentsForHomeworkCheck(@PathVariable int groupId, @RequestParam(defaultValue = "5") int count) {
        return laboratoryService.getStudentsForHomeworkCheck(groupId, count);
    }

}
