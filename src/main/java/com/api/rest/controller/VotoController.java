package com.api.rest.controller;

import java.net.URI;
//import java.net.http.HttpHeaders;
import java.util.Optional;

import org.springframework.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.rest.model.Encuesta;
import com.api.rest.model.Voto;
import com.api.rest.repository.VotoRepository;

import jakarta.servlet.Servlet;

@RestController

public class VotoController {
	@Autowired
	private VotoRepository votoR;

//? devuelve cualquier tipo
	@PostMapping("/encuestas/{encuestaId}/votos")
	public ResponseEntity<?> crearVoto(@PathVariable Long encuestaId,@RequestBody Voto voto) {
		voto.setId(encuestaId);
		voto = votoR.save(voto);
		
		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(voto.getId()).toUri());
		return new ResponseEntity<>(null,httpHeaders, HttpStatus.CREATED);

	}

	@GetMapping("/encuestas/{encuestaId}/votos")
	public Iterable<Voto> listarTodasLosVotos(@PathVariable Long encuestaId) {
		return  votoR.findByEncuesta(encuestaId);
	}
	
	


}
