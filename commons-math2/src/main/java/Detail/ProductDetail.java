package Detail;
import javafx.beans.property.SimpleStringProperty;
public class ProductDetail {
	private final SimpleStringProperty ProductName ;
	private final SimpleStringProperty ProductID ;
	private final SimpleStringProperty TypeProduct;
	private final SimpleStringProperty DateAddProduct;
	private final SimpleStringProperty Quantity;
	private final SimpleStringProperty Price;
	public ProductDetail(String ProductID , String ProductName,String TypeProduct, String chronoDate,String Quantity,String Price ) {
		this.ProductName= new SimpleStringProperty(ProductName);
		this.ProductID= new SimpleStringProperty(ProductID);
		this.TypeProduct= new SimpleStringProperty(TypeProduct);
		this.DateAddProduct= new SimpleStringProperty(chronoDate);
		this.Quantity= new SimpleStringProperty(Quantity);
		this.Price= new SimpleStringProperty(Price);
	}
	public String getProductName() {
		return ProductName.get();
	}
	public String getPrice() {
		return Price.get();
	}
	public String getProductID() {
		return ProductID.get();
	}
	public String getDateAddProduct() {
		return DateAddProduct.get();
	}
	public String getQuantity() {
		return Quantity.get();
	}
	public String getTypeProduct() {
		return TypeProduct.get();	
	}
}
