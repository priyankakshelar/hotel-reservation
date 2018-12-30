package net.practice.hotel.reservation.service;

import net.practice.hotel.reservation.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

  CustomerDto createCustomer(CustomerDto customerDto);

  void updateCustomer(CustomerDto customerDto);

  void deleteCustomer(Long customerNumber);

  CustomerDto getCustomer(Long customerNumber);

  List<CustomerDto> getAllCustomer();
}
