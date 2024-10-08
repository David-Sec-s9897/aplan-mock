package tp_outages_client;


import io.quarkus.rest.client.reactive.QuarkusRestClientBuilder;
import io.vertx.core.http.HttpClientOptions;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import tp_outages_client.logger.TPClientLogger;

import java.net.URI;

@Singleton
public class OutagesResource {

    private final ExtensionsService extensionsService;
    @Inject
    TPClientLogger logger;

    @Inject
    public OutagesResource() {
        HttpClientOptions options = new HttpClientOptions();
        // ...

        extensionsService = QuarkusRestClientBuilder.newBuilder()
                .baseUri(URI.create("http://37.221.248.104:8080/remit-outages/rest/"))
                .httpClientOptions(options)
                .clientLogger(logger)
                .build(ExtensionsService.class);

    }

    public ExtensionsService getExtensionsService() {
        return extensionsService;
    }

}