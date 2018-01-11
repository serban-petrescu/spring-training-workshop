package group.msg.training.school.exceptions;

public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException(int groupId) {
        super("Unable to find group with ID = '" + groupId + "'.");
    }
}
