package com.usa.presidents.middleware

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse

@Component
class RequestLoggingFilter : Filter {
  val loggerFactory = LoggerFactory.getLogger("POTUS Logger")

  override fun doFilter(
    servletRequest: ServletRequest,
    servletResponse: ServletResponse,
    filterChain: FilterChain
  ) {
    val utmSource = servletRequest.getParameter("utm_source")
    loggerFactory.info("Logging UTM source: $utmSource")
    filterChain.doFilter(servletRequest, servletResponse)
  }
}