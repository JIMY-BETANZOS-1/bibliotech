package com.bibliotech.model;

import java.time.LocalDate;

public class Loan {

    private Long id;
    private final Long studentId;
    private final Long bookId;
    private final LocalDate loanDate;
    private final LocalDate dueDate;
    private boolean returned;

    public Loan(Long studentId, Long bookId, LocalDate loanDate, LocalDate dueDate) {
        this.studentId = studentId;
        this.bookId = bookId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returned = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getBookId() {
        return bookId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void markReturned() {
        this.returned = true;
    }
}
