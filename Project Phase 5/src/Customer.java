
public class Customer {
	
	private String name,email,phoneNumber,cardInfo,shippingAddress;

	
	
	public Customer()
	{
		this.name = "";
		this.email = "";
		this.phoneNumber = "";
		this.cardInfo = "";
		this.shippingAddress = "";
		
	}
	public Customer( String n, String e, String pn, String ci, String sa)
	{
		this.name = n;
		this.email = e;
		this.phoneNumber = pn;
		this.cardInfo = ci;
		this.shippingAddress = sa;
		
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the cardInfo
	 */
	public String getCardInfo() {
		return cardInfo;
	}
	/**
	 * @param cardInfo the cardInfo to set
	 */
	public void setCardInfo(String cardInfo) {
		this.cardInfo = cardInfo;
	}
	/**
	 * @return the shippingAddress
	 */
	public String getShippingAddress() {
		return shippingAddress;
	}
	/**
	 * @param shippingAddress the shippingAddress to set
	 */
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	@Override
	public String toString() {
		return "Customer [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", cardInfo=" + cardInfo
				+ ", shippingAddress=" + shippingAddress + "]";
	}

	/*
	 * The updateProfile() method will take the customer's name, email 
	 * , phone number, card information, and
	 * shipping address and it will allow the user to 
	 * modify any of his/her profile information.
	 */
	public void updateProfile()
	{
		
	}
	
	/*
	 * The deleteProfile method() will get the customer's information and asks the
	 * customer if they want to delete the entire profile or just certain fields 
	 * of the profile. Then it will go according to the cutomer's decision. 
	 */
	public void deleteProfile()
	{
		
	}
	
}
