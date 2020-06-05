package com.nineleaps.project.order.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType;

@Table("orders")
public class Order implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4285212120081695660L;

	@PrimaryKey
	@CassandraType(type = DataType.Name.UUID)
	@NotNull
	private UUID orderId = UUID.randomUUID();;
	
	@NotNull
	private Date date=  Calendar.getInstance().getTime();
	
	@NotNull
	private String customerName;
	
	@NotNull
	private String customerEmail;
	
	@CassandraType(type = DataType.Name.UDT, userTypeName = "address")
	private Address customerAddress;
	
	@CassandraType(type = DataType.Name.UDT, userTypeName = "item")
	private List<Item> items;
	
	private int total;
	
	public UUID getOrderId() {
		return orderId;
	}

	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public Address getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(Address customerAddress) {
		this.customerAddress = customerAddress;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}	

}
