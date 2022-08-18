package dev.tpcoder.empfriendly.point.repository;

import com.redis.om.spring.repository.RedisDocumentRepository;
import dev.tpcoder.empfriendly.point.model.PointChangeHistory;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface PointChangeHistoryRepository extends RedisDocumentRepository<PointChangeHistory, String> {
  List<PointChangeHistory> findByReferenceIdOrderByCreatedDateDesc(String refId);
}
