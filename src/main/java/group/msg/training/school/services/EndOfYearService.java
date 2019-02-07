package group.msg.training.school.services;

import group.msg.training.school.entities.StudentAverageGrade;
import group.msg.training.school.repositories.StudentAverageGradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EndOfYearService {
	private final StudentAverageGradeRepository studentAverageGradeRepository;

	public List<StudentAverageGrade> getAverageGradesForGroup(int groupId) {
		return studentAverageGradeRepository.findByGroupId(groupId);
	}
}
