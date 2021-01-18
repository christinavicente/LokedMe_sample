package com.hcl;


import java.io.*;
import java.util.*;


public class App
{
    //Starts program
    public static void main( String[] args ) throws IOException{
        //Creates and initializes variables
        TreeSet<String> filenames;
        String listFilePath ="src/storedfiles/list.txt";
        File listedFiles= new File(listFilePath);
        Scanner scanner=new Scanner(System.in);
        String dataPath="src/storedfiles/";
        filehandling fh= new filehandling(dataPath);
        filenames=initTree(listedFiles,fh);
        //prints out developer statements
        System.out.println("Program: LockedMe_Sample \n" +
                "Developer: Christina Vicente");
        //goes to options
        mainMenu(filenames,scanner,dataPath,fh);
    }

    //Shows initial options
    public static void mainMenu(TreeSet<String> files, Scanner sc,
                                String path,filehandling fh) throws IOException {
        //starts loop
        int code;
        do {
            //prints out possible options
            System.out.println("Choose option: \n 1.) see all files \n " +
                    "2.) more options \n 3.) close program ");
            code=sc.nextInt();
            switch (code){
                case 1://print all files in ascending order
                    readFiles(files);
                    break;
                case 2://calls next method with more options
                    System.out.println("loading more options");
                    moreOptions(files,sc,path,fh);
                    break;
                case 3://closes the program
                    System.out.println("thank you! Have a good day!");
                    closing(fh,files);
                    break;
                default://handles other integer inputs
                    System.out.println("invalid code, please enter 1,2, or 3");
            }
        }while (code!=3);//loop ends when the close program code is entered

    }

    //Shows add, delete, and search options
    private static void moreOptions(TreeSet<String> files, Scanner sc,String path, filehandling fh) throws IOException {
        String temp;
        int code;
        do {//starts loop
            //prints out all options for this screen
            System.out.println("Choose option: \n 1.) add a file \n " +
                    "2.) delete a file \n 3.) search for a file \n" +
                    "4.) return to main menu");
            code=sc.nextInt();
            switch(code){
                case 1://add a file
                    addOne(files, sc, fh);
                    break;
                case 2://delete a file
                    //get file name to be deleted
                    System.out.println("enter file name to be deleted");
                    sc.nextLine();
                    temp=sc.nextLine();
                    removeOne(files,temp,path,fh);
                    break;
                case 3://search for a file
                    //get the file to be searched
                    System.out.println("enter file to search");
                    sc.nextLine();
                    temp=sc.nextLine();
                    if(fh.findFile(temp)){//if file is in the directory
                        System.out.println("file has been found");
                    }else {//if the file is not in the directory
                        System.out.println("no such file is in the directory");
                    }
                    break;
                case 4://returns to previous menu
                    System.out.println("returning to main menu");
                    break;
                default://if an invalid code has been entered
                    System.out.println("invalid code, please enter 1,2, or 3");
            }
        }while (code!=4);//ends loop when return to previous menu is entered

    }

    //does the final edit to the list.txt file
    private static void closing(filehandling fh, TreeSet<String> files) throws IOException {
        //calls the edit method in filehandling
        fh.editList(files);

    }

    //reads all of the files in list.txt
    private static void readFiles(TreeSet<String> files){
        for(String file: files){
            System.out.println(file);
        }
    }

    //initializes the treeSet
    private static TreeSet<String> initTree( File list,filehandling fh) throws IOException {
        TreeSet<String> files=fh.createTreeSet(list);
        return files;
    }

    //removes a node from the TreeSet
    private static TreeSet<String> removeOne(TreeSet<String> files, String name, String path,
                                             filehandling fh) throws IOException {
        //checks to see if the file exists
        if(fh.findFile(name)) {
            //gets file path
            String filePath = path.concat(name);
            File deletedFile = new File(filePath);
            //removes file name for data structures
            files.remove(name);
            fh.removeElement(name);
            //deletes the file
            fh.deleteFile(deletedFile, files);
        }else {
            System.out.println("the file is not in the repository");
        }

        return files;
    }
    //adds a node to the Treeset
    private static TreeSet<String> addOne(TreeSet<String> files, Scanner scanner,
                                          filehandling fh) throws IOException {

        String temp;
        //get new file name
        System.out.println("enter file name: ");
        scanner.nextLine();
        temp=scanner.nextLine();
        while(fh.findFile(temp)){//makes sure that the file does not already exist
            System.out.println("This file already exists, please enter a new name");
            temp=scanner.nextLine();
        }
        //add file to data structures
        files.add(temp);
        fh.populateArrayList(temp);
        //creates file
        fh.createFile(temp);
        return files;
    }


}
