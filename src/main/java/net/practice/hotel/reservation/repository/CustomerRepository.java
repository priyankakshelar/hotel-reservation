package net.practice.hotel.reservation.repository;

import net.practice.hotel.reservation.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

  List<Customer> findByName(String name);

}
