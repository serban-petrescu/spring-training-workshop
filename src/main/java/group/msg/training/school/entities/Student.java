package group.msg.training.school.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String number;
    @OneToMany
    @JoinColumn
    private List<Grade> grades;
}
