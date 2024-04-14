package products.event;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface EventRepository extends MongoRepository<Event, UUID> {
}
