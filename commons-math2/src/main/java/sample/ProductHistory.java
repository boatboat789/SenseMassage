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
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.ConnectDatabase;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import Detail.ProductDetail;
import Detail.ProductHistoryDetail;
public class ProductHistory implements Initializable {
    @FXML private Button add ;
    @FXML	 private Button logout ;
	public ConnectDatabase a = new ConnectDatabase();
	@FXML public Button test;
	@FXML  public   TableView<ProductHistoryDetail> TableView;
    @FXML  public   TableColumn<ProductHistoryDetail,String> HPID;
	@FXML  public   TableColumn<ProductHistoryDetail,String> ProductName;
	@FXML public   TableColumn<ProductHistoryDetail,String> Quantity;
	@FXML public   	TableColumn<ProductHistoryDetail,String> Price;
	@FXML  public   TableColumn<ProductHistoryDetail,String> Detail;
	@FXML  public   TableColumn<ProductHistoryDetail,String> Date;
	public ProductHistory() {	dynamicdate();}
	  @Override
	public void initialize(URL location, ResourceBundle resources){
		HPID.setCellValueFactory(new PropertyValueFactory<ProductHistoryDetail,String>("HPID"));
		ProductName.setCellValueFactory(new PropertyValueFactory<ProductHistoryDetail,String>("ProductName"));
		Quantity.setCellValueFactory(new PropertyValueFactory<ProductHistoryDetail,String>("Quantity"));
		Price.setCellValueFactory(new PropertyValueFactory<ProductHistoryDetail,String>("Price"));
		Detail.setCellValueFactory(new PropertyValueFactory<ProductHistoryDetail,String>("Detail"));
		Date.setCellValueFactory(new PropertyValueFactory<ProductHistoryDetail,String>("Date"));
		TableView.setItems(a.DS13);
	}
    @FXML
    public void actionToMenu(ActionEvent event){
        Button b =(Button)event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Product.fxml"));
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
