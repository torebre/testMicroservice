
package com.kjipo.microservice;

import java.util.concurrent.atomic.AtomicReference;

import io.helidon.config.Config;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Provider for greeting message.
 */
@ApplicationScoped
public class GreetingProvider {
    private final AtomicReference<String> message = new AtomicReference<>();

    private static final System.Logger LOGGER = System.getLogger(GreetingProvider.class.getName());

    @Inject
    Config config;

//    private final AtomicReference<String> message = new AtomicReference<>();

//    /**
//     * Create a new greeting provider, reading the message from configuration.
//     *
//     * @param message greeting to use
//     */
//    @Inject
//    public GreetingProvider(@ConfigProperty(name = "app.greeting") String message) {
//        this.message.set(message);
//    }

    String getMessage() {
        return message.get();
    }

    void setMessage(String message) {
        this.message.set(message);
    }



    public void onStartUp(@Observes @Initialized(ApplicationScoped.class) Object init) {
        LOGGER.log(System.Logger.Level.INFO, "Getting app node");

        Config appNode = config.get("app");

        LOGGER.log(System.Logger.Level.INFO, "Config app node: " +appNode);

        message.set(appNode.get("greeting").asString().get());
    }

}
