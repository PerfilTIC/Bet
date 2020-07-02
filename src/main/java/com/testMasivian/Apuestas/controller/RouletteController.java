package com.testMasivian.Apuestas.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import com.testMasivian.Apuestas.repo.BetRepository;
import com.testMasivian.Apuestas.repo.RouletteRepository;
import com.testMasivian.Apuestas.util.Util;
import com.testMasivian.Apuestas.model.Bet;
import com.testMasivian.Apuestas.model.Roulette;
@RestController
@RequestMapping("/Roulette")
public class RouletteController {
	private BetRepository betRepository;
	private RouletteRepository rouletteRepository;
	public static final String KEY = "Roulets";
	public static final String KEY2 = "Bet";
	public RouletteController(BetRepository betRepository,RouletteRepository rouletteRepository) {
		this.rouletteRepository = rouletteRepository;
		this.betRepository = betRepository;
	}
	@GetMapping
	public Map<Integer, String> findAll(){
	    Map<Integer,String> newMap2 =new HashMap<Integer,String>();
		try{
			Map<Integer,Object> map = rouletteRepository.findAll(KEY); 
		    for (Map.Entry<Integer, Object> entry : map.entrySet()) {
		        try{
		        	Roulette rou = (Roulette) map.get(entry.getKey());
		        	if(rou.isState()) {
		        		newMap2.put(entry.getKey(), "Abierto");
		        	}else {
		        		newMap2.put(entry.getKey(), "Cerrado");
		        	}
		        }
		        catch(ClassCastException e){
		            System.out.println("ERROR: "+entry.getKey()+" -> "+entry.getValue()+
		                               " not added, as "+entry.getValue()+" is not a String");
		        }
		    }
		} catch(ClassCastException e){
            System.out.println(e.getMessage());
		}
		
		return newMap2;
	}
	@PostMapping
	public int createRoulette(@RequestBody Roulette roulette) {
		roulette.setIdRoulette(rouletteRepository.findAll(KEY).size()+1);
		if(roulette.isState()) {
			roulette.setOpenDate(Util.currentDate());
		}else {
			roulette.setOpenDate(null);
			roulette.setCloseDate(null);
		}
		rouletteRepository.save(roulette, KEY);
		return roulette.getIdRoulette();
	}
	@PutMapping("/OpenRoulette/{id}")
	public String openRoulette(@PathVariable int id, @RequestBody Roulette roulette) {
		String message = "";
		if(roulette.getOpenDate() == null) {
			if(roulette.isState()) {
				Roulette r = (Roulette) rouletteRepository.findById(id,KEY);
				r.setIdRoulette(id);
				r.setOpenDate(Util.currentDate());
				r.setState(roulette.isState());
				rouletteRepository.save(r, KEY);
			}else {
				message = "Transacción denegada";
			}
		}else {
			message = "Ya se realizo la apertura de esta ruleta";
		}
		
		return message;
	}
	@PutMapping("/CloseRoulette/{id}")
	public Map<Integer, Bet> closeRoulette(@PathVariable int id, @RequestBody Roulette roulette) {
			if(!roulette.isState()) {
				if(roulette.getCloseDate() == null) {
					Roulette r = (Roulette) rouletteRepository.findById(id,KEY);
					r.setIdRoulette(id);
					r.setCloseDate(Util.currentDate());
					r.setState(roulette.isState());
					rouletteRepository.save(r, KEY);
					
					return betByRoulette(id);
					
				}else {
					return betByRoulette(id);
				}
			}else {
				return null;
			}
	}
	private Map<Integer, Bet> betByRoulette(int idRoulette) {
		Map<Integer,Object> map = betRepository.findAll(KEY2);
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
