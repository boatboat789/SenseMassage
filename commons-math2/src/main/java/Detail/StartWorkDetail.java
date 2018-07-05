package Detail;
import javafx.beans.property.SimpleStringProperty;
public class StartWorkDetail {
	private final SimpleStringProperty Massager_ID ;
	private final SimpleStringProperty Massager_Name ;
	private final SimpleStringProperty TimeIn;
	private final SimpleStringProperty Status;
	private final SimpleStringProperty TimeOut;
	private final SimpleStringProperty Date;
	public StartWorkDetail(String Massager_ID , String Massager_Name,String TimeIn,String TimeOut ,String Date,String Status) {
		this.Massager_ID= new SimpleStringProperty(Massager_ID);
		this.Massager_Name= new SimpleStringProperty(Massager_Name);
		this.TimeIn= new SimpleStringProperty(TimeIn);
		this.TimeOut= new SimpleStringProperty(TimeOut);
		this.Date= new SimpleStringProperty(Date);
		this.Status= new SimpleStringProperty(Status);
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
	public String getTimeOut() {
		return TimeOut.get();
	}
	public String getDate() {
		return Date.get();
	}
	public String getStatus() {
		return Status.get();
	}
}
