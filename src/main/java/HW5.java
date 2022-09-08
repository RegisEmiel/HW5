import org.hibernate.SessionFactory;
import ru.geekbrains.models.Student;
import ru.geekbrains.repositories.StudentRepository;
import ru.geekbrains.services.StudentService;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HW5 {
    private static List<Student> students = null;

    public static void main(String[] args) {
        SessionFactory sessionFactory = SessionFactoryCreator.getSessionFactory();

        StudentService studentService = new StudentService(sessionFactory);

        //Добавляем, удаляем, изменяем по одной сущности
        Student newStudentKolya = new Student("Kolya", 5);
        studentService.add(newStudentKolya);

        Student newStudentPetya = new Student("Petya", 4);
        studentService.add(newStudentPetya);

        System.out.println("Добавили Колю и Петю");
        students = studentService.findAll();
        for (Student student: students) {
            System.out.println(student);
        }

        System.out.println("\nУдалили студента Петю по Id");
        studentService.deleteById(newStudentPetya.getId());
        students = studentService.findAll();

        for (Student student: students) {
            System.out.println(student);
        }

        System.out.println("\nДобавили студента Ивана");
        Student newStudentIvan = new Student("Ivan", 5);
        studentService.add(newStudentIvan);

        students = studentService.findAll();
        for (Student student: students) {
            System.out.println(student);
        }

        System.out.println("\nУдалили Ивана");
        studentService.delete(newStudentIvan);

        students = studentService.findAll();
        for (Student student: students) {
            System.out.println(student);
        }

        System.out.println("\nОбновили Колю");
        newStudentKolya.setMark(1);
        studentService.update(newStudentKolya);

        students = studentService.findAll();
        for (Student student: students) {
            System.out.println(student);
        }

        System.out.println("\nДобавляем 1000 студентов в одной транзакции для скорости");
        Random random = new Random();
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            String name = "Student_" + i;
            Student student = new Student(name, 1+ random.nextInt(4));
            //studentService.add(student);
            studentList.add(student);
        }
        studentService.add1000(studentList);
        students = studentService.findAll();
        for (Student student: students) {
            System.out.println(student);
        }

        sessionFactory.close();
    }
}
