package cmo.personal.project.cash_control.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.project.cash_control.dto.CashControl;

@RestController
@RequestMapping("/cashcontrol")
public class CashCardController {
	 @GetMapping("/{requestedId}")
	    private ResponseEntity<CashControl> findById(@PathVariable Long requestedId) {
		 if(requestedId.equals(99L)){
	            CashControl cashControl = new CashControl(99L,123.45);
	            return ResponseEntity.ok(cashControl);
	        }
	        else{
	            return ResponseEntity.notFound().build();
	        }
	     }
}
