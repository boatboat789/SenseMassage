package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class Login {

    @FXML	
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button buttonlogin;
    
    public Login() {
		// TODO Auto-generated constructor stub
    
	}
    @FXML
    public void actionlogin(ActionEvent event){

        Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        if(username.getText().equals("a") && password.getText().equals("a")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ui_owner.fxml"));
            Parent root = null;
            try {
                stage.setScene(new Scene((Parent) loader.load(), 498, 455));
                stage.setTitle("๏SenseAroma๏");

                stage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else if(username.getText().equals("b")&& password.getText().equals("b")){

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ui_personnel.fxml"));
            Parent root = null;
            try {

                stage.setScene(new Scene((Parent) loader.load(),  498, 389));
                stage.setTitle("๏SenseAroma๏");

                stage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else {
        	JOptionPane.showMessageDialog(null,"ID or Password fail ");
        }

        }
    @FXML
    private Label labell ;
    public void dynamicdate(){
   	 Timeline timeline = new Timeline(
   			    new KeyFrame(Duration.seconds(0),
   			      new EventHandler<ActionEvent>() {
   			        @Override public void handle(ActionEvent actionEvent) {
   			          Calendar time = Calendar.getInstance();
   			          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
   			          labell.setText(simpleDateFormat.format(time.getTime()));
   			        }
   			      }
   			    ),
   			    new KeyFrame(Duration.seconds(1))
   			  );
   			  timeline.setCycleCount(Animation.INDEFINITE);
   			  timeline.play();
    }
    
}



