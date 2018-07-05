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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.VBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollBar;
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
import Detail.MassagerCourseDetail;
import Detail.SkillDetail;
import javafx.scene.control.ScrollPane;
public class Skill implements Initializable {
	public ConnectDatabase a = new ConnectDatabase();
	@FXML public  ComboBox<String> combobox;
    @FXML  public Button test;
    List<CheckBox> checkboxes = new ArrayList<CheckBox>();
    List<CheckBox> checkboxes2 = new ArrayList<CheckBox>();
    @FXML  public VBox listOfCheck = new VBox();
    @FXML  public VBox listOfCheck2 = new VBox();
    @FXML  public ScrollPane testscroll ,testscroll2 ;
    @FXML  public ScrollBar listScroll = new ScrollBar();
    @FXML  public   TableView<SkillDetail> TableView;
    @FXML  public   TableColumn<SkillDetail,String> Massager_Name;
    @FXML  public   TableColumn<SkillDetail,String> Course_Name;
    ArrayList<MassagerCourseDetail> getMCSkill = new ArrayList<>();
    public Skill(){
    	dynamicdate();	
    	listScroll.setValue(100);
    }	
    @Override
    public void initialize(URL location, ResourceBundle resources){
    	Massager_Name.setCellValueFactory(new PropertyValueFactory<SkillDetail,String>("Massager_Name"));
    	Course_Name.setCellValueFactory(new PropertyValueFactory<SkillDetail,String>("Course_Name"));
		TableView.setItems(a.DS5);
     	for(int i = 0 ;i<a.MassagerD.size() ;i++) {
     		CheckBox ch1 = new CheckBox(a.MassagerD.get(i));
     		checkboxes2.add(ch1);
     		
     	}
     	listOfCheck2.getChildren().addAll(checkboxes2);
     	testscroll2= new ScrollPane(listOfCheck2);
     	for(int i = 0 ;i<a.courseName.size() ;i++) {
     		CheckBox ch1 = new CheckBox(a.courseName.get(i));	
     		checkboxes.add(ch1);
     		
     	}
     	listOfCheck.getChildren().addAll(checkboxes);
 		testscroll= new ScrollPane(listOfCheck);
 		
	}
    @FXML
    public void actionButtonTest(ActionEvent event) throws ClassNotFoundException, SQLException{
      	getMCSkill.clear();
         int nub = 0;
         for(int i = 0 ;i<checkboxes.size() ;i++) {
        	 if(checkboxes.get(i).isSelected()) {
        		 nub = 1;
        	 };
      	}
         int nub2 = 0;
         for(int i = 0 ;i<checkboxes2.size() ;i++) {
        	 if(checkboxes2.get(i).isSelected()) {
        		 nub2 = 1;
        	 };
      	}
    	if (nub2 == 0){
      		 JOptionPane.showMessageDialog(null,"Please Choose Massager");
      	}
    	else if (nub == 0){
    		 JOptionPane.showMessageDialog(null,"Please Choose Course");
    	}

    	else{
			for (int x = 0 ; x< checkboxes2.size();x++){
				 String Massager_ID = ""  ;
				 String MassagerName ="";
				 String Course_ID = "" ;
				 String CourseName = "" ;
				 if(checkboxes2.get(x).isSelected()) {		 
					 for(int i = 0; i<a.DS1.size();i++) {
						 if(a.MassagerD.get(x).equals(a.DS1.get(i).getMassager_Name())) {
							Massager_ID = a.DS1.get(i).getMassager_ID();
							MassagerName = a.MassagerD.get(x);
						 }
					}	
						 for (int x1 = 0 ; x1< checkboxes.size();x1++){
							 int checkDuplicate=0;
							 if(checkboxes.get(x1).isSelected()) {	
								 for(int i = 0; i<a.DS.size();i++) {
									 if(a.courseName.get(x1).equals(a.DS.get(i).getCourse_Name())) {
										 Course_ID = a.DS.get(i).getCourse_ID();
										 CourseName = a.DS.get(i).getCourse_Name();
									 }
								 }
								 for(int i1 = 0; i1<a.DS5CHECKONLY.size();i1++) {	
									 if(MassagerName.equals(a.DS5CHECKONLY.get(i1).getMassager_Name())) {
										 if(CourseName.equals(a.DS5CHECKONLY.get(i1).getCourse_Name())) {
											 checkDuplicate=1;
										 }
									 }
								 }
								 if(checkDuplicate==0) {
									 getMCSkill.add(new MassagerCourseDetail(Massager_ID,MassagerName,Course_ID,CourseName));
								 }
							}	
						}
					}	
				}
				if(getMCSkill.size()>0) {
	    			 for(int i = 0 ;i<getMCSkill.size() ;i++) {
	    				 a.insertSkill(getMCSkill.get(i).getCourse_Id(),getMCSkill.get(i).getMassager_Id());
	    			 }
	    			 a.getNewSetTableSkill();
	    			 TableView.setItems(a.DS5);
	    			 JOptionPane.showMessageDialog(null,"Add Skill Success");
				}
				else {
					JOptionPane.showMessageDialog(null,"Massager already have this skill from your lists");
				}	
    	}
    }
    @FXML
    private Button borrow ;
    @FXML
    private Button add ;
    @FXML
    private Button logout ;
	public void  filter() {
		a.DS5SPECIFIC.clear();
		List<String> al = new ArrayList<String>();
 		LinkedHashSet<String> lhs = new LinkedHashSet<String>();
		if(combobox.getValue() == null){
		}	
		else {
			int count = 0;
	 		for(int i = 0 ; i<a.DS5CHECKONLY.size();i++){
	 			if(combobox.getValue().equals(a.DS5CHECKONLY.get(i).getMassager_Name()))
	 				if(count == 0){
	 					count =1 ;
	 					a.DS5SPECIFIC.add(new SkillDetail(a.DS5CHECKONLY.get(i).getMassager_Name(), a.DS5CHECKONLY.get(i).getCourse_Name()));
	 				}
	 				else if(count == 1){
	 					a.DS5SPECIFIC.add(new SkillDetail("", a.DS5CHECKONLY.get(i).getCourse_Name()));
	 				}
	 		}	
	 		lhs.addAll(al);
		     al.clear();
		     al.addAll(lhs);

	 		TableView.setItems(a.DS5SPECIFIC);
		}
 	 }
	 
    @FXML
    public void actionToMenu(ActionEvent event){
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
    private Label labell,laeeee ;
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
