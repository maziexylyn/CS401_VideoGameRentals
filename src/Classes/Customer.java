package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer {
	
	private String name;
	private String phone;
	private String cardNumber;
	private String shippingAddress;
	private int userID;

	public Customer( String name, String phone, String cardNumber, String shippingAddress, int userID) {
		this.name = name;
		this.phone = phone;
		this.cardNumber = cardNumber;
		this.shippingAddress = shippingAddress;
		this.userID = userID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}


	/////////////////////////////////
	////// DB Access Functions //////
	/////////////////////////////////

	public static Customer createCustomer(Connection conn, String name, String phone, String cardNumber, String shippingAddress, int userID){
		Customer temp = null;
		try{
			if(conn != null){
				Statement stmt = conn.createStatement();
				String sql = "INSERT INTO customer (cName, phone, cardNumber, shippingAddress, userID)" +
						" VALUES ('"+name+"','"+phone+"', '"+cardNumber+"','"+shippingAddress+"', "+userID+");";
				stmt.executeUpdate(sql);

				temp = Customer.readCustomerByUserID(conn, userID);

			}
		}catch (Exception error){
			System.out.println(error);
		}
		return temp;
	}

	public static Customer readCustomerByUserID(Connection conn, int userID){
		Customer temp = null;

		try{
			if(conn != null){
				Statement stmt = conn.createStatement();
				String sql = "SELECT customerID, cName , phone, cardNumber, shippingAddress, userID" +
						" FROM customer WHERE userID="+userID+" LIMIT 1;";
				ResultSet rs = stmt.executeQuery(sql);

				while(rs.next()){
					temp = new Customer(
							rs.getString("cName"),
							rs.getString("phone"),
							rs.getString("cardNumber"),
							rs.getString("shippingAddress"),
							rs.getInt("userID")
					);
				}

			}
		}catch(Exception error){
			System.out.println(error);
		}

		return temp;

	}

}
