
public class shippingInfo 
{
	private String shippingId;
	private float shippingFee;
	
	public shippingInfo()
	{
		this.shippingId = "";
		this.shippingFee = 0;
	}
	
	public shippingInfo(String shi, float sf)
	{
		this.shippingId = shi;
		this.shippingFee = sf;
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
	 * @return the shippingFee
	 */
	public float getShippingFee() {
		return shippingFee;
	}

	/**
	 * @param shippingFee the shippingFee to set
	 */
	public void setShippingFee(float shippingFee) {
		this.shippingFee = shippingFee;
	}

	@Override
	public String toString() {
		return "shippingInfo [shippingId=" + shippingId + ", shippingFee=" + shippingFee + "]";
	}

	/*
	 * The trackOrder() method will help the customer to enter the shipping ID number
	 * and then he/she is able to track his/her game order.
	 */
	public void trackOrder()
	{
		
	}

}
