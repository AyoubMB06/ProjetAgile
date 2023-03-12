package env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

public class DockerSecretsDatabaseProcessor implements EnvironmentPostProcessor {
    private final Logger logger = LoggerFactory.getLogger(DockerSecretsDatabaseProcessor.class);

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        Resource resourceDbHost = new FileSystemResource("/run/secrets/db_host");
        Resource resourceDbName = new FileSystemResource("/run/secrets/db_name");
        Resource resourceDbUser = new FileSystemResource("/run/secrets/db_user");
        Resource resourceDbPass = new FileSystemResource("/run/secrets/db_pass");


        if (resourceDbHost.exists() && resourceDbName.exists() && resourceDbUser.exists() && resourceDbPass.exists()) {
            try {
                if (logger.isInfoEnabled()) {
                    logger.info("Using database infos from injected Docker secret files");
                }
                String dbHost = StreamUtils.copyToString(resourceDbHost.getInputStream(), Charset.defaultCharset());
                String dbName = StreamUtils.copyToString(resourceDbName.getInputStream(), Charset.defaultCharset());
                String dbUser = StreamUtils.copyToString(resourceDbUser.getInputStream(), Charset.defaultCharset());
                String dbPassword = StreamUtils.copyToString(resourceDbPass.getInputStream(), Charset.defaultCharset());
                // string to connect to oracle database with oracle thin
                String dbUrl = "jdbc:oracle:thin:@" + dbHost + ":1521:" + dbName;

                Properties props = new Properties();
                props.put("spring.datasource.url", dbUrl);
                props.put("spring.datasource.username", dbUser);
                props.put("spring.datasource.password", dbPassword);
                environment.getPropertySources().addLast(new PropertiesPropertySource("dbProps", props));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}