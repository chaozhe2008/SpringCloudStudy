package com.test.service.client;

import com.test.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookFallbackClient implements BookClient{
    @Override
    public Book getBookById(int bid) {
        Book book = new Book();
        return book;
    }
}
