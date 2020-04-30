package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer {

	private int id;
	private String name;
	private String phone;
	private String cardNumber;
	private String shippingAddress;
	private int user_id;

	public Customer( String name, String phone, String cardNumber, String shippingAddress, int userID) {
		this.name = name;
		this.phone = phone;
		this.cardNumber = cardNumber;
		this.shippingAddress = shippingAddress;
		this.user_id = userID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

	public static Customer createCustomer(Connection conn, String name, String phone, String cardNumber, String shippingAddress, int user_id){
		Customer temp = null;
		try{
			if(conn != null){
				Statement stmt = conn.createStatement();
				String sql = "INSERT INTO customer (name, phone, cardNumber, shippingAddress, user_id)" +
						" VALUES ('"+name+"','"+phone+"', '"+cardNumber+"','"+shippingAddress+"', "+user_id+");";
				stmt.executeUpdate(sql);

				temp = Customer.readCustomerByUserID(conn, user_id);

			}
		}catch (Exception error){
			error.printStackTrace();
		}
		return temp;
	}

	public static Customer readCustomerByUserID(Connection conn, int user_id){
		Customer temp = null;

		try{
			if(conn != null){
				Statement stmt = conn.createStatement();
				String sql = "SELECT id, name , phone, cardNumber, shippingAddress, user_id" +
						" FROM customer WHERE user_id="+user_id+" LIMIT 1;";
				ResultSet rs = stmt.executeQuery(sql);

				while(rs.next()){
					temp = new Customer(

							rs.getString("name"),
							rs.getString("phone"),
							rs.getString("cardNumber"),
							rs.getString("shippingAddress"),
							rs.getInt("userID")
					);
				}

			}
		}catch(Exception error){
			error.printStackTrace();
		}

		return temp;

	}

}
