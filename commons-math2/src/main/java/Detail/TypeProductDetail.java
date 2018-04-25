package Detail;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
public class TypeProductDetail {
	private  BooleanProperty Check;
	private final SimpleStringProperty TypeProductID ;
	private final SimpleStringProperty TypeProductName ;
	public TypeProductDetail(String TypeProductID , String TypeProductName) {
		this.Check = new SimpleBooleanProperty(false);
		this.TypeProductID= new SimpleStringProperty(TypeProductID);
		this.TypeProductName= new SimpleStringProperty(TypeProductName);
	}
	public boolean isCheck() {
        return Check.get();
    }
    public BooleanProperty CheckProperty() {
        return Check;
    }
	public String getTypeProductID() {
		return TypeProductID.get();
	}
	public String getTypeProductName() {
		return TypeProductName.get();		
	}
}
