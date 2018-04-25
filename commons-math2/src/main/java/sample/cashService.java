package sample;

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
import javafx.scene.chart.PieChart.Data;
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

import java.awt.Font;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Detail.BookingDetail;
import Detail.CorseDetail;
import Detail.CustomerDetail;



public class cashService implements Initializable{
    @FXML
    private Label labell ,PricesOut,TotalOut,ChangeOut ;
    @FXML
    private Button borrow ;
    @FXML
    private Button add ;
    @FXML
    private Button logout ;
    @FXML private TextField text,text2;
	public ConnectDatabase a = new ConnectDatabase();
	@FXML public Button test;
	@FXML  public   TableView<BookingDetail> TableView;
	@FXML  public   TableColumn<BookingDetail,Boolean> Check;
	@FXML  public   TableColumn<BookingDetail,String> No;
	@FXML  public   TableColumn<BookingDetail,String> CustomerName;
	@FXML  public   TableColumn<BookingDetail,String> CourseName;
	@FXML public   	TableColumn<BookingDetail,String> MassagerName;
	@FXML  public   TableColumn<BookingDetail,String> TimeService;
	@FXML  public   TableColumn<BookingDetail,String> Price;
	int l = 0,totol = 0;
	  //  public static ObservableList<BookingDetail> list = FXCollections.observableArrayList();
	public  cashService() {
		dynamicdate();
	}@Override
	public void initialize(URL location, ResourceBundle resources){
		Check.setCellValueFactory(new PropertyValueFactory<BookingDetail,Boolean>("Check"));
		Check.setCellFactory(CheckBoxTableCell.forTableColumn(Check));
		No.setCellValueFactory(new PropertyValueFactory<BookingDetail,String>("No"));
		CustomerName.setCellValueFactory(new PropertyValueFactory<BookingDetail,String>("CustomerName"));
		CourseName.setCellValueFactory(new PropertyValueFactory<BookingDetail,String>("CourseName"));
		MassagerName.setCellValueFactory(new PropertyValueFactory<BookingDetail,String>("MassagerName"));
		TimeService.setCellValueFactory(new PropertyValueFactory<BookingDetail,String>("TimeService"));
		Price.setCellValueFactory(new PropertyValueFactory<BookingDetail,String>("Price"));
		TableView.setItems(a.DS4CheckIn);
		TableView.setEditable(true);
	}
    @FXML
    public void actionCALULATE(ActionEvent event) throws ClassNotFoundException, SQLException{
    	 l = 0;
    	int nub = 0;
    	String price = "";
    	for (BookingDetail p : TableView.getItems()) {
	 		if(p.isCheck()==true ){ 
	 			nub = nub+1;
	 			price = p.getPrice();
		 	}
    	}
    	
    	if(text2.getText().equals("") ) {
    		
    	}
    	else if(nub>1) {
    		JOptionPane.showMessageDialog(null,"Please choose only one");
    	}
    	else if (nub ==0) {
    		JOptionPane.showMessageDialog(null,"Please choose only one");
    	}
    	else{
    		int discount     ;
    		if(text.getText().equals("")){
        		discount = 0;
            	text.setText("0");
        	}
        	else {
        		discount =Integer.parseInt(text.getText());
        	}
    		PricesOut.setText(price);
    		if(text.getText().equals("0")) {
    			totol = Integer.parseInt(price);
    		}
    		else {
    			totol = Integer.parseInt(price) - (Integer.parseInt(price)*discount)/100;
    		}
    		TotalOut.setText(Integer.toString(totol));
    		l = Integer.parseInt(text2.getText())-totol;
    		ChangeOut.setText(Integer.toString(l));
    	}

    }
@FXML
    public void actionButtonTest() throws ClassNotFoundException, SQLException{	
    	SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy	");
   		Calendar cal = Calendar.getInstance();
   		String Date = format1.format(cal.getTime());
   		int count = 0;
	   	 for (BookingDetail p : TableView.getItems()) {
		 		if(p.isCheck()==true ){
		 			count = count +1;
		 		}
	   	 }
	   	 if(count == 1 &&!TotalOut.getText().equals("")) {
    	 for (BookingDetail p : TableView.getItems()) {
		 		if(p.isCheck()==true && p.getStatus().equals("CheckIn")){
		 			String Massager_ID = "";
		 			for (int x = 0 ; x< a.DS1.size();x++){
						if(p.getMassagerName().equals(a.DS1.get(x).getMassager_Name())){
							Massager_ID = a.DS1.get(x).getMassager_ID();
						}
					
					}
					String Course_ID = "" ;
		            for (int i = 0 ; i<a.DS.size();i++ ){
		 	        	 if (p.getCourseName().equals(a.DS.get(i).getCourse_Name())){
		 	        		Course_ID = a.DS.get(i).getCourse_ID();
		 	        	 }
		 	         }
					String Price = "";
					Price = Integer.toString(totol);
					String Customer_ID = "" ,MassagerCost="";
					for (int x = 0 ; x< a.DS3.size();x++){
						if(p.getCustomerName().equals(a.DS3.get(x).getCustomer_Name())){
							Customer_ID = a.DS3.get(x).getCustomer_ID();
						}
					}
					for (int x = 0 ; x< a.DS.size();x++){
						if(Course_ID.equals(a.DS.get(x).getCourse_ID())&&a.DS.get(x).getTime().equals(p.getTimeService())){
							MassagerCost = a.DS.get(x).getMassagerCost();
						}
					}
		            if (Customer_ID.equals("Customer")){
		            	Customer_ID = "Customer";
		            }
					int No = 0;
					if(a.DS7.size()==0){
						No =1;
					}
					else {
						No =a.DS7.size()+1;
					}
					String PaymentID = Integer.toString(No);
	                  a.deleteBooking(p.getNo()) ;
	                  a.insertPayment(PaymentID,Customer_ID, Massager_ID, Course_ID, p.getTimeService(), Price,Date,MassagerCost);
	                  String ProductID ="";
	                  Calendar cal1 = Calendar.getInstance();
	                  SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
						for(int x =0 ;x<a.DS10CHECKONLY.size();x++) {
							if(p.getCourseName().equals(a.DS10CHECKONLY.get(x).getCourseName())){
								String pName =  a.DS10CHECKONLY.get(x).getProductName();
								System.out.println(pName +"<<<<<<<<<<<<<<<<<<<<<");
								for(int i = 0 ; i<a.DS8.size();i++) {
									if(a.DS8.get(i).getProductName().equals(pName)){
										ProductID = a.DS8.get(i).getProductID();
										  a.insertProductPayment(PaymentID,ProductID,"0","NotDone", sdf.format(cal1.getTime()));
									}
								}		  
							}
						}	
	                  JOptionPane.showMessageDialog(null,"This service costs   "+ totol+ "  Baths.");
			 		}
		 		else if (p.isCheck()==true && p.getStatus().equals("Booking")){
		 			JOptionPane.showMessageDialog(null,"This service cant pay because Customer did not CheckIn");
		 		}
    	 	}
    	 a.getNewSetTableBooking();
    	 a.getNewSetPayment();
    	 TableView.setItems(a.DS4CheckIn);
	   	 }
	   	 else {
	   		JOptionPane.showMessageDialog(null,"Please choose only one and calculated before confirm");
	   	 }
    }
    @FXML
    public void actionToMenu(ActionEvent event){
        Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        //a=textField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui_personnel.fxml"));
        Parent root = null;
        try {

        	stage.setScene(new Scene((Parent) loader.load(), 498, 389));
            stage.setTitle("๏SenseAroma๏");
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    @FXML
    public void actionToProductService(ActionEvent event){
        Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        //a=textField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui_ProductService.fxml"));
        Parent root = null;
        try {

            stage.setScene(new Scene((Parent) loader.load(), 684, 531));
            stage.setTitle("๏SenseAroma๏");
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
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
