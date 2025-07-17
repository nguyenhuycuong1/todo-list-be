package com.nghcuong.todo_list.services.impl;

import com.nghcuong.todo_list.entity.BaseEntity;
import com.nghcuong.todo_list.exception.AppException;
import com.nghcuong.todo_list.exception.ErrorCode;
import com.nghcuong.todo_list.services.IGenericService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public abstract class GenericServiceImpl<T, ID> implements IGenericService<T, ID> {
    protected final JpaRepository<T, ID> repository;

    public GenericServiceImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T save(T entity) {
        if(entity instanceof BaseEntity baseEntity) {
            baseEntity.setCreatedAt(LocalDateTime.now());
            baseEntity.setUpdatedAt(LocalDateTime.now());
        }
        return repository.save(entity);
    }

    @Override
    public T update(ID id, T entity) {
        T existingEntity = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND, ErrorCode.RESOURCE_NOT_FOUND.getMessage() + " với id: " + id));

        if (entity instanceof BaseEntity baseEntity) {
            baseEntity.setId((Long) id);
            baseEntity.setCreatedAt(((BaseEntity) existingEntity).getCreatedAt());
            baseEntity.setUpdatedAt(LocalDateTime.now());
        }

        return repository.save(entity);
    }

    @Override
    public void deleteById(ID id) {
        if (!repository.existsById(id)) {
            throw new AppException(ErrorCode.RESOURCE_NOT_FOUND, ErrorCode.RESOURCE_NOT_FOUND.getMessage() + " với id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND, ErrorCode.RESOURCE_NOT_FOUND.getMessage() + " với id: " + id));
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }


}
