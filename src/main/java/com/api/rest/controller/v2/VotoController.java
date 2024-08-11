package com.api.rest.controller.v2;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.rest.dto.OpcionCount;
import com.api.rest.dto.VotoResult;
import com.api.rest.model.Encuesta;
import com.api.rest.model.Voto;
import com.api.rest.repository.VotoRepository;

import jakarta.servlet.Servlet;

@RestController("VotoControllerV2")
@RequestMapping("/v2")
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
	
	public ResponseEntity<?> listarTodoslosVotos(@PathVariable Long encuestaId){
	//public Iterable<Voto> listarTodasLosVotos(@PathVariable Long encuestaId) {
	

		Iterable<Voto> votos = votoR.findByEncuesta(encuestaId);

		
		return new ResponseEntity<>(votos,HttpStatus.OK);
		
		//return  votoR.findByEncuesta(encuestaId);
	}
	
	


}
