package net.practice.hotel.reservation.service;

import net.practice.hotel.reservation.entity.Reservation;
import net.practice.hotel.reservation.exception.ReservationNotFoundException;

import java.util.Date;
import java.util.List;

public interface ReservationService {
  List<Reservation> findByPeriod(Date startDate, Date endDate);

  Reservation findByReservationNumberAndCustomerNumber(Long reservationNum, Long customerNumber);

  void deleteByReservationNumberAndCustomerNumber(Long reservationNum, Long customerNumber) throws ReservationNotFoundException;

  void createReservation(Reservation reservation);
}
