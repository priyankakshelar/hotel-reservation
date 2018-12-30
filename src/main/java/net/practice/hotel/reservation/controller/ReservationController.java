package net.practice.hotel.reservation.controller;

import net.practice.hotel.reservation.dto.ReservationDto;
import net.practice.hotel.reservation.exception.ReservationNotFoundException;
import net.practice.hotel.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@RestController
public class ReservationController {

  @Autowired
  private ReservationService reservationService;

  @PostMapping(value = "/reservations")
  @ResponseBody
  public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservationDto) {
    ReservationDto reservationOutput = reservationService.createReservation(reservationDto);
    return new ResponseEntity<>(reservationOutput, HttpStatus.CREATED);
  }

  @GetMapping("/reservations")
  @ResponseBody
  public List<ReservationDto> findReservationByPeriod(@RequestParam @DateTimeFormat(iso = DATE) Date startDate,
                                                      @RequestParam @DateTimeFormat(iso = DATE) Date endDate) {
    return reservationService.findByPeriod(startDate, endDate);
  }

  @DeleteMapping("/reservations")
  @ResponseBody
  public void deleteReservation(@RequestParam(name = "customerNumber", required = true) Long customerNumber,
                                @RequestParam(name = "reservationNumber", required = true) Long reservationNumber) throws ReservationNotFoundException {
    reservationService.deleteByReservationNumberAndCustomerNumber(reservationNumber, customerNumber);
  }
}
