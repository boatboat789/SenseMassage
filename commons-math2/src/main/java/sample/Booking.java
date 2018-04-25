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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import Detail.BookingDetail;
import Detail.CorseDetail;
public class Booking implements Initializable{
    @FXML
    private Button borrow ;
    @FXML
    private Button add ;
    @FXML
    private Button logout ;
	public ConnectDatabase a = new ConnectDatabase();
	@FXML public  ComboBox<String> cusName;
	@FXML public  ComboBox<String> corName;
	public ObservableList<String> CourseNameCB = FXCollections.observableArrayList();
	@FXML public  ComboBox<String> masName;
	public ObservableList<String> MassagerNameCB = FXCollections.observableArrayList();
	@FXML public  ComboBox<String> timeSer;
	@FXML private Label PricesOut;
	@FXML public  ComboBox<String> timeH;
	@FXML public  ComboBox<String> timeM;
	@FXML private TextField priceCB;
	List<String> al = new ArrayList<String>();
	LinkedHashSet<String> lhs = new LinkedHashSet<String>();
	private String PrivateMassager ;
	@FXML public Button test;
	@FXML  public   TableView<BookingDetail> TableView;
	@FXML  public   TableColumn<BookingDetail,Boolean> Check;
	@FXML  public   TableColumn<BookingDetail,String> No;
	@FXML  public   TableColumn<BookingDetail,String> CustomerName;
	@FXML  public   TableColumn<BookingDetail,String> CourseName;
	@FXML public   	TableColumn<BookingDetail,String> TimeService;
	@FXML  public   TableColumn<BookingDetail,String> TimeBegin;
	@FXML  public   TableColumn<BookingDetail,String> TimeEnd;
	@FXML public   TableColumn<BookingDetail,String> MassagerName;
	@FXML public   TableColumn<BookingDetail,String> Status;
	public int Endday = 24 ;
	public int Minute = 60 ;
	  //  public static ObservableList<BookingDetail> list = FXCollections.observableArrayList();
	public ObservableList<String> timeHD = FXCollections.observableArrayList();
	public ObservableList<String> timeMD = FXCollections.observableArrayList();
	public  Booking() {
		dynamicdate();
	}@Override
	public void initialize(URL location, ResourceBundle resources){
		System.out.println("sss");
		Check.setCellValueFactory(new PropertyValueFactory<BookingDetail,Boolean>("Check"));
		Check.setCellFactory(CheckBoxTableCell.forTableColumn(Check));
		No.setCellValueFactory(new PropertyValueFactory<BookingDetail,String>("No"));
		CustomerName.setCellValueFactory(new PropertyValueFactory<BookingDetail,String>("CustomerName"));
		CourseName.setCellValueFactory(new PropertyValueFactory<BookingDetail,String>("CourseName"));
		MassagerName.setCellValueFactory(new PropertyValueFactory<BookingDetail,String>("MassagerName"));
		TimeService.setCellValueFactory(new PropertyValueFactory<BookingDetail,String>("TimeService"));
		TimeBegin.setCellValueFactory(new PropertyValueFactory<BookingDetail,String>("TimeBegin"));
		TimeEnd.setCellValueFactory(new PropertyValueFactory<BookingDetail,String>("TimeEnd"));	
		Status.setCellValueFactory(new PropertyValueFactory<BookingDetail,String>("Status"));
		TableView.setItems(a.DS4);
		cusName.setItems(a.CustomerD);	
		TableView.setEditable(true);
		timeHD.clear();
		setTimeToService();
		timeH.setItems(timeHD);
		timeM.setItems(timeMD);
	}
	public void setTimeToService() {
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("HH");
	    int getTim = Integer.parseInt(sdf.format(cal.getTime()));
	    for(int i = getTim ; i<Endday ;i++){
	    	timeHD.add(Integer.toString(i));
	    }
	    timeMD.clear();
	    for(int i = 0 ; i<Minute ;i++){
	    	if(i<=9){
	    		String sum = "0"+i;
	    		timeMD.add(sum);
	    	}
	    	else{
	    		timeMD.add(Integer.toString(i));
	    	}
	    }
	}
	public void  getTimeHr() {
		int getTim = 0;
		 PricesOut.setText("");
		 int itemCount = MassagerNameCB.size()  ;
		 for(int i=0;i<itemCount;i++){
			masName.getItems().clear();
	     }
		 int itemCount1 = CourseNameCB.size();
		 for(int i=0;i<itemCount1;i++){
			corName.getItems().clear();
	     }
		 int itemCount11 = a.courseTime.size();
		 for(int i=0;i<itemCount11;i++){
			timeSer.getItems().clear();
	     }
		if(timeH.getValue()==null){
			
		}
		else {
			getTim = Integer.parseInt(timeH.getValue());
		}
	    if(timeH.getValue() == null){
	    }
	    else if(cusName.getValue() == null ){
	    }
	    else {
	    	actionCheckTimeMassager(getTim);
	    }   
	 }
	public void  getTimeMin() {

		 String CourseName = corName.getValue();
		 String TimeService = timeSer.getValue();
		 for (CorseDetail c : a.DS){
			 if(c.getCourse_Name().equals(CourseName)&&c.getTime().equals(TimeService)){
				 priceCB.setText(c.getPrice());
			 }
		 }
	 }
    @FXML
    public void actionButtonTest(ActionEvent event) throws ClassNotFoundException, SQLException{
    	String No = null ;
   		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
   		Calendar cal = Calendar.getInstance();
   		String Date = format1.format(cal.getTime());
   		System.out.println("DATE :   "+ Date);
    	if(a.DS4.size()==0){
    		No = "0" ;
    	}
    	else{
    		No = a.DS4.get(a.DS4.size()-1).getNo();
    	}
    	 No = Integer.toString(Integer.parseInt(No)+1);
    	 System.out.println("click");
    	 String CustomerName = cusName.getValue();
    	 String CourseName = corName.getValue();
    	 String MassagerName= masName.getValue();
    	 String timeHour = timeH.getValue();
    	 String timeMinute = timeM.getValue();
    	 String TimeService = timeSer.getValue();
    	 if(CustomerName == null){	 
    		 JOptionPane.showMessageDialog(null,"Please choose Customer Name");
    	 }
    	 else if(timeHour == null){				
    		 JOptionPane.showMessageDialog(null,"Please choose TimeHour");
 		}
     	 else if(timeMinute == null){	
     		 JOptionPane.showMessageDialog(null,"Please choose TimeMinute");
 		}    
    	 else if(MassagerName == null){	
    		 JOptionPane.showMessageDialog(null,"Please choose Massager Name");
		}
    	 else if(CourseName == null){
    		 JOptionPane.showMessageDialog(null,"Please choose Course Name");
		   }
       	 else if(TimeService == null){
       		 JOptionPane.showMessageDialog(null,"Please choose TimeService");		 
 		}
      	 else if(priceCB == null){
       		 JOptionPane.showMessageDialog(null,"Please choose Price");
 		}
       	 else{
       		int check0 =0 ;
       		String CustomerID= null;
       		String CourseID = null;
       		String MassagerID = null;
            for (int i = 0 ; i<a.DS3.size();i++ ){
	        	 if (CustomerName.equals(a.DS3.get(i).getCustomer_Name())){
	        		 CustomerID = a.DS3.get(i).getCustomer_ID();
	        	 }
	         }
            if (CustomerName.equals("Customer")){
            	CustomerID = "Customer";
            }
            for (int i = 0 ; i<a.DS.size();i++ ){
 	        	 if (CourseName.equals(a.DS.get(i).getCourse_Name())){
 	        		CourseID = a.DS.get(i).getCourse_ID();
 	        	 }
 	         }
            for (int i = 0 ; i<a.DS1.size();i++ ){
 	        	 if (MassagerName.equals(a.DS1.get(i).getMassager_Name())){
 	        		MassagerID = a.DS1.get(i).getMassager_ID();
 	        	 }
 	         }
            String TimeBegin = timeHour+":"+timeMinute;
      		 String[] x =TimeService.split(" ");
      		String TimeEnd = null ;
       		int nTM = 0;
   			int nTH = 0 ;
       		if(TimeService.equals("1.30 Hrs.")){
       			nTM = Integer.parseInt(timeMinute)+30;
       			if (nTM == 60){
       				nTM=0;
       				nTH = Integer.parseInt(timeHour)+1;
       				nTH = nTH +1;
       				TimeEnd = Integer.toString(nTH)+":00";
       			}
       			else if(nTM <60){
       				nTH = Integer.parseInt(timeHour)+1;
       				TimeEnd = Integer.toString(nTH)+":"+nTM;
       			}
       			else if(nTM>60){
       				nTM = nTM-60;
       				nTH = Integer.parseInt(timeHour)+1;
       				nTH = nTH +1;
       				TimeEnd = Integer.toString(nTH)+":"+timeMinute;
       			}
       		} 
       		else{
        	  TimeEnd = Integer.toString(Integer.parseInt(timeHour)+Integer.parseInt(x[0]))+":"+timeMinute;
        	  nTH =Integer.parseInt(timeHour)+Integer.parseInt(x[0]);
        	  nTM =Integer.parseInt(timeMinute);
       		}
       		for (int x1 = 0 ; x1< a.DS4.size();x1++){
 				if(MassagerName.equals(a.DS4.get(x1).getMassagerName())){
 					String[] t1 = a.DS4.get(x1).getTimeBegin().split(":");
 					String[] t2 = a.DS4.get(x1).getTimeEnd().split(":");
 					if(Integer.parseInt(timeHour) == Integer.parseInt(t1[0])){
 						check0=1;
 					}
 					if(Integer.parseInt(timeHour) == Integer.parseInt(t2[0])){
 						if(Integer.parseInt(timeMinute)>=Integer.parseInt(t2[1])){
 	
 	  					}
 						else{
 							check0 = 1;
 						}
 					}
 					if(Integer.parseInt(timeHour) > Integer.parseInt(t1[0])&&Integer.parseInt(timeHour) < Integer.parseInt(t2[0])){
 							check0 = 1;
 					}
 					if(nTH > Integer.parseInt(t1[0])&&nTH < Integer.parseInt(t2[0])){
 						check0=1;
 					}
 					if(nTH == Integer.parseInt(t1[0])){
 						if(nTM <= Integer.parseInt(t1[1]) ){
 							
 						}
 						else{
 						check0=1;
 						}
 					}
 					if(nTH == Integer.parseInt(t2[0])){
 						if(nTM <= Integer.parseInt(t2[1]) ){
 							check0=1;
 						}
 						else{
 						check0=1;
 	
 						}
 					}
 				}
       		}	
       		String price = priceCB.getText();
       		if(check0==0){
       			String Status = "Booking";
			     a.insertBooking(No ,  CustomerID, CourseID ,  TimeService, TimeBegin, TimeEnd, Date ,  MassagerID,Status,price);
			     a.getNewSetTableBooking();
			     TableView.setItems(a.DS4);
			     JOptionPane.showMessageDialog(null,"Booking Successs");
       		}
       		else if (check0==1){
       			JOptionPane.showMessageDialog(null,"This time has overlapped with other booking time.");
       		}
       	 }
    }
    @FXML
    public void actionCustomer() {	
    	timeHD.clear();
	    Calendar cal1 = Calendar.getInstance();
	    SimpleDateFormat sdf1 = new SimpleDateFormat("HH");
	    int getTim1 = Integer.parseInt(sdf1.format(cal1.getTime()));
	    for(int i = getTim1 ; i<Endday ;i++){
	    	timeHD.add(Integer.toString(i));
	    }
	    timeMD.clear();
	    for(int i = 0 ; i<Minute ;i++){
	    	if(i<=9){
	    		String sum = "0"+i;
	    		timeMD.add(sum);
	    	}
	    	else{
	    		timeMD.add(Integer.toString(i));
	    	}
	    } 
		timeH.setItems(timeHD);
		timeM.setItems(timeMD);
    	al.clear();
    	lhs.clear();
    	 String CustomerName = cusName.getValue();
    	 int check = 0;
    	Calendar cal = Calendar.getInstance();
  	    SimpleDateFormat sdf = new SimpleDateFormat("HH");
  	    int getTim = Integer.parseInt(sdf.format(cal.getTime()));
  	    PrivateMassager = " ";	
  		PricesOut.setText("");
 		 int itemCount = MassagerNameCB.size()  ;
 		 for(int i=0;i<itemCount;i++){
 			masName.getItems().clear();
 	     }
 		 int itemCount1 = CourseNameCB.size();
 		 for(int i=0;i<itemCount1;i++){
 			corName.getItems().clear();
 	     }
 		 int itemCount11 = a.courseTime.size();
 		 for(int i=0;i<itemCount11;i++){
 			timeSer.getItems().clear();
 	     }
  	    for (int i = 0 ;i < a.DS3.size();i++){
    		if(CustomerName.equals(a.DS3.get(i).getCustomer_Name())){
    			PrivateMassager = a.DS3.get(i).getMassager_Name();
    		}
    	}
  	    if(getTim < 13){
  	    	//add SPECIFIC MASSSGER
  	    for (int i = 0 ;i < a.DS6.size();i++){
  	    	if(check == 1){
	   			}
  	   		else if(PrivateMassager.equals(a.DS6.get(i).getMassager_Name())){
  	   	  	   	check =1;	  	   	
  	   			al.add(PrivateMassager); 
	    	}
  	   	}
  	    	for (int i = 0 ;i < a.DS2CHECKONLY.size();i++){
  	    		if(PrivateMassager.equals(a.DS2CHECKONLY.get(i).getMassager_Name())){
  	    			String[] t1 = a.DS2CHECKONLY.get(i).getDay().split(":");
  	    			int less = Integer.parseInt(t1[0]);
  	    			if(check == 1){	}
  	    			else if(less>=13){
  	    				check = 1;
  	    				al.add(PrivateMassager);
  	    			}
  	    		}
  	    	}
  	  	   //add who work 13:00 - 24:00
  	    	for (int i = 0 ;i < a.DS2CHECKONLY.size();i++){
  	    			String[] t0 = a.DS2CHECKONLY.get(i).getDay().split("-");
  	    			String[] t1 = t0[0].split(":");
  	    			int less = Integer.parseInt(t1[0]);
  	    			if(PrivateMassager.equals(a.DS2CHECKONLY.get(i).getMassager_Name())){
  	    			}
  	    			else if(less >=13 && less< 19){	
  	    				al.add(a.DS2CHECKONLY.get(i).getMassager_Name());
  	    			}		
  	    	}
  	    //add OTHER MASSSGER WHO LOGIN
  	    	for (int i = 0 ;i < a.DS6.size();i++){
	    		if(PrivateMassager.equals(a.DS6.get(i).getMassager_Name())){	}
	    		else {
	    			al.add(a.DS6.get(i).getMassager_Name());
	    		}
	    	}
  	    }
  	    else if(getTim <19){  	
  	    	//add SPECIFIC MASSSGER
  	    	for (int i = 0 ;i < a.DS6.size();i++){
  	    		if(check == 1){	}
  	    		else if(PrivateMassager.equals(a.DS6.get(i).getMassager_Name())){
  	    			check = 1;
  	    			al.add(PrivateMassager); 
  	    		}
  	    	}
  	    //CHECK TIME SPECIFIC MASSSGER
  	    	for (int i = 0 ;i < a.DS2CHECKONLY.size();i++){
  	    		if(PrivateMassager.equals(a.DS2CHECKONLY.get(i).getMassager_Name())){
  	    			String[] t1 = a.DS2CHECKONLY.get(i).getDay().split(":");
  	    			int less = Integer.parseInt(t1[0]);
  	    			if(check == 1){}
  	    			else if(less>=19){
  	    				check = 1;
  	    				al.add(PrivateMassager);
  	    			}
  	    		}
  	    	}
  	    //add who work 19:00 - 24:00
  	    	for (int i = 0 ;i < a.DS2CHECKONLY.size();i++){
  	    			String[] t0 = a.DS2CHECKONLY.get(i).getDay().split("-");
  	    			String[] t1 = t0[0].split(":");
  	    			int less = Integer.parseInt(t1[0]);
  	    			if(PrivateMassager.equals(a.DS2CHECKONLY.get(i).getMassager_Name())){
  	    			}
  	    			else if(less >=19){	
  	    				al.add(a.DS2CHECKONLY.get(i).getMassager_Name());
  	    			}  	    			
  	    	}
  	    //add OTHER MASSSGER WHO LOGIN
  	    	for (int i = 0 ;i < a.DS6.size();i++){ 	    		
	    		if(PrivateMassager.equals(a.DS6.get(i).getMassager_Name())){
	    		}
	    		else {	    			
	    			al.add(a.DS6.get(i).getMassager_Name());
	    		}
	    	}
  	    }
  	    else if (getTim <24){
  	    	//add SPECIFIC MASSSGER
  	    	for (int i = 0 ;i < a.DS6.size();i++){
  	    		if(PrivateMassager.equals(a.DS6.get(i).getMassager_Name())){
  	    			al.add(PrivateMassager);	
  	    		}
  	    	}
  	    //add OTHER MASSSGER WHO LOGIN
  	    	for (int i = 0 ;i < a.DS6.size();i++){
	    		if(PrivateMassager.equals(a.DS6.get(i).getMassager_Name())){
	    		}
	    		else {
	    			al.add(a.DS6.get(i).getMassager_Name());	
	    		}
	    	} 	    	
  	    }
  	    if(corName.getValue() == null){
    		 lhs.addAll(al);
		     al.clear();
		     al.addAll(lhs);
		    MassagerNameCB.addAll(al);
		    a.getNewSetTableBookingWithCustomer(CustomerName); 
	  	    TableView.setItems(a.DS4SPECIFIC);
    	}
  	    else if(CourseNameCB.size() == 0){	
    	}
    }
    public void actionCheckTimeMassager(int getTim) {	
    	PricesOut.setText("");
		 int itemCount1 = CourseNameCB.size();
		 for(int i=0;i<itemCount1;i++){
			corName.getItems().clear();
	     }
		 int itemCount11 = a.courseTime.size();
		 for(int i=0;i<itemCount11;i++){
			timeSer.getItems().clear();
	     }
    	List<String> al = new ArrayList<String>();
    	LinkedHashSet<String> lhs = new LinkedHashSet<String>();
    	ObservableList<String> MassagerNameCB1 = FXCollections.observableArrayList();
    	int check = 0;
    	 //add SPECIFIC MASSSGER
  	   	for (int i = 0 ;i < a.DS6.size();i++){
	  	    if(PrivateMassager.equals(a.DS6.get(i).getMassager_Name())){
	  	    	String[] t0 = a.DS6.get(i).getTimeIn().split(":");
	  	    	int less = Integer.parseInt(t0[0]);
	  	    	if( less <13){
	  	    		less = 13;
	  	    	}
	  	    	else if (less<19){
	  	    		less =19;
	  	    	}
	  	    	else if(less <24){
	  	   			less= 24;
	  	  			}
	   			if(check==1){  	    				}
	  	    	else if(getTim <= less){
	  	    		check=1;
	  	    		al.add(PrivateMassager);
	  	   			}
	  	   		}
  	    	}
  	   		//add specific
  	   		for (int i = 0 ;i < a.DS2CHECKONLY.size();i++){
	 	    	if(PrivateMassager.equals(a.DS2CHECKONLY.get(i).getMassager_Name())){
	  	    		String[] t0 = a.DS2CHECKONLY.get(i).getDay().split("-");
	  	   			String[] t1 = t0[1].split(":");
	  	   			String[] t2 = t0[0].split(":");
	  	   			int less = Integer.parseInt(t1[0]);
	  	   			int less1 = Integer.parseInt(t2[0]);
	  	   			if(check==1){  				}
	  	    		else if(less1 <= getTim &&getTim <= less){
	  	    			check=1;
	  	    			al.add(PrivateMassager);
	  	    		}
	  	    	}
  	    	}
  	    //add who work >get tim
  	    	for (int i = 0 ;i < MassagerNameCB.size();i++){
  	    		for (int i1 = 0 ;i1 < a.DS2CHECKONLY.size();i1++){
	  	    		if(MassagerNameCB.get(i).equals(a.DS2CHECKONLY.get(i1).getMassager_Name())){
	  	    			String[] t0 = a.DS2CHECKONLY.get(i1).getDay().split("-");
	  	    			String[] t1 = t0[1].split(":");
	  	    			String[] t2 = t0[0].split(":");
	  	    			int less = Integer.parseInt(t1[0]);
	  	    			int less1 = Integer.parseInt(t2[0]);
	  	    			if(getTim > less){
	  	    				
	  	    			}
	  	    			else if(less1 <= getTim &&getTim<=less){
	  	    				al.add(MassagerNameCB.get(i));
	  	    			}
	  	    		}
  	    		}
  	    	}
  	    //add who work >get tim
  	    	for (int i = 0 ;i < MassagerNameCB.size();i++){
  	    		for (int i1 = 0 ;i1 < a.DS2CHECKONLY.size();i1++){
	  	    		if(MassagerNameCB.get(i).equals(a.DS2CHECKONLY.get(i1).getMassager_Name())){
	  	    			String[] t0 = a.DS2CHECKONLY.get(i1).getDay().split("-");
	  	    			String[] t1 = t0[1].split(":");
	  	    			String[] t2 = t0[0].split(":");
	  	    			int less = Integer.parseInt(t1[0]);
	  	    			int less1 = Integer.parseInt(t2[0]);
	  	    			if(getTim > less){
	  	    				
	  	    			}
	  	    			else if(less1 <= getTim &&getTim<=less){
	  	    				System.out.println("-------------"+MassagerNameCB.get(i));
	  	    				al.add(MassagerNameCB.get(i));
	  	    			}
	  	    		}
  	    		}
  	    	}
  	    	//add OTHER MASSSGER WHO LOGIN
  	    	for (int i = 0 ;i < a.DS6.size();i++){
  	    		al.add(a.DS6.get(i).getMassager_Name());	
	    	}
  	    	lhs.addAll(al);
  	    	al.clear();
  	    	al.addAll(lhs);
  		    MassagerNameCB.addAll(al);
  		    masName.setItems(MassagerNameCB);	
  	    	MassagerNameCB1.addAll(al);
  	    	masName.setItems(MassagerNameCB1);	
    }
    @FXML
    public void actionCourse() throws ClassNotFoundException, SQLException{	
    	if(corName == null){
    		System.out.println("Pick la");
    	}
    	else if(CourseNameCB.size() == 0){
    		
    	}
    	else{
    		String CID = null ;
    		for(int i = 0 ; i <a.DS.size();i++){
    			if(corName.getValue().equals(a.DS.get(i).getCourse_Name())){
    				 CID = a.DS.get(i).getCourse_ID();
    			}
    		}
    		a.findServiceCourse ( CID);
    		timeSer.setItems(a.courseTime);
		}
    } 
@FXML
    public void actionPay() throws ClassNotFoundException, SQLException{	
    	SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
   		Calendar cal = Calendar.getInstance();
   		String Date = format1.format(cal.getTime());
    	 for (BookingDetail p : TableView.getItems()) {
		 		if(p.isCheck()==true && p.getStatus().equals("CheckIn")){
		 			String Massager_ID = "";
		 			for (int x = 0 ; x< a.DS1.size();x++){
						if(p.getMassagerName().equals(a.DS1.get(x).getMassager_Name())){
							Massager_ID = a.DS1.get(x).getMassager_ID();
						}
					}
		 			String MassagerCost = "";
					String Course_ID = "" ;
					String Price = "";
					for (int x = 0 ; x< a.DS.size();x++){
						if(p.getCourseName().equals(a.DS.get(x).getCourse_Name())){
							Course_ID = a.DS.get(x).getCourse_ID();
							if(p.getTimeService().equals(a.DS.get(x).getTime())){
								Price = a.DS.get(x).getPrice();
							}
						}
					}
					String Customer_ID = "" ;
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
					int No = 0;
					if(a.DS7.size()==0){
						No =1;
					}
					else {
						No =a.DS7.size()+1;
					}
					 if (Customer_ID.equals("")){
						 Customer_ID = "Customer";
			            }
					String PaymentID = Integer.toString(No);
	                  a.deleteBooking(p.getNo()) ;
	                  a.insertPayment(PaymentID,Customer_ID, Massager_ID, Course_ID, p.getTimeService(), Price,Date,MassagerCost);
	                  JOptionPane.showMessageDialog(null,"This service costs   "+ Price+ "  Baths.");
			 		}
		 		else if (p.isCheck()==true && p.getStatus().equals("Booking")){
		 			JOptionPane.showMessageDialog(null,"This service cant pay because Customer did not CheckIn");
		 		}
    	 	}
    	 a.getNewSetTableBooking();
    	 TableView.setItems(a.DS4);
    }
    @FXML
    public void actionButtonCanceled(ActionEvent event) throws ClassNotFoundException, SQLException{
    	 for (BookingDetail p : TableView.getItems()) {
    		 	if(p.isCheck()==true && p.getStatus().equals("Booking")){
	               a.deleteBooking(p.getNo()) ;
	               JOptionPane.showMessageDialog(null,"Remove booking success.");
    			}
    		 	else if (p.isCheck()==true && p.getStatus().equals("CheckIn")){
    		 		JOptionPane.showMessageDialog(null,"This service cant remove because customer already CheckIn.");
    			}
         }
    	 a.getNewSetTableBooking();
    	 TableView.setItems(a.DS4);
    }
    @FXML
    public void actionMassager(ActionEvent event) throws ClassNotFoundException, SQLException{
    	CourseNameCB.clear();
    	if(corName == null){
    		System.out.println("Pick la");
    	}
    	else if(masName.getValue() == null){
    		System.out.println("Pick la");
    	}
    	else{
    		for(int i = 0 ; i <a.DS5CHECKONLY.size();i++){
    			if(masName.getValue() == null){
    			}
    			else if(masName.getValue().equals(a.DS5CHECKONLY.get(i).getMassager_Name())){
    				CourseNameCB.add(a.DS5CHECKONLY.get(i).getCourse_Name());
    			}
    		}
    		corName.setItems(CourseNameCB);
    		a.getNewSetTableBookingWithMassager(masName.getValue()); 
   	  	    TableView.setItems(a.DS4SPECIFIC);
    		}
    }
    @FXML
    public void actionCheck(ActionEvent event) throws ClassNotFoundException, SQLException{
    	for (BookingDetail p : TableView.getItems()) {
    		 		if(p.isCheck()==true){
						p.setStatus();
						a.updateBooking(p.getNo(),p.getStatus()); 
						JOptionPane.showMessageDialog(null,"Customer Status set to CheckIn");
    		 		}
         }
    	 a.getNewSetTableBooking();
    	 TableView.setItems(a.DS4);
    }
    @FXML
    public void actionlogout(ActionEvent event){
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
