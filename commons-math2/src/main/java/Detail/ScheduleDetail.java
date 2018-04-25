package Detail;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
public class ScheduleDetail {
	private  BooleanProperty Check;
	private final SimpleStringProperty Day ;
	private final SimpleStringProperty Massager_ID ;
	private final SimpleStringProperty Massager_Name ;
	private final SimpleStringProperty Status ;
	public ScheduleDetail(String Day , String Massager_ID, String Massager_Name,String Status) {
		this.Check = new SimpleBooleanProperty(false);
		this.Day= new SimpleStringProperty(Day);
		this.Massager_ID= new SimpleStringProperty(Massager_ID);
		this.Massager_Name= new SimpleStringProperty(Massager_Name);
		this.Status= new SimpleStringProperty(Status);
	}
	public boolean isCheck() {
        return Check.get();
    }
    public BooleanProperty CheckProperty() {
        return Check;
    }
	public String getDay() {
		return Day.get();
	}
	public String getMassager_ID() {
		return Massager_ID.get();	
	}
	public String getMassager_Name() {
		return Massager_Name.get();	
	}
	public String getStatus() {
		return Status.get();		
	}
}
