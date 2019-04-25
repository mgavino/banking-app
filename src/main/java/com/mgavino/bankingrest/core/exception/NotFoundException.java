package com.mgavino.bankingrest.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="Entity not found")
public class NotFoundException extends Exception {
}
