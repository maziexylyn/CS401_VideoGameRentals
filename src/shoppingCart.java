
public class shoppingCart {
	
	private String cartId;
	private int quantity;
	private float subTotal;
		
	
	
	public shoppingCart()
	{
		this.cartId = "";
		this.quantity = 0;
		this.subTotal = 0;
	}
	
	public shoppingCart(String cartId,int quantity, float subTotal)
	{
		this.cartId = cartId;
		this.quantity = quantity;
		this.subTotal = subTotal;
	}
	
	
	
	
	/**
	 * @return the cartId
	 */
	public String getCartId() {
		return cartId;
	}

	/**
	 * @param cartId the cartId to set
	 */
	public void setCartId(String cartId) {
		this.cartId = cartId;
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
	 * @return the subTotal
	 */
	public double getSubTotal() {
		return subTotal;
	}

	/**
	 * @param subTotal the subTotal to set
	 */
	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}
	
	
	

	@Override
	public String toString() {
		return "shoppingCart [cartId=" + cartId + ", quantity=" + quantity + ", subTotal=" + subTotal + "]";
	}
	
	/*
	 * The addNewItem() method takes the Classes.Customer's item
	 * selection and adds it to the cart for check out.
	 */
	public void addNewItem()
	{
		
	}
	/*
	 * The updateQuantity() method allows the Classes.Customer to modify the quantity of items selected
	 * and updates the total quantity of the items
	 */
	public void updateQuantity()
	{
		
	}
	/*
	 * The checkOut() method will have all of the items in the cart and finish the
	 * process of checking out.
	 */
	public void checkOut()
	{
		
	}
	

}
