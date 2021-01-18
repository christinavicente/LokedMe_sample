package com.hcl;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.TreeSet;

//class that handles all of the file handling actions
//it stores the file names in an arraylist
public class filehandling {
    //class variables
    private String defaultPath,localPath;
    private ArrayList<String> altFileList;
    private int leng;

    //initializes class and variables
    public filehandling( String pathname){

        defaultPath=pathname;
        localPath=defaultPath.concat("list.txt");
        altFileList=new ArrayList<>(50);
    }

    //creates a new file and adds its name to list.txt
    public File createFile(String name) throws IOException {
        //creates file
        File file;
        //creates path
        String newPath=defaultPath.concat(name);
        file = new File(newPath);
        if (!file.exists()) {
            Files.createFile(Paths.get(newPath));
        }
        //adds file name to list.txt
        Files.write(
                Paths.get(localPath),
                name.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
        return file;
    }

    //deletes a file and removes its name from list.txt
    public void deleteFile(File file, TreeSet<String> list) throws IOException {
        file.delete();
        //calls edit list to remove from list.txt
        editList(list);
    }

    //edits the list.txt file to match the vales in the TreeSet
    public void editList(TreeSet<String> list) throws IOException {
        //puts all strings in the TreeSet into a StringBuilder, separated by '\n'
        StringBuilder stringBuilder=new StringBuilder("");
        for(String item:list){
            stringBuilder=stringBuilder.append(item);
            stringBuilder= stringBuilder.append('\n');
            leng=item.length();

        }
        //adds blank spaces at the end to overwrite the last line
        for(int i=0;i<leng;i++){
            stringBuilder=stringBuilder.append(' ');
        }
        //overwrites the content of the file
        stringBuilder= stringBuilder.append('\n');
        String tempFile=stringBuilder.toString();
        Files.write(
                Paths.get(localPath),
                tempFile.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE
        );

    }

    //creates the TreeSet
    public TreeSet<String> createTreeSet(File listfile) throws IOException {
        String temp,fullList;
        TreeSet<String> files= new TreeSet<String>();
        //gets data from list.txt and puts it into a stringBuilder
        FileInputStream fileInputStream = new FileInputStream(listfile);
        int r = 0;int n=0;
        StringBuilder stringBuilder = new StringBuilder();
        while ((r = fileInputStream.read()) != -1) {
            stringBuilder.append((char) r);
        }
        //changes StringBuilder to String
        fullList=stringBuilder.toString();
        //parse through the string, and add Strings to the list
        while(fullList.indexOf('\n')!=-1){
            temp=fullList.substring(n,fullList.indexOf('\n'));
            if(!temp.contains(" ")) {
                files.add(temp);
                populateArrayList(temp);
            }

            fullList=fullList.substring(fullList.indexOf('\n')+1);
        }
        //gets the last value and adds it to datastructures
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
}
