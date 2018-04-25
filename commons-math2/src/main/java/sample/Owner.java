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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Owner  {
    @FXML
    private Button borrow ;
    @FXML
    private Label labell ;
    @FXML
    private Button add ;
    @FXML
    private Button logout ;
    public  Owner() {
    	dynamicdate();
		// TODO Auto-generated constructor stub
	}
    @FXML
    public void actionlogout(ActionEvent event){
        Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        //a=textField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui1.fxml"));
        Parent root = null;
        try {

            stage.setScene(new Scene((Parent) loader.load(), 361, 250));
            stage.setTitle("Seat");
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    public void actionToCourse(ActionEvent event){
        Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        //a=textField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui_course.fxml"));
        Parent root = null;
        try {

            stage.setScene(new Scene((Parent) loader.load(), 684, 531));
            stage.setTitle("Seat");
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    public void actionToScchedule(ActionEvent event){
        Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        //a=textField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui_schedule.fxml"));
        Parent root = null;
        try {

            stage.setScene(new Scene((Parent) loader.load(), 684, 531));
            stage.setTitle("Seat");
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    public void actionToMassager(ActionEvent event){
        Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        //a=textField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui_massager.fxml"));
        Parent root = null;
        try {

            stage.setScene(new Scene((Parent) loader.load(), 684, 531));
            stage.setTitle("Seat");
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    @FXML
    public void actionToSkill(ActionEvent event){
        Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        //a=textField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui_skill.fxml"));
        Parent root = null;
        try {

            stage.setScene(new Scene((Parent) loader.load(), 684, 531));
            stage.setTitle("Seat");
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    @FXML
    public void actionToReport(ActionEvent event){
        Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        //a=textField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui_report.fxml"));
        Parent root = null;
        try {

            stage.setScene(new Scene((Parent) loader.load(), 684, 531));
            stage.setTitle("Seat");
            stage.show();	
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    @FXML
    public void actionToProduct(ActionEvent event){
        Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        //a=textField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Product.fxml"));
        Parent root = null;
        try {

            stage.setScene(new Scene((Parent) loader.load(), 684, 531));
            stage.setTitle("Seat");
            stage.show();	
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    @FXML
    public void actionToTypeProduct(ActionEvent event){
        Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        //a=textField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("TypeProduct.fxml"));
        Parent root = null;
        try {

            stage.setScene(new Scene((Parent) loader.load(), 684, 531));
            stage.setTitle("Seat");
            stage.show();	
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    @FXML
    public void actionToManagement(ActionEvent event){
        Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        //a=textField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Management.fxml"));
        Parent root = null;
        try {

            stage.setScene(new Scene((Parent) loader.load(), 684, 531));
            stage.setTitle("Seat");
            stage.show();	
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    public void dynamicdate(){
    	 Timeline timeline = new Timeline(
    			    new KeyFrame(Duration.seconds(0),
    			      new EventHandler<ActionEvent>() {
    			        @Override 
    			        public void handle(ActionEvent actionEvent) {
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

