package dev.tpcoder.empfriendly.location.repository;

import com.redis.om.spring.repository.RedisDocumentRepository;
import dev.tpcoder.empfriendly.location.model.Tracking;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingRepository extends RedisDocumentRepository<Tracking, String> {

}
