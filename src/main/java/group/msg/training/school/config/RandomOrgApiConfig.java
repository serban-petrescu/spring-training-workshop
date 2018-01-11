package group.msg.training.school.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "org.random")
public class RandomOrgApiConfig {
    private String apiKey;
    private String url;
}
