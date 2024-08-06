package com.example.studybuddyfinder.service;

import com.example.studybuddyfinder.entity.Student;
import com.example.studybuddyfinder.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchingService {
    @Autowired
    private StudentRepository studentRepository;
    
    public List<Student> findCompatibleBuddies(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        List<Student> potentialBuddies = new ArrayList<>();
        
        for (String course : student.getCourses()) {
            potentialBuddies.addAll(studentRepository.findByCourses(course));
        }
        
        return potentialBuddies.stream()
            .filter(buddy -> !buddy.getId().equals(studentId))
            .filter(buddy -> buddy.getPreferredStudyTime().equals(student.getPreferredStudyTime())
                && buddy.getStudyStyle().equals(student.getStudyStyle()))
            .collect(Collectors.toList());
    }
}