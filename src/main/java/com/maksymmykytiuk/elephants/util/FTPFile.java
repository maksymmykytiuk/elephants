package com.maksymmykytiuk.elephants.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Builder
@Setter
@Getter
public class FTPFile {

    private String name;
    private Long owner;
    private Long subject;

    public String getPathWithoutName() {
        return File.separator
                + this.owner
                + File.separator
                + this.subject
                + File.separator;
    }

    public String getPath() {
        return File.separator
                + this.owner
                + File.separator
                + this.subject
                + File.separator
                + this.name;
    }

    public String getPath(String path) {
        return File.separator
                + path
                + File.separator
                + this.name;
    }
}
