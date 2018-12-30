package net.practice.hotel.reservation.service;

import net.practice.hotel.reservation.dto.ReservationDto;
import net.practice.hotel.reservation.exception.ReservationNotFoundException;

import java.util.Date;
import java.util.List;

public interface ReservationService {
  List<ReservationDto> findByPeriod(Date startDate, Date endDate);

  ReservationDto findByReservationNumberAndCustomerNumber(Long reservationNum, Long customerNumber);

  void deleteByReservationNumberAndCustomerNumber(Long reservationNum, Long customerNumber) throws ReservationNotFoundException;

  ReservationDto createReservation(ReservationDto reservation);
}
