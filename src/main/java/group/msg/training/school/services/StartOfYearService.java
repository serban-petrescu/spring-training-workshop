package group.msg.training.school.services;

import group.msg.training.school.dtos.InboundGroup;
import group.msg.training.school.mappers.GroupMapper;
import group.msg.training.school.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartOfYearService {
    private final GroupRepository groupRepository;

    @Autowired
    public StartOfYearService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public void createEmptyGroup(InboundGroup inbound) {
        groupRepository.save(GroupMapper.fromInbound(inbound));
    }
}
