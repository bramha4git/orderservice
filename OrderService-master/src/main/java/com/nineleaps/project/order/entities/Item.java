package com.nineleaps.project.order.entities;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@UserDefinedType
public class Item {

	private UUID productId;
	
	private int quantity;

	private int price;
	
	public Item(UUID productId, int quantity, int price) {
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}
	
	public UUID getProductId() {
		return productId;
	}

	public void setProductId(UUID productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
