package net.practice.hotel.reservation.service;

import net.practice.hotel.reservation.dto.CustomerDto;
import net.practice.hotel.reservation.entity.Customer;
import net.practice.hotel.reservation.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public CustomerDto createCustomer(CustomerDto customerDto) {
    Customer customer = toCustomer(customerDto);
    Customer savedCustomer = customerRepository.save(customer);
    return toCustomerDto(savedCustomer);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void updateCustomer(CustomerDto customerDto) {
    Long customerNumber = customerDto.getCustomerNumber();
    Optional<Customer> customerOptional = customerRepository.findById(customerNumber);
    if (customerOptional.isPresent()) {
      Customer customer = customerOptional.get();
      customer.setName(customerDto.getName());
      customer.setAddress(customerDto.getAddress());
    }
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void deleteCustomer(Long customerNumber) {
    Optional<Customer> customerOptional = customerRepository.findById(customerNumber);
    if (customerOptional.isPresent()) {
      Customer customer = customerOptional.get();
      customerRepository.delete(customer);
    }
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public CustomerDto getCustomer(Long customerNumber) {
    Optional<Customer> customerOptional = customerRepository.findById(customerNumber);
    if (customerOptional.isPresent()) {
      Customer customer = customerOptional.get();
      return toCustomerDto(customer);
    }
    return null;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public List<CustomerDto> getAllCustomer() {
    Iterable<Customer> customerIterable = customerRepository.findAll();
    List<CustomerDto> customerDtos = new LinkedList<>();
    Iterator<Customer> iterator = customerIterable.iterator();
    while (iterator.hasNext()) {
      Customer customer = iterator.next();
      CustomerDto customerDto = toCustomerDto(customer);
      customerDtos.add(customerDto);
    }
    return customerDtos;
  }

  private CustomerDto toCustomerDto(Customer customer) {
    CustomerDto customerDto = new CustomerDto();
    customerDto.setAddress(customer.getAddress());
    customerDto.setName(customer.getName());
    customerDto.setCustomerNumber(customer.getCustomerNumber());
    return customerDto;
  }

  private Customer toCustomer(CustomerDto customerDto) {
    Customer customer = new Customer();
    customer.setAddress(customerDto.getAddress());
    customer.setName(customerDto.getName());
    return customer;
  }
}
