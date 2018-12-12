package net.practice.hotel.reservation.repository;

import net.practice.hotel.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

  @Query(value = "select r from Reservation r where r.checkinDate >= :startDate and r.checkoutDate <= :endDate")
  List<Reservation> findByPeriod(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

  Reservation findByReservationNumberAndCustomerNumber(Long reservationNum, Long customerNumber);

  void deleteByReservationNumberAndCustomerNumber(Long reservationNum, Long customerNumber);

}
