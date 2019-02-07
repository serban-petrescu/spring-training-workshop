package group.msg.training.school.services;

import group.msg.training.school.dtos.OutboundGrade;
import group.msg.training.school.dtos.OutboundGroup;
import group.msg.training.school.dtos.OutboundStudent;
import group.msg.training.school.exceptions.GroupNotFoundException;
import group.msg.training.school.exceptions.StudentNotFoundException;
import group.msg.training.school.mappers.GradeMapper;
import group.msg.training.school.mappers.GroupMapper;
import group.msg.training.school.mappers.StudentMapper;
import group.msg.training.school.repositories.GroupRepository;
import group.msg.training.school.repositories.StudentAverageGradeRepository;
import group.msg.training.school.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BrowsingService {
	private final GroupRepository groupRepository;
	private final StudentRepository studentRepository;
	private final StudentAverageGradeRepository averageGradeRepository;

	public List<OutboundGroup> listGroups() {
		return groupRepository.findAll().stream()
				.map(GroupMapper::toOutbound)
				.collect(Collectors.toList());
	}

	public List<OutboundStudent> listStudentsForGroup(int groupId) {
		return groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFoundException(groupId))
				.getStudents().stream()
				.map(StudentMapper::toOutbound)
				.collect(Collectors.toList());
	}

	public List<OutboundGrade> listGradesForStudent(int studentId) {
		return studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId))
				.getGrades().stream()
				.map(GradeMapper::toOutbound)
				.collect(Collectors.toList());
	}

}
