package group.msg.training.school.mappers;

import group.msg.training.school.dtos.InboundStudent;
import group.msg.training.school.dtos.OutboundStudent;
import group.msg.training.school.entities.Student;

import java.util.Collections;

public class StudentMapper {

    private StudentMapper() {
        super();
    }

    public static Student fromInbound(InboundStudent inboundStudent) {
        Student student = new Student();
        student.setGrades(Collections.emptyList());
        student.setName(inboundStudent.getName());
        student.setNumber(inboundStudent.getNumber());
        return student;
    }

    public static OutboundStudent toOutbound(Student student) {
        OutboundStudent outboundStudent = new OutboundStudent();
		outboundStudent.setId(student.getId());
        outboundStudent.setName(student.getName());
		outboundStudent.setNumber(student.getNumber());
        return outboundStudent;
    }

}
