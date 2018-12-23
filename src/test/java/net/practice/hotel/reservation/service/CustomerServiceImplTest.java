package net.practice.hotel.reservation.service;

import net.practice.hotel.reservation.dto.CustomerDto;
import net.practice.hotel.reservation.entity.Customer;
import net.practice.hotel.reservation.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.Optional;

public class CustomerServiceImplTest {

  @Mock
  private CustomerRepository customerRepository;

  @InjectMocks
  private CustomerService customerService = new CustomerServiceImpl();

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void createCustomer() throws Exception {
    //given
    Long customerNumber = null;
    String name = "Priyanka";
    String address = "Singapore";
    CustomerDto customerDto = new CustomerDto(customerNumber, name, address);
    Customer customer = new Customer(1l, name, address);
    Mockito.when(customerRepository.save(ArgumentMatchers.any(Customer.class))).thenReturn(customer);

    //when
    CustomerDto outputCustomerDto = customerService.createCustomer(customerDto);

    // then
    Assert.assertEquals(1l, outputCustomerDto.getCustomerNumber().longValue());
    Assert.assertEquals(name, outputCustomerDto.getName());
    Assert.assertEquals(address, outputCustomerDto.getAddress());
  }

  @Test
  public void updateCustomer() throws Exception {
    // given
    Long customerNumber = 1l;
    String name = "Priyanka";
    String address = "Singapore";
    CustomerDto customerDto = new CustomerDto(customerNumber, name, address);
    Customer customer = Mockito.mock(Customer.class);
    Optional<Customer> customerOptional = Optional.of(customer);
    Mockito.when(customerRepository.findById(customerNumber)).thenReturn(customerOptional);

    // when
    customerService.updateCustomer(customerDto);

    // then
    Mockito.verify(customer, Mockito.times(1)).setName(name);
    Mockito.verify(customer, Mockito.times(1)).setAddress(address);
  }

  @Test
  public void deleteCustomer() throws Exception {
    //given
    Long customerNumber = 1l;
    Customer customer = Mockito.mock(Customer.class);
    Optional<Customer> customerOptional = Optional.of(customer);
    Mockito.when(customerRepository.findById(customerNumber)).thenReturn(customerOptional);

    //when
    customerService.deleteCustomer(customerNumber);

    // then
    Mockito.verify(customerRepository, Mockito.times(1)).delete(customer);
  }

  @Test
  public void getCustomer() throws Exception {
    //given
    Long customerNumber = 1l;
    String name = "Priyanka";
    String address = "Singapore";
    Customer customer = new Customer(customerNumber, name, address);
    Optional<Customer> customerOptional = Optional.of(customer);
    Mockito.when(customerRepository.findById(customerNumber)).thenReturn(customerOptional);

    //when
    CustomerDto customerDto = customerService.getCustomer(customerNumber);

    //then
    Assert.assertEquals(customerNumber, customerDto.getCustomerNumber());
    Assert.assertEquals(name, customerDto.getName());
    Assert.assertEquals(address, customerDto.getAddress());
  }

}
