package com.bibliotech.model;

public class Student {

    private final Long id;
    private final String name;
    private final boolean suspended;

    public Student(Long id, String name) {
        this(id, name, false);
    }

    public Student(Long id, String name, boolean suspended) {
        this.id = id;
        this.name = name;
        this.suspended = suspended;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isSuspended() {
        return suspended;
    }
}
