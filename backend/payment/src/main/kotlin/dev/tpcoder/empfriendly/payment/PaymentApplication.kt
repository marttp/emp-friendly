package dev.tpcoder.empfriendly.payment

import com.redis.om.spring.annotations.EnableRedisDocumentRepositories
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableRedisDocumentRepositories
class PaymentApplication

fun main(args: Array<String>) {
	runApplication<PaymentApplication>(*args)
}
