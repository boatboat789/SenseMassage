package Detail;
import javafx.beans.property.SimpleStringProperty;
public class ProductHistoryDetail {
	private final SimpleStringProperty ProductName ;
	private final SimpleStringProperty HPID ;
	private final SimpleStringProperty Date;
	private final SimpleStringProperty Quantity;
	private final SimpleStringProperty Detail;
	private final SimpleStringProperty Price;
	public ProductHistoryDetail(String HPID, String ProductName, String Date, String Quantity,
			String Detail ,String Price) {
		this.ProductName= new SimpleStringProperty(ProductName);
		this.HPID= new SimpleStringProperty(HPID);
		this.Date= new SimpleStringProperty(Date);
		this.Quantity= new SimpleStringProperty(Quantity);
		this.Detail= new SimpleStringProperty(Detail);
		this.Price= new SimpleStringProperty(Price);
	}
	public String getProductName() {
		return ProductName.get();
	}
	public String getPrice() {
		return Price.get();
	}
	public String getDetail() {
		return Detail.get();
	}
	public String getHPID() {
		return HPID.get();
	}
	public String getDate() {
		return Date.get();
	}
	public String getQuantity() {
		return Quantity.get();
	}
}
