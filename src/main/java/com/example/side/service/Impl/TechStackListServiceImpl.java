package com.example.side.service.Impl;

import com.example.side.model.entity.TechStackList;
import com.example.side.repository.TechStackListRepository;
import com.example.side.service.TechStackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TechStackListServiceImpl implements TechStackListService {
    @Autowired
    TechStackListRepository techStackListRepository;

    public Iterable<TechStackList> selectAll() {
        return techStackListRepository.findAll();
    }

    @Override
    public Optional<TechStackList> selectOneById(Long id) {
        return techStackListRepository.findById(id);
    }

    @Override
    public TechStackList insertTechStackList(TechStackList techStackList) {
        return techStackListRepository.save(techStackList);
    }
    @Override
    public void updateTechStackList(TechStackList techStackList) {
        techStackListRepository.save(techStackList);
    }
}
