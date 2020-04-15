
public class Administrator {
	
	private String name, email;

	public Administrator()
	{
		this.name = "";
		this.email = "";
		
	}
	
	public Administrator(String name, String email)
	{
		this.name = name;
		this.email = email;
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

	@Override
	public String toString() {
		return "Administrator [name=" + name + ", email=" + email + "]";
	}

	/*
	 * The updateInventory() method will allow the administrator to 
	 * modify the inventory and update the latest inventory items
	 */
	public void updateInventory()
	{
		
	}
	/*
	 * The manageEmail() method is to let administrator check for emails from customers
	 * and check for any errors with the emails and to troubleshoot any email related
	 * problems.
	 */
	public void manageEmail()
	{
		
	}

}
