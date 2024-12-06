package com.euphy.learn;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    @Test
    void testGetAll() {
        Book book = new Book(1, "Book 1", "Author 1");
        BookDto bookDto = new BookDto(1, "Book 1", "Author 1");
        List<Book> expected = List.of(book);
        when(bookRepository.findAll()).thenReturn(expected);
        when(bookMapper.toDto(expected.get(0))).thenReturn(bookDto);

        List<BookDto> result = bookService.getAll();
        assertEquals(List.of(bookDto), result);
    }

    @Test
    void testGetAllEmpty() {
        when(bookRepository.findAll()).thenReturn(Collections.emptyList());

        List<BookDto> result = bookService.getAll();
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetById() {
        Integer id = 1;
        Book book = new Book(id, "Book 1", "Author 1");
        BookDto expected = new BookDto(id, "Book 1", "Author 1");
        when(bookRepository.findById(id)).thenReturn(Optional.of(book));
        when(bookMapper.toDto(book)).thenReturn(expected);

        BookDto result = bookService.getById(id);
        assertEquals(expected, result);
    }

    @Test
    void testGetByIdNotFound() {
        Integer id = 1;
        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> bookService.getById(id));
    }
}
