package Detail;

import javafx.beans.property.SimpleStringProperty;
public class CoursePriceHistory {
	private final SimpleStringProperty HistoryID ;
	private final SimpleStringProperty Course_Name ;
	private final SimpleStringProperty Time;
	private final SimpleStringProperty Price;
	private final SimpleStringProperty MassagerCost;
	public CoursePriceHistory(String HistoryID, String Course_Name,String Time,String Price,String MassagerCost) {
		this.HistoryID= new SimpleStringProperty(HistoryID);
		this.Course_Name= new SimpleStringProperty(Course_Name);
		this.Time= new SimpleStringProperty(Time);
		this.Price= new SimpleStringProperty(Price);
		this.MassagerCost= new SimpleStringProperty(MassagerCost);
	}
	public String getHistoryID() {
		return HistoryID.get();
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