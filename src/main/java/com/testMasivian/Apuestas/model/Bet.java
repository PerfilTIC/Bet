package com.testMasivian.Apuestas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import com.testMasivian.Apuestas.Enum.Color;

@Entity
public class Bet {
	
	@Id
	int idBet;
	
	@JoinColumn(name = "id_roulette" , nullable = false, foreignKey = @ForeignKey(name = "idRoulette"))
	int idRoulette;
	
	@Column(name = "color")
	Color color;
	

}
