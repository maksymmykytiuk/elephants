package com.maksymmykytiuk.elephants.service.ftp;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface FTPFileWriter {

    boolean open();

    void close();

    boolean loadFile(String remotePath, OutputStream outputStream);

    boolean  saveFile(InputStream inputStream, String destPath, boolean append);

    boolean saveFile(String sourcePath, String destPath, boolean append);

    boolean isConnected();

    List<String> listFile(String sourcePath);
}
