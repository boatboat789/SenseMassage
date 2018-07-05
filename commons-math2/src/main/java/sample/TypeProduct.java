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
import Detail.TypeProductDetail;
public class TypeProduct implements Initializable {
	public  TypeProduct() {
		dynamicdate();
	}
	public ConnectDatabase a = new ConnectDatabase();
		@FXML private TextField text1;
	    @FXML public  Button test;
	    @FXML  public   TableView<TypeProductDetail> TableView;
	    @FXML  public   TableColumn<TypeProductDetail,Boolean> Check;
	    @FXML  public   TableColumn<TypeProductDetail,String> TypeProductID;
	    @FXML  public   TableColumn<TypeProductDetail,String> TypeProductName;
	    public ArrayList<String> x = new ArrayList<>();
	    @Override
	    public void initialize(URL location, ResourceBundle resources){
	    	Check.setCellValueFactory(new PropertyValueFactory<TypeProductDetail,Boolean>("Check"));
	    	Check.setCellFactory(CheckBoxTableCell.forTableColumn(Check));
	    	TypeProductID.setCellValueFactory(new PropertyValueFactory<TypeProductDetail,String>("TypeProductID"));
	    	TypeProductName.setCellValueFactory(new PropertyValueFactory<TypeProductDetail,String>("TypeProductName"));
			TableView.setItems(a.DS9);
			TableView.setEditable(true);
		}
	    @FXML
	    public void actionButtonTest(ActionEvent event) throws ClassNotFoundException, SQLException{
	    	int check= 0;
	    	int count = a.DS9.size()+1;
	    	int checkDoM = 0;
	    	String PTName = text1.getText();
	    	if(text1.getText().equals("")){
	    		JOptionPane.showMessageDialog(null,"Please Fill Product type Name");
	    	}
	    	else{
	    		for (int i = 0 ; i<a.DS9.size();i++ ){
	   	        	 if (PTName.equals(a.DS9.get(i).getTypeProductName())){
	   	        		check =1;
	   	        	 }
	   	         }
			if(check == 0){
				checkDoM = checkDigit();

				if(checkDoM>0){
		    		JOptionPane.showMessageDialog(null,"Can't use something like"+'"'+ "or digit on Name");
		    	}
				else{
					a.typeProductName.add(PTName);
	    		 	a.insertTypeProduct(count, PTName);
	    		 	a.getNewSetProduct();
	    		 	TableView.setItems(a.DS9);
	    		 	JOptionPane.showMessageDialog(null,"Add Type Product Name Success");
				}			 
			}
			 else if (check == 1){
				 JOptionPane.showMessageDialog(null,"Product type already have detail.");
			 			 
			 	}
	    	
			 checkDoM = 0;
	    	}
	    }
    @FXML
    private Button add ;
    @FXML
    private Button logout ;
	    public int checkDigit() {
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
		    	    else if(ae[i].equals(".")){
		    	    	 checkDoM = checkDoM+1;
		    	      }
		    	    else if(ae[i].equals("-")){
		    	    	 checkDoM = checkDoM+1;
		    	      }
				}
		     return checkDoM;
	    }
	    @FXML
	    public void actionButtonCanceled(ActionEvent event) throws ClassNotFoundException, SQLException{
	    	int count = 0;
	    	if(text1.getText().equals("")){
	    		JOptionPane.showMessageDialog(null,"Please Fill Product type Name");
	    	}
	    	 for (TypeProductDetail p : TableView.getItems()) {
	    		 		if(p.isCheck()==true ){
	    		 			int checkDom =checkDigit();
	    		 			if(checkDom>0){
	    			    		count=1;
	    			    	}
	    		 			else {
		                     a.updateProductType(p.getTypeProductID(),text1.getText()) ;
		                     count=2;
		                     
	    		 			}
	    		 		}
	         }
    	 if(count == 0) {
    		 JOptionPane.showMessageDialog(null,"Please choose TypeProduct");
    	 
    	 }
    	 else if(count==1) {
    		 JOptionPane.showMessageDialog(null,"Can't use something like"+'"'+ "or digit on Name");
    	 }
    	 else if(count==2) {
    		 JOptionPane.showMessageDialog(null,"Update Success.");
    		 a.getNewSetProduct();
        	 TableView.setItems(a.DS9);
    	 }
    }
    @FXML
    public void actionToMenu(ActionEvent event){
        Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
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
