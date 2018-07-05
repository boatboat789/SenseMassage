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
import javafx.scene.control.cell.CheckBoxTableCell;
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
import Detail.ScheduleDetail;
public class Schedule implements Initializable {
	public ConnectDatabase a = new ConnectDatabase();
	@FXML public  ComboBox<String> combobox;
	@FXML public  ComboBox<String> combobox1;
    @FXML  public Button test;
    @FXML  public   TableView<ScheduleDetail> TableView;
    @FXML  public   TableColumn<ScheduleDetail,Boolean> Check;
    @FXML  public   TableColumn<ScheduleDetail,String> Day;
    @FXML  public   TableColumn<ScheduleDetail,String> Massager_ID;
    @FXML  public   TableColumn<ScheduleDetail,String> Massager_Name;
     public Schedule() {
    	 dynamicdate();
	}
    @Override
    public void initialize(URL location, ResourceBundle resources){
    	Check.setCellValueFactory(new PropertyValueFactory<ScheduleDetail,Boolean>("Check"));
		Check.setCellFactory(CheckBoxTableCell.forTableColumn(Check));
    	Day.setCellValueFactory(new PropertyValueFactory<ScheduleDetail,String>("Day"));
    	Massager_ID.setCellValueFactory(new PropertyValueFactory<ScheduleDetail,String>("Massager_ID"));
    	Massager_Name.setCellValueFactory(new PropertyValueFactory<ScheduleDetail,String>("Massager_Name"));
		TableView.setItems(a.DS2);
		TableView.setEditable(true);
		combobox.setItems(a.day);
		combobox1.setItems(a.MassagerD);
	}
    @FXML
    public void actionButtonTest(ActionEvent event) throws ClassNotFoundException, SQLException{
      	String Day = combobox.getValue();
         String Massager_Name = combobox1.getValue();
         System.out.println(Day);
    	if (Day == null){
    		JOptionPane.showMessageDialog(null,"Please Choose Time");
    	}
    	else if (Massager_Name == null){
    		JOptionPane.showMessageDialog(null,"Please Choose MassagerName");
    	}
    	else{
	    	int check =0;
	    	int check1 =0;
	    		
	    	 event.getSource();
	         String Massager_ID = " "  ;
				for (int x = 0 ; x< a.DS1.size();x++){
					if(Massager_Name.equals(a.DS1.get(x).getMassager_Name())){
						Massager_ID = a.DS1.get(x).getMassager_ID();
					}	
				}	
				for(int i = 0 ; i<a.DS2CHECKONLY.size(); i++){
	    			String bc =a.DS2CHECKONLY.get(i).getMassager_Name();
					if(bc.equals(Massager_Name)){
						check = 1;
						if(a.DS2CHECKONLY.get(i).getDay().equals(Day)){
							check1=1;
						}
    				}
				}
				String Status = "Using";
	    		if(check == 0){
	    			a.DS2.add(new ScheduleDetail(Day, Massager_ID, Massager_Name,Status));
	    			a.DS2CHECKONLY.add(new ScheduleDetail(Day, Massager_ID, Massager_Name,Status));
	    			a.insertSchedule(Day, Massager_ID,Status);
	    			a.getNewSetTableSchedule();
			        TableView.setItems(a.DS2);
			        JOptionPane.showMessageDialog(null,"Add Schedule Success");
	    		}
	    		else if (check == 1&& check1==0){
	    			 a.DS2.clear();
	    			 a.DS2CHECKONLY.clear();
			        a.insertSchedule(Day, Massager_ID,Status);
	    			a.getNewSetTableSchedule();
	    			TableView.setItems(a.DS2);
	    			JOptionPane.showMessageDialog(null,"Add Detail Success");
	    		}
	    		else if(check == 1&&check1 ==1){
	    			for(int i = 0 ; i<a.DS2CHECKONLY.size(); i++){
						if(a.DS2CHECKONLY.get(i).getMassager_Name().equals(Massager_Name)){
							if(a.DS2CHECKONLY.get(i).getDay().equals(Day)){
								 a.updateSchedule(a.DS2CHECKONLY.get(i).getDay(),a.DS2CHECKONLY.get(i).getMassager_ID(),"Using");
							}
	    				}
					}
	    			 a.DS2.clear();
	    			 a.DS2CHECKONLY.clear();
	    			a.getNewSetTableSchedule();
	    			TableView.setItems(a.DS2);
	    			JOptionPane.showMessageDialog(null,"Add Schedule Success");
    		}
    	}
    }
    @FXML
    private Button borrow ;
    @FXML
    private Button add ;
    @FXML
    private Button logout ;

    @FXML
    public void actionButtonCanceled(ActionEvent event) throws ClassNotFoundException, SQLException{
    	int check =0;
    	String id = "";
    	
    	 for (ScheduleDetail p : TableView.getItems()) {
    		 String Status="";
    		 if(!p.getMassager_ID().equals("")) {
    			 id =p.getMassager_ID();
    		 }
    		 else {
    			
    		 }
		 	if(p.isCheck()==true ){
		 		if(p.getStatus().equals("Using")) {
		 			Status = "Drop";
		 		}
		 		else {
		 			Status = "Using";
		 		}
             a.updateSchedule(p.getDay(),id,Status);
             check=1;
		 	}
  }
	 if(check==0) {
		 JOptionPane.showMessageDialog(null,"Please choose massager");
	 }
	 else {
		 a.day.clear();
    	 a.DS2.clear();
		 a.DS2CHECKONLY.clear();
    	 a.getNewSetTableSchedule();
		 TableView.setItems(a.DS2);
		 JOptionPane.showMessageDialog(null,"Update Success");
	 }
    	
    }
    @FXML
    public void actionButtonHistory(ActionEvent event){
        Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui_scheduleHis.fxml"));
        try {
        	stage.setScene(new Scene((Parent) loader.load(),684, 531));
            stage.setTitle("๏SenseAroma๏");
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    @FXML
    public void actionToMenu(ActionEvent event){
        Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        //a=textField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui_owner.fxml"));
        try {

        	stage.setScene(new Scene((Parent) loader.load(), 498, 455));
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
