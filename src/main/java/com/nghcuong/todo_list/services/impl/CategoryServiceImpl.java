package com.nghcuong.todo_list.services.impl;

import com.nghcuong.todo_list.entity.Category;
import com.nghcuong.todo_list.repository.CategoryRepository;
import com.nghcuong.todo_list.services.CategoryService;
import com.nghcuong.todo_list.utils.UserUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category, Long> implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<Category> findAll() {
        return repository.findByUserId(UserUtils.getCurrentUserId());
    }
}
