package dev.tpcoder.empfriendly.general

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GeneralApplication

fun main(args: Array<String>) {
	runApplication<GeneralApplication>(*args)
}
