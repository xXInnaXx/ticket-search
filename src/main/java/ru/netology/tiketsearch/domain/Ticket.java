package ru.netology.tiketsearch.domain;

public class Ticket implements Comparable<Ticket> {
    private String id;
    private String from;
    private String to;
    private double price;
    private int duration;

    public Ticket(String id, String from, String to, double price, int duration) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.price = price;
        this.duration = duration;
    }

    @Override
    public int compareTo(Ticket ticket) {
        if (this.price > ticket.price) {
            return 1;
        }
        if (this.price < ticket.price) {
            return -1;
        } else {
            return 0;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
