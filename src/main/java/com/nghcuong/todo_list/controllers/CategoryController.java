package com.nghcuong.todo_list.controllers;

import com.nghcuong.todo_list.dto.CategoryDTO;
import com.nghcuong.todo_list.dto.response.ApiResponse;
import com.nghcuong.todo_list.entity.Category;
import com.nghcuong.todo_list.mapper.CategoryMapper;
import com.nghcuong.todo_list.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping("/get-all")
    public ApiResponse<List<CategoryDTO>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        List<CategoryDTO> categoryDTOs = categoryMapper.toDTOList(categories);
        return new ApiResponse<>(categoryDTOs)
                .success();
    }

    @GetMapping("/get-by-id/{id}")
    public ApiResponse<CategoryDTO> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        CategoryDTO categoryDTO = categoryMapper.toDTO(category);
        return new ApiResponse<>(categoryDTO)
                .success();
    }

    @PostMapping("/create")
    public ApiResponse<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        Category createdCategory = categoryService.save(category);
        CategoryDTO createdCategoryDTO = categoryMapper.toDTO(createdCategory);
        return new ApiResponse<>(createdCategoryDTO)
                .success();
    }

    @PutMapping("/update/{id}")
    public ApiResponse<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        Category updatedCategory = categoryService.update(id, category);
        CategoryDTO updatedCategoryDTO = categoryMapper.toDTO(updatedCategory);
        return new ApiResponse<>(updatedCategoryDTO)
                .success();
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<?> deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return new ApiResponse<>()
                .success();
    }
}
