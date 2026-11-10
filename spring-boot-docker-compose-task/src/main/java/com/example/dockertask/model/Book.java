package com.example.dockertask.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Book title is required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Author is required")
    @Column(nullable = false)
    private String author;

    @PositiveOrZero(message = "Price must be zero or greater")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    public Book() {}
    public Book(String title, String author, BigDecimal price) { this.title=title; this.author=author; this.price=price; }
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title=title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author=author; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price=price; }
}
