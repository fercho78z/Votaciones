package com.api.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.rest.model.Opcion;

@Repository
public interface OpcionRespository extends CrudRepository<Opcion, Long> {

}
