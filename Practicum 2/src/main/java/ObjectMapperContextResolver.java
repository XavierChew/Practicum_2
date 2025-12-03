import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;

/**
 * **ObjectMapperContextResolver** is a JAX-RS {@link Provider} that supplies a pre-configured
 * Jackson {@link ObjectMapper} instance for serializing and deserializing JSON.
 *
 * <p>This resolver is typically used in JAX-RS (e.g., Jersey or RESTeasy) applications
 * to ensure consistent JSON processing across all endpoints that handle objects,
 * especially those containing Java 8 Date and Time API ({@link java.time.LocalDate},
 * {@link java.time.LocalDateTime}, etc.) objects.</p>
 *
 * <h2>Configuration Details</h2>
 * <ul>
 * <li>It registers the {@link JavaTimeModule} to correctly handle Java 8 date/time types.</li>
 * <li>It configures {@link SerializationFeature#WRITE_DATES_AS_TIMESTAMPS} to {@code false},
 * ensuring that date/time objects are serialized as **ISO 8601 strings** (e.g., "2025-12-03")
 * instead of long Unix epoch timestamps.</li>
 * </ul>
 *
 * @author Ilyas & Wei Xian
 *
 */
@Provider
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

    /**
     * The configured Jackson ObjectMapper instance.
     */
    private ObjectMapper mapper;

    /**
     * Constructs and configures the {@code ObjectMapper} instance.
     * <p>Registers {@link JavaTimeModule} and disables writing dates as timestamps.</p>
     */
    public ObjectMapperContextResolver(){
        this.mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .build();
    }


    /**
     * Retrieves the pre-configured {@code ObjectMapper} instance for the given class type.
     * <p>In this implementation, the same configured mapper is returned regardless of the requested class.</p>
     * * @param aClass The class type for which the {@code ObjectMapper} is requested.
     * @return The configured {@link ObjectMapper} instance.
     */
    @Override
    public ObjectMapper getContext(Class<?> aClass) {
        return mapper;
    }
}