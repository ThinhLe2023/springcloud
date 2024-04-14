package products.event;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document
public abstract class Event {
    public final UUID id = UUID.randomUUID();
    public final LocalDateTime created = LocalDateTime.now();
}
