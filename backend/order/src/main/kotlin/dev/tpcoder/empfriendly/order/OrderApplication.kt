package dev.tpcoder.empfriendly.order

import com.redis.om.spring.annotations.EnableRedisDocumentRepositories
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableRedisDocumentRepositories
class OrderApplication

fun main(args: Array<String>) {
	runApplication<OrderApplication>(*args)
}
