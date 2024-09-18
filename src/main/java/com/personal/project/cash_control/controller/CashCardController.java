package com.personal.project.cash_control.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}