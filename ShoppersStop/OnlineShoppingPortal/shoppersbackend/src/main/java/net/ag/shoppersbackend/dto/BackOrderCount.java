package net.ag.shoppersbackend.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Back_Order_Count")
public class BackOrderCount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
	private CartLine cartLine;	
	@Column(name = "product_count")
	private int productCount;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CartLine getCartLine() {
		return cartLine;
	}
	public void setCartLine(CartLine cartLine) {
		this.cartLine = cartLine;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "BackOrderCount [id=" + id + ", cartLine=" + cartLine + ", productCount=" + productCount + "]";
	}
	
	
		
		
	
}
