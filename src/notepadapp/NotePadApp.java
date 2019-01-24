/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepadapp;

import com.sun.javafx.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.STYLESHEET_CASPIAN;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.IndexRange;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 *
 * @author Aya
 */
public class NotePadApp extends Application {

    TextArea text;

    @Override
    public void init() throws Exception {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        text = new TextArea();
        text.setPrefHeight(50);
        text.setPrefWidth(50);
    }

    @Override
    public void start(Stage primaryStage) {

        Menu file = new Menu("File");

        Menu m1 = new Menu("Help");
        MenuItem _new = new MenuItem("New");
        MenuItem save = new MenuItem("Save");
        MenuItem open = new MenuItem("Open");
        MenuItem exit = new MenuItem("Exit");
        file.getItems().addAll(_new, save, open, exit);

        Menu edit = new Menu("Edit");
        MenuItem undo = new MenuItem("Undo");
        MenuItem cut = new MenuItem("Cut");
        MenuItem copy = new MenuItem("Copy");
        MenuItem paste = new MenuItem("Paste");
        MenuItem delete = new MenuItem("Delete");
        MenuItem selectAll = new MenuItem("Select All");
        edit.getItems().addAll(undo, cut, copy, paste, delete, selectAll);
        Menu help = new Menu("Help");
        MenuItem about = new MenuItem("about NotePad");
        help.getItems().addAll(about);

        MenuBar menu = new MenuBar(file, edit, help);

        BorderPane pane = new BorderPane();
        pane.setTop(menu);
        pane.setCenter(text);
        _new.setAccelerator(KeyCombination.keyCombination("Alt+n"));
        selectAll.setAccelerator(KeyCombination.keyCombination("Alt+a"));
        copy.setAccelerator(KeyCombination.keyCombination("Alt+c"));
        paste.setAccelerator(KeyCombination.keyCombination("Alt+p"));
        exit.setAccelerator(KeyCombination.keyCombination("Alt+e"));
        save.setAccelerator(KeyCombination.keyCombination("Alt+s"));
        open.setAccelerator(KeyCombination.keyCombination("Alt+o"));
        undo.setAccelerator(KeyCombination.keyCombination("Alt+u"));
        delete.setAccelerator(KeyCombination.keyCombination("Alt+d"));
        cut.setAccelerator(KeyCombination.keyCombination("Alt+x"));

        _new.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                text.clear();
            }
        });

        open.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                openFile(getPath());
            }
        }
        );
        save.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                File sfile = getPath();
                int index = sfile.toString().lastIndexOf(".");
                String extension = sfile.toString().substring(index + 1);
                System.out.println("ex = " + extension);
                if (extension.equals("txt")) {
                    saveText(sfile);
                } else {
                    final Stage dialog = new Stage();
                    Text t = new Text("  pease enter valid file ? ");
                    t.setFont(new Font(STYLESHEET_CASPIAN, 15));
                    Button yesBtn = new Button("OK");
                    yesBtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            dialog.close();
                        }
                    });
                    BorderPane p = new BorderPane();
                    p.setTop(t);
                    p.setCenter(yesBtn);
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(primaryStage);
                    Scene dialogScene = new Scene(p, 250, 100);
                    dialog.setScene(dialogScene);
                    dialog.show();
                }

            }
        }
        );

        exit.setOnAction(
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event
                    ) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        final Stage dialog = new Stage();
                        Text t = new Text("  Do you want to save text ? ");
                        t.setFont(new Font(STYLESHEET_CASPIAN, 15));

                        Button yesBtn = new Button("Yes");
                        Button noBtn = new Button("No");
                        GridPane p = new GridPane();
                        p.addRow(0, t);
                        p.add(yesBtn, 0, 1, 1, 1);
                        p.add(noBtn, 1, 1, 1, 1);

                        dialog.initModality(Modality.APPLICATION_MODAL);
                        dialog.initOwner(primaryStage);
                        Scene dialogScene = new Scene(p, 250, 100);
                        dialog.setScene(dialogScene);
                        dialog.show();
                        yesBtn.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
//                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                //saveText();
                                System.exit(0);
                            }
                        });
                        noBtn.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
//                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                System.exit(0);
                            }
                        });

//               
                    }
                }
        );
        cut.setOnAction(
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event
                    ) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        text.cut();
                    }
                }
        );
        copy.setOnAction(
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event
                    ) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        text.copy();
                    }
                }
        );

        paste.setOnAction(
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event
                    ) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        text.paste();
                    }
                }
        );
        delete.setOnAction(
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event
                    ) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        IndexRange selection = text.getSelection();
                        int start = selection.getStart();
                        int end = selection.getEnd();
                        text.deleteText(start, end);
                    }
                }
        );
        undo.setOnAction(
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event
                    ) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        text.undo();
                    }
                }
        );

        help.setOnAction(
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event
                    ) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        final Stage dialog = new Stage();
                        dialog.setTitle("  About me ");
                        Text t = new Text("This NotePad Created by Aia abdalla ,"
                                + "\n in 23/1/2019 1:44pm"
                                + "\n Gmail : ayalaban2@gmail.com"
                                + "\n LinkedIn : https://www.linkedin.com/in/aia-abdalla-a62aa8145/"
                                + "\n GoodBye ");
                        dialog.initModality(Modality.APPLICATION_MODAL);
                        dialog.initOwner(primaryStage);
                        VBox dialogVbox = new VBox(20);
                        dialogVbox.getChildren().add(t);
                        Scene dialogScene = new Scene(dialogVbox, 350, 150);
                        dialog.setScene(dialogScene);
                        dialog.show();
                    }
                }
        );

        selectAll.setOnAction(
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event
                    ) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        text.selectAll();
                    }
                }
        );

        Scene scene = new Scene(pane, 400, 400);

        primaryStage.setTitle(
                "my notepad");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public File getPath() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("save file");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
        } else {
            System.out.println("No Selection ");
        }

//                int index = chooser.getSelectedFile().toString().lastIndexOf(".");
//                String extension = chooser.getSelectedFile().toString().substring(index + 1);
//                System.out.println("ex = " + extension);
        return chooser.getSelectedFile();
    }

    public void openFile(File path) {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
            int size = fis.available();
            byte[] b = new byte[size];
            fis.read(b);
            text.setText(new String(b));
            fis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NotePadApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NotePadApp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(NotePadApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void saveText(File path) {
        FileOutputStream fis = null;
        try {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            fis = new FileOutputStream(path);
            byte[] b = text.getText().getBytes();
            fis.write(b);
            fis.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(NotePadApp.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NotePadApp.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();

            } catch (IOException ex) {
                Logger.getLogger(NotePadApp.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
