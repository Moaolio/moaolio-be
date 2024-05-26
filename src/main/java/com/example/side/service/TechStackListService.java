package com.example.side.service;

import com.example.side.model.entity.TechStackList;

import java.util.Optional;

public interface TechStackListService {
    Iterable<TechStackList> selectAll();
    Optional<TechStackList> selectOneById(Long id);
    TechStackList insertTechStackList(TechStackList techStackList);
    void updateTechStackList(TechStackList techStackList);

}
