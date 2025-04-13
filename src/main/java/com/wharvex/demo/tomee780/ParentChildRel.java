package com.wharvex.demo.tomee780;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "PARENT_CHILD_REL")
public class ParentChildRel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PARENT_CHILD_REL_ID", nullable = false)
  private Long id;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "PARENT_ID", nullable = false)
  private Person parent;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "CHILD_ID", nullable = false)
  private Person child;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Person getParent() {
    return parent;
  }

  public void setParent(Person parent) {
    this.parent = parent;
  }

  public Person getChild() {
    return child;
  }

  public void setChild(Person child) {
    this.child = child;
  }

}