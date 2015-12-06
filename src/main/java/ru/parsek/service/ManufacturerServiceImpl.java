package ru.parsek.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.parsek.entity.Manufacturer;
import ru.parsek.repository.ManufacturerRepository;

@Service
@Transactional
public class ManufacturerServiceImpl implements ManufacturerService {

	@Autowired
	private ManufacturerRepository repository;
	
	@Override
	public Manufacturer save(Manufacturer manufacturer) {
		return repository.save(manufacturer);
	}

	@Override
	public List<Manufacturer> findAll() {
		return repository.findAll();
	}

}
