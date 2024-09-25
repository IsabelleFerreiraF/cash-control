package com.personal.project.cash_control.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.personal.project.cash_control.dto.CashControl;
import com.personal.project.cash_control.repository.CashCardRepository;

@RestController
@RequestMapping("/cashcontrol")
public class CashCardController {
	
	private final CashCardRepository cashCardRepository;
	   
    private CashCardController(CashCardRepository cashCardRepository){
        this.cashCardRepository = cashCardRepository;
    }
	/*
	 @GetMapping("/{requestedId}")
	    private ResponseEntity<CashControl> findById(@PathVariable Long requestedId) {
		 if(requestedId.equals(99L)){
	            CashControl cashControl = new CashControl(99L,123.45);
	            return ResponseEntity.ok(cashControl);
	        }
	        else{
	            return ResponseEntity.notFound().build();
	        }
	     }*/
    
    @GetMapping("/{requestedId}")
    private ResponseEntity<CashControl> findById(@PathVariable Long requestedId) {
       Optional<CashControl> cashCardOptional = cashCardRepository.findById(requestedId);
        if (cashCardOptional.isPresent()) {
            return ResponseEntity.ok(cashCardOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    private ResponseEntity<Void> createCashControl(@RequestBody CashControl newCashControl, UriComponentsBuilder ucb){
    	CashControl savedCashCard = cashCardRepository.save(newCashControl);
        URI locationOfNewCashCard = ucb.path("cashcards/{id}")
        .buildAndExpand(savedCashCard.id()).toUri();
        return ResponseEntity.created(locationOfNewCashCard).build();
    
    }
}