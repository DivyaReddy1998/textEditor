/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.texteditor;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;


public class TextEditor implements ActionListener {

    JFrame jFrame;
    JTextArea jTextArea;
    JMenuBar jMenuBar;
    JMenu jFile;
    JMenu jEdit;
    JMenu jClose;
    JMenuItem NewFile;
    JMenuItem OpenFile;
    JMenuItem SaveFile;
    JMenuItem PrintFile;

    JMenuItem jCut;
    JMenuItem jCopy;
    JMenuItem jPaste;
    JMenuItem jCloseEditor;

    TextEditor(){

        jFrame = new JFrame();
        jFrame.setBounds(0,0,1000,800);

        jTextArea = new JTextArea("Welcome to simple text editor");

        jFrame.add(jTextArea);

        jMenuBar = new JMenuBar();

        jFile = new JMenu("File");
        jFile.addActionListener(this); // adding function to the file
        jEdit = new JMenu("Edit");
        jEdit.addActionListener(this);
        jClose = new JMenu("Close");
        jClose.addActionListener(this);

        jMenuBar.add(jFile);
        jMenuBar.add(jEdit);
        jMenuBar.add(jClose);
        // creating the menu items of jFile
        NewFile = new JMenuItem("New");
        NewFile.addActionListener(this);
        OpenFile = new JMenuItem("Open");
        OpenFile.addActionListener(this);
        SaveFile = new JMenuItem("Save");
        SaveFile.addActionListener(this);
        PrintFile = new JMenuItem("Print");
        PrintFile.addActionListener(this);
        //adding the MenuItems into the file menu
        jFile.add(NewFile);
        jFile.add(OpenFile);
        jFile.add(SaveFile);
        jFile.add(PrintFile);

        jCut = new JMenuItem("Cut");
        jCut.addActionListener(this);
        jCopy = new JMenuItem("Copy");
        jCopy.addActionListener(this);
        jPaste = new JMenuItem("Paste");
        jPaste.addActionListener(this);
        //adding MenuItems into the edit menu
        jEdit.add(jCut);
        jEdit.add(jCopy);
        jEdit.add(jPaste);

        jCloseEditor = new JMenuItem("Close");

        jClose.add(jCloseEditor);


        jFrame.setJMenuBar(jMenuBar);
        //adding the close features
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jFrame.setVisible(true);

    }
    public static void main(String[] args){
        TextEditor editor = new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String s = e.getActionCommand();
        if(s.equals("Cut")){
            jTextArea.cut();
        }
        else if(s.equals("Copy")){
            jTextArea.copy();
        }
        else if(s.equals("Paste")){
            jTextArea.paste();
        }
        else if(s.equals("Print")){
            try {
                jTextArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(s.equals("New")){
            jTextArea.setText(" ");
        }
        else if(s.equals("Open")){

            JFileChooser jFileChooser = new JFileChooser("C:");

            int ans = jFileChooser.showOpenDialog(null);

            if(ans==jFileChooser.APPROVE_OPTION){

                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                String s1="",s2="";
                try {

                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

                    s2=bufferedReader.readLine();

                    while((s1=bufferedReader.readLine())!=null){
                        s2+=s1+"\n";
                    }

                    jTextArea.setText(s2);

                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
        else if(s.equals("Save")){
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans = jFileChooser.showOpenDialog(null);
            if(ans==jFileChooser.APPROVE_OPTION){
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                String s1="",s2="";
                try {
                    BufferedWriter bufferedWriter= null;

                    bufferedWriter = new BufferedWriter(new FileWriter(file,false));

                    bufferedWriter.write((jTextArea.getText()));
                    bufferedWriter.flush();
                    bufferedWriter.close();


                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
        else if(s.equals("Close")){
            jFrame.setVisible(false);
        }

    }
}
