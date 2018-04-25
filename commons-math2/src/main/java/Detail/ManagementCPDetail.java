package Detail;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
public class ManagementCPDetail {
	private  BooleanProperty Check;
	private final SimpleStringProperty CourseName ;
	private final SimpleStringProperty ProductName;
	public ManagementCPDetail(String CourseName , String ProductName) {
		this.Check = new SimpleBooleanProperty(false);
		this.CourseName= new SimpleStringProperty(CourseName);
		this.ProductName= new SimpleStringProperty(ProductName);
	}
	public boolean isCheck() {
        return Check.get();
    }
    public BooleanProperty CheckProperty() {
        return Check;
    }
	public String getCourseName() {
		return CourseName.get();
	}
	public String getProductName() {
		return ProductName.get();	
	}
}
