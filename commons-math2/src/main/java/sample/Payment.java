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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ResourceBundle;

import Detail.PaymentDetail;

public class Payment implements Initializable {
	public ConnectDatabase a = new ConnectDatabase();
	@FXML public  ComboBox<String> Day;
	public ObservableList<String> DayList = FXCollections.observableArrayList();
	@FXML public  ComboBox<String> Month;
	public ObservableList<String> MonthList = FXCollections.observableArrayList();
	@FXML public  ComboBox<String> Year;
	public ObservableList<String> YearList = FXCollections.observableArrayList();
	@FXML public  ComboBox<String> MassagerCB;
	public ObservableList<String> MassagerNameList = FXCollections.observableArrayList();
	@FXML public  Label totalPrice ,totalPrice1,totalPrice11 ;
    @FXML  public Button test;
    @FXML  public   TableView<PaymentDetail> TableView;
    @FXML  public   TableColumn<PaymentDetail,String> PaymentID;
    @FXML  public   TableColumn<PaymentDetail,String> MassagerName;
    @FXML  public   TableColumn<PaymentDetail,String> CustomerName;
    @FXML  public   TableColumn<PaymentDetail,String> CourseName;
    @FXML  public   TableColumn<PaymentDetail,String> ServiceTime;
    @FXML  public   TableColumn<PaymentDetail,String> Price;
    @FXML  public   TableColumn<PaymentDetail,String> MassgerCost;
  //  public static ObservableList<PaymentDetail> list = FXCollections.observableArrayList();
    public ArrayList<String> x = new ArrayList<>();
   
    public Payment (){
    	dynamicdate();
    } 
    @Override
    public void initialize(URL location, ResourceBundle resources){
    	PaymentID.setCellValueFactory(new PropertyValueFactory<PaymentDetail,String>("PaymentID"));
    	MassagerName.setCellValueFactory(new PropertyValueFactory<PaymentDetail,String>("MassagerName"));
    	CustomerName.setCellValueFactory(new PropertyValueFactory<PaymentDetail,String>("CustomerName"));
    	CourseName.setCellValueFactory(new PropertyValueFactory<PaymentDetail,String>("CourseName"));
    	CustomerName.setCellValueFactory(new PropertyValueFactory<PaymentDetail,String>("CustomerName"));
    	ServiceTime.setCellValueFactory(new PropertyValueFactory<PaymentDetail,String>("ServiceTime"));
    	Price.setCellValueFactory(new PropertyValueFactory<PaymentDetail,String>("Price"));
    	MassgerCost.setCellValueFactory(new PropertyValueFactory<PaymentDetail,String>("MassgerCost"));
    	List<String> al = new ArrayList<String>();
    	LinkedHashSet<String> lhs = new LinkedHashSet<String>();
    	for(int i = 0 ; i<a.DS7.size();i++){
    		String[] yearAR = a.DS7.get(i).getDate().split("/");
    		al.add(yearAR[2].trim());
    	}
    	lhs.addAll(al);
	     al.clear();
	     al.addAll(lhs);
	     Collections.sort(al);
	     YearList.addAll(al);
    	Year.setItems(YearList);
    	System.out.println(YearList.size());
		//TableView.setItems(a.DS7);
	
	}

