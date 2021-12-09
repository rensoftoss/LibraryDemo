package com.library.librarydemo;

import com.library.librarydemo.config.ApplicationProperties;
import com.library.librarydemo.config.PropConstants;
import java.net.InetAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
@Slf4j
public class LibraryDemoApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(LibraryDemoApplication.class);
        Environment env = app.run(args).getEnvironment();

        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }

        log.info(
            "\n----------------------------------------------------------\n\t"
                + "Application '{}' is running !\n\t"
                + "Profile(s): \t{}\n\t"
                + "Local: \t\t{}://localhost:{}{}\n\t"
                + "Swagger: \t{}://{}:{}/webjars/swagger-ui/index.html?url=http://{}:{}/v3/api-docs\n"
                + "----------------------------------------------------------",
            env.getProperty(PropConstants.SPRING_APPLICATION_NAME),
            env.getActiveProfiles(),
            protocol,
            env.getProperty(PropConstants.SERVER_PORT),
            env.getProperty(PropConstants.SERVER_CONTEXT_PATH),
            protocol,
            hostAddress,
            env.getProperty(PropConstants.SERVER_PORT),
            hostAddress,
            env.getProperty(PropConstants.SERVER_PORT));

    }

}
