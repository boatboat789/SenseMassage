package Detail;
public class MassagerCourseDetail {
	private String Massager_Id ;
	private String Massager_Name ;
	private String Course_Name ;
	private String Course_Id ;
	public MassagerCourseDetail(String Massager_Id,String Massager_Name,String Course_Id, String Course_Name ) {
		this.Massager_Id= Massager_Id;
		this.Course_Name= Course_Name;
		this.Massager_Name= Massager_Name;
		this.Course_Id= Course_Id;
	}
	public String getMassager_Name() {
		return Massager_Name;	
	}
	public String getCourse_Name() {
		return Course_Name;	
	}
	public String getMassager_Id() {
		return Massager_Id;	
	}
	public String getCourse_Id() {
		return Course_Id;	
	}
}
