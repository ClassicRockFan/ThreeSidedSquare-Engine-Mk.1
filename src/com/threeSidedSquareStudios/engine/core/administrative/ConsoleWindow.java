package com.threeSidedSquareStudios.engine.core.administrative;


import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ConsoleWindow {

    private static JTextArea mainField;
    private static JTextField textField;
    private static JScrollPane scrollPane;
    private static String[] commands;
    private static JFrame frame;
    private static boolean instantiated = false;

    public static void instantiate() {
        if (!instantiated) {

            frame = new JFrame();

            //this.engine = engine;
            frame.setSize(300, 300);
            frame.setResizable(false);

            frame.getContentPane().setBackground(Color.BLACK);
            SpringLayout springLayout = new SpringLayout();
            frame.getContentPane().setLayout(springLayout);

            mainField = new JTextArea();
            mainField.setBackground(Color.BLACK);
            mainField.setForeground(Color.WHITE);

            DefaultCaret caret = (DefaultCaret) mainField.getCaret();
            caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

            JButton btnNewButton = new JButton("Submit");
            springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, 0, SpringLayout.SOUTH, frame.getContentPane());
            springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -80, SpringLayout.WEST, frame.getContentPane());
            frame.getContentPane().add(btnNewButton);
            setupSubmitButon(btnNewButton);

            textField = new JTextField();
            springLayout.putConstraint(SpringLayout.NORTH, textField, -25, SpringLayout.SOUTH, frame.getContentPane());
            springLayout.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, frame.getContentPane());
            springLayout.putConstraint(SpringLayout.SOUTH, textField, 0, SpringLayout.SOUTH, frame.getContentPane());
            springLayout.putConstraint(SpringLayout.EAST, textField, 210, SpringLayout.WEST, frame.getContentPane());
            frame.getContentPane().add(textField);
            textField.setColumns(10);


            scrollPane = new JScrollPane(mainField);
            springLayout.putConstraint(SpringLayout.EAST, btnNewButton, 0, SpringLayout.EAST, scrollPane);
            springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, frame.getContentPane());
            springLayout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, frame.getContentPane());
            springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 237, SpringLayout.NORTH, frame.getContentPane());
            springLayout.putConstraint(SpringLayout.EAST, scrollPane, 284, SpringLayout.WEST, frame.getContentPane());
            scrollPane.setAutoscrolls(true);
            frame.getContentPane().add(scrollPane);

            setupCommands();

            frame.setVisible(true);
            instantiated = true;
        }
    }

    private static void setupCommands() {
        commands = new String[]{
                "help - displays all available commands",
                "terminate - stops the game"
        };

        addConsoleText("POSSIBLE COMMANDS:");
        for (int i = 0; i < commands.length; i++) {
            addConsoleText(commands[i]);
        }
    }

    private static void setupSubmitButon(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText().trim().toLowerCase();

                if (text.equals("terminate"))
                    System.exit(2);
//                else if (text.equals("pause"))
//                    pauseCaret();
//                else if (text.equals("resume"))
//                    resumeCaret();
                else if (text.equals("help")) {
                    addConsoleText("POSSIBLE COMMANDS:");
                    for (int i = 0; i < commands.length; i++) {
                        addConsoleText(commands[i]);
                    }
                }
                textField.setText("");
            }
        });

    }

    private static void pauseCaret() {
        if (instantiated) {
            DefaultCaret caret = (DefaultCaret) mainField.getCaret();
            caret.setUpdatePolicy(DefaultCaret.UPDATE_WHEN_ON_EDT);
            mainField.setAutoscrolls(false);
            scrollPane.setAutoscrolls(false);
        }
    }

    private static void resumeCaret() {
        if (instantiated) {
            DefaultCaret caret = (DefaultCaret) mainField.getCaret();
            caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
            mainField.setAutoscrolls(true);
            scrollPane.setAutoscrolls(true);
        }
    }

    public static void addConsoleText(String message) {
        if (instantiated)
            mainField.append(message + "\n");
    }

    public static void addConsoleText(String message, int numWhiteSpaces, String message2) {
        if (instantiated) {
            String whiteSpace = "";

            for (int i = 0; i < numWhiteSpaces - 1; i++) {
                whiteSpace += " ";
            }

            mainField.append(message + whiteSpace + message2 + "\n");
        }
    }

    public static JTextArea getMainField() {
        return mainField;
    }
}
