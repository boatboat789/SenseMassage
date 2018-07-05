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
import Detail.CustomerDetail;
public class Customer implements Initializable {
	public ConnectDatabase a = new ConnectDatabase();
	@FXML private TextField text1;
    @FXML private TextField text2;
    @FXML private TextField text3;
    @FXML public  ComboBox<String> combobox;
    @FXML public Button test;
    @FXML  public   TableView<CustomerDetail> TableView;
    @FXML  public   TableColumn<CustomerDetail,Boolean> Check;
    @FXML  public   TableColumn<CustomerDetail,String> Customer_ID;    
    @FXML  public   TableColumn<CustomerDetail,String> Customer_Name;
    @FXML  public   TableColumn<CustomerDetail,String> Massager_Name;
    @FXML  public   TableColumn<CustomerDetail,String> Phone;
    @FXML  public   TableColumn<CustomerDetail,String> Details;
    public ArrayList<String> x = new ArrayList<>();
   public Customer (){
	   dynamicdate();
   }
    @Override
    public void initialize(URL location, ResourceBundle resources){
    	Check.setCellValueFactory(new PropertyValueFactory<CustomerDetail,Boolean>("Check"));
		Check.setCellFactory(CheckBoxTableCell.forTableColumn(Check));
		Customer_ID.setCellValueFactory(new PropertyValueFactory<CustomerDetail,String>("Customer_ID"));
    	Customer_Name.setCellValueFactory(new PropertyValueFactory<CustomerDetail,String>("Customer_Name"));
		Massager_Name.setCellValueFactory(new PropertyValueFactory<CustomerDetail,String>("Massager_Name"));
		Phone.setCellValueFactory(new PropertyValueFactory<CustomerDetail,String>("Phone"));
		Details.setCellValueFactory(new PropertyValueFactory<CustomerDetail,String>("Details"));
		TableView.setItems(a.DS3);
		combobox.setItems(a.MassagerD);
		for(int i = 0;i<a.MassagerD.size();i++){
			System.out.println(a.MassagerD.get(i));
		}
		TableView.setEditable(true);
	}
    @FXML
    public void actionButtonTest(ActionEvent event) throws ClassNotFoundException, SQLException{
    	int count = a.DS3.size()+1;
    	int checkDoM1 = 0;
    	String Customer_ID = Integer.toString(count);
    	String Customer_Name = text1.getText();
    	String Massager_Name = combobox.getValue();
    	String Phone = text2.getText(); 
    	String dataild = text3.getText();
    	String PhoneDB = null;
    	if(Massager_Name == null){
    		Massager_Name = "0";
    	}
    	String Massager_ID = "";
    	if(text1.getText().equals("")){
    		JOptionPane.showMessageDialog(null,"Please Fill Customer Name");
    	}
    	else if(text2.getText().equals("")){
    		JOptionPane.showMessageDialog(null,"Please Fill phone number");
    	}
    	else{
    		int check =0;
    		if(combobox.getValue() == null){
    			Massager_ID = "None";
        	}
    		else {
	         for (int i = 0 ; i<a.DS1.size();i++ ){
	        	 if (Massager_Name.equals(a.DS1.get(i).getMassager_Name())){
	        		 Massager_ID = a.DS1.get(i).getMassager_ID();
	        	 }
	         	}
    		}
	         for (int i = 0 ; i<a.DS3.size();i++ ){
	        	 if (Customer_Name.equals(a.DS3.get(i).getCustomer_Name())){
	        		 a.DS3.get(i).getMassager_Name();
	        		 PhoneDB = a.DS3.get(i).getPhone();
	        		 System.out.println("PhoneDB : "+PhoneDB);
	        		 a.DS3.get(i).getDetails();
	        		 check =1;
	        	 }
	         }
	         if(dataild.equals("")){
	        	 dataild = "0";
	         }
	      // CHECK " ' DIGIT
	    		int checkDoM = 0;
				String[] ae = text1.getText().split("");
				for(int i = 0 ; i < ae.length ;i++){
			    	      char c = text1.getText().charAt(i);
			    	      if(Character.isDigit(c)){
			    	    	 checkDoM = checkDoM+1;
			    	       }
			    	      else if(ae[i].equals("\"")){
			    	    	 checkDoM = checkDoM+1;
			    	      }
			    	     else if(ae[i].equals("'")){
			    	    	 checkDoM = checkDoM+1;
			    	      }
			    	 }
	    	 checkDoM1 = checkDigit(Phone);
	    	if(checkDoM>0){
		    	JOptionPane.showMessageDialog(null,"Can't use something like"+'"'+ "or digit. ");
		    }
	    	else if(checkDoM1==9 &checkDoM1==10){
		    	JOptionPane.showMessageDialog(null,"Phone need 9-10 digit Only");
		    }
	    	 else if(check == 0){
	    		 a.CustomerD.clear();
		    	 a.CustomerD.add(Customer_Name);
		    	 a.DS3.clear();
			     a.insertCustomer(Customer_ID,Customer_Name, Massager_ID, Phone,dataild);
			     a.getNewSetTableCustomer();
			     TableView.setItems(a.DS3);
			     JOptionPane.showMessageDialog(null,"Add Customer Detail Success");
	      }
	 	else if (check == 1){
	 		JOptionPane.showMessageDialog(null,"This customer already have information.");
	   		}
	 		
    	}
	      
    }
    public int checkDigit(String price) {
		 int checkDoM1 = 0;
		  String[] sp = price.split("");
	    	 if(sp.length>0){
		    	 for(int i = 0 ; i < sp.length ;i++){
		    	      char c = price.charAt(i);
		    	      if(Character.isAlphabetic(c)){
		    	    	  checkDoM1 = 10000;
		    	       }
		    	      if(Character.isDigit(c)){
		    	    	  checkDoM1 = checkDoM1+1;
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
	    	int count =0;
	    	for (CustomerDetail p1 : TableView.getItems()) {
	 			if(p1.isCheck()==true ){
	    			count = count +1;	
		    	 }
			}
	    	 if(count ==0){
	    			int checkDoM1 = 0;
	    	    	String MassagerID="", CustomerID="";
	    	 		if(count ==0) {
	    	 	    	String Massager_Name = combobox.getValue();
	    	     	 	String Phone = text2.getText(); 
	    	     	 	String dataild = text3.getText();
	    	 			String Name =text1.getText();
	    		    	checkDoM1 = checkDigit(text2.getText());
	    		    	for(int i = 0 ;i<a.DS3.size();i++) {
	    		    		if(a.DS3.get(i).getCustomer_Name().equals(Name)) {
	    		    			CustomerID= a.DS3.get(i).getCustomer_ID();
	    		    		}
	    		    	}
		    		 	if(Massager_Name == null) {
		    		 		MassagerID="None";
		    		    }
		    		 	else {
	
							for (int i1 = 0 ; i1<a.DS1.size();i1++ ){
				   	        	 if (Massager_Name.equals(a.DS1.get(i1).getMassager_Name())){
				   	        		MassagerID = a.DS1.get(i1).getMassager_ID();
				   	        	 }
					   	      	if(Massager_Name.equals("None")){
					   	      		MassagerID = "None";
								}
				   	         }
		    		 	}
	    		    	if(checkDoM1==1){
	    		    	   	JOptionPane.showMessageDialog(null,"Check prices that more than or equal or less than to other times. ");	
	    		    	 }
	    		    	else if(CustomerID.equals("")) {
	    		    		JOptionPane.showMessageDialog(null,"Please check name ");	
	    		    	}
	    		    	else {
	    		    		a.updateCustomer(CustomerID,Phone,dataild,MassagerID);
	    		    		 JOptionPane.showMessageDialog(null,"Update Price Success");
	    		    	}  
	    	 		}
	    	 }
	    	 else if(count ==1) {
	    		 for (CustomerDetail p11 : TableView.getItems()) {
		    			 if(p11.isCheck()==true ){
			    			 text1.setText(p11.getCustomer_Name());
				             text2.setText(p11.getPhone());
				             combobox.setValue(p11.getMassager_Name());
				             text3.setText(p11.getDetails());
		    			 }
		    		 }
	    		 }
	       	else {
	       		JOptionPane.showMessageDialog(null,"Please choose only one");
	       	}
		     	
		 a.DS3.clear();
    	 a.getNewSetTableCustomer();
    	 TableView.setItems(a.DS3);
    }
    @FXML
    public void actionlogout(ActionEvent event){
        Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        //a=textField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui_personnel.fxml"));
        try {

        	stage.setScene(new Scene((Parent) loader.load(), 498, 389));
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
