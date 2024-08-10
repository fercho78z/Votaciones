package com.api.rest.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.rest.model.Encuesta;
@Repository
public interface EncuestaRepository  extends CrudRepository<Encuesta, Long> {

}

