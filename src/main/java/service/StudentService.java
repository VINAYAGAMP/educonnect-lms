package service;

import dto.StudentDTO;
import entity.Student;
import repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Student not found with id: " + id));
    }

    public Student createStudent(StudentDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setDepartment(dto.getDepartment());
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, StudentDTO dto) {
        return studentRepository.findById(id).map(student -> {
            student.setName(dto.getName());
            student.setEmail(dto.getEmail());
            student.setDepartment(dto.getDepartment());
            return studentRepository.save(student);
        }).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Student not found with id: " + id));
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}