package group.msg.training.school.services;

import group.msg.training.school.dtos.InboundGroup;
import group.msg.training.school.dtos.InboundStudent;
import group.msg.training.school.dtos.OutboundGroup;
import group.msg.training.school.dtos.OutboundStudent;
import group.msg.training.school.entities.Group;
import group.msg.training.school.entities.Student;
import group.msg.training.school.exceptions.GroupNotFoundException;
import group.msg.training.school.mappers.GroupMapper;
import group.msg.training.school.mappers.StudentMapper;
import group.msg.training.school.repositories.GroupRepository;
import group.msg.training.school.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StartOfYearService {
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

	public OutboundGroup createEmptyGroup(InboundGroup inbound) {
		return GroupMapper.toOutbound(groupRepository.save(GroupMapper.fromInbound(inbound)));
    }

	public List<OutboundStudent> addStudentsToGroup(int groupId, List<InboundStudent> inboundStudents) {
		Group group = groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFoundException(groupId));
		List<Student> students = studentRepository.saveAll(inboundStudents.stream()
				.map(StudentMapper::fromInbound)
				.collect(Collectors.toList()));
		group.getStudents().addAll(students);
		return students.stream().map(StudentMapper::toOutbound).collect(Collectors.toList());
	}

	public OutboundStudent addStudentToGroup(int groupId, InboundStudent inboundStudent) {
		Group group = groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFoundException(groupId));
		Student student = studentRepository.save(StudentMapper.fromInbound(inboundStudent));
		group.getStudents().add(student);
		return StudentMapper.toOutbound(student);
    }
}
