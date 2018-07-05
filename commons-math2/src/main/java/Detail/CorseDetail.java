package Detail;
import javafx.beans.property.SimpleStringProperty;
public class CorseDetail {
	private final SimpleStringProperty Course_ID ;
	private final SimpleStringProperty Course_Name ;
	private final SimpleStringProperty Time;
	private final SimpleStringProperty Price;
	private final SimpleStringProperty MassagerCost;
	public CorseDetail(String Course_ID , String Course_Name,String Time,String Price,String MassagerCost) {
		this.Course_ID= new SimpleStringProperty(Course_ID);
		this.Course_Name= new SimpleStringProperty(Course_Name);
		this.Time= new SimpleStringProperty(Time);
		this.Price= new SimpleStringProperty(Price);
		this.MassagerCost= new SimpleStringProperty(MassagerCost);
	}
	public String getCourse_ID() {
		return Course_ID.get();
	}
	public String getCourse_Name() {
		return Course_Name.get();	
	}
	public String getTime() {
		return Time.get();
	}
	public String getPrice() {
		return Price.get();
	}
	public String getMassagerCost() {
		return MassagerCost.get();
	}
}