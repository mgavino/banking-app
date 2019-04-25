package com.mgavino.bankingrest.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason="Not enough money in bank account")
public class NotEnoughMoneyException extends Exception {
}
