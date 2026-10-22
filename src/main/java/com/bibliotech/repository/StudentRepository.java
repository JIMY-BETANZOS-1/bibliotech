package com.bibliotech.repository;

import com.bibliotech.model.Student;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class StudentRepository {

    private final Map<Long, Student> students = new ConcurrentHashMap<>();

    public Student save(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(students.get(id));
    }
}
