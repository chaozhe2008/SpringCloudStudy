package com.test.service.impl;

import com.test.entity.Book;
import com.test.mapper.BookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BookService implements com.test.service.BookService {
    @Resource
    BookMapper mapper;
    @Override
    public Book getBookById(int bid) {
        return mapper.getBookById(bid);
    }
}
