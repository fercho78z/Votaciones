package com.api.rest.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Encuesta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ENCUESTA_ID")
	private Long Id;
	@Column(name="pregunta", nullable=false)
	private String pregunta;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="ENCUESTA_ID")
	@OrderBy
	private Set<Opcion> opciones;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public Set<Opcion> getOpciones() {
		return opciones;
	}

	public void setOpciones(Set<Opcion> opciones) {
		this.opciones = opciones;
	}

	public Encuesta(Long id, String pregunta, Set<Opcion> opciones) {
		super();
		Id = id;
		this.pregunta = pregunta;
		this.opciones = opciones;
	}
	public Encuesta() {
		
	}

}
