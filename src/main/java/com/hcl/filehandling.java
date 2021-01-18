<<<<<<< HEAD
package com.hcl;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.TreeSet;

public class filehandling {
    private String defaultPath,localPath;
    private FileWriter fileWriter;
    private ArrayList<String> altFileList;
    private int leng;
    public filehandling( String pathname){

        defaultPath=pathname;
        localPath=defaultPath.concat("list.txt");
        altFileList=new ArrayList<>(50);
    }
    public File createFile(String name) throws IOException {
        File file;
        String newPath=defaultPath.concat(name);
        String localPath=defaultPath.concat("list.txt");
        file = new File(newPath);
        if (!file.exists()) {
            Files.createFile(Paths.get(newPath));
        }
        Files.write(
                Paths.get(localPath),
                name.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
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
    public void deleteFile(File file, TreeSet<String> list) throws IOException {
        file.delete();
        editList(list);


    }
    public ArrayList<String> getArrayList(){
        return altFileList;
    }
    public void editList(TreeSet<String> list) throws IOException {
        //String localPath=defaultPath.concat("list.txt");
        StringBuilder stringBuilder=new StringBuilder("");

        for(String item:list){
            stringBuilder=stringBuilder.append(item);
            stringBuilder= stringBuilder.append('\n');
            leng=item.length();

        }
        for(int i=0;i<leng;i++){
            stringBuilder=stringBuilder.append(' ');
        }
        stringBuilder= stringBuilder.append('\n');
        String tempFile=stringBuilder.toString();
        Files.write(
                Paths.get(localPath),
                tempFile.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE
        );


    }

    public TreeSet<String> createTreeSet(File listfile) throws IOException {
        String temp,fullList;
        TreeSet<String> files= new TreeSet<String>();
        FileInputStream fileInputStream = new FileInputStream(listfile);
        int r = 0;int n=0;
        StringBuilder stringBuilder = new StringBuilder();
        while ((r = fileInputStream.read()) != -1) {
            stringBuilder.append((char) r);
        }
        fullList=stringBuilder.toString();

        while(fullList.indexOf('\n')!=-1){
            temp=fullList.substring(n,fullList.indexOf('\n'));
            if(!temp.contains(" ")) {
                files.add(temp);
                populateArrayList(temp);
            }
            fullList=fullList.substring(fullList.indexOf('\n')+1);
        }
        files.add(fullList);
        populateArrayList(fullList);

        return files;
    }
    public  void populateArrayList(String item){
        altFileList.add(item);
    }
    public boolean findFile( String file){

        return altFileList.contains(file);
    }
    public void removeElement(String item){
        int i=0;
        while((i<altFileList.size())&&(!altFileList.get(i).equals(item))){
            i++;
        }
        if(altFileList.get(i).equals(item)){
            altFileList.remove(i);
        }
    }
=======
package com.hcl;

import oracle.jrockit.jfr.StringConstantPool;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.TreeSet;

public class filehandling {
    private String defaultPath,localPath;
    private FileWriter fileWriter;
    private ArrayList<String> altFileList;
    public filehandling( String pathname){

        defaultPath=pathname;
        localPath=defaultPath.concat("list.txt");
        altFileList=new ArrayList<>(50);
    }
    public File createFile(String name) throws IOException {
        File file;
        String newPath=defaultPath.concat(name);
        String localPath=defaultPath.concat("list.txt");
        file = new File(newPath);
        if (!file.exists()) {
            Files.createFile(Paths.get(newPath));
        }
        Files.write(
                Paths.get(localPath),
                name.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
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
    public void deleteFile(File file, TreeSet<String> list) throws IOException {
        file.delete();
        editList(list);
        String temp= "\n";
        Files.write(
                Paths.get(localPath),
                temp.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );


    }
    public ArrayList<String> getArrayList(){
        return altFileList;
    }
    public void editList(TreeSet<String> list) throws IOException {
        //String localPath=defaultPath.concat("list.txt");
        StringBuilder stringBuilder=new StringBuilder("");

        for(String item:list){
            stringBuilder=stringBuilder.append(item);
            stringBuilder= stringBuilder.append('\n');

        }
        String tempFile=stringBuilder.toString();
        Files.write(
                Paths.get(localPath),
                tempFile.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE
        );

    }

    public TreeSet<String> createTreeSet(File listfile) throws IOException {
        String temp,fullList;
        TreeSet<String> files= new TreeSet<String>();
        FileInputStream fileInputStream = new FileInputStream(listfile);
        int r = 0;int n=0;
        StringBuilder stringBuilder = new StringBuilder();
        while ((r = fileInputStream.read()) != -1) {
            stringBuilder.append((char) r);
        }
        fullList=stringBuilder.toString();

        while(fullList.indexOf('\n')!=-1){
            temp=fullList.substring(n,fullList.indexOf('\n'));
            files.add(temp);
            populateArrayList(temp);
            fullList=fullList.substring(fullList.indexOf('\n')+1);
        }
        files.add(fullList);
        populateArrayList(fullList);

        return files;
    }
    public  void populateArrayList(String item){
        altFileList.add(item);
    }
    public boolean findFile( String file){

        return altFileList.contains(file);
    }
    public void removeElement(String item){
        int i=0;
        while((i<altFileList.size())&&(!altFileList.get(i).equals(item))){
            i++;
        }
        if(altFileList.get(i).equals(item)){
            altFileList.remove(i);
        }
    }
>>>>>>> c7589a939b132efd833e3984d68cf1a6c5996038
}