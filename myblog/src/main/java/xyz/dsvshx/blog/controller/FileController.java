package xyz.dsvshx.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.dsvshx.blog.constant.FileConstant;
import xyz.dsvshx.blog.utils.AliyunOSSUtil;
import xyz.dsvshx.blog.utils.ResultUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.UUID;

@Controller
public class FileController {
    @Autowired
    private AliyunOSSUtil ossUtil;

    @PostMapping("/upload")
    @ResponseBody
    public Object upload(MultipartFile fileUpload) {
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = UUID.randomUUID() + suffixName;
        //指定本地文件夹存储图片
        //String filePath = "C:\\Users\\dong0\\Documents\\java_practice\\AAA\\myblog\\src\\main\\resources\\static\\file\\";
        try {

            //将图片保存到static文件夹里
            //fileUpload.transferTo(new File(filePath+fileName));
            //上传到oss服务器，但是保存到本地和上传到服务器不能同时进行。
            String url = ossUtil.upLoadFile(fileUpload);

            return ResultUtil.success(url, "success to upload");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.success(500, "fail to upload");
        }
    }

    @PostMapping("/uploadV")
    @ResponseBody
    public Object uploadVideo(@RequestParam(value = "files", required = false) MultipartFile multipartFile, HttpServletRequest request) {
        //获取文件名
        String fileName = multipartFile.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        //fileName = UUID.randomUUID()+suffixName;
        //指定本地文件夹存储图片
        String filePath = FileConstant.filePath;
        try {

            //将图片保存到static文件夹里
            multipartFile.transferTo(new File(filePath + fileName));
            //上传到oss服务器，但是保存到本地和上传到服务器不能同时进行。
            //String url = ossUtil.upLoadFile(fileUpload);

            return ResultUtil.success(filePath + fileName, "success to upload");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.success(500, "fail to upload");
        }
    }

    @GetMapping("/merge")
    @ResponseBody
    public Object merge(@RequestParam(value = "filename") String filename, @RequestParam String totalPieces)
            throws IOException {
        HashMap<String, Object> result = new HashMap<>();
        String target = FileConstant.filePath + filename;
        FileOutputStream output = new FileOutputStream(new File(target));
        for (int i = 0; i < Integer.parseInt(totalPieces); i++) {
            String tempFileName = target.substring(0, target.lastIndexOf(".") - 1) + i
                    + target.substring(target.lastIndexOf("."));
            File file = new File(tempFileName);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int num;
            while ((num = fileInputStream.read(buffer)) != -1)
                output.write(buffer, 0, num);
            fileInputStream.close();
            file.delete();
        }
        output.close();
        result.put("status", 200);
        result.put("filepath", target);

        return result;
    }
}
