package edu.asu.ser421.grocerydemo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PayInfo {

	public String toString() {
		return "PayInfo (name, cc, cvv): " + name + ", " + creditCardNum + ", " + cvv;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreditCardNum() {
		return creditCardNum;
	}

	public void setCreditCardNum(String creditCardNum) {
		this.creditCardNum = creditCardNum;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	@NotBlank(message = "Name cannot be blank")
	private String name;
	

	@NotEmpty
	@Size(min=16,max=19)
	private String creditCardNum;
	
	@NotEmpty(message = "CVV canot be empty")
	@Size(min=3,max=3,message="CVV is 3 characters")
	private String cvv;
	
	public PayInfo() {}
	
	public PayInfo(String name, String cc, String cvv) {
		this.name=name;
		this.creditCardNum=cc;
		this.cvv=cvv;
	}
	

}
