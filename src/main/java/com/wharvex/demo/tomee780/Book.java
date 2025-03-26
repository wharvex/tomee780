package com.wharvex.demo.tomee780;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "BOOK")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "BOOKID", nullable = false)
  private Long id;

  @Size(max = 100)
  @NotNull
  @Column(name = "TITLE", nullable = false, length = 100)
  private String title;

  @Column(name = "YEARPUBLISHED")
  private Short yearpublished;

  public Book() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Short getYearpublished() {
    return yearpublished;
  }

  public void setYearpublished(Short yearpublished) {
    this.yearpublished = yearpublished;
  }

}