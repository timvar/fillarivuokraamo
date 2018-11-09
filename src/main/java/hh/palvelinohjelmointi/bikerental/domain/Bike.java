package hh.palvelinohjelmointi.bikerental.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Rental item: Bike
 * Stored data: manufacturer, model, rentPerDay (minimum rent time 1 day)
 * Reference (FK) to Category
 *
 */

@Entity
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BIKE_ID")
    private Long bikeId;
    private String manufacturer;
    private String model;
    private double rentPerDay;
    /** Is is possible to have more than one bike of similar model for rent **/
    private int bikeQty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="category")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bike")
    @JsonIgnore
    private List<Rental> rentals;

    public Bike() {}

    public Bike(String manufacturer, String model, double rentPerDay, int bikeQty, Category category) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.bikeQty = bikeQty;
        this.category = category;
    }

    public Long getBikeId() {
        return bikeId;
    }

    public void setBikeId(Long bikeId) {
        this.bikeId = bikeId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getRentPerDay() {
        return rentPerDay;
    }

    public void setRentPerDay(double rentPerDay) {
        this.rentPerDay = rentPerDay;
    }

    public int getBikeQty() {
        return bikeQty;
    }

    public void setBikeQty(int bikeQty) {
        this.bikeQty = bikeQty;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
