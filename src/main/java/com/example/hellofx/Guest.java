package com.example.hellofx;

public class Guest {

    private String guestId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String gender;
    private String roomType;
    private int nights;
    private String address;

    public Guest(
            String guestId,
            String firstName,
            String lastName,
            String phone,
            String email,
            String gender,
            String roomType,
            int nights,
            String address
    ) {

        this.guestId = guestId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.roomType = roomType;
        this.nights = nights;
        this.address = address;
    }

    public String getGuestId() {
        return guestId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNights() {
        return nights;
    }

    public String getAddress() {
        return address;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}