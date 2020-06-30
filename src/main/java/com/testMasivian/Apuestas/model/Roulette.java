package com.testMasivian.Apuestas.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Roulette {
	@Id
	int idRoulette;
	@Column(name = "state")
	boolean state;
	
	public int getIdRoulette() {
		return idRoulette;
	}
	public void setIdRoulette(int idRoulette) {
		this.idRoulette = idRoulette;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}

}
