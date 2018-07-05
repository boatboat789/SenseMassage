package sample;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import Detail.StartWorkDetail;
public class StartWorkHistory implements Initializable {
	public ConnectDatabase a = new ConnectDatabase();
	@FXML  public   TableView<StartWorkDetail> TableView;
	@FXML  public   TableColumn<StartWorkDetail,String> Massager_ID;
	@FXML  public   TableColumn<StartWorkDetail,String> Massager_Name;
	@FXML  public   TableColumn<StartWorkDetail,String> TimeIn;
	@FXML  public   TableColumn<StartWorkDetail,String> TimeOut;
	@FXML  public   TableColumn<StartWorkDetail,String> Date;
	public StartWorkHistory(){
		dynamicdate();
	}
	  @Override
	public void initialize(URL location, ResourceBundle resources){
		Massager_ID.setCellValueFactory(new PropertyValueFactory<StartWorkDetail,String>("Massager_ID"));
		Massager_Name.setCellValueFactory(new PropertyValueFactory<StartWorkDetail,String>("Massager_Name"));
		TimeIn.setCellValueFactory(new PropertyValueFactory<StartWorkDetail,String>("TimeIn"));
		TimeOut.setCellValueFactory(new PropertyValueFactory<StartWorkDetail,String>("TimeOut"));
		Date.setCellValueFactory(new PropertyValueFactory<StartWorkDetail,String>("Date"));
		TableView.setItems(a.DS6);
	}
    @FXML
    public void actionToMenu(ActionEvent event){
            Button b =(Button)event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ui_schedule.fxml"));
            try {
                stage.setScene(new Scene((Parent) loader.load(), 684, 531));
                stage.setTitle("๏SenseAroma๏");
                stage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
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
