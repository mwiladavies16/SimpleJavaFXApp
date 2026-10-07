package com.example.hellofx;

public class Bill {

    private String billNumber;
    private String guestName;
    private String roomType;
    private int nights;
    private double total;
    private double paid;
    private double balance;
    private String notes;

    public Bill(
            String billNumber,
            String guestName,
            String roomType,
            int nights,
            double total,
            double paid,
            String notes
    ) {
        this.billNumber = billNumber;
        this.guestName = guestName;
        this.roomType = roomType;
        this.nights = nights;
        this.total = total;
        this.paid = paid;
        this.balance = total - paid;
        this.notes = notes;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNights() {
        return nights;
    }

    public double getTotal() {
        return total;
    }

    public double getPaid() {
        return paid;
    }

    public double getBalance() {
        return balance;
    }

    public String getNotes() {
        return notes;
    }
}