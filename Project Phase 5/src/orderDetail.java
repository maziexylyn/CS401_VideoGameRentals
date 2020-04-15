
public class orderDetail {

	private String orderId, gameId, gameName;
	private int quantity;
	private float price;
	
	public orderDetail()
	{
		this.orderId = "";
		this.gameId = "";
		this.gameName = "";
		this.quantity = 0;
		this.price = 0;
	}
	public orderDetail(String orderId, String gameId, String gameName, int quantity, float price)
	{
		this.orderId = orderId;
		this.gameId = gameId;
		this.gameName = gameName;
		this.quantity = quantity;
		this.price = price;
	}
				
	
	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the gameId
	 */
	public String getGameId() {
		return gameId;
	}
	/**
	 * @param gameId the gameId to set
	 */
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	/**
	 * @return the gameName
	 */
	public String getGameName() {
		return gameName;
	}
	/**
	 * @param gameName the gameName to set
	 */
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	
	
	@Override
	public String toString() {
		return "orderDetail [orderId=" + orderId + ", gameId=" + gameId + ", gameName=" + gameName + ", quantity="
				+ quantity + ", price=" + price + "]";
	}
	/*
	 * The calculateTotal() method will generate the total by using the
	 * quantity and price of the game and it would use the
	 * game name, gameId and it will link it to the orderId to calculate 
	 * the total.
	 */
	public void calculateTotal()
	{
		
	}
}
