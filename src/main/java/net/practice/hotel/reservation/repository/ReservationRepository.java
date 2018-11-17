package net.practice.hotel.reservation.repository;

import net.practice.hotel.reservation.entity.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

}
