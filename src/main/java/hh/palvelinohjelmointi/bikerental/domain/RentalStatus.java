package hh.palvelinohjelmointi.bikerental.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Rental status: active, paid, cancelled
 *
 */
@Entity
public class RentalStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long statusId;
    private String statusName;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "status")
    private List<Rental> rentals ;

    public RentalStatus() {
    }

    public RentalStatus(String statusName) {
        this.statusName = statusName;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
