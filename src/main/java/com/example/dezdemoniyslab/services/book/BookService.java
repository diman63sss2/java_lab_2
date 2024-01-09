package com.example.dezdemoniyslab.services.book;


import com.example.dezdemoniyslab.models.Book;
import com.example.dezdemoniyslab.models.User;
import com.example.dezdemoniyslab.services.user.BaseUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BaseBookService baseBookService;
    private final BaseUserService baseUserService;

    public List<Book> getAllBooks() {
        return  baseBookService.getAllBooksFromDatabase();
    }

    public  Book getBookById(Long bookId){
        return baseBookService.getBookFromDatabaseById(bookId);
    }

    public List<Book> getAllAuthorBooksByAuthorId(Long userId){
        return baseBookService.getAllBooksFromDatabaseByAuthorId(userId);
    }

    public void createBook(Book book, User user){
        book.setUser(user);
        baseBookService.saveBookToDatabase(book);
    }

    public void updateBook(Book book){
        baseBookService.saveBookToDatabase(book);
    }

    public void deleteBookById(Long bookId){
        baseBookService.softDeleteBookFromDatabaseById(bookId);
    }
}
