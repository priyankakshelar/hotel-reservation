package net.practice.hotel.reservation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ReservationDto {
  private Long reservationNumber;
  private Long customerNumber;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date checkinDate;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date checkoutDate;

  public ReservationDto(Long customerNumber, Date checkinDate, Date checkoutDate) {
    this.customerNumber = customerNumber;
    this.checkinDate = checkinDate;
    this.checkoutDate = checkoutDate;
  }

  public ReservationDto() {
  }

  public ReservationDto(Long reservationNumber, Long customerNumber, Date checkinDate, Date checkoutDate) {
    this.reservationNumber = reservationNumber;
    this.customerNumber = customerNumber;
    this.checkinDate = checkinDate;
    this.checkoutDate = checkoutDate;
  }


  public ReservationDto(Long reservationNumber) {
    this.reservationNumber = reservationNumber;
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
    return "ReservationDto{" +
      "reservationNumber=" + reservationNumber +
      ", customerNumber=" + customerNumber +
      ", checkinDate=" + checkinDate +
      ", checkoutDate=" + checkoutDate +
      '}';
  }
}
