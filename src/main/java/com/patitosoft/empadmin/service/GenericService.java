package com.patitosoft.empadmin.service;

import com.patitosoft.empadmin.http.ResponseAPI;

import java.util.List;

public interface GenericService<T> {

    T create(T t);
    T read(Long id);
    List<T> readAll();
    T update(Long id, T t);
    ResponseAPI delete(T t);
}
