package ru.parsek.service;

import java.util.List;

import ru.parsek.entity.Manufacturer;

public interface ManufacturerService {

	Manufacturer save(Manufacturer manufacturer);
	
	List<Manufacturer> findAll();
	
}
