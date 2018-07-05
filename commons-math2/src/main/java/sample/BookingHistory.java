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
import Detail.BookingDetail;
public class BookingHistory implements Initializable{
    @FXML
    private Button logout ;
	public ConnectDatabase a = new ConnectDatabase();
	@FXML  public   TableView<BookingDetail> TableView;
	@FXML  public   TableColumn<BookingDetail,String> No;
	@FXML  public   TableColumn<BookingDetail,String> CustomerName;
	@FXML  public   TableColumn<BookingDetail,String> CourseName;
	@FXML public   	TableColumn<BookingDetail,String> TimeService;
	@FXML  public   TableColumn<BookingDetail,String> TimeBegin;
	@FXML  public   TableColumn<BookingDetail,String> TimeEnd;
	@FXML public   TableColumn<BookingDetail,String> MassagerName;
	@FXML public   TableColumn<BookingDetail,String> Status;
	public  BookingHistory() {
		a.DS4History.clear();
		for(int i = 0 ;i<a.DS4.size();i++) {
			if(a.DS4.get(i).getStatus().equals("Cancled")) {
				a.DS4History.add(a.DS4.get(i));
			}
		}
		dynamicdate();
	}@Override
	public void initialize(URL location, ResourceBundle resources){
		No.setCellValueFactory(new PropertyValueFactory<BookingDetail,String>("No"));
		CustomerName.setCellValueFactory(new PropertyValueFactory<BookingDetail,String>("CustomerName"));
		CourseName.setCellValueFactory(new PropertyValueFactory<BookingDetail,String>("CourseName"));
		MassagerName.setCellValueFactory(new PropertyValueFactory<BookingDetail,String>("MassagerName"));
		TimeService.setCellValueFactory(new PropertyValueFactory<BookingDetail,String>("TimeService"));
		TimeBegin.setCellValueFactory(new PropertyValueFactory<BookingDetail,String>("TimeBegin"));
		TimeEnd.setCellValueFactory(new PropertyValueFactory<BookingDetail,String>("TimeEnd"));	
		Status.setCellValueFactory(new PropertyValueFactory<BookingDetail,String>("Status"));
		TableView.setItems(a.DS4History);
	}

    @FXML
    public void actionlogout(ActionEvent event){
        Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui_booking.fxml"));
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
