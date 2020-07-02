package com.testMasivian.Apuestas.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.testMasivian.Apuestas.Enum.Color;
import com.testMasivian.Apuestas.model.Bet;
import com.testMasivian.Apuestas.model.Roulette;
import com.testMasivian.Apuestas.repo.BetRepository;
import com.testMasivian.Apuestas.repo.RouletteRepository;
@RestController
@RequestMapping("/Bet")
public class BetController {
	private BetRepository betRepository;
	private RouletteRepository rouletteRepository;
	public static final String KEY = "Bet";
	public static final String KEY2 = "Roulets";
	public BetController(BetRepository betRepository, RouletteRepository rouletteRepository) {
		this.betRepository = betRepository;
		this.rouletteRepository = rouletteRepository;
	}
	@GetMapping
	public Map<Integer, Bet> findAll(){
		Map<Integer,Object> map = betRepository.findAll(KEY); //Object is containing String
	    Map<Integer,Bet> newMap2 =new HashMap<Integer,Bet>();
	    for (Map.Entry<Integer, Object> entry : map.entrySet()) {
	        try{
	        	Bet bet = (Bet) entry.getValue();
	        	newMap2.put(entry.getKey(), bet);	
	        }
	        catch(ClassCastException e){
	            System.out.println("ERROR: "+entry.getKey()+" -> "+entry.getValue()+
	                               " not added, as "+entry.getValue()+" is not a String");
	        }
	    }
		
		return newMap2;
	}
	@GetMapping("/{id}")
	public Map<Integer, Bet> findAll(@PathVariable int id){
		return countBetByRoulette(id);
	}
	@PostMapping("/{id}")
	public String createBet(@RequestBody Bet bet, @PathVariable int id, @RequestHeader(value="Idusuario") String userAgent) {
		String message = "";
		Roulette roulette = (Roulette) rouletteRepository.findById(id, KEY2);
		message = validateCreation(roulette, bet, userAgent);
		
		return message;
	}
	public String validateCreation(Roulette roulette, Bet bet, String usuario) {
		String message = "";
		if(roulette != null) {
			if(roulette.isState()) {
				message = validateNumber(roulette,bet,usuario);
			}else {
				message = "Esta ruleta no se encuentra abierta para apuestas.";
			}
		}else {
			message = "No existe la ruleta a la cual se quiere realizar la apuesta";
		}

		return message;
	}
	private String validateNumber(Roulette roulette, Bet bet, String usuario) {
		String message = "";
		if(bet.getNumero() != null) {
			if(Integer.parseInt(bet.getNumero()) >= 0 && Integer.parseInt(bet.getNumero()) <= 36) {
				if(bet.getColor() != null) {
					message = validateColor(roulette, bet, usuario);
			}else {
				message = validateCash(roulette,bet, usuario);
			}
			}else {
				message = "Debe apostar por un número valido";
			}
		}else {
			if(bet.getColor() != null) {
				message = validateColor(roulette, bet, usuario);
			}else {
				message = "Debe apostar por un color o por un numero.";
			}
		}
		
		return message;
	}
	private String validateColor(Roulette roulette, Bet bet, String usuario) {
		String message = "";
		if(bet.getColor().equals(Color.RED) || bet.getColor().equals(Color.BLACK)) {
			message = validateCash(roulette,bet, usuario);
		}else {
			message = "Debe apostar por un color valido";
		}
		return message;
	}
	public String validateCash(Roulette roulette, Bet bet, String usuario) {
		String message = "";
		if(bet.getMonto() > 0 && bet.getMonto() <= 10000) {
			if(usuario != null && !usuario.isEmpty()) {
				saveBet(roulette, bet, usuario);
				message = "Transaccion exitosa";
			}else {
				message = "No ha sido identificado un usario";
			}
		}else {
			message = "Debe ingresar un monto valido";
		}
		
		return message;
	}
	private void saveBet(Roulette roulette, Bet bet, String usuario) {
		bet.setIdBet(countBetByRoulette(roulette.getIdRoulette()).size() + 1);
		bet.setIdRoulette(roulette.getIdRoulette());
		bet.setIdUsuario(usuario);
		betRepository.save(bet, KEY);
	}
	private Map<Integer, Bet> countBetByRoulette(int idRoulette) {
		Map<Integer,Object> map = betRepository.findAll(KEY);
	    Map<Integer,Bet> newMap2 =new HashMap<Integer,Bet>();

	    for (Map.Entry<Integer, Object> entry : map.entrySet()) {
	        try{
	        	Bet bet = (Bet) entry.getValue();
	        	if(bet.getIdRoulette() == idRoulette) {
	        		newMap2.put(entry.getKey(), bet);
	        	}
	        }
	        catch(ClassCastException e){
	            System.out.println("ERROR: "+entry.getKey()+" -> "+entry.getValue()+
	                               " not added, as "+entry.getValue()+" is not a String");
	        }
	    }
	    
		return newMap2;
	}
}
