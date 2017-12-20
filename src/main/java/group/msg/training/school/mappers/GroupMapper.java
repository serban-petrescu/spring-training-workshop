package group.msg.training.school.mappers;

import group.msg.training.school.dtos.InboundGroup;
import group.msg.training.school.entities.Group;

public class GroupMapper {

    private GroupMapper() {
        super();
    }


    public static Group fromInbound(InboundGroup inbound) {
        Group group = new Group();
        group.setName(inbound.getName());
        group.setSpecialization(inbound.getSpecialization());
        group.setYear(inbound.getYear());
        return group;
    }
}
