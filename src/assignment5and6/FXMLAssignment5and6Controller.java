/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5and6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author bener
 */
public class FXMLAssignment5and6Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
//    @FXML
//    private FlowPane pane;
//    
    @FXML
    TextField destination, duration, wentwith, year, comments;
    @FXML
    ListView lstDestinationList;
    @FXML
    Button add, save, open, first, next, delete;
    private static int count = 0;
    private static int ctr = 0;

    ArrayList<Destination> list = new ArrayList<>();
    ArrayList<Destination> newList = new ArrayList<>();
    File file;
    FileOutputStream fo;
    FileInputStream fi;
    ObjectInputStream oi;
    ObjectOutputStream os;

    @FXML
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    String titleTxt = "Alert Box";

    @FXML //adds the list in the textfields in the pane. Error issue is whenever user exits and comes back, whenever the user adds a new lists, it deletes the list before and replaces it with the new one
    private void add(ActionEvent event) throws IOException {
        try {
            Destination d = new Destination();

            d.setDestination(destination.getText());
            d.setDuration(Integer.parseInt(duration.getText()));
            d.setWentwith(wentwith.getText());
            d.setYear(Integer.parseInt(year.getText()));
            d.setComments(comments.getText());

            if (d.getYear() > 2021 || d.getYear() < 2000) {
                alert.setTitle(titleTxt);
                alert.setHeaderText("Information Alert");
                String s = "Year must be between 2000 - 2021";
                alert.setContentText(s);
                alert.show();
            } else {
                lstDestinationList.getItems().add(d);
                list.add(d);
            }

        } catch (RuntimeException e) {
            alert.setTitle(titleTxt);
            alert.setHeaderText("Information Alert");
            String s = "Please fill out all required fields correctly";
            alert.setContentText(s);
            alert.show();
        }

    }

    @FXML // edits list by deleting current list then asking user to renew it. User stil has to add it and save it again
    private void edit(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException {
        try {
            String s;
            for (int i = 0; i < newList.size(); i++) {
                s = newList.get(i).getDestination();
                if (s.equals(destination.getText())) {
                    newList.remove(i);
                }
            }
            count--;
            duration.clear();
            wentwith.clear();
            year.clear();
            comments.clear();
            oi.close();
            fi.close();

            fo = new FileOutputStream("list.dat");
            os = new ObjectOutputStream(fo);
            os.writeObject(newList);
            
            os.close();
            fo.close();
            
            Destination e = new Destination();

            e.setDestination(destination.getText());
            e.setDuration(Integer.parseInt(duration.getText()));
            e.setWentwith(wentwith.getText());
            e.setYear(Integer.parseInt(year.getText()));
            e.setComments(comments.getText());

            lstDestinationList.getItems().add(e);
            list.add(e);

            fo = new FileOutputStream("list.dat");
            os = new ObjectOutputStream(fo);
            os.writeObject(list);
            
            

        } catch (RuntimeException e) {
            alert.setTitle(titleTxt);
            alert.setHeaderText("Information Alert");
            String s = "Begin Editing. Press Add to List then Save to File after editing";
            alert.setContentText(s);
            alert.show();
        }

    }

    @FXML // clears the textfields
    private void clear(ActionEvent event) throws IOException {
        destination.clear();
        duration.clear();
        wentwith.clear();
        year.clear();
        comments.clear();
    }

    @FXML //saves the lists shown in the pane inside the file
    private void save(ActionEvent event) throws FileNotFoundException, IOException {
        try {

            alert.setHeaderText("Lists have been saved");
            String s = "";
            alert.setContentText(s);
            alert.show();

            fo = new FileOutputStream("list.dat");
            os = new ObjectOutputStream(fo);
            os.writeObject(list);

        } catch (RuntimeException e) {
            alert.setHeaderText("Information Alert");
            String s = "Please fill out all fields correctly";
            alert.setContentText(s);
            alert.show();
        }

    }

    @FXML
    private void open(ActionEvent event) throws IOException, ClassNotFoundException {
        try {
            FileChooser filechooser = new FileChooser();

            file = filechooser.showOpenDialog(open.getScene().getWindow());

            fi = new FileInputStream(file.getName());
            oi = new ObjectInputStream(fi);
            newList = (ArrayList) oi.readObject();
            if (!newList.isEmpty()) {
                Destination e = newList.get(0);
                destination.setText(e.getDestination());
                duration.setText("" + e.getDuration());
                wentwith.setText(e.getWentwith());
                year.setText("" + e.getYear());
                comments.setText(e.getComments());
                count++;

            }
        } catch (RuntimeException e) {
            alert.setHeaderText("Information Alert");
            String s = "Please select a valid file to open";
            alert.setContentText(s);
            alert.show();
        }

    }

    @FXML //first button will open the first list from the file
    private void first(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException {
        try {
            Destination e = new Destination();
            e = newList.get(0);
            destination.setText(e.getDestination());
            duration.setText("" + e.getDuration());
            wentwith.setText(e.getWentwith());
            year.setText("" + e.getYear());
            comments.setText(e.getComments());
            count = 1;
        } catch (RuntimeException e) {
            alert.setHeaderText("Information Alert");
            String s = "Invalid";
            alert.setContentText(s);
            alert.show();
        }

    }

    @FXML //next button opens next list after the current list shown
    private void next(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException {
        try {
            if (count < newList.size()) {
                Destination e = new Destination();
                e = newList.get(count);
                destination.setText(e.getDestination());
                duration.setText("" + e.getDuration());
                wentwith.setText(e.getWentwith());
                year.setText("" + e.getYear());
                comments.setText(e.getComments());
                count++;
            }
        } catch (RuntimeException e) {
            alert.setHeaderText("Information Alert");
            String s = "Invalid";
            alert.setContentText(s);
            alert.show();
        }

    }

    @FXML // acts as Ctrl+Z of the program. Will open previous list shown
    private void previous(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException {
        try {
            if (count > 0) {
                Destination e = new Destination();
                e = newList.get(count - 1);
                destination.setText(e.getDestination());
                duration.setText("" + e.getDuration());
                wentwith.setText(e.getWentwith());
                year.setText("" + e.getYear());
                comments.setText(e.getComments());
                count--;
            }
        } catch (RuntimeException e) {
            alert.setHeaderText("Information Alert");
            String s = "Invalid";
            alert.setContentText(s);
            alert.show();
        }

    }

    @FXML// last button opens the last list from the file.
    private void last(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException {
        try {
            Destination e = new Destination();
            e = newList.get(newList.size() - 1);
            destination.setText(e.getDestination());
            duration.setText("" + e.getDuration());
            wentwith.setText(e.getWentwith());
            year.setText("" + e.getYear());
            comments.setText(e.getComments());
            count = 1;
        } catch (RuntimeException e) {
            alert.setHeaderText("Information Alert");
            String s = "Invalid";
            alert.setContentText(s);
            alert.show();
        }

    }

    @FXML// deletes everything in the file
    private void delete(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(titleTxt);
            String confirm = "Confirm to delete list in file ! ";
            alert.setContentText(confirm);

            Optional<ButtonType> result = alert.showAndWait();
            //confirmation to delete file
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                String s;
                for (int i = 0; i < newList.size(); i++) {
                    s = newList.get(i).getDestination();
                    if (s.equals(destination.getText())) {
                        newList.remove(i);
                    }
                }
                count--;
                duration.clear();
                wentwith.clear();
                year.clear();
                comments.clear();
                oi.close();
                fi.close();

                fo = new FileOutputStream("list.dat");
                os = new ObjectOutputStream(fo);
                os.writeObject(newList);

                os.close();
                fo.close();
            }
        } catch(RuntimeException e) {
            alert.setHeaderText("Information Alert");
            String s = "Please enter list to be deleted from the file";
            alert.setContentText(s);
            alert.show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
