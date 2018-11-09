package hh.palvelinohjelmointi.bikerental.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Input parameters for bike availability search
 * startDay for search
 * bike to search
 */

public class BikeSearch {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDay;
    private Bike bike;

    public BikeSearch() {
    }

    public BikeSearch(Date startday, Bike bike) {
        this.startDay = startday;
        this.bike = bike;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }
}
