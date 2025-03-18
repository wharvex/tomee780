package com.wharvex.demo.tomee780;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
  @Id
  @Lob
  @Column(name = "ID")
  private String id;

  @Size(max = 50)
  @Column(name = "FIRST_NAME", length = 50)
  private String firstName;

  @Size(max = 50)
  @Column(name = "LAST_NAME", length = 50)
  private String lastName;

  public Employee() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

}