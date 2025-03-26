package com.wharvex.demo.tomee780;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "PERSON")
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PERSON_ID", nullable = false)
  private Long id;

  @Size(max = 50)
  @Column(name = "FIRST_NAME", length = 50)
  private String firstName;

  @Size(max = 50)
  @Column(name = "LAST_NAME", length = 50)
  private String lastName;

  @Size(max = 50)
  @Column(name = "NICK_NAME", length = 50)
  private String nickName;

  @Column(name = "YEAR_BORN")
  private Short yearBorn;

  @Column(name = "YEAR_DIED")
  private Short yearDied;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public Short getYearBorn() {
    return yearBorn;
  }

  public void setYearBorn(Short yearBorn) {
    this.yearBorn = yearBorn;
  }

  public Short getYearDied() {
    return yearDied;
  }

  public void setYearDied(Short yearDied) {
    this.yearDied = yearDied;
  }

}