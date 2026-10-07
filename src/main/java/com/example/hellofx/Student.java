package com.example.hellofx;

public class Student {

    private String studentId;
    private String firstName;
    private String lastName;
    private String gender;
    private String programme;
    private String yearOfStudy;
    private String email;
    private String phone;

    public Student(String studentId, String firstName, String lastName,
                   String gender, String programme, String yearOfStudy,
                   String email, String phone) {

        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.programme = programme;
        this.yearOfStudy = yearOfStudy;
        this.email = email;
        this.phone = phone;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getProgramme() {
        return programme;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}