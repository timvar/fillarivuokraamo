package hh.palvelinohjelmointi.bikerental.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
/**
 * Rental agreement data
 * First name, last name, email and/or phone, rental start day, rental end day
 * Reference (FK) to bike and rental status
 */
@Entity
public class Rental {
    @Id
    @GeneratedValue(generator = "seq-generator")
    @GenericGenerator(
            name = "seq-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "rental_sequence"),
                    @Parameter(name = "initial_value", value = "1000"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long rentalId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDay;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDay;

    @ManyToOne
	@JsonIgnore
	@JoinColumn(name = "bike")
	private Bike bike;

    @ManyToOne
	@JsonIgnore
	@JoinColumn(name = "status")
    private RentalStatus status;

    public Rental() {}

	public Rental(String firstName, String lastName, String email, String phone, Date startDay, Date endDay, Bike bike, RentalStatus status) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.startDay = startDay;
		this.endDay = endDay;
		this.bike = bike;
		this.status = status
		;
	}

	public Long getRentalId() {
		return rentalId;
	}

	public void setRentalId(Long rentalId) {
		this.rentalId = rentalId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getStartDay() {
		return startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public Date getEndDay() {
		return endDay;
	}

	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public RentalStatus getStatus() {
		return status;
	}

	public void setStatus(RentalStatus status) {
		this.status = status;
	}

}
