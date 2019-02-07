package group.msg.training.school.controllers.rest;

import group.msg.training.school.entities.StudentAverageGrade;
import group.msg.training.school.services.EndOfYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rest/groups/{groupId}/averages")
public class AverageGradeRestController {
	private final EndOfYearService endOfYearService;

	@GetMapping
	public List<StudentAverageGrade> findAveragesForGroup(@PathVariable int groupId) {
		return endOfYearService.getAverageGradesForGroup(groupId);
	}
}
