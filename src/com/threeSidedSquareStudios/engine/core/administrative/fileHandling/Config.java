package com.threeSidedSquareStudios.engine.core.administrative.fileHandling;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class Config {

    private static Properties prop;

    public static void saveProp(ArrayList<String> title, ArrayList<String> value, String filePath) {
        prop = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream(filePath);
            for (int i = 0; i < title.size(); i++) {
                prop.setProperty(title.get(i), value.get(i));
                System.out.println("Config File Updated at " + filePath + " title: " + title.get(i) + " for the value " + value.get(i));
            }
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void saveProp(String title, int value, String filePath) {
        saveProp(title, Integer.toString(value), filePath);
    }

    public static void saveProp(String title, String value, String filePath) {
        ArrayList<String> valueArray = new ArrayList<String>();
        ArrayList<String> titleArray = new ArrayList<String>();

        titleArray.add(title);
        valueArray.add(value);

        saveProp(titleArray, valueArray, filePath);

    }

    public static String getProp(String title, String filePath) {
        prop = new Properties();


        String value = "";
        try {

            FileInputStream input = new FileInputStream(filePath);

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            value = prop.getProperty(title);
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return value;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static HashMap<String, String> getAllProps(String filePath) {

        HashMap<String, String> result = new HashMap<String, String>();

        Properties prop = new Properties();
        File inputFile = new File(filePath);
        FileInputStream input = null;
        try {
            input = new FileInputStream(inputFile);

            try {

                String filename = filePath;
                if (input == null) {
                    System.out.println("Sorry, unable to find " + filename);
                    return null;
                }

                prop.load(input);

                Enumeration<?> e = prop.propertyNames();
                while (e.hasMoreElements()) {
                    String key = (String) e.nextElement();
                    String value = prop.getProperty(key);
                    result.put(key, value);
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getPropInt(String title, String filePath) {
        try {
            return Integer.parseInt(getProp(title, filePath));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Sorry.  An error has occurred loading a file and we can run without the data.");
            System.exit(1);
            return -1;
        }
    }
}
