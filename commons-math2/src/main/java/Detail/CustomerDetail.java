package Detail;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class CustomerDetail {
	private  BooleanProperty Check;
	private final SimpleStringProperty Customer_ID ;
	private final SimpleStringProperty Customer_Name ;
	private final SimpleStringProperty Massager_Name ;
	private final SimpleStringProperty Phone ;
	private final SimpleStringProperty Details ;
	public CustomerDetail(String Customer_ID , String Customer_Name,String Massager_Name,String Phone,String Details) {
		this.Check = new SimpleBooleanProperty(false);
		this.Customer_ID= new SimpleStringProperty(Customer_ID);
		this.Customer_Name= new SimpleStringProperty(Customer_Name);
		this.Massager_Name= new SimpleStringProperty(Massager_Name);
		this.Phone= new SimpleStringProperty(Phone);
		this.Details= new SimpleStringProperty(Details);
	}
	public boolean isCheck() {
        return Check.get();
    }
    public BooleanProperty CheckProperty() {
        return Check;
    }
	public String getCustomer_ID() {
		return Customer_ID.get();
	}
	public String getCustomer_Name() {
		return Customer_Name.get();	
	}
	public String getMassager_Name() {
		return Massager_Name.get();	
	}
	public String getPhone() {
		return Phone.get();	
	}
	public String getDetails() {
		return Details.get();	
	}
}