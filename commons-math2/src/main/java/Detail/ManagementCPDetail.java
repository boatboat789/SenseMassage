package Detail;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
public class ManagementCPDetail {
	private  BooleanProperty Check;
	private final String CPID;
	private final SimpleStringProperty CourseName ;
	private final SimpleStringProperty ProductName;
	public ManagementCPDetail(String CourseName , String ProductName, String CPID) {
		this.Check = new SimpleBooleanProperty(false);
		this.CourseName= new SimpleStringProperty(CourseName);
		this.ProductName= new SimpleStringProperty(ProductName);
		this.CPID = CPID;
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
	public String getCPID() {
		return this.CPID;
	}
}
