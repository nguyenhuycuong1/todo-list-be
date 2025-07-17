package com.nghcuong.todo_list.mapper;

import com.nghcuong.todo_list.dto.CategoryDTO;
import com.nghcuong.todo_list.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryDTO categoryDTO);
    CategoryDTO toDTO(Category category);
    List<CategoryDTO> toDTOList(List<Category> categories);
}
