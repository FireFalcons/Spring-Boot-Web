package org.example.springbootweb.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.springbootweb.Exception.EntityNotFoundException;
import org.example.springbootweb.dto.BookDto;
import org.example.springbootweb.dto.CreateBookRequestDto;
import org.example.springbootweb.mapper.BookMapper;
import org.example.springbootweb.model.Book;
import org.example.springbootweb.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    public final BookMapper bookMapper;

    @Transactional
    @Override
    public BookDto createBook(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        book = bookRepository.createBook(book);
        return bookMapper.toDto(book);
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.getBookById(id).orElseThrow(
            () -> new EntityNotFoundException("Can't book by id" + id)
        );
        return bookMapper.toDto(book);
    }

    @Override
    public List<BookDto> getAll() {
        return bookRepository.getAll().stream()
            .map(bookMapper::toDto)
            .toList();
    }
}
