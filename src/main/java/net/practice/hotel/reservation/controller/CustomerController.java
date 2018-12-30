package net.practice.hotel.reservation.controller;

import net.practice.hotel.reservation.dto.CustomerDto;
import net.practice.hotel.reservation.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @GetMapping("/customers")
  @ResponseBody
  public List<CustomerDto> getAllCustomers() {
    return customerService.getAllCustomer();
  }

  @GetMapping("/customers/{customerNumber}")
  @ResponseBody
  public CustomerDto findCustomer(@PathVariable Long customerNumber) {
    return customerService.getCustomer(customerNumber);
  }

  @PostMapping(value = "/customers")
  @ResponseBody
  public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
    CustomerDto outputCustomerDto = customerService.createCustomer(customerDto);
    return new ResponseEntity<>(outputCustomerDto, HttpStatus.CREATED);
  }

  @PutMapping(value = "/customers")
  public void updateCustomer(@RequestBody CustomerDto customerDto) {
    customerService.updateCustomer(customerDto);
  }

  @DeleteMapping(value = "/customers")
  @ResponseBody
  public void deleteCustomer(@RequestParam(name = "customerNumber", required = true) Long customerNumber) {
    customerService.deleteCustomer(customerNumber);
  }
}
