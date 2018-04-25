package Detail;
import javafx.beans.property.SimpleStringProperty;
public class SkillDetail {
	private final SimpleStringProperty Massager_Name ;
	private final SimpleStringProperty Course_Name ;
	public SkillDetail(String Massager_Name, String Course_Name ) {
		this.Massager_Name= new SimpleStringProperty(Massager_Name);
		this.Course_Name= new SimpleStringProperty(Course_Name);
	}
	public String getMassager_Name() {
		return Massager_Name.get();	
	}
	public String getCourse_Name() {
		return Course_Name.get();	
	}
}
