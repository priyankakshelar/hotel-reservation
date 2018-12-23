package net.practice.hotel.reservation.service;

import net.practice.hotel.reservation.entity.Reservation;
import net.practice.hotel.reservation.exception.ReservationNotFoundException;
import net.practice.hotel.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ReservationServiceImpl implements ReservationService {

  @Autowired
  private ReservationRepository reservationRepository;

  @Override
  public List<Reservation> findByPeriod(Date startDate, Date endDate) {
    return reservationRepository.findByPeriod(startDate, endDate);
  }

  @Override
  public Reservation findByReservationNumberAndCustomerNumber(Long reservationNum, Long customerNumber) {
    return reservationRepository.findByReservationNumberAndCustomerNumber(reservationNum, customerNumber);
  }

  @Override
  public void deleteByReservationNumberAndCustomerNumber(Long reservationNum, Long customerNumber) throws ReservationNotFoundException {
    Reservation reservation = reservationRepository.findByReservationNumberAndCustomerNumber(reservationNum, customerNumber);
    if (reservation != null) {
      reservationRepository.delete(reservation);
    } else {
      throw new ReservationNotFoundException("Reservation not found");
    }
  }

  @Override
  public void createReservation(Reservation reservation) {
    reservationRepository.save(reservation);
  }
}
