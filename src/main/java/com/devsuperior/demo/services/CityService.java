package com.devsuperior.demo.services;

import com.devsuperior.demo.comparators.CityComparatorByName;
import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    CityRepository repository;

    public List<CityDTO> findAll(){
        List<City> entities = repository.findAll();
        entities.sort(new CityComparatorByName());
        return entities.stream().map(CityDTO::new).toList();
    }

    public CityDTO insert(CityDTO dto) {
        City entity = new City();
        cityDtoToCity(dto, entity);
        entity = repository.save(entity);
        return new CityDTO(entity);
    }

    private void cityDtoToCity(CityDTO dto, City entity){
        entity.setName(dto.getName());
    }
}
