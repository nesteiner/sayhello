package com.example.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageArgumentException extends Exception {
    String message;
}
