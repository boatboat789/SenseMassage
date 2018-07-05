package Detail;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
public class ProductPaymentDetail {
	private  BooleanProperty Check1;
	private  String ProductName ;
	private String Quantity ;
	public ProductPaymentDetail(String ProductName ,String Quantity) {
		this.Check1 = new SimpleBooleanProperty(false);
		this.ProductName= ProductName;
		this.Quantity= Quantity;
	}
	public boolean isCheck1() {
        return Check1.get();
    }
    public BooleanProperty Check1Property() {
        return Check1;
    }
	public String getProductName() {
		return ProductName;	
	}
	public String getQuantity() {
		return Quantity;		
	}	
	public void setQuantity(String Quantity) {
		this.Quantity = Quantity;
	}	
}
