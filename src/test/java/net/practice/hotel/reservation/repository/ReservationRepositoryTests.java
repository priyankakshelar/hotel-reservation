package net.practice.hotel.reservation.repository;

import net.practice.hotel.reservation.entity.Customer;
import net.practice.hotel.reservation.entity.Reservation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ReservationRepositoryTests {

  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private ReservationRepository reservationRepository;

  @Test
  public void shouldReturnReservationForReservationNoAndCustomerNo() throws ParseException {
    // given
    resetAutoIncrement("reservation");
    resetAutoIncrement("customer");
    testEntityManager.persist(new Customer("sameer", "nashik"));
    testEntityManager.persist(new Customer("priyanka", "pune"));
    testEntityManager.persist(new Reservation(1l, toDate("2018-11-24"), toDate("2018-12-01")));
    testEntityManager.persist(new Reservation(2l, toDate("2018-12-01"), toDate("2018-12-08")));

    // when
    Reservation reservation = reservationRepository.findByReservationNumberAndCustomerNumber(1l, 1l);

    // then
    Assert.assertEquals(1l, reservation.getReservationNumber().longValue());
    Assert.assertEquals(1l, reservation.getCustomerNumber().longValue());
    Assert.assertEquals(toDate("2018-11-24"), reservation.getCheckinDate());
    Assert.assertEquals(toDate("2018-12-01"), reservation.getCheckoutDate());
  }

  @Test
  public void shouldReturnReservationForPeriod() throws ParseException {
    // given
    resetAutoIncrement("reservation");
    resetAutoIncrement("customer");
    testEntityManager.persist(new Customer("sameer", "nashik"));
    testEntityManager.persist(new Customer("priyanka", "pune"));
    testEntityManager.persist(new Customer("Saanvi", "Singapore"));
    testEntityManager.persist(new Customer("Pooja", "Nashik"));
    testEntityManager.persist(new Reservation(1l, toDate("2018-11-24"), toDate("2018-12-01")));
    testEntityManager.persist(new Reservation(2l, toDate("2018-12-01"), toDate("2018-12-08")));
    testEntityManager.persist(new Reservation(3l, toDate("2018-12-08"), toDate("2018-12-15")));
    testEntityManager.persist(new Reservation(4l, toDate("2018-12-15"), toDate("2018-12-22")));

    //when
    List<Reservation> reservations = reservationRepository.findByPeriod(toDate("2018-12-01"), toDate("2018-12-15"));

    //then
    Assert.assertEquals(2, reservations.size());
    Reservation reservation = reservations.get(0);
    Assert.assertEquals(2l, reservation.getCustomerNumber().longValue());
    Assert.assertEquals(toDate("2018-12-01"), reservation.getCheckinDate());
    Assert.assertEquals(toDate("2018-12-08"), reservation.getCheckoutDate());
  }

  @Test
  public void shouldNotReturnReservationForPeriod() throws ParseException {
    //given
    resetAutoIncrement("reservation");
    resetAutoIncrement("customer");
    testEntityManager.persist(new Customer("sameer", "nashik"));
    testEntityManager.persist(new Customer("priyanka", "pune"));
    testEntityManager.persist(new Customer("Saanvi", "Singapore"));
    testEntityManager.persist(new Customer("Pooja", "Nashik"));
    testEntityManager.persist(new Reservation(1l, toDate("2018-11-24"), toDate("2018-12-01")));
    testEntityManager.persist(new Reservation(2l, toDate("2018-12-01"), toDate("2018-12-08")));
    testEntityManager.persist(new Reservation(3l, toDate("2018-12-08"), toDate("2018-12-15")));
    testEntityManager.persist(new Reservation(4l, toDate("2018-12-15"), toDate("2018-12-22")));

    //when
    List<Reservation> reservations = reservationRepository.findByPeriod(toDate("2018-10-01"), toDate("2018-11-21"));

    //then
    Assert.assertEquals(0, reservations.size());
  }

  @Test
  public void deleteReservation() throws ParseException {
    // given
    resetAutoIncrement("reservation");
    resetAutoIncrement("customer");
    testEntityManager.persist(new Customer("sameer", "nashik"));
    testEntityManager.persist(new Customer("priyanka", "pune"));
    testEntityManager.persist(new Customer("Saanvi", "Singapore"));
    testEntityManager.persist(new Customer("Pooja", "Nashik"));
    testEntityManager.persist(new Reservation(1l, toDate("2018-11-24"), toDate("2018-12-01")));
    testEntityManager.persist(new Reservation(2l, toDate("2018-12-01"), toDate("2018-12-08")));
    testEntityManager.persist(new Reservation(3l, toDate("2018-12-08"), toDate("2018-12-15")));
    testEntityManager.persist(new Reservation(4l, toDate("2018-12-15"), toDate("2018-12-22")));

    //when
    reservationRepository.deleteByReservationNumberAndCustomerNumber(1l, 1l);
    Iterable<Reservation> reservations = reservationRepository.findAll();

    //then
    for (Reservation reservation : reservations) {
      Assert.assertTrue(!reservation.getReservationNumber().equals(1l));
    }
  }

  private void resetAutoIncrement(String tableName) {
    testEntityManager
      .getEntityManager()
      .createNativeQuery("ALTER TABLE " + tableName + " AUTO_INCREMENT = 1")
      .executeUpdate();
  }

  private Date toDate(String input) throws ParseException {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    return formatter.parse(input);
  }

}
