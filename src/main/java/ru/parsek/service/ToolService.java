package ru.parsek.service;

import java.util.List;

import ru.parsek.entity.Tool;

public interface ToolService {

    Tool save(Tool tool);

    List<Tool> findAll();

}
