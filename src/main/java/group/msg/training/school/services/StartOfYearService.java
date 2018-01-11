package group.msg.training.school.services;

import group.msg.training.school.dtos.InboundGroup;
import group.msg.training.school.dtos.InboundStudent;
import group.msg.training.school.entities.Group;
import group.msg.training.school.entities.Student;
import group.msg.training.school.exceptions.GroupNotFoundException;
import group.msg.training.school.mappers.GroupMapper;
import group.msg.training.school.mappers.StudentMapper;
import group.msg.training.school.repositories.GroupRepository;
import group.msg.training.school.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StartOfYearService {
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public StartOfYearService(GroupRepository groupRepository, StudentRepository studentRepository) {
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
    }

    public void createEmptyGroup(InboundGroup inbound) {
        groupRepository.save(GroupMapper.fromInbound(inbound));
    }

    @Transactional
    public void createGroupStudents(int groupId, List<InboundStudent> inboundStudents) {
        Group group = groupRepository.findOne(groupId);
        if (group != null) {
            List<Student> students = inboundStudents.stream()
                    .map(StudentMapper::fromInbound)
                    .collect(Collectors.toList());
            studentRepository.save(students);
            group.getStudents().addAll(students);
        } else {
            throw new GroupNotFoundException(groupId);
        }
    }
}
