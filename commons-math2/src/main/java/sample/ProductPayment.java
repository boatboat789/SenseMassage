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
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Detail.BookingDetail;
import Detail.ProductPaymentDetail;
import Detail.ProductPaymentDetail;



public class ProductPayment implements Initializable{
    @FXML
    private Label labell ,label ;
    @FXML
    private Button borrow ;
    @FXML
    private Button add ;
    @FXML public  ComboBox<String> cusName;
    @FXML
    private Button logout ;
    @FXML private TextField text,text2;
	public ConnectDatabase a = new ConnectDatabase();
	@FXML public Button test;
	@FXML  public   TableView<ProductPaymentDetail> TableView;
	@FXML  public   TableColumn<ProductPaymentDetail,Boolean> Check;
	@FXML  public   TableColumn<ProductPaymentDetail,String> DateTime;
	@FXML  public   TableColumn<ProductPaymentDetail,String> MassagerName;
	@FXML  public   TableColumn<ProductPaymentDetail,String> CourseName;
	@FXML public   	TableColumn<ProductPaymentDetail,String> Time;
	@FXML  public   TableColumn<ProductPaymentDetail,String> ProductName;
	@FXML  public   TableColumn<ProductPaymentDetail,String> Quantity;
	int l = 0;
	  //  public static ObservableList<ProductPaymentDetail> list = FXCollections.observableArrayList();
	public  ProductPayment() {
		dynamicdate();
	}@Override
	public void initialize(URL location, ResourceBundle resources){
		Check.setCellValueFactory(new PropertyValueFactory<ProductPaymentDetail,Boolean>("Check"));
		Check.setCellFactory(CheckBoxTableCell.forTableColumn(Check));
		DateTime.setCellValueFactory(new PropertyValueFactory<ProductPaymentDetail,String>("DateTime"));
		MassagerName.setCellValueFactory(new PropertyValueFactory<ProductPaymentDetail,String>("MassagerName"));
		CourseName.setCellValueFactory(new PropertyValueFactory<ProductPaymentDetail,String>("CourseName"));
		Time.setCellValueFactory(new PropertyValueFactory<ProductPaymentDetail,String>("Time"));
		MassagerName.setCellValueFactory(new PropertyValueFactory<ProductPaymentDetail,String>("MassagerName"));
		ProductName.setCellValueFactory(new PropertyValueFactory<ProductPaymentDetail,String>("ProductName"));
		Quantity.setCellValueFactory(new PropertyValueFactory<ProductPaymentDetail,String>("Quantity"));
		TableView.setItems(a.DS12);
		TableView.setEditable(true);
	}
	public void actionCustomer() throws ClassNotFoundException, SQLException {
		
	}
	 public void add(){	
		 int i = Integer.parseInt(label.getText());
		 label.setText(Integer.toString(i+1));
	 }
	 public void substract(){	
		 int i = Integer.parseInt(label.getText());
		 if(i==0) {
			 
		 }
		 else {
			 label.setText(Integer.toString(i-1));
		 }
	 	
	 }
    @FXML
    public void actionButtonTest() throws ClassNotFoundException, SQLException{	
    	 String i =label.getText();
    	 int count = 0;
		 for (ProductPaymentDetail p : TableView.getItems()) {
		 		if(p.isCheck()==true && p.getQuantity().equals("0")){
		 			count =count + 1;
		 		}
		 }
		 if(count == 1) {
			 int o = 0;
			 for (ProductPaymentDetail p : TableView.getItems()) {
			 		if(p.isCheck()==true && p.getQuantity().equals("0")){
			 			String ProductID="";
			 			String PaymentIDx = "";
			 			int value =0;
			 			for(int i1 = 0 ; i1<a.DS8.size();i1++) {
							if(a.DS8.get(i1).getProductName().equals(p.getProductName())){
								System.out.println("dxxxxxxxxxxxxxdsd");
								ProductID = a.DS8.get(i1).getProductID();
								value = Integer.parseInt(a.DS8.get(i1).getQuantity())-Integer.parseInt(i);
							}
						}
			 			for(int i1 = 0 ; i1<a.DS12CHECKONLY.size();i1++) {
			 				System.out.println(a.DS12CHECKONLY.get(i1).getDateTime() + "  "+p.getDateTime());
							if(a.DS12CHECKONLY.get(i1).getDateTime().equals(p.getDateTime())){
							
								PaymentIDx = a.DS12CHECKONLY.get(i1).getPaymentID();
							}	
						}
			 			System.out.println(value+"  "+ProductID);
			 			a.updateProductPayment(PaymentIDx,ProductID, i);			
						a.updateProduct(ProductID,Integer.toString(value));
						 JOptionPane.showMessageDialog(null,"Change Done");
			 		}
			 }

			 a.getNewSetProductPayment();
			 TableView.setItems(a.DS12);
		 }
		 else {
			 JOptionPane.showMessageDialog(null,"Please choose only one or quantity already changed");
		 }
    }
    @FXML
    public void actionButtonCLEAR(ActionEvent event) throws ClassNotFoundException, SQLException{
     for (int i = 0 ; i<a.DS12CHECKONLY.size();i++) {
    	 a.updateProductPayment(a.DS12CHECKONLY.get(i).getPaymentID(),"Done");
     }
     a.getNewSetProductPayment();
	 TableView.setItems(a.DS12);
    }
    @FXML
    public void actionToMenu(ActionEvent event){
        Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        //a=textField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui_Payment.fxml"));
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
