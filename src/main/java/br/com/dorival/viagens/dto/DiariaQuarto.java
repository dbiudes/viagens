package br.com.dorival.viagens.dto;

import org.springframework.format.annotation.NumberFormat;

public class DiariaQuarto {
	
	int roomID;
	String categoryName;
	@NumberFormat(pattern = "0.00")
	double totalPrice;
	DiariaPreco priceDetail;
	
	/* {
	  "id": 1,
	  "cityName": "Porto Seguro",
	  "rooms":[{
	      "roomID": 1,
	      "categoryName": "Standard",
	      "totalPrice": 10000.00,
	      "priceDetail": {
	        "pricePerDayAdult": 500.00,
	        "pricePerDayChild": 50.00
	      }
	    }]
	} */
	
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public DiariaPreco getPriceDetail() {
		return priceDetail;
	}
	public void setPriceDetail(DiariaPreco priceDetail) {
		this.priceDetail = priceDetail;
	}
}
