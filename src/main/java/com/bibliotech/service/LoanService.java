package com.bibliotech.service;

import com.bibliotech.model.Book;
import com.bibliotech.model.Loan;
import com.bibliotech.model.Student;
import com.bibliotech.repository.BookRepository;
import com.bibliotech.repository.LoanRepository;
import com.bibliotech.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LoanService {

    public static final int MAX_ACTIVE_LOANS = 3;
    public static final int LOAN_DAYS = 14;

    private final StudentRepository students;
    private final BookRepository books;
    private final LoanRepository loans;

    public LoanService(StudentRepository students, BookRepository books, LoanRepository loans) {
        this.students = students;
        this.books = books;
        this.loans = loans;
    }

    public Loan requestLoan(Long studentId, Long bookId) {
        Student student = students.findById(studentId)
                .orElseThrow(() -> new BusinessRuleException("Estudiante no encontrado"));
        Book book = books.findById(bookId)
                .orElseThrow(() -> new BusinessRuleException("Libro no encontrado"));

        if (loans.countActiveByStudent(student.getId()) >= MAX_ACTIVE_LOANS) {
            throw new BusinessRuleException("El estudiante alcanzó el límite de préstamos activos");
        }
        if (book.getAvailableCopies() <= 0) {
            throw new BusinessRuleException("No hay ejemplares disponibles del libro");
        }

        book.decreaseCopies();
        LocalDate today = LocalDate.now();
        return loans.save(new Loan(student.getId(), book.getId(), today, today.plusDays(LOAN_DAYS)));
    }
}
