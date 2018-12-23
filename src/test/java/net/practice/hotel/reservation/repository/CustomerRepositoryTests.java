package net.practice.hotel.reservation.repository;

import net.practice.hotel.reservation.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CustomerRepositoryTests {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private CustomerRepository customerRepository;

  @Test
  public void findByName() throws Exception {
    // given
    entityManager.persist(new Customer("sameer", "nashik"));
    entityManager.persist(new Customer("priyanka", "pune"));
    entityManager.persist(new Customer("Saanvi", "Singapore"));

    // when
    List<Customer> customers = customerRepository.findByName("Saanvi");

    // then
    Customer customer1 = customers.get(0);
    assertEquals("Saanvi", customer1.getName());
    assertEquals("Singapore", customer1.getAddress());
  }

}
