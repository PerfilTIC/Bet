package com.testMasivian.Apuestas.model;
import java.io.Serializable;
public class Roulette implements Serializable {
	int idRoulette;
	boolean state;
	String openDate;
	String closeDate;
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
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
