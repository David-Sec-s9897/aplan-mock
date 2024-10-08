package tp_outages_client.logger;

import io.quarkus.arc.Unremovable;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import jakarta.inject.Singleton;
import org.jboss.resteasy.reactive.client.api.ClientLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
@Unremovable
public class TPClientLogger implements ClientLogger {
    public static final Logger LOGGER = Logger.getLogger(TPClientLogger.class.getName());

    int bodySize;

    @Override
    public void setBodySize(int bodySize) {
        this.bodySize = bodySize;
    }

    @Override
    public void logResponse(HttpClientResponse response, boolean redirect) {
        LOGGER.log(Level.INFO, ()-> "RESPONSE : (HTTP " +
                response.statusCode() + ") " +
                "<-- " + response.request().absoluteURI());

    }

    @Override
    public void logRequest(HttpClientRequest request, Buffer body, boolean omitBody) {
        LOGGER.log(Level.INFO, ()-> "REQUEST --> " + request.absoluteURI());
    }
}
