package org.example.springbootweb.mapper;

import org.example.springbootweb.config.MapperConfig;
import org.example.springbootweb.dto.BookDto;
import org.example.springbootweb.dto.CreateBookRequestDto;
import org.example.springbootweb.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);
}
