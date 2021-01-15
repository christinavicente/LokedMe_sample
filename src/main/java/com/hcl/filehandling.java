package com.hcl;

import oracle.jrockit.jfr.StringConstantPool;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class filehandling {
    String defaultPath;
    FileReader fileReader;
    FileWriter fileWriter;
    File nameFile;
    public filehandling( String pathname, File file){

        defaultPath=pathname;
        nameFile=file;
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
    public File copyFile(String oldPath, String name) throws IOException {
        String localPath=defaultPath.concat(name);
        File oldFile=new File(oldPath);
        File newFile=new File(localPath);
        fileWriter=new FileWriter(localPath);
        StringBuilder stringBuilder=new StringBuilder();
        FileInputStream fileInputStream = new FileInputStream(oldFile);
        int r = 0;
        while ((r = fileInputStream.read()) != -1) {
            stringBuilder.append((char) r);
        }
        String fileInput = stringBuilder.toString();
        if (!newFile.exists()) {
            Files.createFile(Paths.get(localPath));
        }
        fileWriter.write(fileInput);
        return newFile;
    }
    public void deleteFile(File file){
        file.delete();
    }
}
