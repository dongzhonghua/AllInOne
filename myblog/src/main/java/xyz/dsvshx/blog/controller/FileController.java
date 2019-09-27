package xyz.dsvshx.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import xyz.dsvshx.blog.utils.AliyunOSSUtil;
import xyz.dsvshx.blog.utils.ResultUtil;

import java.io.File;
import java.util.UUID;

@Controller
public class FileController {
    @Autowired
    private AliyunOSSUtil ossUtil;
    @PostMapping("/upload")
    @ResponseBody
    public Object upload(MultipartFile fileUpload){
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = UUID.randomUUID()+suffixName;
        //指定本地文件夹存储图片
        //String filePath = "C:\\Users\\dong0\\Documents\\java_practice\\AAA\\myblog\\src\\main\\resources\\static\\file\\";
        try {

            //将图片保存到static文件夹里
            //fileUpload.transferTo(new File(filePath+fileName));
            //上传到oss服务器，但是保存到本地和上传到服务器不能同时进行。
            String url = ossUtil.upLoadFile(fileUpload);

            return ResultUtil.success(url,"success to upload");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.success(500,"fail to upload");
        }
    }
}
