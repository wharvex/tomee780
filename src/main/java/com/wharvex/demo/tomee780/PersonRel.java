package com.wharvex.demo.tomee780;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "PERSON_REL")
public class PersonRel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PERSON_REL_ID", nullable = false)
  private Long id;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "PERSON1_ID", nullable = false)
  private Person person1;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "PERSON2_ID", nullable = false)
  private Person person2;

  @Size(max = 50)
  @NotNull
  @Column(name = "REL_TYPE", nullable = false, length = 50)
  private String relType;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Person getPerson1() {
    return person1;
  }

  public void setPerson1(Person person1) {
    this.person1 = person1;
  }

  public Person getPerson2() {
    return person2;
  }

  public void setPerson2(Person person2) {
    this.person2 = person2;
  }

  public String getRelType() {
    return relType;
  }

  public void setRelType(String relType) {
    this.relType = relType;
  }

}