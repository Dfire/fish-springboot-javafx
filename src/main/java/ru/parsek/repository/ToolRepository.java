package ru.parsek.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.parsek.entity.Tool;

@Transactional(propagation = Propagation.MANDATORY)
public interface ToolRepository extends CrudRepository<Tool, Long> {

    List<Tool> findAll();

}
