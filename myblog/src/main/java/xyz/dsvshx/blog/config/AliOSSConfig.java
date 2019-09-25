package xyz.dsvshx.blog.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
public class AliOSSConfig {
    @Value("${oss.endpoint}")
    private String END_POINT;
    @Value("${oss.keyid}")
    private String ACCESS_KEY_ID;
    @Value("${oss.keysecret}")
    private String ACCESS_KEY_SECRET;
    @Value("${oss.filehost}")
    private String FILE_HOST;
    @Value("${oss.bucketname}")
    private String BUCKET_NAME;

}
