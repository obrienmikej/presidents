package com.usa.presidents.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import jakarta.servlet.http.HttpServletRequest

@ControllerAdvice
class POTUSErrorHandler  {
  @ExceptionHandler(POTUSNotFoundException::class)
  fun handlePOTUSNotFoundException(
    servletRequest: HttpServletRequest,
    exception: Exception
  ): ResponseEntity<String> {
    return ResponseEntity("President not found", HttpStatus.NOT_FOUND)
  }
}