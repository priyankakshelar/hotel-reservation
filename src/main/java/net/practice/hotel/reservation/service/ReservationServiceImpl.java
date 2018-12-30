package net.practice.hotel.reservation.service;

import net.practice.hotel.reservation.dto.ReservationDto;
import net.practice.hotel.reservation.entity.Reservation;
import net.practice.hotel.reservation.exception.ReservationNotFoundException;
import net.practice.hotel.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

  @Autowired
  private ReservationRepository reservationRepository;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public List<ReservationDto> findByPeriod(Date startDate, Date endDate) {
    List<Reservation> reservations = reservationRepository.findByPeriod(startDate, endDate);
    List<ReservationDto> reservationDtoList = new ArrayList<>();
    for (Reservation reservation : reservations) {
      ReservationDto reservationDto = toReservationDto(reservation);
      reservationDtoList.add(reservationDto);
    }
    return reservationDtoList;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public ReservationDto findByReservationNumberAndCustomerNumber(Long reservationNum, Long customerNumber) {
    Reservation reservation = reservationRepository.findByReservationNumberAndCustomerNumber(reservationNum, customerNumber);
    return toReservationDto(reservation);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void deleteByReservationNumberAndCustomerNumber(Long reservationNum, Long customerNumber) throws ReservationNotFoundException {
    Reservation reservation = reservationRepository.findByReservationNumberAndCustomerNumber(reservationNum, customerNumber);
    if (reservation != null) {
      reservationRepository.delete(reservation);
    } else {
      throw new ReservationNotFoundException("Reservation not found");
    }
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public ReservationDto createReservation(ReservationDto reservationDto) {
    Reservation reservation = toReservation(reservationDto);
    Reservation savedReservation = reservationRepository.save(reservation);
    return toReservationDto(savedReservation);
  }

  private Reservation toReservation(ReservationDto reservationDto) {
    Reservation reservation = new Reservation();
    reservation.setCheckinDate(reservationDto.getCheckinDate());
    reservation.setCheckoutDate(reservationDto.getCheckoutDate());
    reservation.setCustomerNumber(reservationDto.getCustomerNumber());
    return reservation;
  }

  private ReservationDto toReservationDto(Reservation reservation) {
    ReservationDto reservationDto = new ReservationDto();
    reservationDto.setCheckinDate(reservation.getCheckinDate());
    reservationDto.setCheckoutDate(reservation.getCheckoutDate());
    reservationDto.setCustomerNumber(reservation.getCustomerNumber());
    reservationDto.setReservationNumber(reservation.getReservationNumber());
    return reservationDto;
  }
}
