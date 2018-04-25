package Detail;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
public class ProductPaymentDetail {
	private  BooleanProperty Check;
	private final SimpleStringProperty PaymentID ;
	private final SimpleStringProperty MassagerName ;
	private final SimpleStringProperty CourseName ;
	private final SimpleStringProperty Time ;
	private final SimpleStringProperty ProductName ;
	private final SimpleStringProperty Quantity ;
	private final SimpleStringProperty DateTime ;
	public ProductPaymentDetail(String PaymentID ,String MassagerName,String CourseName , String Time, String ProductName, String Quantity,String DateTime) {
		this.Check = new SimpleBooleanProperty(false);
		this.PaymentID= new SimpleStringProperty(PaymentID);
		this.MassagerName= new SimpleStringProperty(MassagerName);
		this.CourseName= new SimpleStringProperty(CourseName);
		this.Time= new SimpleStringProperty(Time);
		this.ProductName= new SimpleStringProperty(ProductName);
		this.DateTime= new SimpleStringProperty(DateTime);
		this.Quantity= new SimpleStringProperty(Quantity);
	}
	public boolean isCheck() {
        return Check.get();
    }
    public BooleanProperty CheckProperty() {
        return Check;
    }
	public String getPaymentID() {
		return PaymentID.get();
	}
	public String getMassagerName() {
		return MassagerName.get();
		
	}
	public String getCourseName() {
		return CourseName.get();
	}
	public String getTime() {
		return Time.get();		
	}
	public String getProductName() {
		return ProductName.get();	
	}
	public String getDateTime() {
		return DateTime.get();	
	}
	public String getQuantity() {
		return Quantity.get();		
	}	
}
