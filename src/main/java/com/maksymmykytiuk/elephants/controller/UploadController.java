package com.maksymmykytiuk.elephants.controller;

import com.maksymmykytiuk.elephants.service.SftpService;
import com.maksymmykytiuk.elephants.util.FTPFile;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sftp")
public class UploadController {

    @Autowired
    SftpService sftpService;

    @PostMapping(value = "/upload/file")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile uploadFile,
                                     @RequestParam("subject") Long subject,
                                     @RequestParam("owner") Long owner) {
        if (uploadFile.isEmpty()) {
            return new ResponseEntity<>("please select a file!", HttpStatus.OK);
        }

        try {
            sftpService.uploadFile(uploadFile);
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
        String uploadedFileName = Arrays.stream(uploadFiles).map(MultipartFile::getOriginalFilename)
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        if (StringUtils.isEmpty(uploadedFileName)) {
            return new ResponseEntity<>("please select a file!", HttpStatus.OK);
        }

        try {
            sftpService.uploadFiles(Arrays.asList(uploadFiles));
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Successfully uploaded - " + uploadedFileName, HttpStatus.OK);
    }

    @GetMapping(value = "download/file")
    public ResponseEntity<byte[]> downloadFile(@RequestBody FTPFile FTPFile, @RequestParam String path)
            throws IOException {
        File file = sftpService.downloadFile(FTPFile.getPath(), FTPFile.getPath(path));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", FTPFile.getName());
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }
}
