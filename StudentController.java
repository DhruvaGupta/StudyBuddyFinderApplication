package com.example.studybuddyfinder.controller;

import com.example.studybuddyfinder.entity.Student;
import com.example.studybuddyfinder.repository.StudentRepository;
import com.example.studybuddyfinder.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private MatchingService matchingService;
    
    @PostMapping
    public Student registerStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }
    
    @GetMapping("/match/{courseId}")
    public List<Student> findStudyBuddiesByCourse(@PathVariable String courseId) {
        return studentRepository.findByCourses(courseId);
    }
    
    @GetMapping("/compatible/{studentId}")
    public List<Student> findCompatibleStudyBuddies(@PathVariable Long studentId) {
        return matchingService.findCompatibleBuddies(studentId);
    }
}