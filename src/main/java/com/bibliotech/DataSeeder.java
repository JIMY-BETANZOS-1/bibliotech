package com.bibliotech;

import com.bibliotech.model.Book;
import com.bibliotech.model.Student;
import com.bibliotech.repository.BookRepository;
import com.bibliotech.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/** Datos de ejemplo para probar la API manualmente. */
@Component
public class DataSeeder implements CommandLineRunner {

    private final StudentRepository students;
    private final BookRepository books;

    public DataSeeder(StudentRepository students, BookRepository books) {
        this.students = students;
        this.books = books;
    }

    @Override
    public void run(String... args) {
        students.save(new Student(1L, "Ana Torres"));
        students.save(new Student(2L, "Luis Quispe"));
        books.save(new Book(1L, "Ingeniería de Software", 2));
        books.save(new Book(2L, "Redes de Computadoras", 1));
        books.save(new Book(3L, "Bases de Datos", 5));
    }
}
