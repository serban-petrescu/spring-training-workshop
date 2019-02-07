package group.msg.training.school.controllers.rest;

import group.msg.training.school.dtos.OutboundStudent;
import group.msg.training.school.services.LaboratoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rest/groups/{groupId}/homework")
public class HomeworkRestController {
	private final LaboratoryService laboratoryService;

	@GetMapping
	public List<OutboundStudent> getRandomStudentsForHomeworkCheck(@PathVariable int groupId, @RequestParam(defaultValue = "5") int count) {
		return laboratoryService.getStudentsForHomeworkCheck(groupId, count);
	}

}
