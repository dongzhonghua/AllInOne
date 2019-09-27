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

    private String END_POINT = "oss-cn-beijing.aliyuncs.com";
    private String ACCESS_KEY_ID = "LTAI4FjACkPyL82MPkFpxagu";
    private String ACCESS_KEY_SECRET = "LDqgwj1fXJlN8slwlHVdM7DEMkHK6d";
    private String FILE_HOST = "file";
    private String BUCKET_NAME = "dsvshx";

}
