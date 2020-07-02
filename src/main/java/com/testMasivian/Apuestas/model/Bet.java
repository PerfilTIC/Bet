package com.testMasivian.Apuestas.model;
import java.io.Serializable;
import com.testMasivian.Apuestas.Enum.Color;
public class Bet implements Serializable {
	int idBet;
	int idRoulette;
	Color color;
	String idUsuario;
	String numero;
	float monto;
	public int getIdBet() {
		return idBet;
	}
	public void setIdBet(int idBet) {
		this.idBet = idBet;
	}
	public int getIdRoulette() {
		return idRoulette;
	}
	public void setIdRoulette(int idRoulette) {
		this.idRoulette = idRoulette;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
}
