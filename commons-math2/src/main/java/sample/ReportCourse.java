package sample;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import Detail.CoursePriceHistory;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class ReportCourse  implements Initializable {
	public ConnectDatabase a = new ConnectDatabase();
    @FXML  public Button test;
	@FXML  public   TableView<CoursePriceHistory> TableView;
	@FXML  public   TableColumn<CoursePriceHistory,String> HistoryID;
	@FXML  public   TableColumn<CoursePriceHistory,String> Course_Name;
	@FXML  public   TableColumn<CoursePriceHistory,String> Time	;
	@FXML public   TableColumn<CoursePriceHistory,String> 	Price;
	@FXML public   TableColumn<CoursePriceHistory,String> MassagerCost;
	@FXML public   TableColumn<CoursePriceHistory,String> Date;
	public ObservableList<CoursePriceHistory> DS11SPECIFIC = FXCollections.observableArrayList();
	@FXML public  ComboBox<String> MassagerCB;
    public ReportCourse (){
    	dynamicdate();
    } 
    @Override
    public void initialize(URL location, ResourceBundle resources){
    	HistoryID.setCellValueFactory(new PropertyValueFactory<CoursePriceHistory,String>("HistoryID"));
    	Course_Name.setCellValueFactory(new PropertyValueFactory<CoursePriceHistory,String>("Course_Name"));
    	Time.setCellValueFactory(new PropertyValueFactory<CoursePriceHistory,String>("Time"));
    	Price.setCellValueFactory(new PropertyValueFactory<CoursePriceHistory,String>("Price"));
    	MassagerCost.setCellValueFactory(new PropertyValueFactory<CoursePriceHistory,String>("MassagerCost"));
    	Date.setCellValueFactory(new PropertyValueFactory<CoursePriceHistory,String>("Date"));
    	 MassagerCB.setItems(a.courseName);
    	 TableView.setItems(a.DS11);
	}
    @FXML
    private Button borrow ;
    @FXML
    private Button add ;
    @FXML
    private Button logout ;
	public void  filter() {
		DS11SPECIFIC.clear();
		if(MassagerCB.getValue() == null){
		}	
		else {
			for(int i = 0 ; i<a.DS11.size();i++){
	 			if(MassagerCB.getValue().equals(a.DS11.get(i).getCourse_Name()))
	 				DS11SPECIFIC.add(new CoursePriceHistory(a.DS11.get(i).getHistoryID(), a.DS11.get(i).getCourse_Name(), a.DS11.get(i).getTime()
	 						, a.DS11.get(i).getPrice(), a.DS11.get(i).getMassagerCost(), a.DS11.get(i).getDate()));
		    	}
	 		}
	 		TableView.setItems(DS11SPECIFIC);
		}
 	 
	 	
    @FXML
    public void actionToMenu(ActionEvent event){
    	Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
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
