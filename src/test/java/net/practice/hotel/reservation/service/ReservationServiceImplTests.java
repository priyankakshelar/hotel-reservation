package net.practice.hotel.reservation.service;

import net.practice.hotel.reservation.entity.Reservation;
import net.practice.hotel.reservation.exception.ReservationNotFoundException;
import net.practice.hotel.reservation.repository.ReservationRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationServiceImplTests {

  @Rule
  public ExpectedException exception = ExpectedException.none();
  @Mock
  private ReservationRepository reservationRepository;
  @InjectMocks
  private ReservationService reservationService = new ReservationServiceImpl();

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testReservationByPeriod() throws ParseException {
    //given
    Date startDate = toDate("2018-11-24");
    Date endDate = toDate("2018-12-15");
    Mockito.when(reservationRepository.findByPeriod(startDate, endDate)).thenReturn(getReservationList());

    //when
    List<Reservation> reservations = reservationService.findByPeriod(startDate, endDate);

    //then

    Assert.assertEquals(3, reservations.size());
    Reservation reservation = reservations.get(0);
    Assert.assertEquals(toDate("2018-12-01"), reservation.getCheckoutDate());

  }

  @Test
  public void testReservationByReservationAndCustomerNum() throws ParseException {
    //given
    Long reservationNumber = 1l;
    Long customerNumber = 1l;
    Reservation reservation = new Reservation(customerNumber, toDate("2018-11-24"), toDate("2018-12-01"));
    Mockito.when(reservationRepository.findByReservationNumberAndCustomerNumber(reservationNumber, customerNumber)).thenReturn(reservation);

    //when
    Reservation actualReservation = reservationService.findByReservationNumberAndCustomerNumber(reservationNumber, customerNumber);

    // then
    Assert.assertEquals(actualReservation, reservation);
  }

  @Test
  public void testDeleteByReservationNumberAndCustomerNumber() throws ParseException, ReservationNotFoundException {
    // given
    Long reservationNumber = 1l;
    Long customerNumber = 1l;
    Reservation reservation = new Reservation(customerNumber, toDate("2018-11-24"), toDate("2018-12-01"));
    Mockito.when(reservationRepository.findByReservationNumberAndCustomerNumber(reservationNumber, customerNumber)).thenReturn(reservation);

    // when
    reservationService.deleteByReservationNumberAndCustomerNumber(reservationNumber, customerNumber);

    // then
    Mockito.verify(reservationRepository, Mockito.times(1)).delete(reservation);
  }

  @Test
  public void testCreateReservation() throws ParseException {
    //given
    Reservation reservation = new Reservation(null, toDate("2018-11-24"), toDate("2018-12-01"));

    //when
    reservationService.createReservation(reservation);

    //then
    Mockito.verify(reservationRepository, Mockito.times(1)).save(reservation);
  }

  @Test
  public void testReservationNotFoundException() throws ParseException, ReservationNotFoundException {
    Long reservationNumber = 1l;
    Long customerNumber = 1l;
    Mockito.when(reservationRepository.findByReservationNumberAndCustomerNumber(reservationNumber, customerNumber)).thenReturn(null);

    exception.expect(ReservationNotFoundException.class);
    exception.expectMessage("Reservation not found");

    reservationService.deleteByReservationNumberAndCustomerNumber(reservationNumber, customerNumber);
  }

  private Date toDate(String input) throws ParseException {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    return formatter.parse(input);
  }

  private List<Reservation> getReservationList() throws ParseException {
    List<Reservation> reservationList = new ArrayList<>();
    Reservation reservation = new Reservation(1l, toDate("2018-11-24"), toDate("2018-12-01"));
    Reservation reservation1 = new Reservation(2l, toDate("2018-12-01"), toDate("2018-12-08"));
    Reservation reservation2 = new Reservation(3l, toDate("2018-12-08"), toDate("2018-12-15"));
    reservationList.add(reservation);
    reservationList.add(reservation1);
    reservationList.add(reservation2);
    return reservationList;
  }
}
