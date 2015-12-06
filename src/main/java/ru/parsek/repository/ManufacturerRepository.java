package ru.parsek.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.parsek.entity.Manufacturer;

@Transactional(propagation = Propagation.MANDATORY)
public interface ManufacturerRepository  extends CrudRepository<Manufacturer, Long> {

	List<Manufacturer> findAll();
	
}
