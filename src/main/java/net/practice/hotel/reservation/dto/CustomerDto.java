package net.practice.hotel.reservation.dto;

public class CustomerDto {

  private Long customerNumber;
  private String name;
  private String address;

  public CustomerDto() {
  }

  public CustomerDto(Long customerNumber, String name, String address) {
    this.customerNumber = customerNumber;
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
    return "CustomerDto{" +
      "customerNumber=" + customerNumber +
      ", name='" + name + '\'' +
      ", address='" + address + '\'' +
      '}';
  }
}
