package com.maksymmykytiuk.elephants.controller;

import com.maksymmykytiuk.elephants.service.ftp.FTPFileWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/ftp")
public class UploadController {

    private final String FTP_PATH = "/%s/%s/%s";

    @Autowired
    FTPFileWriter ftpFileWriter;

    @PostMapping(value = "/upload/file")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile uploadFile,
                                     @RequestParam("subject") Long subject,
                                     @RequestParam("owner") Long owner) {
        if (uploadFile.isEmpty()) {
            return new ResponseEntity<>("please select a file!", HttpStatus.OK);
        }

//        String dest = String.format(FTP_PATH, owner, subject, uploadFile.getOriginalFilename());
        String dest = "/" + uploadFile.getOriginalFilename();

        try {
            ftpFileWriter.saveFile(uploadFile.getInputStream(), dest, false);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Successfully uploaded - " +
                uploadFile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/upload/files")
    public ResponseEntity uploadFile(@RequestParam("files") MultipartFile[] uploadFiles,
                                     @RequestParam("subject") Long subject,
                                     @RequestParam("owner") Long owner) {

        Stream<MultipartFile> multipartFileStream = Arrays.stream(uploadFiles);

        String uploadedFileName = multipartFileStream.map(MultipartFile::getOriginalFilename)
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        if (StringUtils.isEmpty(uploadedFileName)) {
            return new ResponseEntity<>("please select a file!", HttpStatus.OK);
        }

        multipartFileStream.forEach(file -> {
            String dest = String.format(FTP_PATH, owner, subject, file.getOriginalFilename());

            try {
                ftpFileWriter.saveFile(file.getInputStream(), dest, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return new ResponseEntity<>("Successfully uploaded - " + uploadedFileName, HttpStatus.OK);
    }

    @GetMapping(value = "/download/file")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("files") String name,
                                               @RequestParam("subject") Long subject,
                                               @RequestParam("owner") Long owner) {

//        String src = String.format(FTP_PATH, owner, subject, name);
        String src = "/" + name;
        File tmpFile = new File(name);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(tmpFile);
            ftpFileWriter.loadFile(name, outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", name);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        byte[] data = new byte[]{0};

        try {
            data = Files.readAllBytes(tmpFile.toPath());
            tmpFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(data, headers, HttpStatus.CREATED);
    }
}
