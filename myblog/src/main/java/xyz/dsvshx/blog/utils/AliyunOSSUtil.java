package xyz.dsvshx.blog.utils;

import com.aliyun.oss.*;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import xyz.dsvshx.blog.config.AliOSSConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
@Slf4j(topic = "AliyunOSSUtil")
public class AliyunOSSUtil {
    @Autowired
    private AliOSSConfig aliOSSConfig;

    private String endpoint = "oss-cn-beijing.aliyuncs.com";
    private String accessKeyId = "LTAI4FjACkPyL82MPkFpxagu";
    private String accessKeySecret = "LDqgwj1fXJlN8slwlHVdM7DEMkHK6d";
    private String fileHost = "file";
    private String bucketName = "dsvshx";


    /**
     * 上传文件
     */
    public String upLoad(File file) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());

        // 判断文件
        if (file == null) {
            return null;
        }
        String fileUrl = null;
        OSS client = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 判断容器是否存在,不存在就创建
            if (!client.doesBucketExist(bucketName)) {
                client.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                client.createBucket(createBucketRequest);
            }
            // 设置文件路径和名称
            fileUrl = fileHost + "/" + (dateStr + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + file.getName());

            // 上传文件
            PutObjectResult result = client.putObject(new PutObjectRequest(bucketName, fileUrl, file));
            // 设置权限(公开读)
            client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);

        } catch (OSSException oe) {
            oe.getMessage();
        } catch (ClientException ce) {
            ce.getErrorMessage();
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }
        String fileurl = bucketName + "." + endpoint + "/" + fileUrl;
        log.info("图片上传成功"+fileUrl);
        return fileurl;
    }

    public String upLoadFile(MultipartFile multipartFile) {
        File newFile = null;
        String url = null;

        try {
            String filename = multipartFile.getOriginalFilename();
            if (multipartFile != null) {
                if (!"".equals(filename.trim())) {
                    newFile = new File(filename);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(multipartFile.getBytes());
                    os.close();
                    multipartFile.transferTo(newFile);
                    url = upLoad(newFile);
                    newFile.delete();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return url;
    }

    public void deleteFile(String filePath) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        boolean exist = ossClient.doesObjectExist(bucketName, filePath);
        ossClient.deleteObject(bucketName, filePath);
        ossClient.shutdown();
    }


}

