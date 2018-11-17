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
  private Integer customerNumber;

  @Column(name = "checkin_date")
  private Date checkinDate;

  @Column(name = "checkout_date")
  private Date checkoutDate;

  public Long getReservationNumber() {
    return reservationNumber;
  }

  public void setReservationNumber(Long reservationNumber) {
    this.reservationNumber = reservationNumber;
  }

  public Integer getCustomerNumber() {
    return customerNumber;
  }

  public void setCustomerNumber(Integer customerNumber) {
    this.customerNumber = customerNumber;
  }

  public Date getCheckinDate() {
    return checkinDate;
  }

  public void setCheckinDate(Date checkinDate) {
    this.checkinDate = checkinDate;
  }

  public Date getCheckoutDate() {
    return checkoutDate;
  }

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
