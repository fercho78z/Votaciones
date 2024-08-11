package com.api.rest.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Opcion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="OPCION_ID")
	private Long Id;
	
	private String value;
	
/*		public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
		public Opcion(Long id, String value) {
		super();
		Id = id;
		this.value = value;
	}

	public Opcion() {
		super();

	}
*/



}




