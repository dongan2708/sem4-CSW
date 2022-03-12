package com.example.practical_csw.entity;

public class Employee {
    private int id;
    private String name;
    private double salary;
    private int status;

    public Employee() {
    }

    public Employee(int id, String name, double salary, int status) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.status = status;
    }

    public Employee(String name, double salary, int status) {
        this.name = name;
        this.salary = salary;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

