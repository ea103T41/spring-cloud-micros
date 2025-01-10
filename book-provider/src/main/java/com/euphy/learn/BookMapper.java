package com.euphy.learn;

import com.euphy.learn.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getName(), book.getAuthor());
    }

    public Book toEntity(BookDto bookDto) {
        return new Book(bookDto.getId(), bookDto.getName(), bookDto.getAuthor());
    }
}
