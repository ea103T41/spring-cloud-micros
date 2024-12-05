package com.euphy.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getAll() {
        return bookRepository.findAll().stream().map(bookMapper::toDto).toList();
    }

    public BookDto getById(Integer id) {
        return bookMapper.toDto(bookRepository.getReferenceById(id));
    }
}
