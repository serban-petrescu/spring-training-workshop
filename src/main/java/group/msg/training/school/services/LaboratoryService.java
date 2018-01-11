package group.msg.training.school.services;

import group.msg.training.school.dtos.OutboundStudent;
import group.msg.training.school.entities.Group;
import group.msg.training.school.entities.Student;
import group.msg.training.school.exceptions.GroupNotFoundException;
import group.msg.training.school.mappers.StudentMapper;
import group.msg.training.school.repositories.GroupRepository;
import group.msg.training.school.util.RandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LaboratoryService {
    private final GroupRepository groupRepository;
    private final RandomNumberGenerator randomNumberGenerator;

    @Autowired
    public LaboratoryService(GroupRepository groupRepository, RandomNumberGenerator randomNumberGenerator) {
        this.groupRepository = groupRepository;
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public List<OutboundStudent> getStudentsForHomeworkCheck(int groupId, int count) {
        Group group = groupRepository.findOne(groupId);
        if (group != null) {
            if (count < group.getStudents().size()) {
                return randomNumberGenerator.generateIntegers(count, 0, group.getStudents().size() - 1)
                        .stream()
                        .map(i -> group.getStudents().get(i))
                        .map(StudentMapper::toOutbound)
                        .collect(Collectors.toList());
            } else {
                return group.getStudents().stream().map(StudentMapper::toOutbound).collect(Collectors.toList());
            }
        } else {
            throw new GroupNotFoundException(groupId);
        }
    }

}
