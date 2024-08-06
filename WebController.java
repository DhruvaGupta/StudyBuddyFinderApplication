package com.example.studybuddyfinder.controller;

import com.example.studybuddyfinder.entity.Student;
import com.example.studybuddyfinder.repository.StudentRepository;
import com.example.studybuddyfinder.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MatchingService matchingService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "home";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new Student());
        return "register";
    }

    @PostMapping("/register")
    public String registerStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/";
    }

    @GetMapping("/match/{id}")
    public String findMatches(@PathVariable Long id, Model model) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        model.addAttribute("student", student);
        model.addAttribute("matches", matchingService.findCompatibleBuddies(id));
        return "matches";
    }
}