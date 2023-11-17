package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author longteng
 * @date 2023/11/16 21:15
 **/

@RestController

public class FileUploadController {
    @PostMapping("upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        // 把文件存储到本地磁盘上面
        String originFilename = file.getOriginalFilename(); // 单纯的这样储存存在bug，容易出现问题
        // 这样可以防止同名文件被覆盖
        String filename = UUID.randomUUID().toString()+originFilename.substring(originFilename.indexOf("."));
//        file.transferTo(new File("C:\\Users\\longteng\\Desktop\\Files\\"+filename));
        String url = AliOssUtil.upload(filename,file.getInputStream());
        return Result.success(url);
    }
}
