package com.kjipo.microservice;

import io.helidon.config.Config;
import io.helidon.config.mp.MpConfigSources;
import io.helidon.config.spi.ConfigSource;
import io.helidon.microprofile.server.Server;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

import java.nio.file.Files;
import java.nio.file.Path;

import static io.helidon.config.ConfigSources.file;


public final class Main {
    private static final System.Logger LOGGER = System.getLogger(Main.class.getName());

    private Main() {
        // Empty private constructor to prevent instantiation
    }


    static Server startServer() {
//        ConfigProviderResolver resolver = ConfigProviderResolver.instance();
        var configFilePath = Path.of("/home/student/workspace/testMicroservice/docker_mount/application.yaml");
//        var config = Config.builder()
//                .sources(file(configFilePath).optional())
//                .build();
//        resolver.registerConfig(config, null);

        if (Files.exists(configFilePath)) {
            LOGGER.log(System.Logger.Level.INFO, "Found config file");

//            var config = resolver.getBuilder()
//                    .withSources(MpConfigSources.create(configFilePath))
//                    .build();
//            resolver.registerConfig(config, null);
        } else {
            LOGGER.log(System.Logger.Level.INFO, "Did not find config file");
        }

        return Server.create().start();
    }

    public static void main(final String[] args) {
        Server server = startServer();
    }


}
