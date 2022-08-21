package dev.tpcoder.empfriendly.payment.payments

import com.redis.om.spring.repository.RedisDocumentRepository
import dev.tpcoder.empfriendly.payment.payments.models.Payment
import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository : RedisDocumentRepository<Payment, String>