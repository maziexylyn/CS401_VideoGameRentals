
public class inventory {
	
	private String gameId, location;
	private int quantity;

	public inventory()
	{
		this.gameId = "";
		this.location = "";
		this.quantity = 0;
	}
	
	public inventory(String gameId,String location, int quantity)
	{
		this.gameId = gameId;
		this.location = location;
		this.quantity = quantity;
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
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
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

	@Override
	public String toString() {
		return "inventory [gameId=" + gameId + ", location=" + location + ", quantity=" + quantity + "]";
	}
	

}
