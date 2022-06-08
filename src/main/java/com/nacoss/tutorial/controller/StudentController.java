package com.nacoss.tutorial.controller;

import com.nacoss.tutorial.model.Student;
import com.nacoss.tutorial.model.User;
import com.nacoss.tutorial.model.repositories.StudentRepositry;
import com.nacoss.tutorial.model.requests.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/student")

public class StudentController {

    @Autowired
    private StudentRepositry studentRepositry;

    @GetMapping("/")
    public ResponseEntity<List<Student>> findall() {
        List<Student> students = studentRepositry.findAll();

        return students == null || students.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findStudentId(@PathVariable Long id) {
        return ResponseEntity.of(studentRepositry.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createUser(@RequestBody StudentCreateRequest studentCreateRequest) {
        Student student = new Student();

        student.setFirstname(studentCreateRequest.getFirstname());
        student.setLastname(studentCreateRequest.getLastname());
        student.setEmail(studentCreateRequest.getEmail());
        student.setPhoneNumber(studentCreateRequest.getPhoneNumber());

        studentRepositry.save(student);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        Optional<Student> student = studentRepositry.findById(id);


        studentRepositry.deleteById(id);
        return ResponseEntity.of(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody StudentCreateRequest createRequest) {
        Optional<Student> student = studentRepositry.findById(id);

        //        checking for retrievable record
        if(student.isPresent()) {
            student.map(element -> {
                element.setFirstname(createRequest.getFirstname());
                return studentRepositry.save(element);
            });
        }
        return ResponseEntity.notFound().build();
    }
}
