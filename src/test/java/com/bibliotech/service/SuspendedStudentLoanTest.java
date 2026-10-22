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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SuspendedStudentLoanTest {

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
        books.save(new Book(10L, "Bases de Datos", 3));
    }

    // Escenario válido: un estudiante activo (no suspendido) sí puede pedir un préstamo.
    @Test
    void estudianteNoSuspendido_puedeSolicitarPrestamo() {
        students.save(new Student(1L, "Ana Torres", false));

        Loan loan = service.requestLoan(1L, 10L);

        assertNotNull(loan.getId());
        assertEquals(1, loans.countActiveByStudent(1L));
    }

    // Escenario inválido: un estudiante suspendido no puede pedir un préstamo.
    @Test
    void estudianteSuspendido_noPuedeSolicitarPrestamo() {
        students.save(new Student(2L, "Luis Quispe", true));

        BusinessRuleException ex = assertThrows(BusinessRuleException.class,
                () -> service.requestLoan(2L, 10L));

        assertEquals("El estudiante está suspendido y no puede solicitar préstamos", ex.getMessage());
    }

    // Escenario inválido: al rechazar, no se crea el préstamo ni se descuenta el ejemplar.
    @Test
    void estudianteSuspendido_noGeneraPrestamoNiDescuentaEjemplares() {
        students.save(new Student(2L, "Luis Quispe", true));

        assertThrows(BusinessRuleException.class, () -> service.requestLoan(2L, 10L));

        assertEquals(0, loans.countActiveByStudent(2L));
        assertEquals(3, books.findById(10L).orElseThrow().getAvailableCopies());
    }
}
