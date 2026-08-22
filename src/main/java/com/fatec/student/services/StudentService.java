package com.fatec.student.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fatec.student.entities.Student;
import com.fatec.student.repositories.StudentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public Student findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estudante não cadastrado"));
    }

    public void deleteById(Long id) {

        if (repository.existsById(id))
            repository.deleteById(id);
        else
            throw new EntityNotFoundException("Estudante não cadastrado");
    }

    public Student save(Student student) {
        return repository.save(student);
    }

    public void update(Student student, Long id) {

        Student s = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estudante não cadastrado"));

        s.setNomeCompleto(student.getNomeCompleto());
        s.setDataNascimento(student.getDataNascimento());
        s.setGenero(student.getGenero());
        s.setEmail(student.getEmail());
        s.setTelefone(student.getTelefone());
        s.setDataCadastro(student.getDataCadastro());

        repository.save(s);
    }

}