package hibernate.demo;

import hibernate.demo.entity.Student;

import java.util.List;

public interface StudentRegisterRemote {
    void addStudent(Student student);
    List getStudents();
}
