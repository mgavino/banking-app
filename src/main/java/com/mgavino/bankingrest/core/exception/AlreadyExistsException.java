package com.mgavino.bankingrest.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason="Entity already exists")
public class AlreadyExistsException extends Exception {
}
