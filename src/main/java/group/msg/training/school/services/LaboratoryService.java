package group.msg.training.school.services;

import group.msg.training.school.dtos.InboundGrade;
import group.msg.training.school.dtos.OutboundGrade;
import group.msg.training.school.dtos.OutboundStudent;
import group.msg.training.school.entities.Grade;
import group.msg.training.school.entities.Group;
import group.msg.training.school.entities.Student;
import group.msg.training.school.exceptions.GroupNotFoundException;
import group.msg.training.school.exceptions.StudentNotFoundException;
import group.msg.training.school.mappers.GradeMapper;
import group.msg.training.school.mappers.StudentMapper;
import group.msg.training.school.repositories.GradeRepository;
import group.msg.training.school.repositories.GroupRepository;
import group.msg.training.school.repositories.StudentRepository;
import group.msg.training.school.util.RandomNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class LaboratoryService {
    private final GroupRepository groupRepository;
    private final RandomNumberGenerator randomNumberGenerator;
	private final StudentRepository studentRepository;
	private final GradeRepository gradeRepository;

    public List<OutboundStudent> getStudentsForHomeworkCheck(int groupId, int count) {
		Group group = groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFoundException(groupId));
		if (count < group.getStudents().size()) {
			return randomNumberGenerator.generateIntegers(count, 0, group.getStudents().size() - 1)
					.stream()
					.map(i -> group.getStudents().get(i))
					.map(StudentMapper::toOutbound)
					.collect(Collectors.toList());
		} else {
			return group.getStudents().stream().map(StudentMapper::toOutbound).collect(Collectors.toList());
		}
	}

	public OutboundGrade gradeStudent(int studentId, InboundGrade inboundGrade) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new StudentNotFoundException(studentId));
		Grade grade = new Grade();
		grade.setDate(LocalDate.now());
		grade.setScore(inboundGrade.getScore());
		grade = gradeRepository.save(grade);
		student.getGrades().add(grade);
		return GradeMapper.toOutbound(grade);
    }

}
