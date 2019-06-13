package hibernate.demo;

import hibernate.demo.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRegisterBean implements StudentRegisterRemote{

    private List<Student> studentRecord;

    public StudentRegisterBean(List<Student> studentRecord) {
        studentRecord = new ArrayList<>();
    }

    @Override
    public void addStudent(Student student) {
        studentRecord.add(student);
    }

    @Override
    public List getStudents() {
        return studentRecord;
    }


}
