package assignment5and6;

/**
 *
 * @author Jeciel Benerayan
 */
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jeciel Benerayan
 */
public class Assignment5and6 extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MenuFXML.fxml"));
        stage.setTitle("Welcome");
       
        stage.setScene(new Scene(root));
        stage.show();
    }
    
}
