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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import Detail.StartWorkDetail;
public class StartWork implements Initializable {
    @FXML
    private Button borrow ;
    @FXML
    private Button add ;
    @FXML
    private Button edit ;
    @FXML	
    private Button logout ;
	public ConnectDatabase a = new ConnectDatabase();
	@FXML public  ComboBox<String> combobox1;
	@FXML public Button test;
	@FXML  public   TableView<StartWorkDetail> TableView;
	@FXML  public   TableColumn<StartWorkDetail,String> Massager_ID;
	@FXML  public   TableColumn<StartWorkDetail,String> Massager_Name;
	@FXML  public   TableColumn<StartWorkDetail,String> TimeIn;
	  //  public static ObservableList<StartWorkDetail> list = FXCollections.observableArrayList();
	public StartWork(){
		dynamicdate();
	}
	  @Override
	public void initialize(URL location, ResourceBundle resources){
		Massager_ID.setCellValueFactory(new PropertyValueFactory<StartWorkDetail,String>("Massager_ID"));
		Massager_Name.setCellValueFactory(new PropertyValueFactory<StartWorkDetail,String>("Massager_Name"));
		TimeIn.setCellValueFactory(new PropertyValueFactory<StartWorkDetail,String>("TimeIn"));
		TableView.setItems(a.DS6Present);
		combobox1.setItems(a.MassagerD);
	}
    @FXML
    public void actionButtonTest(ActionEvent event) throws ClassNotFoundException, SQLException{
    	int check = 0 ;
    	if(combobox1.getValue() == null){
    		JOptionPane.showMessageDialog(null,"Please Choose Massager");
    	}
    	else{
    		 Calendar cal = Calendar.getInstance();
    		 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    		 String TimeIn = sdf.format(cal.getTime());
    		 String MassagerName1 = combobox1.getValue();
    		 String Massager_ID = " "  ;
    		 SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
    		 Calendar cal1 = Calendar.getInstance();
    	   	 String Date = format1.format(cal1.getTime());
    		 for (int x = 0 ; x< a.DS1.size();x++){
				if(MassagerName1.equals(a.DS1.get(x).getMassager_Name())){
					Massager_ID = a.DS1.get(x).getMassager_ID();
				}
    		 }
    		 for(int i = 0; i < a.DS6Present.size();i++){
    			 if(a.DS6Present.get(i).getMassager_Name().equals(MassagerName1)){
    				 check =1;
    			 }
    		 }
    		 if(check == 0 ){	 
    			 a.insertHistoryWork(Massager_ID, TimeIn, Date,"Clock In");
    			 a.getNewSetTableStartWorking();
    			 TableView.setItems(a.DS6Present);
    			 JOptionPane.showMessageDialog(null,"Check In complete");
    		 }
    		 else if(check==1){
    			  JOptionPane.showMessageDialog(null,"This massager already checkIn");
    		 }
    		} 	 
    }
    @FXML
    public void actionButtonOut(ActionEvent event) throws ClassNotFoundException, SQLException{
   	   int check = 0 ;
	   	if(combobox1.getValue() == null){
	   		JOptionPane.showMessageDialog(null,"Please Choose Massager");
	   	}
	   	else{
	   		 Calendar cal = Calendar.getInstance();
	   		 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	   		 String TimeOut = sdf.format(cal.getTime());
	   		 String MassagerName1 = combobox1.getValue();
	   		 String Massager_ID = " "  ;
	   		 for (int x = 0 ; x< a.DS1.size();x++){
					if(MassagerName1.equals(a.DS1.get(x).getMassager_Name())){
						Massager_ID = a.DS1.get(x).getMassager_ID();
					}
	   		 }
	   		 String TimeIn = "";
	   		 for(int i = 0; i < a.DS6Present.size();i++){
	   			 if(a.DS6Present.get(i).getMassager_Name().equals(MassagerName1)){
	   				 TimeIn= a.DS6Present.get(i).getTimeIn();
	   				 check =1;
	   			 }
	   		 }
	   		 if(check == 0 ){
	   			 JOptionPane.showMessageDialog(null,"This massager did not check in");
	   		 }
	   		 else if(check==1){ 
	   			a.updateHistoryWork(Massager_ID, "Clock Out",TimeIn,TimeOut);
    			a.getNewSetTableStartWorking();
    			TableView.setItems(a.DS6Present);
    			JOptionPane.showMessageDialog(null,"Check out complete");		 
	   		 }	 	
   		}
    }
    @FXML
    public void actionToMenu(ActionEvent event){
        Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui_personnel.fxml"));
        try {
        	stage.setScene(new Scene((Parent) loader.load(),498, 389));
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
