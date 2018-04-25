package sample;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Detail.BookingDetail;
import Detail.CorseDetail;
import Detail.CoursePriceHistory;
import Detail.CustomerDetail;
import Detail.ManagementCPDetail;
import Detail.MassagerDetail;
import Detail.PaymentDetail;
import Detail.ProductDetail;
import Detail.ProductPaymentDetail;
import Detail.ScheduleDetail;
import Detail.SkillDetail;
import Detail.StartWorkDetail;
import Detail.TypeProductDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class ConnectDatabase {
	public static int nub = 0;
	public ArrayList<String> checkPrice = new ArrayList<>();
	public ArrayList<String> courseID = new ArrayList<>();
	public ArrayList<String> sortTime = new ArrayList<>();
	public ArrayList<String> sortHr = new ArrayList<>();
	public ObservableList<String> typeProductName = FXCollections.observableArrayList();
	public ObservableList<String> courseName = FXCollections.observableArrayList();
	public ObservableList<String> courseTime = FXCollections.observableArrayList();
	public ObservableList<String> courseProduct = FXCollections.observableArrayList();
	public ObservableList<String> courseMassagerCost = FXCollections.observableArrayList();
	public ObservableList<String> day = FXCollections.observableArrayList();
	public ObservableList<String> checkMC = FXCollections.observableArrayList();
	public ObservableList<String> MassagerD = FXCollections.observableArrayList();
	public ObservableList<String> MassagerForBook = FXCollections.observableArrayList();
	public ObservableList<String> CustomerD = FXCollections.observableArrayList();
	public ObservableList<CorseDetail> DS = FXCollections.observableArrayList();
	public ObservableList<CorseDetail> DSCHECKONLY = FXCollections.observableArrayList();
	public ObservableList<MassagerDetail> DS1 = FXCollections.observableArrayList();
	public ObservableList<ScheduleDetail> DS2 = FXCollections.observableArrayList();
	public ObservableList<ScheduleDetail> DS2CHECKONLY = FXCollections.observableArrayList();
	public ObservableList<CustomerDetail> DS3 = FXCollections.observableArrayList();
	public ObservableList<BookingDetail> DS4 = FXCollections.observableArrayList();
	public ObservableList<BookingDetail> DS4CheckIn = FXCollections.observableArrayList();
	public ObservableList<BookingDetail> DS4SPECIFIC = FXCollections.observableArrayList();
	public ObservableList<SkillDetail> DS5 = FXCollections.observableArrayList();
	public ObservableList<SkillDetail> DS5CHECKONLY = FXCollections.observableArrayList();
	public ObservableList<SkillDetail> DS5SPECIFIC = FXCollections.observableArrayList();
	public ObservableList<StartWorkDetail> DS6 = FXCollections.observableArrayList();
	public ObservableList<String> MassagerCIn = FXCollections.observableArrayList();
	public ObservableList<PaymentDetail> DS7 = FXCollections.observableArrayList();
	public ObservableList<PaymentDetail> DS7SPECIFIC = FXCollections.observableArrayList();
	public ObservableList<PaymentDetail> DS7SPECIFIC1 = FXCollections.observableArrayList();
	public ObservableList<ProductDetail> DS8 = FXCollections.observableArrayList();
	public ObservableList<TypeProductDetail> DS9 = FXCollections.observableArrayList();
	public ObservableList<ManagementCPDetail> DS10 = FXCollections.observableArrayList();
	public ObservableList<ManagementCPDetail> DS10CHECKONLY = FXCollections.observableArrayList();
	public ObservableList<CoursePriceHistory> DS11 = FXCollections.observableArrayList();
	public ObservableList<ProductPaymentDetail> DS12 = FXCollections.observableArrayList();
	public ObservableList<ProductPaymentDetail> DS12CHECKONLY = FXCollections.observableArrayList();
	public int countPayment =0 ;
	public ObservableList<String> productName= FXCollections.observableArrayList();
	public ConnectDatabase() {
		getTimeWork();
		getNewSetTable();
		getNewSetTableMassager();
		getNewSetTableSchedule();
		getNewSetTableCustomer();
		getNewSetTableBooking();
		getNewSetTableSkill();
		getNewSetTableStartWorking();
		getNewSetPayment();
		getNewSetProduct();
		getNewSetTableManagementCP();
		getNewSetCourseHistory();
		getNewSetProductPayment();
	}
	public void getTimeWork() {
		sortHr.clear();
		sortHr.add("1 Hr.");
		sortHr.add("1.30 Hrs.");
		sortHr.add("2 Hrs.");
		sortTime.clear();
		sortTime.add("9:00-13:00");
		sortTime.add("13:00-19:00");
		sortTime.add("19:00-24:00");
	}
	public void getNewSetPayment(){
		DS7.clear();
		try {
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:Course.db";
			Connection conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				String query = "Select * from Payment";
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
						String  PaymentID = resultSet.getString(1);
						String Customer_ID = resultSet.getString(2);
						String Massager_ID = resultSet.getString(3);
						String Course_ID = resultSet.getString(4);
						String TimeService = resultSet.getString(5);
						String Price = resultSet.getString(6);
						String Date = resultSet.getString(7);
						String MassagerCost = resultSet.getString(8);
						String Massager_Name = "";
						for (int i = 0 ; i<DS1.size();i++ ){
			   	        	 if (Massager_ID.equals(DS1.get(i).getMassager_ID())){
			   	        		Massager_Name = DS1.get(i).getMassager_Name();
			   	        	 }
			   	         }
						String Course_Name = "" ;
						for (int x = 0 ; x< DS.size();x++){
							if(Course_ID.equals(DS.get(x).getCourse_ID())){
								Course_Name = DS.get(x).getCourse_Name();
							}
						}
						String Customer_Name = "" ;
						for (int x = 0 ; x< DS3.size();x++){
							if(Customer_ID.equals(DS3.get(x).getCustomer_ID())){
								Customer_Name = DS3.get(x).getCustomer_Name();
							}
						}
						DS7.add(new PaymentDetail(PaymentID,Customer_Name, Massager_Name, Course_Name,TimeService, Price,Date,MassagerCost));
					
				}
				// close connection
				conn.close();
			}
		} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	public void getNewSetProduct(){
		productName.clear();
		typeProductName.clear();
		
		try {
			DS9.clear();
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:Course.db";
			Connection conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				String query = "Select * from TypeProduct";
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
						String TypeProductID = resultSet.getString(1);
						String TypeProductName = resultSet.getString(2);
						DS9.add(new TypeProductDetail(TypeProductID,TypeProductName));
						typeProductName.add(TypeProductName);
				}
				// close connection
				conn.close();
			}
		} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		try {
			DS8.clear();
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:Course.db";
			Connection conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				String query = "Select * from Product";
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
						String  ProductID = resultSet.getString(1);
						String ProductName = resultSet.getString(2);
						String TypeProductID = resultSet.getString(3);
						String DateAddProduct = resultSet.getString(4);
						String Quantity = resultSet.getString(5);
						String Price = resultSet.getString(6);
						String TypeProduct = "";
						for (int i = 0 ; i<DS9.size();i++ ){
			   	        	 if (TypeProductID.equals(DS9.get(i).getTypeProductID())){
			   	        		TypeProduct = DS9.get(i).getTypeProductName();
			   	        	 }
			   	         }
						productName.add(ProductName);
						DS8.add(new ProductDetail(ProductID,ProductName, TypeProduct, DateAddProduct,Quantity, Price));
					
				}
				// close connection
				conn.close();
			}
		} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	public void getNewSetCourseHistory(){
		DS11.clear();
		try {
			Class.forName("org.sqlite.JDBC");
			String dbURL1 = "jdbc:sqlite:Course.db";
			Connection conn = DriverManager.getConnection(dbURL1);
			if (conn != null) {
				String queryPrice = "Select * from Course_PriceHistory";
				Statement statement = conn.createStatement();
					ResultSet resultSet = statement.executeQuery(queryPrice);
					while (resultSet.next()){
						String HID = resultSet.getString(1); ;
						String CID = resultSet.getString(2); ;
						String CourseName = ""  ;
						for (int x = 0 ; x< DS.size();x++){
							if(CID.equals(DS.get(x).getCourse_ID())){
								CourseName = DS.get(x).getCourse_Name();
							}
						}
						String ServiceTime = resultSet.getString(3);	
						String Price = resultSet.getString(4);
						String MassagerCost = resultSet.getString(5);
						DS11.add(new CoursePriceHistory(HID,CourseName, ServiceTime, Price,MassagerCost));
						
						}
					}
				// close connection
				conn.close();
			}
		 catch (ClassNotFoundException ex) {
				ex.printStackTrace();
		} catch (SQLException ex) {
		ex.printStackTrace();
		}
	}
	public void getNewSetTable(){
		DS.clear();
		int lastNumber = 0;
		try {
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:Course.db";
			Connection conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				String query = "Select * from Course";
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					String id = resultSet.getString(1);
					String name = resultSet.getString(2);
					courseID.add(id);
					courseName.add(name);
					lastNumber = Integer.parseInt(id);
				}
				String dbURL1 = "jdbc:sqlite:Course.db";
				conn = DriverManager.getConnection(dbURL1);
				if (conn != null) {
					String queryPrice = "Select * from Course_Price";
					statement = conn.createStatement();
					int check = 0;
					for(int i = 0 ; i<lastNumber ;i++){
						resultSet = statement.executeQuery(queryPrice);
						while (resultSet.next()){
							if(resultSet.getString(1).equals(Integer.toString(i+1))){
								String ServiceTime = resultSet.getString(2); ;
								String Price = resultSet.getString(3);	
								String MassagerCost = resultSet.getString(4);
								if(check==0) {
									check=1;
									DSCHECKONLY.add(new CorseDetail(courseID.get(i), courseName.get(i),ServiceTime,Price,MassagerCost));
								}
								else {
									DSCHECKONLY.add(new CorseDetail("","",ServiceTime,Price,MassagerCost));
								}
									DS.add(new CorseDetail(courseID.get(i), courseName.get(i),ServiceTime,Price,MassagerCost));
							}
						}
						check= 0;
					}
				}
					conn.close();
				}
			}
			 catch (ClassNotFoundException ex) {
					ex.printStackTrace();
			} catch (SQLException ex) {
			ex.printStackTrace();
			}
	}
	public void getNewSetTableMassager(){
		int c =0;
		DS1.clear();
		try {
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:Course.db";
			Connection conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				String query = "Select * from Massager";
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					String id = resultSet.getString(1);
					String name = resultSet.getString(2);
					String Phone = resultSet.getString(3);
					String Status = resultSet.getString(4);;
					c = Integer.parseInt(id);
					DS1.add(new MassagerDetail(id, name,Phone,Status));
					
					if(Status.equals("Active")) {
						MassagerD.add(name);
						MassagerForBook.add(name);
					}
				}
				conn.close();
			}
		} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	public void getNewSetTableSchedule(){
		day.add("9:00-13:00");
		day.add("13:00-19:00");
		day.add("19:00-24:00");
		try {
			Class.forName("org.sqlite.JDBC");
			String dbURL1 = "jdbc:sqlite:Course.db";
			Connection conn = DriverManager.getConnection(dbURL1);
			if (conn != null) {
				String queryPrice = "Select * from Massager_WorkingDay";
				Statement statement = conn.createStatement();
				int check = 0;
				for(int i = 0 ; i<DS1.size() ;i++){
					ResultSet resultSet = statement.executeQuery(queryPrice);
					while (resultSet.next()){
						if(resultSet.getString(2).equals(DS1.get(i).getMassager_ID())){			
							String Day = resultSet.getString(1); ;
							String Massager_ID = resultSet.getString(2);
							String Massager_Name = ""  ;
							String Status = resultSet.getString(3);
							for (int x = 0 ; x< DS1.size();x++){
								if(Massager_ID.equals(DS1.get(x).getMassager_ID())){
									Massager_Name = DS1.get(x).getMassager_Name();
								}
							}
							int fillter = checkStatus(Massager_Name);
							if(check == 0&&fillter==1 ){
								check = 1 ;
								if(resultSet.getString(3).equals("Using")) {
									DS2.add(new ScheduleDetail(Day,Massager_ID, Massager_Name,Status));
								}
								DS2CHECKONLY.add(new ScheduleDetail(Day,Massager_ID, Massager_Name,Status));
							}
							else if(check==1&&fillter==1){
								if(resultSet.getString(3).equals("Using")) {
									DS2.add(new ScheduleDetail(Day ,"", "",Status));
								}
								DS2CHECKONLY.add(new ScheduleDetail(Day,Massager_ID, Massager_Name,Status));
							}					
						}					
					}
					check= 0;
				}
				conn.close();
			}
		} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
		} catch (SQLException ex) {
		ex.printStackTrace();
		}
	}
	public int checkStatus(String name) {
		int check1 = 0;
		for(int i = 0 ;i<DS1.size() ; i++) {
			if(DS1.get(i).getMassager_Name().equals(name)&&DS1.get(i).getStatus().equals("Active")){
				check1=1;
			}
		}
		return check1;
	}
	public void getNewSetTableManagementCP(){
		try {
			DS10.clear();
			DS10CHECKONLY.clear();
			Class.forName("org.sqlite.JDBC");
			String dbURL1 = "jdbc:sqlite:Course.db";
			Connection conn = DriverManager.getConnection(dbURL1);
			if (conn != null) {
				String queryPrice = "Select * from Course_Product";
				Statement statement = conn.createStatement();
				int check = 0;
				for(int i = 0 ; i<DSCHECKONLY.size() ;i++){
					ResultSet resultSet = statement.executeQuery(queryPrice);
					while (resultSet.next()){
						if(resultSet.getString(2).equals(DSCHECKONLY.get(i).getCourse_ID())){	
							String CourseID = resultSet.getString(2); ;
							String ProductID = resultSet.getString(3);
							String CourseName = ""  ;
							for (int x = 0 ; x< DSCHECKONLY.size();x++){
								if(CourseID.equals(DSCHECKONLY.get(x).getCourse_ID())){
									CourseName = DSCHECKONLY.get(x).getCourse_Name();
								}
							}
							String ProductName = ""  ;
							for (int x = 0 ; x< DS8.size();x++){
								if(ProductID.equals(DS8.get(x).getProductID())){
									ProductName = DS8.get(x).getProductName();
								}
							}
							if(check == 0){
								check = 1 ;
								DS10.add(new ManagementCPDetail(CourseName,ProductName));
								DS10CHECKONLY.add(new ManagementCPDetail(CourseName,ProductName));
							}
							else{
								DS10.add(new ManagementCPDetail("" , ProductName));
								DS10CHECKONLY.add(new ManagementCPDetail(CourseName,ProductName));
							}
							
						}
						
					}
					check= 0;
				}
				// close connection
				conn.close();
			}
		} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
		} catch (SQLException ex) {
		ex.printStackTrace();
		}
	}
	public void getNewSetTableCustomer(){
		int c =0;
		try {
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:Course.db";
			Connection conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				String query = "Select * from Customer";
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					c = c+1;
				}
				CustomerD.add("Customer");
				for(int i = 0 ; i<c;i++){
					ResultSet resultSet1 = statement.executeQuery(query);
					while (resultSet1.next()) {
						if(resultSet1.getString(1).equals(Integer.toString(i+1))){
							String name = "";
							String Customer_ID = resultSet1.getString(1);
							String Customer_Name = resultSet1.getString(2);
							String Massager_ID = resultSet1.getString(4);
							for(int i1 = 0; i1 < DS1.size();i1++){
								if(Massager_ID.equals(DS1.get(i1).getMassager_ID())){
									name = DS1.get(i1).getMassager_Name();
								}
								if(Massager_ID.equals("None")){
									name = "None";
								}
							}
							String Phone = resultSet1.getString(3);
							String Detail = resultSet1.getString(5);
							DS3.add(new CustomerDetail(Customer_ID,Customer_Name,name,Phone,Detail));
							CustomerD.add(Customer_Name);
							}		
					}
				}
				// close connection
				conn.close();
			}
		} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	public void findServiceCourse (String CID)throws ClassNotFoundException, SQLException {
		courseTime.clear();
		checkPrice.clear();
		try {
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:Course.db";
			Connection conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				String query = "Select * from Course_Price where CID = ?";
				PreparedStatement pst = conn.prepareStatement(query);
				pst.setString(1,CID);
				for(int i = 0; i <3 ;i++){
					ResultSet resultSet = pst.executeQuery();
					while (resultSet.next()) {	
						if(sortHr.get(i).equals(resultSet.getString(2))){
							String time = resultSet.getString(2);
							String Price = resultSet.getString(3);
							courseTime.add(time);
							checkPrice.add(Price);
						}
					}
				}
				// close connection
				conn.close();
			}
		} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
		} catch (SQLException ex) {
		ex.printStackTrace();
		}
    }
	public void getNewSetTableBooking(){
		DS4.clear();
		DS4CheckIn.clear();
		try {
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:Course.db";
			Connection conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				String query = "Select * from Booking";
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(query);

				while (resultSet.next()) {
					String No = resultSet.getString(1);
					String CustomerID = resultSet.getString(3);
					String CustomerName = null;
					for(int i = 0; i < DS3.size();i++){
						if(CustomerID.equals(DS3.get(i).getCustomer_ID())){
							CustomerName = DS3.get(i).getCustomer_Name();
						}
					}
					  if (CustomerID.equals("Customer")){
						  CustomerName = "Customer";
			            }
					String MassagerID = resultSet.getString(4);
					String MassagerName = null;
					for(int i = 0; i < DS1.size();i++){
						if(MassagerID.equals(DS1.get(i).getMassager_ID())){
							MassagerName = DS1.get(i).getMassager_Name();
						}
					}
					String CourseID = resultSet.getString(5);
					String CourseName = null ;
					for(int i = 0; i < DS.size();i++){
						if(CourseID.equals(DS.get(i).getCourse_ID())){
							CourseName = DS.get(i).getCourse_Name();
						}
					}
					String TimeService = resultSet.getString(6);
					String TimeBegin = resultSet.getString(7);
					String TimeEnd = resultSet.getString(8);
					String Status = resultSet.getString(9);
					String price = resultSet.getString(10);
					DS4.add(new BookingDetail( No ,  CustomerName, CourseName ,MassagerName,  TimeService, TimeBegin, TimeEnd ,Status,price));
				}
				// close connection
				conn.close();
			}
		} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		for (int i = 0 ; i<DS4.size();i++) {
			if(DS4.get(i).getStatus().equals("CheckIn")) {
				DS4CheckIn.add(new BookingDetail(DS4.get(i).getNo(),  DS4.get(i).getCustomerName(), 
						DS4.get(i).getCourseName() ,DS4.get(i).getMassagerName(),  DS4.get(i).getTimeService(), DS4.get(i).getTimeBegin()
						,DS4.get(i).getTimeEnd(),DS4.get(i).getStatus(), DS4.get(i).getPrice()));
			}
		}
	}
	
	public void getNewSetTableBookingWithCustomer(String cusName){
		DS4SPECIFIC.clear();
		for (int i = 0 ; i<DS4.size();i++){
			if(cusName.equals(DS4.get(i).getCustomerName())){
				DS4SPECIFIC.add(DS4.get(i));
				
			}
		}
	}
	public void getNewSetTableBookingWithMassager(String masName){
		DS4SPECIFIC.clear();
		for (int i = 0 ; i<DS4.size();i++){
			if(masName.equals(DS4.get(i).getMassagerName())){
				DS4SPECIFIC.add(DS4.get(i));
		
			}
		}
	}
	public void getNewSetTableSkill(){
		DS5CHECKONLY.clear();
		DS5.clear();
		try {
			Class.forName("org.sqlite.JDBC");
			String dbURL1 = "jdbc:sqlite:Course.db";
			Connection conn = DriverManager.getConnection(dbURL1);
			if (conn != null) {
				String queryPrice = "Select * from Course_Massager";
				Statement statement = conn.createStatement();
				int check = 0;
				for(int i = 0 ; i<DS1.size() ;i++){
					ResultSet resultSet = statement.executeQuery(queryPrice);
					while (resultSet.next()){
						if(resultSet.getString(2).equals(DS1.get(i).getMassager_ID())){
							String Massager_ID = resultSet.getString(2); 
							String Course_ID = resultSet.getString(1);
							String Massager_Name = ""  ;
							String Course_Name = ""  ;
							for (int x = 0 ; x< DS1.size();x++){
								if(Massager_ID.equals(DS1.get(x).getMassager_ID())){
									Massager_Name = DS1.get(x).getMassager_Name();
								}
							}
							for (int x = 0 ; x< DS.size();x++){
								if(Course_ID.equals(DS.get(x).getCourse_ID())){
									Course_Name = DS.get(x).getCourse_Name();
								}
							}
							int fillter = checkStatus(Massager_Name);
							if(check == 0&&fillter==1){
								check = 1 ;
								DS5.add(new SkillDetail(Massager_Name, Course_Name));
								DS5CHECKONLY.add(new SkillDetail(Massager_Name, Course_Name));
							}
							else if(check==1 &&fillter==1){
								DS5.add(new SkillDetail("", Course_Name));
								DS5CHECKONLY.add(new SkillDetail(Massager_Name, Course_Name));
							}
							
						}
						
					}
					check= 0;
				}
				
				// close connection
				conn.close();
			}
		} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
		} catch (SQLException ex) {
		ex.printStackTrace();
		}
	}
	public void getNewSetTableStartWorking(){
		try {
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:Course.db";
			Connection conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				String query = "Select * from HistoryWork";
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					String MID = resultSet.getString(1);
					String TimeIn = resultSet.getString(2);
					String MassagerName = null;
					for(int i = 0; i < DS1.size();i++){
						if(MID.equals(DS1.get(i).getMassager_ID())){
							MassagerName = DS1.get(i).getMassager_Name();
						}
					}
					DS6.add(new StartWorkDetail( MID ,  MassagerName, TimeIn));
				}
				// close connection
				conn.close();
			}
		} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	public void getNewSetProductPayment(){
		 DS12.clear();
		 DS12CHECKONLY.clear();
		try {
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:Course.db";
			Connection conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				String query = "Select * from ProductPayment";
				Statement statement = conn.createStatement();
				int count =0, check= 0;
				String checkID = "";
				ResultSet resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
						String PaymentID =  resultSet.getString(1);
						String Massager_Name = "";
						String courseName =  "";
						String time =  "";
						String productName ="";
						String Status = resultSet.getString(4);
						String datetime = resultSet.getString(5);
						for(int x =0 ;x<DS7.size();x++) {
							if(PaymentID.equals(DS7.get(x).getPaymentID())) {
								 Massager_Name = DS7.get(x).getMassagerName();
								 courseName = DS7.get(x).getCourseName();
								 time = DS7.get(x).getServiceTime();
							}	
						}
						String ProductID = resultSet.getString(2);
						for(int x =0 ;x<DS8.size();x++) {
							if(ProductID.equals(DS8.get(x).getProductID()))
								productName = DS8.get(x).getProductName();
						}
						String Quantity = resultSet.getString(3);
						if(checkID=="") {
							checkID =resultSet.getString(1);
						}
						else if(!checkID.equals(PaymentID)){
							count = 0;
							checkID =resultSet.getString(1);
						}
						else {
							count = 1;
						}
						if(count == 0 && Status.equals("NotDone")) {
							DS12.add(new ProductPaymentDetail(PaymentID, Massager_Name, courseName, time, productName, Quantity,datetime));
							DS12CHECKONLY.add(new ProductPaymentDetail(PaymentID, Massager_Name, courseName, time, productName, Quantity,datetime));
						}
						else if(count == 1 && Status.equals("NotDone")) {
							DS12.add(new ProductPaymentDetail("","" ,"" ,"" , productName, Quantity,datetime));
							DS12CHECKONLY.add(new ProductPaymentDetail(PaymentID, Massager_Name, courseName, time, productName, Quantity,datetime));
						}	
				}
				conn.close();
			}
		} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	public void insertProductPayment(String no ,String productID, String Quantity, String Status, String time) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String sql = "INSERT INTO ProductPayment(PaymentID,ProductID,Quantity,Status,Time) VALUES(?,?,?,?,?)";
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,no);
        pstmt.setString(2,productID);
        pstmt.setString(3,Quantity);
        pstmt.setString(4,Status);
        pstmt.setString(5,time);
        pstmt.executeUpdate();
        conn.close();
    }

	public void updateProduct(String pid, String quantity) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		 Class.forName("org.sqlite.JDBC");
	        String sql = "update Product set Quantity = ? where ProductID = ?";
			String dbURL = "jdbc:sqlite:Course.db";
			Connection conn = DriverManager.getConnection(dbURL);
			PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1,quantity);
	        pstmt.setString(2,pid);
	        pstmt.executeUpdate();
	        conn.close();
	}
	public void updateProduct(String pid, String quantity,String dateADD,String TypeID ,String Price) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		 Class.forName("org.sqlite.JDBC");
	        String sql = "update Product set Quantity = ? ,DateAddProduct=? ,TypeProduct = ? , Price = ? where ProductID = ?";
			String dbURL = "jdbc:sqlite:Course.db";
			Connection conn = DriverManager.getConnection(dbURL);
			PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1,quantity);
	        pstmt.setString(2,dateADD);
	        pstmt.setString(3,TypeID);
	        pstmt.setString(4,Price);
	        
	        pstmt.setString(5,pid);
	        pstmt.executeUpdate();
	        conn.close();
	}
	public void updateProductPayment(String no ,String productID, String Quantity)throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		String sql = "update ProductPayment set Quantity =? where PaymentID = ? and ProductID = ?" ;
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,Quantity);
		pstmt.setString(2,no);
		 pstmt.setString(3,productID);
		pstmt.executeUpdate();
		conn.close();
	}
	public void updateProductPayment(String no ,String Status)throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		String sql = "update ProductPayment set Status =? where PaymentID = ? " ;
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,Status);
		pstmt.setString(2,no);
		pstmt.executeUpdate();
		conn.close();
	}
	public void insertCourse(String BookId, String name) throws ClassNotFoundException, SQLException {
	        Class.forName("org.sqlite.JDBC");
	        String sql = "INSERT INTO Course(CID,CName) VALUES(?,?)";
			String dbURL = "jdbc:sqlite:Course.db";
			Connection conn = DriverManager.getConnection(dbURL);
			PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1,BookId);
	        pstmt.setString(2,name);
	        pstmt.executeUpdate();
	        conn.close();  
	    }
	public void insertCoursePrice(String ID,String ServiceTime,String Price, String massagerCost) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String sql = "INSERT INTO Course_Price(CID,ServiceTime,Price,MassagerCost) VALUES(?,?,?,?)";
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,ID);
        pstmt.setString(2,ServiceTime);
        pstmt.setString(3,Price);
        pstmt.setString(4,massagerCost);
        pstmt.executeUpdate();
        conn.close();
    }
	public void insertBooking(String no, String customerName, String courseName, String timeService, String timeBegin, String timeEnd, String date, String massagerName,String status, String price) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String sql = "INSERT INTO Booking(BookingNumber,Date,CustomerID,MID,CID,ServiceTime,TimeBegin,TimeEnd,Status,price) VALUES(?,?,?,?,?,?,?,?,?,?)";
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,no);
        pstmt.setString(2,date);
        pstmt.setString(3,customerName);
        pstmt.setString(4, massagerName);
        pstmt.setString(5,courseName);
        pstmt.setString(6,timeService);
        pstmt.setString(7,timeBegin);
        pstmt.setString(8,timeEnd);
        pstmt.setString(9,status);
        pstmt.setString(10, price);
        pstmt.executeUpdate();
        conn.close();
    }
	public void insertPayment(String no ,String customerName, String massagerName,String courseName, String timeService, String Price,String date,String MassagerCost) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String sql = "INSERT INTO Payment(PaymentID,CustomerID,MID,CID,ServiceTime,Price,Date,MassagerCost) VALUES(?,?,?,?,?,?,?,?)";
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,no);
        pstmt.setString(2,customerName);
        pstmt.setString(3,massagerName);
        pstmt.setString(4, courseName);
        pstmt.setString(5,timeService);
        pstmt.setString(6,Price);
        pstmt.setString(7,date);
        pstmt.setString(8,MassagerCost);
        pstmt.executeUpdate();
        conn.close();
    }
	public void deleteBooking(String ID) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		String sql = "DELETE  FROM Booking  where BookingNumber = ?";
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// set the corresponding param
		pstmt.setString(1,ID);
		// update 
		pstmt.executeUpdate();
		conn.close();
	}
	
	
	public void insertMassager(String Massager_ID , String Massager_Name,String Phone) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String sql = "INSERT INTO Massager(MID,MName,Phone) VALUES(?,?,?)";
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,Massager_ID);
        pstmt.setString(2,Massager_Name);
        pstmt.setString(3,Phone);
        pstmt.executeUpdate();
        conn.close();
    }
	public void insertSchedule(String DAY , String MID, String status) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String sql = "INSERT INTO Massager_WorkingDay(DAY,MID,Status) VALUES(?,?,?)";
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,DAY);
        pstmt.setString(2,MID);
        pstmt.setString(3,status);
        pstmt.executeUpdate();
        conn.close();
    }
	public void insertSkill(String cid , String MID) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String sql = "INSERT INTO Course_Massager(CID,MID) VALUES(?,?)";
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,cid);
        pstmt.setString(2,MID);
        pstmt.executeUpdate();
        conn.close();
    }
	public void deleteSkill(String cid,String MID) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		String sql = "DELETE  FROM Course_Massager  where CID = ? and MID = ?";
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,cid);
		pstmt.setString(2,MID); 
		pstmt.executeUpdate();
		conn.close();
	}
	public void deleteSkillMassager(String MID) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		String sql = "DELETE  FROM Course_Massager  where and MID = ?";
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// set the corresponding param
		pstmt.setString(2,MID);
		// update 
		pstmt.executeUpdate();
		conn.close();
	}
	public void insertHistoryWork(String MID, String TIN, String DATE)throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String sql = "INSERT INTO HistoryWork(MID,TimeIn,Date) VALUES(?,?,?)";
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,MID);
        pstmt.setString(2,TIN);
        pstmt.setString(3,DATE );
        pstmt.executeUpdate();
        conn.close();
    }
	public void deleteHistoryWork(String MID)throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String sql = "DELETE from HistoryWork where MID =? ";
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,MID);
        pstmt.executeUpdate();
        conn.close();
    }
	public void insertCustomer(String customer_ID, String customer_Name, String massager_ID, String phone,String dataild)throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String sql = "INSERT INTO Customer(CustomerID,CustomerName,Phone,MID,DetailForMassage) VALUES(?,?,?,?,?)";
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,customer_ID);
        pstmt.setString(2,customer_Name);
        pstmt.setString(3,phone);
        pstmt.setString(4,massager_ID );
        pstmt.setString(5,dataild);
        pstmt.executeUpdate();
        conn.close();
    }
	public void deleteCustomer(String customer_ID) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		String sql = "DELETE  FROM Customer  where CustomerID = ? ";
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// set the corresponding param
		pstmt.setString(1,customer_ID);
		// update 
		pstmt.executeUpdate();
		conn.close();
	}
	public void updateMassagerStatus(String massager_ID,String Status)throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		String sql = "update Massager set Status =? where MID = ?" ;
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,Status);
		pstmt.setString(2,massager_ID);
		pstmt.executeUpdate();
		conn.close();
	}
	public void updateSchedule(String DAY,String MID, String status) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		String sql = "update Massager_WorkingDay set Status =? where DAY = ? and MID = ?";
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// set the corresponding param
		pstmt.setString(1,status);
		pstmt.setString(2,DAY);
		pstmt.setString(3,MID);
		// update 
		pstmt.executeUpdate();
		conn.close();
	}
	public void insertProduct(String pid, String pName, String cbTypePro, String date, String quantity, String price) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		 Class.forName("org.sqlite.JDBC");
	        String sql = "INSERT INTO Product(ProductID,ProductName,TypeProduct,DateAddProduct,Quantity,Price) VALUES(?,?,?,?,?,?)";
			String dbURL = "jdbc:sqlite:Course.db";
			Connection conn = DriverManager.getConnection(dbURL);
			PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1,pid);
	        pstmt.setString(2,pName);
	        pstmt.setString(3,cbTypePro);
	        pstmt.setString(4,date );
	        pstmt.setString(5,quantity);
	        pstmt.setString(6,price);
	        pstmt.executeUpdate();
	        conn.close();
	}

	public void insertTypeProduct(int count, String pTName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
				 Class.forName("org.sqlite.JDBC");
			        String sql = "INSERT INTO TypeProduct(TypeProductID,TypeProductName) VALUES(?,?)";
					String dbURL = "jdbc:sqlite:Course.db";
					Connection conn = DriverManager.getConnection(dbURL);
					PreparedStatement pstmt = conn.prepareStatement(sql);
			        pstmt.setString(1,Integer.toString(count));
			        pstmt.setString(2,pTName);
			        pstmt.executeUpdate();
			        conn.close();
	}

	public void updateProductType(String typeProductID, String string) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("org.sqlite.JDBC");
		String sql = "update TypeProduct set TypeProductName =? where TypeProductID = ? ";
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// set the corresponding param
		pstmt.setString(1,string);
		pstmt.setString(2,typeProductID);
		// update 
		pstmt.executeUpdate();
		conn.close();
	}
	public void updateCoursePrice(String ID,String ServiceTime, String price, String mCost) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		String sql = "update Course_Price set Price= ? ,MassagerCost=? where CID = ? and ServiceTime = ?";
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// set the corresponding param
		pstmt.setString(1,price);
		pstmt.setString(2,mCost);
		pstmt.setString(3,ID);
		pstmt.setString(4,ServiceTime);		
		// update 
		pstmt.executeUpdate();
		conn.close();
	}
	public void updateMassagerPhone(String massager_ID,String phone)throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		String sql = "update Massager set Phone =? where MID = ?" ;
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,phone);
		pstmt.setString(2,massager_ID);		
		// set the corresponding param		
		// update 
		pstmt.executeUpdate();
		conn.close();
	}
	public void updateMCP(String cid, String pid) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		String sql = "update Course_Product set ProductID=? where CPID	 = ?";
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// set the corresponding param
	       pstmt.setString(1,pid);
	        pstmt.setString(2,cid);
		// update 
		pstmt.executeUpdate();
		conn.close();
	}
	public void insertCoursePriceHistory(int i,String ID,String ServiceTime,String Price, String massagerCost) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String sql = "INSERT INTO Course_PriceHistory(HID,CID,ServiceTime,Price,MassagerCost) VALUES(?,?,?,?,?)";
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,i);
        pstmt.setString(2,ID);
        pstmt.setString(3,ServiceTime);
        pstmt.setString(4,Price);
        pstmt.setString(5,massagerCost);
        pstmt.executeUpdate();
        conn.close();
    }
	public void insertMCP(String cpid,String cid, String pid) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Class.forName("org.sqlite.JDBC");
		String sql = "INSERT INTO Course_Product(CPID,CID,ProductID) VALUES(?,?,?)";
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,cpid);
		pstmt.setString(2,cid);
		pstmt.setString(3,pid);
		pstmt.executeUpdate();
		conn.close();
	}
	public void updateCustomer(String customer_ID, String phone, String dataild, String massager_Name) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("org.sqlite.JDBC");
		String sql = "update Customer set Phone=?, MID=? ,DetailForMassage=? where CustomerID	 = ?";
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// set the corresponding param
	    pstmt.setString(1,phone);
	    pstmt.setString(2,massager_Name);
	    pstmt.setString(3,dataild);
	    pstmt.setString(4,customer_ID);
		// update 
		pstmt.executeUpdate();
		conn.close();
	}
	public void updateBooking(String no ,String status) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String sql = "update Booking set Status=? where BookingNumber=?";
		String dbURL = "jdbc:sqlite:Course.db";
		Connection conn = DriverManager.getConnection(dbURL);
		PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1	,status);
        pstmt.setString(2,no);
        pstmt.executeUpdate();
        conn.close();

    }
}