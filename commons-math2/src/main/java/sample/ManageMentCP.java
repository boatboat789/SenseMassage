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

import Detail.ManagementCPDetail;

public class ManageMentCP implements Initializable {
	public ConnectDatabase a = new ConnectDatabase();
	@FXML public  ComboBox<String> combobox;
	@FXML public  ComboBox<String> combobox1;
    @FXML  public Button test;
    @FXML  public   TableView<ManagementCPDetail> TableView;
    @FXML  public   TableColumn<ManagementCPDetail,Boolean> Check;
    @FXML  public   TableColumn<ManagementCPDetail,String> CourseName;
    @FXML  public   TableColumn<ManagementCPDetail,String> ProductName;
  //  public static ObservableList<ManagementCPDetail> list = FXCollections.observableArrayList();
    public ArrayList<String> x = new ArrayList<>();
     public ManageMentCP() {
    	 dynamicdate();
	}
    @Override
    public void initialize(URL location, ResourceBundle resources){
    	Check.setCellValueFactory(new PropertyValueFactory<ManagementCPDetail,Boolean>("Check"));
		Check.setCellFactory(CheckBoxTableCell.forTableColumn(Check));
    	CourseName.setCellValueFactory(new PropertyValueFactory<ManagementCPDetail,String>("CourseName"));
    	ProductName.setCellValueFactory(new PropertyValueFactory<ManagementCPDetail,String>("ProductName"));
		TableView.setItems(a.DS10);
		TableView.setEditable(true);
		combobox.setItems(a.courseName);
		combobox1.setItems(a.productName);
	}
    @FXML
    public void actionButtonTest(ActionEvent event) throws ClassNotFoundException, SQLException{
      	String count = null ;
      	 String courseName = combobox.getValue();
         String productName = combobox1.getValue();
    	if (courseName == null){
    		JOptionPane.showMessageDialog(null,"Please Choose courseName");
    	}
    	else if (productName == null){
    		JOptionPane.showMessageDialog(null,"Please Choose Product Name");
    	}
    	else{
	    	int check =0;
	    	int check1 =0;
	    		
	    	 Button b =(Button)event.getSource();
	         String courseID = " "  ;
				for (int x = 0 ; x< a.DS.size();x++){
					if(courseName.equals(a.DS.get(x).getCourse_Name())){
						courseID = a.DS.get(x).getCourse_ID();
					}
				
				}
				String ProductID = " "  ;
				for (int x = 0 ; x< a.DS8.size();x++){
					if(productName.equals(a.DS8.get(x).getProductName())){
						ProductID = a.DS8.get(x).getProductID();
					}
				}
				
				for(int i = 0 ; i<a.DS10CHECKONLY.size(); i++){
	    			String bc =a.DS10CHECKONLY.get(i).getProductName();
					if(bc.equals(productName)){
						check = 1;
						if(a.DS10CHECKONLY.get(i).getCourseName().equals(courseName)){
							check1=1;
						}
    				}

		    		

				}
				System.out.println(productName + "      "+ProductID);
				//Time nameไม่ซ้ำ
				System.out.println(check + "       "+ check1);
				String cpid = Integer.toString(a.DS10.size()+1);
	    		if(check == 0){
	    			a.insertMCP(cpid,courseID, ProductID);
	    			a.getNewSetTableManagementCP();
			        TableView.setItems(a.DS10);
			        JOptionPane.showMessageDialog(null,"Add Product Course Success");
	    		}
	    		//nameซ้ำ timeไม่ซ้ำ
	    		else if (check == 1&& check1==0){
	    		/*	for ( int i = 0; i<TableView.getItems().size(); i++) {
	    			    TableView.getItems().clear();
	    			}*/
	    			 a.DS10.clear();
	    			 a.DS10CHECKONLY.clear();
			        a.insertMCP(cpid,courseID, ProductID);
	    			a.getNewSetTableManagementCP();
	    			TableView.setItems(a.DS10);
	    			JOptionPane.showMessageDialog(null,"Add Detail Success");
	    		}
	    		//time + name ซ้ำ
	    		else if(check == 1&&check1 ==1){
	    			JOptionPane.showMessageDialog(null,"This Course already have this Product.");
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
    	int i = 0;
    	 String productName = combobox1.getValue();
    	 if (productName == null){
     		JOptionPane.showMessageDialog(null,"Please Choose Product Name");
     	}
    	 else {
    		 String CName="";
    		 int count=0;
    		 int nub=0;
    	for (ManagementCPDetail p : TableView.getItems()) {
    		 if(!p.getCourseName().equals("")) {
    			 CName =p.getCourseName();
    			 count=0;
    			 nub=nub+1;
    		 }
    		 else {
    		 }
    		 if(p.isCheck()==true){
    			 String courseID = " "  ;
 				for (int x = 0 ; x< a.DS.size();x++){
 					if(CName.equals(a.DS.get(x).getCourse_Name())){
 						courseID = a.DS.get(x).getCourse_ID();
 					}
 				
 				}
 				String ProductID = " "  ;
 				for (int x = 0 ; x< a.DS8.size();x++){
 					if(productName.equals(a.DS8.get(x).getProductName())){
 						ProductID=a.DS8.get(x).getProductID();
 					}
 				}
 				for (int x = 0 ; x< a.DS10CHECKONLY.size();x++){
 					if(CName.equals(a.DS10CHECKONLY.get(x).getCourseName())){
	 					if(productName.equals(a.DS10CHECKONLY.get(x).getProductName())){
	 						count=10;
	 					}
 					}
 				}
 				if(count==0) {
 					String nub1 = Integer.toString(nub);
 					System.out.println(courseID+"><><><><>"+ ProductID);
	 				a.updateMCP(nub1, ProductID); 
	    			JOptionPane.showMessageDialog(null,"Update Success");
	    			count=count+1;
 				}
 				else if(count==10) {
 					JOptionPane.showMessageDialog(null,"Update Fail");
 				}
    			 }
    				 
         }
    	 a.DS10.clear();
		 a.DS10CHECKONLY.clear();
    	 a.getNewSetTableManagementCP();
		 TableView.setItems(a.DS10);
    	 }
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
