package Detail;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
public class MassagerDetail {
	private  BooleanProperty Check;
	private final SimpleStringProperty Massager_ID ;
	private final SimpleStringProperty Massager_Name ;
	private final SimpleStringProperty Phone ;
	private final SimpleStringProperty Status ;
	public MassagerDetail(String Massager_ID , String Massager_Name,String Phone,String Status) {
		this.Check = new SimpleBooleanProperty(false);
		this.Massager_ID= new SimpleStringProperty(Massager_ID);
		this.Massager_Name= new SimpleStringProperty(Massager_Name);
		this.Phone= new SimpleStringProperty(Phone);
		this.Status= new SimpleStringProperty(Status);
	}
	public boolean isCheck() {
        return Check.get();
    }
    public BooleanProperty CheckProperty() {
        return Check;
    }
	public String getMassager_ID() {
		return Massager_ID.get();
	}
	public String getMassager_Name() {
		return Massager_Name.get();	
	}
	public String getPhone() {
		return Phone.get();	
	}
	public String getStatus() {
		return Status.get();	
	}
}