import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;

import java.util.ArrayList;
import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        Scanner Scan = new Scanner(System.in);
        ArrayList<String> myArrList = new ArrayList<>();
        String addItem = "";
        int itemToDel = 0;
        int listLength = 0;
        String fileName = "";

        boolean done = false;
        boolean saved = false;


            do
            {
                String choice = SafeInput.getRegExString(Scan, "Choose from the menu options: Add, Delete, View, Quit, Save, Clear, Open", "[AaDdVvQqSsCcOo]");

                if(choice.equalsIgnoreCase("A"))
                {
                    addItem = SafeInput.getNonZeroLenString(Scan, "Add an item to the list: ");
                    myArrList.add(addItem);
                    listLength++;
                }
                else if(choice.equalsIgnoreCase("D"))
                {

                    System.out.println("Delete an item from the list");
                    itemToDel = SafeInput.getInt(Scan, "Enter the number of the item you want to remove");
                    // itemToDel = SafeInput.getRangedInt(Scan, "Enter the number of the item you want to remove", 1, listLength);

                    myArrList.remove(itemToDel - 1);


                }
                else if(choice.equalsIgnoreCase("V"))
                {
                    System.out.println();
                    System.out.println("Viewing your list");
                    for(String f  : myArrList)
                    {
                        System.out.println(f);
                    }
                }
                else if(choice.equalsIgnoreCase("Q"))
                {
                    if (!saved)
                    {
                        System.out.println("Wait! You haven't saved yet!");
                        saved = SafeInput.getYNConfirm(Scan, "Do you want to save?");
                        if (saved) {
                            fileName = SafeInput.getNonZeroLenString(Scan, "Enter the name of your file");

                            try {
                                FileWriter myWriter = new FileWriter(fileName + ".txt");

                                for (String line : myArrList) {
                                    myWriter.write(line + "\n");
                                }
                                myWriter.close();
                                System.out.println("Successfully wrote to the file.");
                            } catch (IOException e) {
                                System.out.println("An error occurred.");
                                e.printStackTrace();
                            }
                        }
                    }
                    done = SafeInput.getYNConfirm(Scan, "Do you want to quit?");
                }
                else if(choice.equalsIgnoreCase("C"))
                {

                    System.out.println("Clearing all items from list.");
                    myArrList.clear();
                }
                else if(choice.equalsIgnoreCase("S"))
                {
                    System.out.println("Saving list as text file");
                    fileName = SafeInput.getNonZeroLenString(Scan, "Enter the name of your file");

                    try {
                        FileWriter myWriter = new FileWriter(fileName + ".txt");



                        for (String line : myArrList)
                        {
                            myWriter.write(line + "\n");
                        }
                        myWriter.close();
                        System.out.println("Successfully wrote to the file.");
                    } catch (IOException e)
                    {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                    saved = true;
                }
                else if(choice.equalsIgnoreCase("O"))
                {
                    if (!saved)
                    {
                        System.out.println("Wait! You haven't saved yet!");
                        saved = SafeInput.getYNConfirm(Scan, "Do you want to save?");
                        if (saved) {
                            fileName = SafeInput.getNonZeroLenString(Scan, "Enter the name of your file");

                            try {
                                FileWriter myWriter = new FileWriter(fileName + ".txt");

                                for (String line : myArrList) {
                                    myWriter.write(line + "\n");
                                }
                                myWriter.close();
                                System.out.println("Successfully wrote to the file.");
                            } catch (IOException e) {
                                System.out.println("An error occurred.");
                                e.printStackTrace();
                            }
                        }
                    }
                    System.out.println("You chose to open a file!");
                    JFileChooser chooser = new JFileChooser();
                    File selectedFile;
                    String rec = "";

                    try {

                        File workingDirectory = new File(System.getProperty("user.dir"));
                        chooser.setCurrentDirectory(workingDirectory);

                        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                            selectedFile = chooser.getSelectedFile();
                            Path file = selectedFile.toPath();

                            InputStream in =
                                    new BufferedInputStream(Files.newInputStream(file, CREATE));
                            BufferedReader reader =
                                    new BufferedReader(new InputStreamReader(in));


                            int line = 0;
                            while (reader.ready())
                            {
                                rec = reader.readLine();
                                line++;

                                System.out.printf("\nLine %4d %-60s ", line, rec);

                            }
                            reader.close();
                            System.out.println("\n\nData file read!");


                        } else  // User closed the chooser without selecting a file
                        {
                            System.out.println("You didn't select a file. Exiting program.\nRun the program again and select a file.");
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found!!!");
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }while(!done);




    }
}