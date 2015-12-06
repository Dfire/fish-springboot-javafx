package ru.parsek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.parsek.entity.Tool;
import ru.parsek.repository.ToolRepository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ToolServiceImpl implements ToolService {

    @Autowired
    private ToolRepository repository;

    @Override
    public Tool save(Tool tool) {
        return repository.save(tool);
    }

    @Override
    public List<Tool> findAll() {
        return repository.findAll();
    }
}
