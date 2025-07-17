package com.nghcuong.todo_list.services;

import java.util.List;

public interface IGenericService<T, ID> {
    T save(T entity);
    T update(ID id, T entity);
    T findById(ID id);
    void deleteById(ID id);
    List<T> findAll();
}
