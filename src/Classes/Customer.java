package Classes;

import java.sql.*;

public class Customer {

	private int id;
	private String name;
	private String phone;
	private String card;
	private String address;
	private int user_id;

	public Customer(int id, String name, String phone, String card, String address, int userID) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.card = card;
		this.address = address;
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

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/////////////////////////////////
	////// DB Access Functions //////
	/////////////////////////////////

	public static boolean create(Connection conn, String name, String phone, String card, String address, int user_id){
		boolean isCreated = false;

		try{
			if(conn != null){
				CallableStatement stmt = conn.prepareCall("CALL User_Create(?, ?, ?, ?, ?, ?)");
				stmt.setString(1, name);
				stmt.setString(2, phone);
				stmt.setString(3, card);
				stmt.setString(4, address);
				stmt.setInt(5, user_id);
				stmt.registerOutParameter(6, Types.TINYINT);
				stmt.execute();
				isCreated = stmt.getBoolean(6);
				stmt.close();
			}
		}catch(Exception error){
			error.printStackTrace();
		}
		return isCreated;
	}

	public static boolean existsByUserID(Connection conn, int user_id){
		boolean exists = false;
		try{
			if(conn != null){
				CallableStatement stmt = conn.prepareCall("CALL Customer_Exists_ByUserID(?, ?)");
				stmt.setInt(1, user_id);
				stmt.registerOutParameter(2, Types.TINYINT);
				stmt.execute();
				exists = stmt.getBoolean(2);
				stmt.close();
			}
		}catch(Exception error){
			error.printStackTrace();
		}
		return exists;
	}

	public static Customer readByUserID(Connection conn, int user_id) {
		Customer temp = null;

		try{
			if(conn != null){
				CallableStatement stmt = conn.prepareCall("CALL Customer_Read_ByUserID(?)");
				stmt.setInt(1, user_id);
				ResultSet rs = stmt.executeQuery();

				while(rs.next()){
					temp = new Customer(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getString("phone"),
							rs.getString("card"),
							rs.getString("address"),
							rs.getInt("user_id")
					);
				}
				rs.close();
				stmt.close();
			}
		}catch(Exception error){
			error.printStackTrace();
		}
		return temp;
	}


}
