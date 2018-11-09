package hh.palvelinohjelmointi.bikerental.domain;

import java.util.Date;

/**
 * Shows rented bikes
 * date: calendar day
 * availability: number of rented bikes
 */

public class Availability {

    private Date date;
    private long availability;

    public Availability() {
    }

    public Availability(Date date, long availability) {
        this.date = date;
        this.availability = availability;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getAvailability() {
        return availability;
    }

    public void setAvailability(long availability) {
        this.availability = availability;
    }
}
