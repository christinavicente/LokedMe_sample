package com.hcl;

import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException{
        TreeSet<String> filenames = new TreeSet<>();
        String listFilePath ="src/storedfiles/list.txt";
        File listedFiles= new File(listFilePath);
        Scanner scanner=new Scanner(System.in);
        String dataPath="src/storedfiles/";
        filehandling fh= new filehandling(dataPath);
        filenames=initTree(listedFiles,fh);
        mainMenu(filenames,scanner,dataPath,fh);
    }

    public static void mainMenu(TreeSet<String> files, Scanner sc,
                                String path,filehandling fh) throws IOException {
        int code;
        do {
            System.out.println("Choose option: \n 1.) see all files \n " +
                    "2.) more options \n 3.) close program ");
            code=sc.nextInt();
            switch (code){
                case 1:
                readFiles(files);
                break;
                case 2:
                System.out.println("loading more options");
                moreOptions(files,sc,path,fh);
                break;
                case 3:
                System.out.println("thank you! Have a good day!");
                closing(fh,files);
                break;
                default:
                System.out.println("invalid code, please enter 1,2, or 3");
            }
        }while (code!=3);
        //closing(bw,files);
    }
    private static void moreOptions(TreeSet<String> files, Scanner sc,String path, filehandling fh) throws IOException {
        String temp;
        int code;
        do {
            System.out.println("Choose option: \n 1.) add a file \n " +
                    "2.) delete a file \n 3.) search for a file \n" +
                    "4.) return to main menu");
            code=sc.nextInt();
            switch(code){
                case 1:

                addOne(files, path, sc, fh);
                break;
                case 2:
                System.out.println("enter file name to be deleted");
                sc.nextLine();
                temp=sc.nextLine();
                removeOne(files,temp,path,fh);
                break;
                case 3:
                System.out.println("enter file to search");
                sc.nextLine();
                temp=sc.nextLine();
                if(fh.findFile(temp)){
                    System.out.println("file has been found");
                }else {
                    System.out.println("no such file is in the directory");
                }
                break;
                case 4:
                    System.out.println("returning to main menu");
                break;
                default:
                System.out.println("invalid code, please enter 1,2, or 3");
            }
        }while (code!=4);

    }

    private static void closing(filehandling fh, TreeSet<String> files) throws IOException {
        fh.editList(files);

    }
    private static void readFiles(TreeSet<String> files){
        for(String file: files){
            System.out.println(file);
        }
    }

    private static TreeSet<String> initTree( File list,filehandling fh) throws IOException {
        TreeSet<String> files=fh.createTreeSet(list);
        return files;
    }



    private static TreeSet<String> removeOne(TreeSet<String> files, String name, String path,
                                             filehandling fh){
        if(fh.findFile(name)) {
            String filePath = path.concat(name);
            File deletedFile = new File(filePath);
            files.remove(name);
            fh.removeElement(name);
            fh.deleteFile(deletedFile);
        }else {
            System.out.println("the file is not in the repository");
        }

        return files;
    }
    private static TreeSet<String> addOne(TreeSet<String> files,  String path,
                                          Scanner scanner, filehandling fh) throws IOException {
        String temp,name; int option;
        File file;

            //System.out.println("1.)the file already exists \n" +
            //        "2.) a new file is created");
            //option=scanner.nextInt();
            //switch (option) {
                //case 1:
            //      System.out.println("enter path");
            //      scanner.nextLine();
            //        temp=scanner.nextLine();
            //        System.out.println(temp);
            //        name=temp.substring(temp.lastIndexOf('/')+1);
            //        System.out.println(name);
            //        files.add(name);
            //        fh.populateArrayList(name);
            //        copyFile(temp,fh,name);
                //break;
                //default:
                    System.out.println("enter file name: ");
                    scanner.nextLine();
                    temp=scanner.nextLine();
                    files.add(temp);
                    fh.populateArrayList(temp);
                    fh.createFile(temp);

                //break;
            //}
        return files;
    }

    private static void copyFile(String path,filehandling fh, String name) throws IOException {
        System.out.println("copying file to current repository");
        fh.copyFile(path,name);
        System.out.println("finished copying file");
    }

}

