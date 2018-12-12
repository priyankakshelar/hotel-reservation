package net.practice.hotel.reservation.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "reservation_number")
  private Long reservationNumber;

  @Column(name = "customer_number")
  private Long customerNumber;

  @Column(name = "checkin_date")
  private Date checkinDate;

  @Column(name = "checkout_date")
  private Date checkoutDate;

  public Reservation() {
  }

  public Reservation(Long customerNumber, Date checkinDate, Date checkoutDate) {
    this.customerNumber = customerNumber;
    this.checkinDate = checkinDate;
    this.checkoutDate = checkoutDate;
  }

  public Long getReservationNumber() {
    return reservationNumber;
  }

  public void setReservationNumber(Long reservationNumber) {
    this.reservationNumber = reservationNumber;
  }

  public Long getCustomerNumber() {
    return customerNumber;
  }

  public void setCustomerNumber(Long customerNumber) {
    this.customerNumber = customerNumber;
  }

  public Date getCheckinDate() {
    return checkinDate;
  }

  @Temporal(TemporalType.DATE)
  public void setCheckinDate(Date checkinDate) {
    this.checkinDate = checkinDate;
  }

  public Date getCheckoutDate() {
    return checkoutDate;
  }

  @Temporal(TemporalType.DATE)
  public void setCheckoutDate(Date checkoutDate) {
    this.checkoutDate = checkoutDate;
  }

  @Override
  public String toString() {
    return "Reservation{" +
      "reservationNumber=" + reservationNumber +
      ", customerNumber=" + customerNumber +
      ", checkinDate=" + checkinDate +
      ", checkoutDate=" + checkoutDate +
      '}';
  }
}
