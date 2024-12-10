package com.mds.task.stock_task_mds.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, name = "date_founded")
    private LocalDate dateFounded;

    public Long getId() {
        return id;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(final String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public LocalDate getDateFounded() {
        return dateFounded;
    }
    public void setDateFounded(final LocalDate dateFounded) {
        this.dateFounded = dateFounded;
    }
}
