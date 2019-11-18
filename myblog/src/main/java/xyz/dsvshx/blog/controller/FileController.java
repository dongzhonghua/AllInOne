package xyz.dsvshx.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.dsvshx.blog.constant.FileConstant;
import xyz.dsvshx.blog.utils.AliyunOSSUtil;
import xyz.dsvshx.blog.utils.ResultUtil;

import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
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

    @PostMapping("/uploadImages")
    @ResponseBody
    public Object uploadImage(@RequestParam(value = "file", required = false) MultipartFile multipartFile, HttpServletRequest request) {
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

            return ResultUtil.success(FileConstant.imageBed + fileName, "success to upload");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.success(500, "fail to upload");
        }
    }

    @PostMapping("/downImage")
    @ResponseBody
    public Object dowmImage(@RequestParam(value = "url", required = false) String imageurl, @RequestParam(value = "imageName") String imageName) throws Exception {
        //new一个URL对象
        URL url = new URL(imageurl);
        //打开链接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置请求方式为"GET"
        conn.setRequestMethod("GET");
        //超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);
        //通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = readInputStream(inStream);
        //获取文件后缀名
        String suffixName = imageurl.substring(imageurl.lastIndexOf("."));
        if (!suffixName.equals(".jpg") && !suffixName.equals(".png") && !suffixName.equals(".jpeg")){
           suffixName=".png";
        }
        //new一个文件对象用来保存图片，默认保存当前工程根目录
        String fileName = imageName+suffixName;
        File imageFile = new File(FileConstant.filePath + fileName);
        //创建输出流
        FileOutputStream outStream = new FileOutputStream(imageFile);
        //写入数据
        outStream.write(data);
        //关闭输出流
        outStream.close();
        return ResultUtil.success(FileConstant.imageBed + fileName, "success to upload");
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
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
