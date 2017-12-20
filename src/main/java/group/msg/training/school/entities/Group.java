package group.msg.training.school.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "class")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int year;
    private String name;
    private String specialization;

    @OneToMany
    @JoinColumn
    private List<Student> students;
}
