package com.euphy.learn.controller;

import com.euphy.learn.BookDto;
import com.euphy.learn.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    void testGetAll() {
        List<BookDto> expected = List.of(new BookDto(1, "Book 1", "Author 1"));
        when(bookService.getAll()).thenReturn(expected);

        List<BookDto> result = bookController.getAll();
        assertEquals(expected, result);
    }

    @Test
    void testGetAllEmpty() {
        List<BookDto> expected = Collections.emptyList();
        when(bookService.getAll()).thenReturn(expected);

        List<BookDto> result = bookController.getAll();
        assertEquals(expected, result);
    }

    @Test
    void testGetById() {
        Integer id = 1;
        BookDto expected = new BookDto(id, "Book 1", "Author 1");
        when(bookService.getById(id)).thenReturn(expected);

        BookDto result = bookController.getById(id);
        assertEquals(expected, result);
    }

    @Test
    void testGetByIdNotFound() {
        Integer id = 1;
        when(bookService.getById(id)).thenReturn(null);

        BookDto result = bookController.getById(id);
        assertNull(result);
    }
}
