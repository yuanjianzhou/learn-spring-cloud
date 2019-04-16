package com.jason.eurekaConsumer.controller;

import com.jason.eurekaConsumer.service.DcClient;
import com.jason.eurekaConsumer.service.UploadService;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by jason on 2019/4/12.
 */
@RestController
public class DcController {

    @Autowired
    DcClient dcClient;
    @Autowired
    private UploadService uploadService;

    @GetMapping("/consumer")
    public String dc() {
        File file = new File("E:\\spring cloud\\upload.txt");
        DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory(10240,file).createItem("file",
                MediaType.TEXT_PLAIN_VALUE, true, file.getName());

        try (InputStream input = new FileInputStream(file); OutputStream os = fileItem.getOutputStream()) {
            IOUtils.copy(input, os);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file: " + e, e);
        }

        MultipartFile multi = new CommonsMultipartFile(fileItem);

        System.out.println(uploadService.handleFileUpload(multi));
        return dcClient.consumer();
    }
}
