package group.msg.training.school.controllers;


import group.msg.training.school.dtos.InboundGroup;
import group.msg.training.school.services.StartOfYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups")
public class CreateGroupController {
    private final StartOfYearService startOfYearService;

    @Autowired
    public CreateGroupController(StartOfYearService startOfYearService) {
        this.startOfYearService = startOfYearService;
    }

    @PostMapping
    public void createEmptyGroup(@RequestBody InboundGroup inbound) {
        startOfYearService.createEmptyGroup(inbound);
    }

}
