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
import javafx.scene.control.TableRow;
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

import Detail.CorseDetail;
import Detail.ManagementCPDetail;

public class Course implements Initializable {
    @FXML
    private Button borrow ;
    @FXML
    private Button add ;
    @FXML
    private Button edit ;
    @FXML	
    private Button logout ;
	public ConnectDatabase a = new ConnectDatabase();
	@FXML public  ComboBox<String> combobox;
	@FXML public  ComboBox<String> combobox1;
	@FXML private TextField text1;
	@FXML private TextField text2 ;
	@FXML private TextField text3;
	@FXML public Button test;
	@FXML  public   TableView<CorseDetail> TableView;
    @FXML  public   TableColumn<CorseDetail,Boolean> Check;
	@FXML  public   TableColumn<CorseDetail,String> Course_ID;
	@FXML  public   TableColumn<CorseDetail,String> Course_Name;
	@FXML  public   TableColumn<CorseDetail,String> Time;
	@FXML public   TableColumn<CorseDetail,String> Price;
	@FXML public   TableColumn<CorseDetail,String> MassagerCost;
	  //  public static ObservableList<CorseDetail> list = FXCollections.observableArrayList();
	public ObservableList<String> categoryA = FXCollections.observableArrayList("1 Hr.","1.30 Hrs.","2 Hrs.");
	public Course() {
		dynamicdate();
	}
	  @Override
	public void initialize(URL location, ResourceBundle resources){
		  TableView.setRowFactory(tv -> {
	            TableRow<CorseDetail> row = new TableRow<>();
	            row.setOnMouseClicked(event -> {
	                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
	                	CorseDetail rowData = row.getItem();
	                	
	                    System.out.println("Double click on: "+rowData.getCourse_ID());
	       		    	text1.setText(rowData.getCourse_Name());
	       			    text2.setText(rowData.getPrice());
	       			    combobox.setValue(rowData.getTime());
	       			    text3.setText(rowData.getMassagerCost());
	       	   
	       	    		 
	                }
	            });
	            return row ;
	        });
		Course_ID.setCellValueFactory(new PropertyValueFactory<CorseDetail,String>("Course_ID"));
		Course_Name.setCellValueFactory(new PropertyValueFactory<CorseDetail,String>("Course_Name"));
		Price.setCellValueFactory(new PropertyValueFactory<CorseDetail,String>("Price"));
		Time.setCellValueFactory(new PropertyValueFactory<CorseDetail,String>("Time"));
		MassagerCost.setCellValueFactory(new PropertyValueFactory<CorseDetail,String>("MassagerCost"));
		TableView.setItems(a.DS);
		combobox.setItems(categoryA);
		TableView.setEditable(true);
	}
    @FXML
    public void actionButtonTest(ActionEvent event) throws ClassNotFoundException, SQLException{
    	String count = null ;
    	 String x = combobox.getValue();
    	 int checkDoM1 = 0;
    	 int c12 = 0;
    	 String[] ssss = text2.getText().split("");
    	 if(ssss.length >0){
    		 if(!text2.getText().equals("") )
	    	 for(int i = 0 ; i < ssss.length ;i++){
	   	      text2.getText().charAt(i);
	   	      if(ssss[i].equals(".")){
	   	    	 c12 = c12+1;
	   	      }
    	 }
    	 }
    	 System.out.println("im here -----------------------------------------------" +x);
    	 if(text1.getText().equals("")){
    		 JOptionPane.showMessageDialog(null,"Please Fill Course Name");
    	}
    	else if(text2.getText().equals("")){
    		JOptionPane.showMessageDialog(null,"Please Fill Price");
    	}
     	else if(text3.getText().equals("")){
    		JOptionPane.showMessageDialog(null,"Please Fill CostMassager");
    	}
    	else if (c12 >0){
    		JOptionPane.showMessageDialog(null,"Please Fill Price Digit only");	
    	}
    	else if (x == null){
    		JOptionPane.showMessageDialog(null,"Please Choose TimeServices");
    	}	
    	else{
    		int check =0;
    		int check1 =0;
    		int check2 =0;
    		String Name = text1.getText();
             String Price = text2.getText();
             String ServiceTime = combobox.getValue();
    		for(int i = 0 ; i<a.courseName.size(); i++){
	    		if(Name.equals(a.courseName.get(i))){
	    			count = a.courseID.get(i);
	    			check = 1;
	    			Name = " ";
	    			a.findServiceCourse(count);
	    			for(int i1 = 0 ;i1< a.courseTime.size();i1++){
	    				System.out.println(ServiceTime + "     "+a.courseTime.get(i1));
	    	    		if(ServiceTime.equals(a.courseTime.get(i1))){
	    					check1 = 1 ;
	    				}
	        		}
	    		}
	    		else {
	    			if(check==0){
	    				int c1 = Integer.parseInt(a.courseID.get(a.courseID.size()-1))+1;
	    				count = Integer.toString(c1);
	    				System.out.println(count );
	    			}
	    			
	    		}
    		}
    		if(check==1){
    			for(int i = 0 ; i<a.courseTime.size(); i++){
        			System.out.println(" Course   "+a.courseTime.get(i) +" Price   "+ a.checkPrice.get(i));
        		}
    		}
    		check2= checkTimePrice(combobox.getValue(),Price);
    			
    	
    		// CHECK " ' DIGIT
    		int checkDoM =checkDigit(Name);
			// check price digit
    		 checkDoM1 = checkDigit(Price);
	    	String  MassagerCost = text3.getText();
    		if(checkDoM>0){
	    		JOptionPane.showMessageDialog(null,"Can't use something like"+'"'+ "or digit. ");
	    	}
    		else  if(checkDoM1 >=1){
       			JOptionPane.showMessageDialog(null,"Please Fill Digit Only.");
       		 }	
    		else if(Integer.parseInt(Price) < 150 ){
    			System.out.println("hhhhhhhhhhhhhh");
    			JOptionPane.showMessageDialog(null,"Price should not be less than 150 baht.");
    		}
    		else if(Integer.parseInt(Price) > 2000 ){
    			JOptionPane.showMessageDialog(null,"Price should not be more than 1000 baht.");
    		}
    		else if(check2==1){
    			JOptionPane.showMessageDialog(null,"Check prices that more than or equal or less than to other times. ");	
    		}
    		else if(check == 0){
		        a.courseName.add(Name);
		        a.courseID.add(count);
		        System.out.println("check :"+check  + "count : "+ count);
		        a.insertCourse(count, Name);
		        a.insertCoursePrice(count,ServiceTime,Price,MassagerCost);
		        TableView.setItems(a.DS);
		        JOptionPane.showMessageDialog(null,"Add course Success.");
    		}
    		//nameซ้ำ timeไม่ซ้ำ
    		else if (check == 1&& check1==0){
    			 a.DS.clear();
    			 a.courseName.clear();
    			 a.courseID.clear();
    			 System.out.println(count + "    "+ ServiceTime+ "    "+Price);
		        a.insertCoursePrice(count,ServiceTime,Price,MassagerCost);
    			a.getNewSetTable();
    			TableView.setItems(a.DS);
    			JOptionPane.showMessageDialog(null,"Add service time + price Success.");
    		}
  
    		//time + name ซ้ำ
    		else if(check == 1&&check1 ==1){
    			JOptionPane.showMessageDialog(null,"You already have this servicetime cost");
    			
    		}
    		
    	}
    }
    @FXML
    public void actionButtonCanceled(ActionEvent event) throws ClassNotFoundException, SQLException{
		String Price,MCost="";
		int count =0;
		 String x = combobox.getValue();
    	 int checkDoM1 = 0;
    	 int c12 = 0;
    	 String[] ssss = text2.getText().split("");
    	 if(ssss.length >0){
    		 if(!text2.getText().equals("") )
	    	 for(int i = 0 ; i < ssss.length ;i++){
	   	      text2.getText().charAt(i);
	   	      if(ssss[i].equals(".")){
	   	    	 c12 = c12+1;
	   	      }
    	 }
    	 }
    	 System.out.println("im here -----------------------------------------------" +x);
    	 if(text1.getText().equals("")){
    		 JOptionPane.showMessageDialog(null,"Please Fill Course Name");
    	}
    	else if(text2.getText().equals("")){
    		JOptionPane.showMessageDialog(null,"Please Fill Price");
    	}
     	else if(text3.getText().equals("")){
    		JOptionPane.showMessageDialog(null,"Please Fill CostMassager");
    	}
    	else if (c12 >0){
    		JOptionPane.showMessageDialog(null,"Please Fill Price Digit only");	
    	}
    	else if (x == null){
    		JOptionPane.showMessageDialog(null,"Please Choose TimeServices");
    	}	
    	else{
    			int  check=0,checkDoM3 = 0;
    	    	String CID="";
    	 		int check2=0;
    	 		if(count ==0) {
    		    	 Price =text2.getText();
    		    	 String Name =text1.getText();
    		    	 for (CorseDetail c : a.DS){
    					 if(c.getCourse_Name().equals(Name)){
    						CID = c.getCourse_ID();
    					 }
    				 }
    		    	 System.out.println(CID+"DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
    		    	 a.findServiceCourse(CID);
    		    	 check2 =checkTimePrice(combobox.getValue(),Price);
    		    	 checkDoM1 = checkDigit(text2.getText());
    		    	 MCost =text3.getText();
    		    	 checkDoM3 = checkDigit(text3.getText());
    		    	 System.out.println(check2);
    		    	if(check2==1){
    		    	   	JOptionPane.showMessageDialog(null,"Check prices that more than or equal or less than to other times. ");	
    		    	 }
    		    	else if(CID.equals("")) {
    		    		JOptionPane.showMessageDialog(null,"Please check name ");	
    		    	}
    		    	else {
    		    		check=1;
    		    		a.insertCoursePriceHistory(a.DS11.size()+1,CID, combobox.getValue(),Price, MCost);
    		    		a.updateCoursePrice(CID, combobox.getValue(),Price,MCost);
    		    		 		}  
    	 		}
    	 		 if(check==0) {
 		    		
		    	 }
		    	 else if(checkDoM1 >=1){
		    			JOptionPane.showMessageDialog(null,"Please Fill Digit Only (Price).");
				 }	
		    	 else if(checkDoM3 >=1) {
			 			JOptionPane.showMessageDialog(null,"Please Fill Digit Only(MassagerCost).");
			 			}
		    	 else {
		    		 JOptionPane.showMessageDialog(null,"Update Price Success");
		    	 }	
    	 }     
    	 a.getNewSetTable();
    	 a.getNewSetCourseHistory();
    	 TableView.setItems(a.DS);
       	
    }
	private int checkTimePrice(String STime, String Price) {
    	int check2=0;
   
    	
		if(a.courseTime.size() == 1){
			System.out.println("1.00hereeeeee1");
			if(STime.equals("1 Hr.")){
				 if("1.30 Hrs.".equals(a.courseTime.get(0))){
    				if(Integer.parseInt(Price) >= Integer.parseInt(a.checkPrice.get(0))){
    					check2=1;
    				}
				}
				
				else if("2 Hrs.".equals(a.courseTime.get(0))){
    				if(Integer.parseInt(Price) >= Integer.parseInt(a.checkPrice.get(0))){
    					check2=1;
    				}
				}
			}
			else if(STime.equals("1.30 Hrs.")){
				if("1 Hr.".equals(a.courseTime.get(0))){
    				if(Integer.parseInt(Price) <= Integer.parseInt(a.checkPrice.get(0))){
    					check2=1;
    				}
				}
				else if("2 Hrs.".equals(a.courseTime.get(0))){
					if(Integer.parseInt(Price) >= Integer.parseInt(a.checkPrice.get(0))){
    					check2=1;
    				}
				}
				
			}
			else if(STime.equals("2 Hrs.")){
				if("1 Hr.".equals(a.courseTime.get(0))){
    				if(Integer.parseInt(Price) <= Integer.parseInt(a.checkPrice.get(0))){
    					check2=1;
    				}
				}
				else if("1.30 Hrs.".equals(a.courseTime.get(0))){
    				if(Integer.parseInt(Price) <= Integer.parseInt(a.checkPrice.get(0))){
    					check2=1;
    				}
				}
				
			}
				
		}
		else if(a.courseTime.size() == 2){
			System.out.println("2");
			if(STime.equals("1 Hr.")){
				System.out.println("21");
				if("1 Hr.".equals(a.courseTime.get(0))&&"1.30 Hrs.".equals(a.courseTime.get(1))){
    				if(Integer.parseInt(Price) >= Integer.parseInt(a.checkPrice.get(1))){
    					check2=1;
    				}
				}
				else if("1 Hr.".equals(a.courseTime.get(0))&&"2 Hrs.".equals(a.courseTime.get(1))){
    				if(Integer.parseInt(Price) >= Integer.parseInt(a.checkPrice.get(1))){
    					check2=1;
    				}
				}
				else if("1.30 Hrs.".equals(a.courseTime.get(0))&&"2 Hrs.".equals(a.courseTime.get(1))){
    				if(Integer.parseInt(Price) >= Integer.parseInt(a.checkPrice.get(0))){
    					check2=1;
    				}
    				if(Integer.parseInt(Price) >= Integer.parseInt(a.checkPrice.get(1))){
    					check2=1;
    				}
				}
			}
			else if(STime.equals("1.30 Hrs.")){
				if("1 Hr.".equals(a.courseTime.get(0))&&"1.30 Hrs.".equals(a.courseTime.get(1))){
    				if(Integer.parseInt(Price) <= Integer.parseInt(a.checkPrice.get(0))){
    					check2=1;
    				}
				}
				else if("1 Hr.".equals(a.courseTime.get(0))&&"2 Hrs.".equals(a.courseTime.get(1))){
    				if(Integer.parseInt(Price) <= Integer.parseInt(a.checkPrice.get(0))){
    					check2=1;
    				}
    				if(Integer.parseInt(Price) >= Integer.parseInt(a.checkPrice.get(1))){
    					check2=1;
    				}
				}
				else if("1.30 Hrs.".equals(a.courseTime.get(0))&&"2 Hrs.".equals(a.courseTime.get(1))){
    				if(Integer.parseInt(Price) >= Integer.parseInt(a.checkPrice.get(1))){
    					check2=1;
    				}
				}
			}
			else if(STime.equals("2 Hrs.")){
				if("1 Hr.".equals(a.courseTime.get(0))&&"1.30 Hrs.".equals(a.courseTime.get(1))){
					System.out.println(Price +"    "+ a.checkPrice.get(0)+"    "+  a.checkPrice.get(1));
    				if(Integer.parseInt(Price) <= Integer.parseInt(a.checkPrice.get(0))){
    					check2=1;
    				}
    				if(Integer.parseInt(Price) <= Integer.parseInt(a.checkPrice.get(1))){
    					check2=1;
    				}
				}
				else if("1 Hr.".equals(a.courseTime.get(0))&&"2 Hrs.".equals(a.courseTime.get(1))){
    				if(Integer.parseInt(Price) <= Integer.parseInt(a.checkPrice.get(0))){
    					check2=1;
    				}
				}
				else if("1.30 Hrs.".equals(a.courseTime.get(0))&&"2 Hrs.".equals(a.courseTime.get(1))){
    				if(Integer.parseInt(Price) <= Integer.parseInt(a.checkPrice.get(0))){
    					check2=1;
    				}
				}
				
			}
		}
		else if(a.courseTime.size() == 3){
			if(STime.equals(a.courseTime.get(0))){
				System.out.println("hereeeeee1");
				if(Integer.parseInt(Price) >= Integer.parseInt(a.checkPrice.get(1))){
					check2=1;
				}
				if(Integer.parseInt(Price) >= Integer.parseInt(a.checkPrice.get(2))){
					check2=1;
				}
				
			}
			else if(STime.equals(a.courseTime.get(1))){
				System.out.println("hereeeeee2");
				if(Integer.parseInt(Price) <= Integer.parseInt(a.checkPrice.get(0))){
					check2=1;
				}
				if(Integer.parseInt(Price) >= Integer.parseInt(a.checkPrice.get(2))){
					check2=1;
				}
				
			}
			else if(STime.equals(a.courseTime.get(2))){
				System.out.println("hereeeeee3");
				if(Integer.parseInt(Price) <= Integer.parseInt(a.checkPrice.get(0))){
					check2=1;
				}
				if(Integer.parseInt(Price) <= Integer.parseInt(a.checkPrice.get(1))){
					check2=1;
				}
			}
				
		}
		return check2;
	}
	
    public int checkDigit(String price) {
		 int checkDoM1 = 0;
		  String[] sp = price.split("");
	    	 if(sp.length>0){
	    		 String[] ae1 = price.split("");
	    		 for(int i = 0 ; i < ae1.length ;i++){
		    	      price.charAt(i);
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
		    	      char c = price.charAt(i);
		    	      if(Character.isAlphabetic(c)){
		    	    	  checkDoM1 = 10000;
		    	       }
		    	      
		    	 }
	    		 }
	    	 }

		 return checkDoM1;
	 }
    @FXML
    public void actionToMenu(ActionEvent event){
    	a.DS.clear();
    	a.nub= 0;
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
    public void actionToHistory(ActionEvent event){
        Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        //a=textField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui_courseReport.fxml"));
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
   	 Timeline timeline = new Timeline( new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
   			  @Override 
   			  public void handle(ActionEvent actionEvent) {
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
