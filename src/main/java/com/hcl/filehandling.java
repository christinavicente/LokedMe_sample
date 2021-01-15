package com.hcl;

import oracle.jrockit.jfr.StringConstantPool;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.TreeSet;

public class filehandling {
    String defaultPath;
    FileWriter fileWriter;
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
    public void editList(TreeSet<String> list) throws IOException {
        String localPath=defaultPath.concat("list");
        StringBuilder stringBuilder=new StringBuilder(localPath);

        //File listOfNames=new File(localPath);
        for(String item:list){
            stringBuilder=stringBuilder.append(item);
            stringBuilder= stringBuilder.append('\n');

        }
        String tempFile=stringBuilder.toString();
        System.out.println(tempFile);
        Files.write(
                Paths.get(localPath),
                tempFile.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE
        );

    }
}
