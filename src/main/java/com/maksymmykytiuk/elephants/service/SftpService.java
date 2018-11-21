package com.maksymmykytiuk.elephants.service;

import com.maksymmykytiuk.elephants.config.SFTPConfig;
import com.maksymmykytiuk.elephants.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SftpService {

    @Autowired
    private SftpRemoteFileTemplate remoteFileTemplate;

    @Autowired
    private SFTPConfig.UploadGateway gateway;

    public void uploadFile(File file) {
        gateway.upload(file);
    }

    public void uploadFile(MultipartFile multipartFile) throws IOException {
        File file = FileUtils.convert(multipartFile);
        gateway.upload(file);
        file.delete();
    }

    public void uploadFiles(List<MultipartFile> files) throws IOException {
        for (MultipartFile multipartFile : files) {
            if (multipartFile.isEmpty()) {
                continue;
            }
            File file = FileUtils.convert(multipartFile);
            gateway.upload(file);
            file.delete();
        }
    }

    public File downloadFile(String fileName, String savePath) {
        return remoteFileTemplate.execute(session -> {
            boolean existFile = session.exists(fileName);
            if (existFile) {
                InputStream is = session.readRaw(fileName);
                return FileUtils.convertInputStreamToFile(is, savePath);
            } else {
                return null;
            }
        });
    }

    public boolean deleteFile(String fileName) {
        return remoteFileTemplate.execute(session -> {
            boolean existFile = session.exists(fileName);
            if (existFile) {
                return session.remove(fileName);
            } else {
                return false;
            }
        });
    }

    public List<String> listAllFile(String path) {
        return remoteFileTemplate.execute(session -> {
            Stream<String> names = Arrays.stream(session.listNames(path));
            return names.collect(Collectors.toList());
        });
    }

    public boolean existFile(String filePath) {
        return remoteFileTemplate.execute(session ->
                session.exists(filePath));
    }
}
