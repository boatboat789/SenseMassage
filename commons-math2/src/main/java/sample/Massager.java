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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import Detail.MassagerDetail;
public class Massager implements Initializable {
	public  Massager() {
		// TODO Auto-generated method stub
		dynamicdate();
	}
	public ConnectDatabase a = new ConnectDatabase();
		@FXML private TextField text1;
	    @FXML private TextField text2;
	    @FXML public  Button test,updateButton;
	    @FXML  public   TableView<MassagerDetail> TableView;
	    @FXML  public   TableColumn<MassagerDetail,Boolean> Check;
	    @FXML  public   TableColumn<MassagerDetail,String> Massager_ID;
	    @FXML  public   TableColumn<MassagerDetail,String> Massager_Name;
	    @FXML  public   TableColumn<MassagerDetail,String> Phone;
	    @FXML  public   TableColumn<MassagerDetail,String> Status;
	  //  public static ObservableList<MassagerDetail> list = FXCollections.observableArrayList();
	    public ArrayList<String> x = new ArrayList<>();
	    @Override
	    public void initialize(URL location, ResourceBundle resources){
	    	Check.setCellValueFactory(new PropertyValueFactory<MassagerDetail,Boolean>("Check"));
			Check.setCellFactory(CheckBoxTableCell.forTableColumn(Check));
	    	Massager_ID.setCellValueFactory(new PropertyValueFactory<MassagerDetail,String>("Massager_ID"));
			Massager_Name.setCellValueFactory(new PropertyValueFactory<MassagerDetail,String>("Massager_Name"));
			Phone.setCellValueFactory(new PropertyValueFactory<MassagerDetail,String>("Phone"));
			Status.setCellValueFactory(new PropertyValueFactory<MassagerDetail,String>("Status"));
			TableView.setItems(a.DS1);	
			TableView.setEditable(true);
		}
	    @FXML
	    public void actionButtonTest(ActionEvent event) throws ClassNotFoundException, SQLException{
	    	int check= 0;
	    	int count = a.DS1.size()+1;
	    	int checkDoM = 0;
	    	int checkDoM1 = 0;
	    	String Massager_ID = Integer.toString(count);
	         String Massager_Name = text1.getText();
	       String Phone = text2.getText();
	    	if(text1.getText().equals("")){
	    		JOptionPane.showMessageDialog(null,"Please Fill Massager Name");
	    	}
	    	else if(text2.getText().equals("")){
	    		JOptionPane.showMessageDialog(null,"Please Fill Phone number");
	    	}
	    	else{
	    		for (int i = 0 ; i<a.DS1.size();i++ ){
	   	        	 if (Massager_Name.equals(a.DS1.get(i).getMassager_Name())){
	   	        		System.out.println("Massager name "+ Massager_Name+"       "+a.DS1.get(i).getMassager_Name());
	   	        		Massager_ID = a.DS1.get(i).getMassager_ID();
	   	        		a.DS1.get(i).getPhone();
	   	        		check =1;
	   	        	 }
	   	         }
		    	 int phoneCheck = checkPhone();
			if(check == 0){
				String[] ae = text1.getText().split("");
				for(int i = 0 ; i < ae.length ;i++){
 		    	      char c = text1.getText().charAt(i);
 		    	      if(Character.isDigit(c)){
 		    	    	 checkDoM = checkDoM+1;
 		    	    	System.out.println(checkDoM+"oh1");
 		    	       }
 		    	      else if(ae[i].equals("\"")){
 		    	    	 checkDoM = checkDoM+1;
 		    	    	System.out.println(checkDoM+"oh2");
 		    	      }
 		    	     else if(ae[i].equals("'")){
 		    	    	 checkDoM = checkDoM+1;
 		    	    	System.out.println(checkDoM+"oh3");
 		    	      }
 		    	    else if(ae[i].equals(".")){
		    	    	 checkDoM = checkDoM+1;
		    	    	System.out.println(checkDoM+"oh3");
		    	      }
 		    	    else if(ae[i].equals("-")){
		    	    	 checkDoM = checkDoM+1;
		    	    	System.out.println(checkDoM+"oh3");
		    	      }
 		    	 }
				System.out.println(checkDoM+"oh");
				System.out.println(checkDoM1 +"checkDoM1 ");
				if(checkDoM>0){
		    		JOptionPane.showMessageDialog(null,"Can't use something like"+'"'+ "or digit on Name");
		    	}
				else if(phoneCheck !=9 & phoneCheck != 10 ){
		    		JOptionPane.showMessageDialog(null,"Phone need 9-10 digit Only");
		    	}
				else{
					String Status= "Active";
					a.DS1.add(new MassagerDetail(Massager_ID, Massager_Name, Phone,Status));
					a.MassagerD.add(Massager_Name);
	    		 	a.insertMassager(Massager_ID, Massager_Name, Phone);
	    		 	TableView.setItems(a.DS1);
	    		 	JOptionPane.showMessageDialog(null,"Add Massager Success");
				}
				 
			      }
			 else if (check == 1){
				 JOptionPane.showMessageDialog(null,"Massager already have detail.");
			 			 
			 	}
	    	
			 checkDoM = 0;
	    	 checkDoM1 = 0;
	    	}
	    }
	 public int checkPhone() {
		 int checkDoM1 = 0;
		  String[] sp = text2.getText().split("");
	    	 if(sp.length>0){
	    		 String[] ae1 = text2.getText().split("");
	    		 for(int i = 0 ; i < ae1.length ;i++){
		    	      text2.getText().charAt(i);
		    	    if(ae1[i].equals("\"")){
		    	    	 checkDoM1 = checkDoM1+1;
		    	      }
		    	     else if(ae1[i].equals("'")){
		    	    	 checkDoM1 = checkDoM1+1;
		    	      }
		    	    else if(ae1[i].equals(".")){
		    	    	 checkDoM1 = checkDoM1+1;
		    	      }
		    	    else if(ae1[i].equals("-")){
		    	    	 checkDoM1 = checkDoM1+1;
		    	      }
		    	 }
	    		 if(checkDoM1==0) {
		    	 for(int i = 0 ; i < sp.length ;i++){
		    	      char c = text2.getText().charAt(i);
		    	      if(Character.isDigit(c)){
		    	    	  checkDoM1 = checkDoM1+1;
		    	       }
		    	      if(Character.isAlphabetic(c)){
		    	    	  checkDoM1 = 10000;
		    	       }   
		    	 }
	    		 }
	    	 }

		 return checkDoM1;
	 }
    @FXML
    private Button add ;
    @FXML
    private Button logout ;
    @FXML
    public void actionButtonCanceled(ActionEvent event) throws ClassNotFoundException, SQLException{
    	int check = 0;
    	 for (MassagerDetail p : TableView.getItems()) {
    		 String Status="";
    		 		if(p.isCheck()==true ){
    		 			if(p.getStatus().equals("Active")) {
    		 				Status = "Unactive";
    		 			}
    		 			else {
    		 				Status = "Active";
    		 			}
	                     a.updateMassagerStatus(p.getMassager_ID(),Status);
	                     check=1;
    		 		}
         }
    	 if(check==0) {
    		 JOptionPane.showMessageDialog(null,"Please choose massager");
    	 }
    	 else {
    		 JOptionPane.showMessageDialog(null,"Change Status Success");
        	 a.getNewSetTableMassager();
        	 TableView.setItems(a.DS1);
    	 }
    	
    }
    @FXML
    public void actionButtonUpdate(ActionEvent event) throws ClassNotFoundException, SQLException{
    	int check = 0;
    	if(text2.getText().equals("")) {
    		JOptionPane.showMessageDialog(null,"Please Fill Phone number");
    	}
    	else {
    		
    		 int phoneCheck = checkPhone();
    		 if(phoneCheck !=9 & phoneCheck != 10){
		    		JOptionPane.showMessageDialog(null,"Phone need 9-10 digit Only");
		    	}
			else{
	    	 for (MassagerDetail p : TableView.getItems()) {
	    		 if(p.isCheck()==true ){
	    		 	String phone = text2.getText();
		            a.updateMassagerPhone(p.getMassager_ID(),phone);
		            check=1;
	    		 	}
	    	 	}
	    	 if(check==0) {
	    		 JOptionPane.showMessageDialog(null,"Please choose massager");
	    	 }
	    	 else {
	    		 JOptionPane.showMessageDialog(null,"Update Phone Success");
	    	 }
			}
    	 a.getNewSetTableMassager();
    	 TableView.setItems(a.DS1);
    	}
    }
    @FXML
    public void actionToMenu(ActionEvent event){
        Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        //a=textField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui_owner.fxml"));
        try {

        	stage.setScene(new Scene((Parent) loader.load(),  498, 455));
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
