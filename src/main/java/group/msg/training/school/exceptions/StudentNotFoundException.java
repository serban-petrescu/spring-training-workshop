package group.msg.training.school.exceptions;

public class StudentNotFoundException extends RuntimeException {
	public StudentNotFoundException(int studentId) {
		super("Unable to find student with ID = '" + studentId + "'.");
	}
}
