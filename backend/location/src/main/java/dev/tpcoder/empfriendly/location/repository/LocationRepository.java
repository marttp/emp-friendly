package dev.tpcoder.empfriendly.location.repository;

import com.redis.om.spring.repository.RedisDocumentRepository;
import dev.tpcoder.empfriendly.location.model.Location;
import java.util.Collection;

public interface LocationRepository extends RedisDocumentRepository<Location, String> {

  Collection<Location> findByReferenceId(String referenceId);
}
