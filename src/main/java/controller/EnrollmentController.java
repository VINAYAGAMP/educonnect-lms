package controller;

import entity.Enrollment;
import repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @PostMapping
    public Enrollment createEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @GetMapping("/{id}")
    public Enrollment getEnrollmentById(@PathVariable Long id) {
        return enrollmentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Enrollment not found with id: " + id));
    }

    @PutMapping("/{id}")
    public Enrollment updateEnrollment(@PathVariable Long id, @RequestBody Enrollment updated) {
        return enrollmentRepository.findById(id).map(enrollment -> {
            enrollment.setSemester(updated.getSemester());
            enrollment.setGrade(updated.getGrade());
            enrollment.setStudent(updated.getStudent());
            enrollment.setCourse(updated.getCourse());
            return enrollmentRepository.save(enrollment);
        }).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Enrollment not found with id: " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteEnrollment(@PathVariable Long id) {
        enrollmentRepository.deleteById(id);
    }
}