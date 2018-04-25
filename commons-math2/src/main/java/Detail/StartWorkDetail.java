package Detail;
import javafx.beans.property.SimpleStringProperty;
public class StartWorkDetail {
	private final SimpleStringProperty Massager_ID ;
	private final SimpleStringProperty Massager_Name ;
	private final SimpleStringProperty TimeIn;
	public StartWorkDetail(String Massager_ID , String Massager_Name,String TimeIn ) {
		this.Massager_ID= new SimpleStringProperty(Massager_ID);
		this.Massager_Name= new SimpleStringProperty(Massager_Name);
		this.TimeIn= new SimpleStringProperty(TimeIn);
	}
	public String getMassager_ID() {
		return Massager_ID.get();
	}
	public String getMassager_Name() {
		return Massager_Name.get();
	}
	public String getTimeIn() {
		return TimeIn.get();
	}
}
