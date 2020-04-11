
public class order {
	
	private String orderId, customerId, shippingId,customerName, shipDate;
	
	public order()
	{
		this.orderId = "";
		this.customerId = "";
		this.shippingId = "";
		this.customerName = "";
		this.shipDate = "";
	}
	
	public order(String oi, String ci, String si, String cn, String sd)
	{
		this.orderId = oi;
		this.customerId = ci;
		this.shippingId = si;
		this.customerName = cn;
		this.shipDate = sd;
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
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the shippingId
	 */
	public String getShippingId() {
		return shippingId;
	}

	/**
	 * @param shippingId the shippingId to set
	 */
	public void setShippingId(String shippingId) {
		this.shippingId = shippingId;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the shipDate
	 */
	public String getShipDate() {
		return shipDate;
	}

	/**
	 * @param shipDate the shipDate to set
	 */
	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}

	@Override
	public String toString() {
		return "order [orderId=" + orderId + ", customerId=" + customerId + ", shippingId=" + shippingId
				+ ", customerName=" + customerName + ", shipDate=" + shipDate + "]";
	}

	/*
	 * The shippingOption() method gives the customer a selection of 
	 * options to use for shipping of the game.
	 */
	public void shippingOption()
	{
		
	}
	 /*
	  * The placeOrder() method allows the customer to 
	  * place his/her order for the game and it links it
	  * to their account.
	  */
	public void placeOrder()
	{
		
	}

}
