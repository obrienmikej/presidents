package com.usa.presidents

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PresidentsApplication

fun main(args: Array<String>) {
	runApplication<PresidentsApplication>(*args)
}
