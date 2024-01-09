package com.example.dezdemoniyslab.controllers;


import com.example.dezdemoniyslab.models.Book;
import com.example.dezdemoniyslab.models.User;
import com.example.dezdemoniyslab.services.book.BookService;
import com.example.dezdemoniyslab.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final UserService userService;

    @GetMapping("/index-book")
    public String showBookList(Model model){
        model.addAttribute("books", bookService.getAllBooks());
        // всё сосётся к неудалённому адммину, можно поставить другого пользователя
        // это антипаттерн. По-хорошему нужно через токен чекать, но сам понимаешь)
        model.addAttribute("admin", userService.getAnyAdmin());
        return "books";
    }

    @GetMapping("/create/{userId}")
    public String createBookFormForUser(Model model,  @PathVariable("userId") String userId) {
        model.addAttribute("book", new Book());
        model.addAttribute("userId", userId);
        return "create-book";
    }

    @PostMapping("/create/{userId}")
    public  String createBook (@ModelAttribute("book") Book book, @PathVariable("userId") Long userId){
        User user = userService.getUserById(userId);
        if (user == null){
            return "errorPage";
        }

        bookService.createBook(book, user);
        return "redirect:/books/index-book";
    }

    @GetMapping("/edit/{bookId}")
    public String editBookForm(@PathVariable("bookId") Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "edit-book";
    }

    @PostMapping("/edit/{bookId}")
    public String updateBook(@PathVariable ("bookId") Long id,
                             @ModelAttribute("book") Book book){
        Book existingBook = bookService.getBookById(id);
        if (existingBook == null){
            return "errorPage";
        } else{
            existingBook.setContent(book.getContent());
            existingBook.setUser(book.getUser());
            bookService.updateBook(existingBook);
        }
        return "redirect:/books/index-book";
    }

    @PostMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId") Long bookId){
        bookService.deleteBookById(bookId);
        return "redirect:/books/index-book";
    }
}
