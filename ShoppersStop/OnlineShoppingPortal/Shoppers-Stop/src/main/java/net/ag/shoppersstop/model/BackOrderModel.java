package net.ag.shoppersstop.model;

public class BackOrderModel {

	int userid;
	String username;
	int productId;
	String productname;
	int quantityavailable;
	int quantityneeded;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getQuantityavailable() {
		return quantityavailable;
	}
	public void setQuantityavailable(int quantityavailable) {
		this.quantityavailable = quantityavailable;
	}
	public int getQuantityneeded() {
		return quantityneeded;
	}
	public void setQuantityneeded(int quantityneeded) {
		this.quantityneeded = quantityneeded;
	}
	@Override
	public String toString() {
		return "BackOrderModel [userid=" + userid + ", username=" + username + ", productId=" + productId
				+ ", productname=" + productname + ", quantityavailable=" + quantityavailable + ", quantityneeded="
				+ quantityneeded + "]";
	}
	
	
	
	
}
