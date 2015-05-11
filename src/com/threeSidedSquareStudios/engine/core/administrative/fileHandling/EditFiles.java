package com.threeSidedSquareStudios.engine.core.administrative.fileHandling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by Tyler on 3/19/2015.
 */
public class EditFiles {

    public static void createNewFile(String filePath) {
        File outputFile;
        BufferedWriter outputWriter;
        try {
            outputFile = new File(filePath);
            outputWriter = new BufferedWriter(new FileWriter(outputFile));
            outputWriter.write("\n");
            outputWriter.close();
            System.out.println("Created New File: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error" + e.getMessage());
        }
    }

}

