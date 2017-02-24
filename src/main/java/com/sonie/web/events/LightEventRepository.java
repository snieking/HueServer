package com.sonie.web.events;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Service;

/**
 * Repository for LightEvents.
 * 
 * @author viktorplane
 */
@Service
public interface LightEventRepository extends CouchbaseRepository<LightEvent, String> {

}
