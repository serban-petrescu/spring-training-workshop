package group.msg.training.school.controllers.rest;

import group.msg.training.school.dtos.InboundGrade;
import group.msg.training.school.dtos.OutboundGrade;
import group.msg.training.school.services.BrowsingService;
import group.msg.training.school.services.LaboratoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rest/groups/{groupId}/students/{studentId}/grades")
public class GradeRestController {
	private final LaboratoryService laboratoryService;
	private final BrowsingService browsingService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OutboundGrade addGradeToStudent(@PathVariable int studentId, @RequestBody InboundGrade grade) {
		return laboratoryService.gradeStudent(studentId, grade);
	}

	@GetMapping
	public List<OutboundGrade> getGradesForStudent(@PathVariable int studentId) {
		return browsingService.listGradesForStudent(studentId);
	}

}
