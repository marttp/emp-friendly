package dev.tpcoder.empfriendly.point.repository;

import com.redis.om.spring.repository.RedisDocumentRepository;
import dev.tpcoder.empfriendly.point.model.Point;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends RedisDocumentRepository<Point, String> {

}