    @FXML
    private Button borrow ;
    @FXML
    private Button add ;
    @FXML
    private Button logout ;
    public void filtterSort() throws ClassNotFoundException, SQLException{
    	a.DS7SPECIFIC.clear();
    	String YearG= Year.getValue();
	   	 String MonthG = Month.getValue();
	   	String DayG = Day.getValue();
 		 int itemCount11 = MassagerNameList.size();
 		 for(int i=0;i<itemCount11;i++){
 			MassagerCB.getItems().clear();
 	     }
 		MassagerNameList.clear();
 		List<String> al = new ArrayList<String>();
 		LinkedHashSet<String> lhs = new LinkedHashSet<String>();
    	 if(YearG == null){	 
    		
    	 }
    	 else if(MonthG == null){				
    
 		}
     	 else if(MonthG !=null &&YearG != null && DayG == null){
     		 System.out.println("here");
     		for(int i = 0 ; i<a.DS7.size();i++){
        		String[] yearAR = a.DS7.get(i).getDate().split("/");
        		if(yearAR[2].equals(YearG)){
        			if(yearAR[1].equals(MonthG)){
        					a.DS7SPECIFIC.add(new PaymentDetail(a.DS7.get(i).getPaymentID(), a.DS7.get(i).getCustomerName(), a.DS7.get(i).getMassagerName(), 
        					a.DS7.get(i).getCourseName(), a.DS7.get(i).getServiceTime(), a.DS7.get(i).getPrice(), a.DS7.get(i).getDate(),a.DS7.get(i).getMassgerCost()));
            		}
        		}
        	}
     		int priceA = 0;
     		int priceMC = 0;
     		int pricePRO = 0;
     		for(int i = 0 ; i<a.DS7SPECIFIC.size();i++){
     			priceMC = priceMC +Integer.parseInt(a.DS7SPECIFIC.get(i).getMassgerCost());	
     			priceA = priceA +Integer.parseInt(a.DS7SPECIFIC.get(i).getPrice());	
     		}
     		for(int i = 0 ; i<a.DS7SPECIFIC.size();i++){
  	 				al.add(a.DS7SPECIFIC.get(i).getMassagerName());
  	 			
  	    	}
     		pricePRO = priceA - priceMC;
  	    	 lhs.addAll(al);
		     al.clear();
		     al.addAll(lhs);
		     MassagerNameList.addAll(al);
		     MassagerCB.setItems(MassagerNameList);
		     totalPrice11.setText(Integer.toString(priceMC));
     		totalPrice1.setText(Integer.toString(priceA));
     		totalPrice.setText(Integer.toString(pricePRO));
     		TableView.setItems(a.DS7SPECIFIC);
     	 }
     	else if(MonthG !=null &&YearG != null && DayG != null){
     		for(int i = 0 ; i<a.DS7.size();i++){
        		String[] yearAR = a.DS7.get(i).getDate().split("/");
        		if(yearAR[2].equals(YearG)){
        			if(yearAR[1].equals(MonthG)){
        				if(yearAR[0].equals(DayG)){
        					a.DS7SPECIFIC.add(new PaymentDetail(a.DS7.get(i).getPaymentID(), a.DS7.get(i).getCustomerName(), a.DS7.get(i).getMassagerName(), 
        					a.DS7.get(i).getCourseName(), a.DS7.get(i).getServiceTime(), a.DS7.get(i).getPrice(), a.DS7.get(i).getDate(),a.DS7.get(i).getMassgerCost()));
        				}
            		}
        		}
        	}
     		int priceA = 0;
     		int priceMC = 0;
     		int pricePRO = 0;
     		for(int i = 0 ; i<a.DS7SPECIFIC.size();i++){
     			priceMC = priceMC +Integer.parseInt(a.DS7SPECIFIC.get(i).getMassgerCost());	
     			priceA = priceA +Integer.parseInt(a.DS7SPECIFIC.get(i).getPrice());	
     		}
     		for(int i = 0 ; i<a.DS7SPECIFIC.size();i++){
  	 				al.add(a.DS7SPECIFIC.get(i).getMassagerName());
  	 			
  	    	}
     		pricePRO = priceA - priceMC;
  	    	 lhs.addAll(al);
		     al.clear();
		     al.addAll(lhs);
		     MassagerNameList.addAll(al);
		     MassagerCB.setItems(MassagerNameList);
		     totalPrice11.setText(Integer.toString(priceMC));
     		totalPrice1.setText(Integer.toString(priceA));
     		totalPrice.setText(Integer.toString(pricePRO));
     			TableView.setItems(a.DS7SPECIFIC);
     	 }
    }
	public void  filter() {
		a.DS7SPECIFIC1.clear();
		System.out.println("hi");
		List<String> al = new ArrayList<String>();
 		LinkedHashSet<String> lhs = new LinkedHashSet<String>();
		if(MassagerCB.getValue() == null){
		}	
		else {
			int priceA = 0;
	 		int count = 0;
	 		 for(int i = 0 ; i<a.DS7SPECIFIC.size();i++){
					System.out.println(a.DS7SPECIFIC.get(i).getMassagerName());
				
	   	}
	 		for(int i = 0 ; i<a.DS7SPECIFIC.size();i++){
	 			if(MassagerCB.getValue().equals(a.DS7SPECIFIC.get(i).getMassagerName())) {
	 					a.DS7SPECIFIC1.add(new PaymentDetail(a.DS7SPECIFIC.get(i).getPaymentID(), a.DS7SPECIFIC.get(i).getCustomerName(), a.DS7SPECIFIC.get(i).getMassagerName(), 
	        		a.DS7SPECIFIC.get(i).getCourseName(), a.DS7SPECIFIC.get(i).getServiceTime(), a.DS7SPECIFIC.get(i).getPrice(), a.DS7SPECIFIC.get(i).getDate(),a.DS7SPECIFIC.get(i).getMassgerCost()));	 			
	 			}
	 		}
	 		lhs.addAll(al);
		     al.clear();
		     al.addAll(lhs);
	     		int priceMC = 0;
	     		int pricePRO = 0;
	     		for(int i = 0 ; i<a.DS7SPECIFIC1.size();i++){
	     			priceMC = priceMC +Integer.parseInt(a.DS7SPECIFIC1.get(i).getMassgerCost());	
	     			priceA = priceA +Integer.parseInt(a.DS7SPECIFIC1.get(i).getPrice());	
	     		}
	      		pricePRO = priceA - priceMC;
	      		totalPrice11.setText(Integer.toString(priceMC));
	     		totalPrice1.setText(Integer.toString(priceA));
	     		totalPrice.setText(Integer.toString(pricePRO));
	 		TableView.setItems(a.DS7SPECIFIC1);
		}
		
 	 }
	public void  yearGetMonth() {
		a.DS7SPECIFIC.clear();
		TableView.setItems(a.DS7SPECIFIC);
		totalPrice.setText(" ");
		 int itemCount = MonthList.size()  ;
 		 for(int i=0;i<itemCount;i++){
 			Month.getItems().clear();
 	     }
 		 int itemCount11 = MassagerNameList.size();
 		 for(int i=0;i<itemCount11;i++){
 			MassagerCB.getItems().clear();
 	     }
 		 int itemCount111 = DayList.size();
 		 for(int i=0;i<itemCount111;i++){
 			Day.getItems().clear();
 	     }
 		MonthList.clear();
 		DayList.clear();
 		MassagerNameList.clear();
 		List<String> al = new ArrayList<String>();
 		LinkedHashSet<String> lhs = new LinkedHashSet<String>();
 		if(Year.getValue()==null){
 			
			}
 		
 		else if(YearList.size() == 0){
    		
    	}
  	    else {
  	 		for(int i = 0 ; i<a.DS7.size();i++){
  	 			String[] yearAR = a.DS7.get(i).getDate().split("/");
  	 			if(Year.getValue().equals(yearAR[2])){
  	 				al.add(yearAR[1]);
  	 			}
  	    	}
  	    	 lhs.addAll(al);
		     al.clear();
		     al.addAll(lhs);
		     MonthList.addAll(al);
		     Month.setItems(MonthList);
    	}
	 }
	public void  monthGetDay() throws ClassNotFoundException, SQLException {
		a.DS7SPECIFIC.clear();
		totalPrice.setText(" ");
		 int itemCount11 = MassagerNameList.size();
		 for(int i=0;i<itemCount11;i++){
			MassagerCB.getItems().clear();
	     }
		DayList.clear();
		MassagerNameList.clear();
		List<String> al = new ArrayList<String>();
		LinkedHashSet<String> lhs = new LinkedHashSet<String>();
		if(Month.getValue()==null){
 			
		}
		else if(MonthList.size() == 0){
		
		}
	    else {
	    	for(int i = 0 ; i<a.DS7.size();i++){
				String[] yearAR = a.DS7.get(i).getDate().split("/");
				if(Month.getValue().equals(yearAR[1])){
					al.add(yearAR[0]);
				}
			}
   		 	lhs.addAll(al);
		     al.clear();
		     al.addAll(lhs);
		     DayList.addAll(al);
		     Day.setItems(DayList);
	    }
		filtterSort();
	 }	
	public void  GetDay() throws ClassNotFoundException, SQLException {
		a.DS7SPECIFIC.clear();
		totalPrice.setText(" ");
		 int itemCount11 = MassagerNameList.size();
		 for(int i=0;i<itemCount11;i++){
			MassagerCB.getItems().clear();
	     }
		 filtterSort();
	 }	
    @FXML
    public void actionToMenu(ActionEvent event){
        Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        //a=textField.getText();

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
