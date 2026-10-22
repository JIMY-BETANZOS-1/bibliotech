package com.bibliotech.model;

public class Book {

    private final Long id;
    private final String title;
    private int availableCopies;

    public Book(Long id, String title, int availableCopies) {
        this.id = id;
        this.title = title;
        this.availableCopies = availableCopies;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void decreaseCopies() {
        availableCopies--;
    }
}
