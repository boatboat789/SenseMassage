package Detail;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
public class BookingDetail {
	private  BooleanProperty Check;
	private final SimpleStringProperty No ;
	private final SimpleStringProperty CustomerName ;
	private final SimpleStringProperty CourseName;
	private final SimpleStringProperty TimeService;
	private final SimpleStringProperty TimeBegin;
	private final SimpleStringProperty TimeEnd;
	private final SimpleStringProperty MassagerName;
	private SimpleStringProperty Status;
	private final SimpleStringProperty Price;
	public BookingDetail(String No , String CustomerName,String CourseName ,String MassagerName, String TimeService,String TimeBegin,String TimeEnd ,String Status, String price) {	
		this.Check = new SimpleBooleanProperty(false);
		this.No= new SimpleStringProperty(No);
		this.CustomerName= new SimpleStringProperty(CustomerName);
		this.CourseName= new SimpleStringProperty(CourseName);
		this.TimeService= new SimpleStringProperty(TimeService);
		this.TimeBegin= new SimpleStringProperty(TimeBegin);
		this.TimeEnd= new SimpleStringProperty(TimeEnd);
		this.MassagerName= new SimpleStringProperty(MassagerName);
		this.Status= new SimpleStringProperty(Status);
		this.Price= new SimpleStringProperty(price);
	}
	public boolean isCheck() {
        return Check.get();
    }
    public BooleanProperty CheckProperty() {
        return Check;
    }
	public String getNo() {
		return No.get();
	}
	public String getTimeEnd() {
		return TimeEnd.get();
	}
	public String getCustomerName() {
		return CustomerName.get();
	}
	public String getTimeService() {
		return TimeService.get();
	}
	public String getTimeBegin() {
		return TimeBegin.get();
	}
	public String getCourseName() {
		return CourseName.get();
	}
	public String getMassagerName() {
		return MassagerName.get();
	}
	public String getStatus() {
		return Status.get();
	}
	public String getPrice() {
		return Price.get();
	}
	public void setStatus() {
		this.Status = new SimpleStringProperty("CheckIn");	
	}
	public void setStatusCancled() {
		this.Status = new SimpleStringProperty("Cancled");	
	}
}
