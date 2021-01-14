package com.hcl;

import oracle.jrockit.jfr.StringConstantPool;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class filehandling {
    String defaultPath;
    public filehandling( String pathname){
        defaultPath=pathname;
    }
    public File createFile(String name) throws IOException {
        File file;
        String newPath=defaultPath.concat(name);
        file = new File(newPath);
        if (!file.exists()) {
            Files.createFile(Paths.get(newPath));
        }
        return file;
    }
}
