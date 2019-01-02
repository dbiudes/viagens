package br.com.dorival.viagens.dto;

//import org.springframework.format.annotation.NumberFormat;

public class DiariaPreco {

	String pricePerDayAdult;
	String pricePerDayChild;
	
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
	
	public String getPricePerDayAdult() {
		return pricePerDayAdult;
	}
	public void setPricePerDayAdult(String pricePerDayAdult) {
		this.pricePerDayAdult = pricePerDayAdult;
	}
	public String getPricePerDayChild() {
		return pricePerDayChild;
	}
	public void setPricePerDayChild(String pricePerDayChild) {
		this.pricePerDayChild = pricePerDayChild;
	}	
}
