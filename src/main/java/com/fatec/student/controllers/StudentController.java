package com.fatec.student.controllers;

import java.net.URI;
import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatec.student.entities.Student;
import com.fatec.student.services.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {

        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody Student student) {

        Student s = service.save(student);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(s.getId())
                .toUri();

        return ResponseEntity.created(location).body(s);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable long id,
                                       @RequestBody Student student) {

        service.update(student, id);

        return ResponseEntity.noContent().build();
    }

}