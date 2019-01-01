package br.com.dorival.viagens.dto;

//import org.springframework.format.annotation.NumberFormat;

public class DiariaPreco {

	double pricePerDayAdult;
	double pricePerDayChild;
	
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
	
	public double getPricePerDayAdult() {
		return pricePerDayAdult;
	}
	public void setPricePerDayAdult(double pricePerDayAdult) {
		this.pricePerDayAdult = pricePerDayAdult;
	}
	public double getPricePerDayChild() {
		return pricePerDayChild;
	}
	public void setPricePerDayChild(double pricePerDayChild) {
		this.pricePerDayChild = pricePerDayChild;
	}	
}
