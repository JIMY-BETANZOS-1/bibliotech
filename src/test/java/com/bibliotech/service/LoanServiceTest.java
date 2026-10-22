package com.bibliotech.service;

import com.bibliotech.model.Book;
import com.bibliotech.model.Loan;
import com.bibliotech.model.Student;
import com.bibliotech.repository.BookRepository;
import com.bibliotech.repository.LoanRepository;
import com.bibliotech.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LoanServiceTest {

    private StudentRepository students;
    private BookRepository books;
    private LoanRepository loans;
    private LoanService service;

    @BeforeEach
    void setUp() {
        students = new StudentRepository();
        books = new BookRepository();
        loans = new LoanRepository();
        service = new LoanService(students, books, loans);
        students.save(new Student(1L, "Ana Torres"));
    }

    @Test
    void prestamoValido_registraPrestamoYDescuentaEjemplar() {
        books.save(new Book(10L, "Bases de Datos", 2));

        Loan loan = service.requestLoan(1L, 10L);

        assertEquals(1L, loan.getStudentId());
        assertEquals(1, books.findById(10L).orElseThrow().getAvailableCopies());
        assertEquals(1, loans.countActiveByStudent(1L));
    }

    @Test
    void cuartoPrestamoActivo_esRechazado() {
        books.save(new Book(10L, "Bases de Datos", 10));
        service.requestLoan(1L, 10L);
        service.requestLoan(1L, 10L);
        service.requestLoan(1L, 10L);

        assertThrows(BusinessRuleException.class, () -> service.requestLoan(1L, 10L));
    }

    @Test
    void libroSinEjemplares_esRechazado() {
        books.save(new Book(11L, "Redes", 0));

        assertThrows(BusinessRuleException.class, () -> service.requestLoan(1L, 11L));
    }
}
