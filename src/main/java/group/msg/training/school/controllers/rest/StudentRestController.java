package group.msg.training.school.controllers.rest;

import group.msg.training.school.dtos.InboundStudent;
import group.msg.training.school.dtos.OutboundStudent;
import group.msg.training.school.services.BrowsingService;
import group.msg.training.school.services.StartOfYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rest/groups/{groupId}/students")
public class StudentRestController {
	private final StartOfYearService startOfYearService;
	private final BrowsingService browsingService;

	@PostMapping("/import")
	@ResponseStatus(HttpStatus.CREATED)
	public void importStudentsIntoGroup(@PathVariable int groupId, @RequestBody List<InboundStudent> students) {
		startOfYearService.addStudentsToGroup(groupId, students);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OutboundStudent addStudentToGroup(@PathVariable int groupId, @RequestBody InboundStudent student) {
		return startOfYearService.addStudentToGroup(groupId, student);
	}

	@GetMapping
	public List<OutboundStudent> getStudentsFromGroup(@PathVariable int groupId) {
		return browsingService.listStudentsForGroup(groupId);
	}
}
