package com.example.dockertask.controller;

import com.example.dockertask.model.Book;
import com.example.dockertask.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookRepository bookRepository;
    public BookController(BookRepository bookRepository) { this.bookRepository=bookRepository; }

    @GetMapping public List<Book> getAllBooks() { return bookRepository.findAll(); }
    @GetMapping("/{id}") public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Book createBook(@Valid @RequestBody Book book) { return bookRepository.save(book); }
    @PutMapping("/{id}") public Book updateBook(@PathVariable Long id, @Valid @RequestBody Book updatedBook) {
        Book existing=bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        existing.setTitle(updatedBook.getTitle()); existing.setAuthor(updatedBook.getAuthor()); existing.setPrice(updatedBook.getPrice());
        return bookRepository.save(existing);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void deleteBook(@PathVariable Long id) {
        if (!bookRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        bookRepository.deleteById(id);
    }
}
