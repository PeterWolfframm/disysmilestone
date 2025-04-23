package at.uastw.distrsysvz.controller;


import at.uastw.distrsysvz.dto.Book;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    private Map<Integer, Book> booksById = new HashMap<>();

    @PostMapping("/book")
    public void createBook(@RequestBody Book book) {
        booksById.put(book.getId(), book);
    }

    @GetMapping("/book")
    public List<Book> getAllBooks(
            @RequestParam(value="search", defaultValue = "") String search
    ) {
        return booksById.values().stream()
                .filter(book -> book.getTitle().contains(search))
                .toList();
    }

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable int id) {
        //Book book = new Book(1, "An unexpected Journey", "Fantasy");
        return booksById.get(id);
    }

}
