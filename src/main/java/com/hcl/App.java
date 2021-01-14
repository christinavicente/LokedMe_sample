package com.hcl;

import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException{
        TreeSet<String> filenames = new TreeSet<>();
        String listFilePath ="src/storedfiles/list";
        File listedFiles= new File(listFilePath);
        Scanner scanner=new Scanner(System.in);
        String dataPath="reposityory/key/storedfiles/";
        initTree(filenames,listedFiles);
        //System.out.println("Enter path: ");
        //String path= scanner.nextLine();
        mainMenu(filenames,scanner,dataPath);
    }

    public static void mainMenu(TreeSet<String> files, Scanner sc,String path) throws IOException {
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
                moreOptions(files,sc,path);
                break;
                case 3:
                System.out.println("thank you! Have a good day!");
                break;
                default:
                System.out.println("invalid code, please enter 1,2, or 3");
            }
        }while (code!=3);
        //closing(bw,files);
    }
    private static void moreOptions(TreeSet<String> files, Scanner sc,String path) throws IOException {
        String temp;
        int code;
        do {
            System.out.println("Choose option: \n 1.) add a file \n " +
                    "2.) delete a file \n 3.) search for a file \n" +
                    "4.) return to main menu");
            code=sc.nextInt();
            switch(code){
                case 1:

                addOne(files, path, sc);
                break;
                case 2:
                System.out.println("enter file name to be deleted");
                temp=sc.nextLine();
                removeOne(files,temp,path);
                break;
                case 3:
                System.out.println("enter file to search");
                temp=sc.nextLine();
                if(findFile(files,temp)){
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

    private static void closing(BufferedWriter bw, TreeSet<String> files) throws IOException {
        for(String file: files){
            bw.write(file + "\n");
        }

    }
    private static void readFiles(TreeSet<String> files){
        for(String file: files){

            System.out.println(file);
        }
    }

    private static TreeSet<String> initTree(TreeSet<String> files, File list) throws IOException {
        files.add("test.txt");
        files.add("aaaa.txt");
        files.add("zzzz.txt");
        files.add("bbbb.txt");
        return files;
    }

    private static boolean findFile(TreeSet<String> files, String file){
        return files.contains(file);
    }

    private static TreeSet<String> removeOne(TreeSet<String> files, String name, String path){
        if(findFile(files, name)) {
            String filePath = path.concat(name);
            File deletedFile = new File(filePath);
            files.remove(name);
            deletedFile.delete();
        }else {
            System.out.println("the file is not in the file");
        }

        return files;
    }
    private static TreeSet<String> addOne(TreeSet<String> files,  String path, Scanner scanner){
        String temp,name; int option,curr;
        temp="";

            System.out.println("1.)the file already exists " +
                    "2.) a new file is created");
            option=scanner.nextInt();
            switch (option) {
                case 1:
                  System.out.println("enter path");
                  scanner.nextLine();
                    temp=scanner.nextLine();
                break;
                default:
                    System.out.print("enter file name");
                    scanner.nextLine();
                    temp=scanner.nextLine();
                    files.add(temp);

                break;
            }
        return files;
    }

    private static void sort(TreeSet<String> files){
    }

}

