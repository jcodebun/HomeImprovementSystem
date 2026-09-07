package in.co.home.imp.system.bean;

import java.sql.Timestamp;
import java.util.Date;

public class ApplicationBean extends BaseBean {
	
	private long userId;
	private String userName;
	private String applicationName;
	private String city;
	private String state;
	private String addressline1;
	private String addressline2;
	private String pincode;
	private Timestamp date;
	private String fixedIncome;
	private String anotherIncome;
	private String residentDetail;
	private String genralCondition;
	private String workDescription;
	private long applicationId;
	
	
	
	
	
	
	
	

	public long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getFixedIncome() {
		return fixedIncome;
	}

	public void setFixedIncome(String fixedIncome) {
		this.fixedIncome = fixedIncome;
	}

	public String getAnotherIncome() {
		return anotherIncome;
	}

	public void setAnotherIncome(String anotherIncome) {
		this.anotherIncome = anotherIncome;
	}

	public String getResidentDetail() {
		return residentDetail;
	}

	public void setResidentDetail(String residentDetail) {
		this.residentDetail = residentDetail;
	}

	public String getGenralCondition() {
		return genralCondition;
	}

	public void setGenralCondition(String genralCondition) {
		this.genralCondition = genralCondition;
	}

	public String getWorkDescription() {
		return workDescription;
	}

	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
