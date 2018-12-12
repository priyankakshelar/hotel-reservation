package net.practice.hotel.reservation.entity;


import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "customer_number")
  private Long customerNumber;

  private String name;

  private String address;

  public Customer() {
  }

  public Customer(String name, String address) {
    this.name = name;
    this.address = address;
  }

  public Long getCustomerNumber() {
    return customerNumber;
  }

  public void setCustomerNumber(Long customerNumber) {
    this.customerNumber = customerNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "Customer{" +
      "customerNumber=" + customerNumber +
      ", name='" + name + '\'' +
      ", address='" + address + '\'' +
      '}';
  }
}
