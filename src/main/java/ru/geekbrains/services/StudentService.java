package ru.geekbrains.services;

import org.hibernate.SessionFactory;
import ru.geekbrains.models.Student;
import ru.geekbrains.repositories.StudentRepository;

import java.util.List;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(SessionFactory sessionFactory) {
        this.studentRepository = new StudentRepository(sessionFactory);
    }

    public void add(Student student) {
        studentRepository.add(student);
    }

    public void add1000(List<Student> students) {
        studentRepository.add1000(students);
    }

    public Student findStudentById(Long id){
        return studentRepository.findById(id);
    }

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public boolean deleteById(Long id) {
        return studentRepository.deleteById(id);
    }

    public boolean delete(Student removingStudent) {
        return studentRepository.delete(removingStudent);
    }

    public void update(Student student) {
        studentRepository.update(student);
    }
}
