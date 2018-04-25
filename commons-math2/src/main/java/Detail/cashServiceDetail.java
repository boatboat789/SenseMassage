package Detail;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
public class cashServiceDetail {
	private  BooleanProperty Check;
	private final SimpleStringProperty PaymentID ;
	private final SimpleStringProperty CustomerName ;
	private final SimpleStringProperty MassagerName ;
	private final SimpleStringProperty CourseName ;
	private final SimpleStringProperty ServiceTime ;
	private final SimpleStringProperty Price ;
	private final SimpleStringProperty Date ;
	public cashServiceDetail(String PaymentID , String CustomerName, String MassagerName,String CourseName , String ServiceTime, String Price, String Date) {
		this.Check = new SimpleBooleanProperty(false);
		this.PaymentID= new SimpleStringProperty(PaymentID);
		this.CustomerName= new SimpleStringProperty(CustomerName);
		this.MassagerName= new SimpleStringProperty(MassagerName);
		this.CourseName= new SimpleStringProperty(CourseName);
		this.ServiceTime= new SimpleStringProperty(ServiceTime);
		this.Price= new SimpleStringProperty(Price);
		this.Date= new SimpleStringProperty(Date);
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
	public String getCustomerName() {
		return CustomerName.get();
		
	}
	public String getMassagerName() {
		return MassagerName.get();
	}
	public String getCourseName() {
		return CourseName.get();
	}
	public String getServiceTime() {
		return ServiceTime.get();
	}
	public String getPrice() {
		return Price.get();	
	}
	public String getDate() {
		return Date.get();	
	}
}
