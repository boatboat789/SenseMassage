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
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Detail.CorseDetail;
import Detail.ProductDetail;

public class Product implements Initializable {
    @FXML
    private Button add ;
    @FXML	
    private Button logout ;
	public ConnectDatabase a = new ConnectDatabase();
	@FXML public  ComboBox<String> combobox;
	@FXML public  ComboBox<String> combobox1;
	@FXML private TextField text1;
	@FXML private TextField text2;
	@FXML private TextField text3;
	@FXML public Button test;
	@FXML private  DatePicker datePicker = new DatePicker();
	@FXML  public   TableView<ProductDetail> TableView;
    @FXML  public   TableColumn<ProductDetail,String> ProductID;
	@FXML  public   TableColumn<ProductDetail,String> ProductName;
	@FXML  public   TableColumn<ProductDetail,String> TypeProduct;
	@FXML  public   TableColumn<ProductDetail,String> DateAddProduct;
	@FXML public   TableColumn<ProductDetail,String> Quantity;
	@FXML  public   TableColumn<ProductDetail,String> Price;
	  //  public static ObservableList<CorseDetail> list = FXCollections.observableArrayList();
	public ObservableList<String> categoryA = FXCollections.observableArrayList("1 Hr.","1.30 Hrs.","2 Hrs.");
	public Product() {
		dynamicdate();
	}
	  @Override
	public void initialize(URL location, ResourceBundle resources){
		  TableView.setRowFactory(tv -> {
	            TableRow<ProductDetail> row = new TableRow<>();
	            row.setOnMouseClicked(event -> {
	                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
	                	ProductDetail rowData = row.getItem();
	       		    	text1.setText(rowData.getProductName());
	       			    text2.setText(rowData.getPrice());
	       			    combobox.setValue(rowData.getTypeProduct());
	       			    text3.setText("0");
	
	                }
	            });
	            return row ;
	        });
		  datePicker.setValue(LocalDate.now());
		ProductID.setCellValueFactory(new PropertyValueFactory<ProductDetail,String>("ProductID"));
		ProductName.setCellValueFactory(new PropertyValueFactory<ProductDetail,String>("ProductName"));
		TypeProduct.setCellValueFactory(new PropertyValueFactory<ProductDetail,String>("TypeProduct"));
		DateAddProduct.setCellValueFactory(new PropertyValueFactory<ProductDetail,String>("DateAddProduct"));
		Quantity.setCellValueFactory(new PropertyValueFactory<ProductDetail,String>("Quantity"));
		Price.setCellValueFactory(new PropertyValueFactory<ProductDetail,String>("Price"));
		TableView.setItems(a.DS8);
		combobox.setItems(a.typeProductName);
	}
    @FXML
    public void actionButtonTest(ActionEvent event) throws ClassNotFoundException, SQLException{
    	 String pName = text1.getText();
    	 String price = text2.getText();
    	 String quantity = text3.getText();
    	 String cbTypePro = combobox.getValue();
    	 int checkDoM1 = 0;
    	 int c12 = 0;
    	 LocalDate isoDate = datePicker.getValue();
    	 ChronoLocalDate chronoDate =
    	     ((isoDate != null) ? datePicker.getChronology().date(isoDate) : null);
    	 System.out.println(chronoDate);
    	 String[] ssss = text2.getText().split("");
    	 if(ssss.length >0){
    		 if(!text2.getText().equals("") )
	    	 for(int i = 0 ; i < ssss.length ;i++){
	   	      char c = text2.getText().charAt(i);
	   	      if(ssss[i].equals(".")){
	   	    	 c12 = c12+1;
	   	      }
    	 }
    	 }

    	 if(pName.equals("")){
    		 JOptionPane.showMessageDialog(null,"Please Fill Course Name");
    	}
    	else if(price.equals("")){
    		JOptionPane.showMessageDialog(null,"Please Fill Price");
    	}
     	else if(quantity.equals("")){
    		JOptionPane.showMessageDialog(null,"Please Fill CostMassager");
    	}
    	else if (c12 >0){
    		JOptionPane.showMessageDialog(null,"Please Fill Price Digit only");	
    	}
    	else if (cbTypePro == null){
    		JOptionPane.showMessageDialog(null,"Please Choose Producttype");
    	}
    	else if (chronoDate == null){
    		JOptionPane.showMessageDialog(null,"Please Choose TimeServices");
    	}
    	else{
    		int check =0;

          // CHECK " ' DIGIT
     		int checkDoM = 0;
 			String[] ae = text2.getText().split("");
 			for(int i = 0 ; i < ae.length ;i++){
 		    	      char c = text2.getText().charAt(i);
 		    	      if(Character.isAlphabetic(c)){
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
 		    	 }
 			String[] a0 = text3.getText().split("");
 			for(int i = 0 ; i < a0.length ;i++){
 		    	      char c = text3.getText().charAt(i);
 		    	      if(Character.isAlphabetic(c)){
 		    	    	 checkDoM = checkDoM+1;
 		    	    	System.out.println(checkDoM+"oh1");
 		    	       }
 		    	      else if(a0[i].equals("\"")){
 		    	    	 checkDoM = checkDoM+1;
 		    	    	System.out.println(checkDoM+"oh2");
 		    	      }
 		    	     else if(a0[i].equals("'")){
 		    	    	 checkDoM = checkDoM+1;
 		    	    	System.out.println(checkDoM+"oh3");
 		    	      }
 		    	     else if(a0[i].equals(".")){
 		    	    	 checkDoM = checkDoM+1;
 		    	    	System.out.println(checkDoM+"oh3");
 		    	      }
 		    	 }
 		   String TypeProductID = " "  ;
			for (int x = 0 ; x< a.DS9.size();x++){
				if(cbTypePro.equals(a.DS9.get(x).getTypeProductName())){
					TypeProductID = a.DS9.get(x).getTypeProductID();
				}
			
			}
    		if(checkDoM>0){
    			JOptionPane.showMessageDialog(null,"Please Fill Digit Only.");
	    	}
    		else if(check == 0){
    			int pid = a.DS8.size() +1;
    		
    			String date = ""+chronoDate+"";
    			 a.insertProduct(Integer.toString(pid), pName, TypeProductID, date, quantity, price);
    			 a.getNewSetProduct();
		         TableView.setItems(a.DS8);
		        JOptionPane.showMessageDialog(null,"Add course Success");
    		}
    		
    	}
    }

    @FXML
    public void actionButtonCanceled(ActionEvent event) throws ClassNotFoundException, SQLException{
    	 String pName = text1.getText();
    	 String price = text2.getText();
    	 String quantity = text3.getText();
    	 String cbTypePro = combobox.getValue();
    	 int c12 = 0;
    	 LocalDate isoDate = datePicker.getValue();
    	 ChronoLocalDate chronoDate =
    	     ((isoDate != null) ? datePicker.getChronology().date(isoDate) : null);
    	 System.out.println(chronoDate);
    	 String[] ssss = text2.getText().split("");
    	 if(ssss.length >0){
    		 if(!text2.getText().equals("") )
	    	 for(int i = 0 ; i < ssss.length ;i++){
	   	      char c = text2.getText().charAt(i);
	   	      if(ssss[i].equals(".")){
	   	    	 c12 = c12+1;
	   	      }
    	 }
    	 }

    	 if(pName.equals("")){
    		 JOptionPane.showMessageDialog(null,"Please Fill Course Name");
    	}
    	else if(price.equals("")){
    		JOptionPane.showMessageDialog(null,"Please Fill Price");
    	}
     	else if(quantity.equals("")){
    		JOptionPane.showMessageDialog(null,"Please Fill CostMassager");
    	}
    	else if (c12 >0){
    		JOptionPane.showMessageDialog(null,"Please Fill Price Digit only");	
    	}
    	else if (cbTypePro == null){
    		JOptionPane.showMessageDialog(null,"Please Choose Producttype");
    	}
    	else if (chronoDate == null){
    		JOptionPane.showMessageDialog(null,"Please Choose TimeServices");
    	}
    	else{
    		int check =0;

          // CHECK " ' DIGIT
     		int checkDoM = 0;
 			String[] ae = text2.getText().split("");
 			for(int i = 0 ; i < ae.length ;i++){
 		    	      char c = text2.getText().charAt(i);
 		    	      if(Character.isAlphabetic(c)){
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
 		    	 }
 			String[] a0 = text3.getText().split("");
 			for(int i = 0 ; i < a0.length ;i++){
 		    	      char c = text3.getText().charAt(i);
 		    	      if(Character.isAlphabetic(c)){
 		    	    	 checkDoM = checkDoM+1;
 		    	    	System.out.println(checkDoM+"oh1");
 		    	       }
 		    	      else if(a0[i].equals("\"")){
 		    	    	 checkDoM = checkDoM+1;
 		    	    	System.out.println(checkDoM+"oh2");
 		    	      }
 		    	     else if(a0[i].equals("'")){
 		    	    	 checkDoM = checkDoM+1;
 		    	    	System.out.println(checkDoM+"oh3");
 		    	      }
 		    	     else if(a0[i].equals(".")){
 		    	    	 checkDoM = checkDoM+1;
 		    	    	System.out.println(checkDoM+"oh3");
 		    	      }
 		    	 }
 		   String TypeProductID = " "  ;
			for (int x = 0 ; x< a.DS9.size();x++){
				if(cbTypePro.equals(a.DS9.get(x).getTypeProductName())){
					TypeProductID = a.DS9.get(x).getTypeProductID();
				}
			
			}
    		if(checkDoM>0){
    			JOptionPane.showMessageDialog(null,"Please Fill Digit Only.");
	    	}
    		else if(check == 0){
    			String pid = "";
    			int realQ = 0;
    			for(int i = 0 ;i<a.DS8.size();i++) {
    				if(a.DS8.get(i).getProductName().equals(pName)) {
    					pid =  a.DS8.get(i).getProductID();
    					 realQ = Integer.parseInt(quantity) + Integer.parseInt(a.DS8.get(i).getQuantity());
    				}
    			}
    			if(!pid.equals("")) {
    				String date = ""+chronoDate+"";
    				System.out.println(pid+"   xxx  "+TypeProductID+"   xxx  "+date+"   xxx  "+Integer.toString(realQ)+"   xxx  "+price);
	    			 a.updateProduct(pid,  Integer.toString(realQ), date,TypeProductID, price);
	    			 a.getNewSetProduct();
			         TableView.setItems(a.DS8);
			        JOptionPane.showMessageDialog(null,"Add course Success");
    			}
    			else {
    				 JOptionPane.showMessageDialog(null,"Check your Product name.");
    			}
    		}
    		
    	}
    }

    @FXML
    public void actionToMenu(ActionEvent event){
    	a.DS.clear();
    	a.nub= 0;
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
