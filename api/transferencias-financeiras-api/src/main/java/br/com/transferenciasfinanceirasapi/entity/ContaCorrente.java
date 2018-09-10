package br.com.transferenciasfinanceirasapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_cta_corrente")
public class ContaCorrente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String cta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCta() {
		return cta;
	}

	public void setCta(String cta) {
		this.cta = cta;
	}

}
