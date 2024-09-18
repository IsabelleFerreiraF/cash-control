package com.personal.project.cash_control.dto;

import org.springframework.data.annotation.Id;

public record CashControl(@Id Long id, Double amount ) {

}
