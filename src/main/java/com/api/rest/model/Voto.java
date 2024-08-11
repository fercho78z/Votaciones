package com.api.rest.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Voto {

	@Id
	@Column(name="VOTO_ID")
	private Long Id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="OPCION_ID")
	@OrderBy
	private Opcion opcion;

		public Long getId() {
		return Id;
	}

	/*public void setId(Long id) {
		Id = id;
	}

	public Opcion getOpcion() {
		return opcion;
	}

	public void setOpcion(Opcion opcion) {
		this.opcion = opcion;
	}

		public Voto(Long id, Opcion opcion) {
		super();
		Id = id;
		this.opcion = opcion;
	}
	public Voto() {
		
	}
*/


}


