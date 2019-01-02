package br.com.dorival.viagens.dto;

import java.util.List;

public class Diaria {
	int id;
	String name;
	String cityCode;
	String cityName;
	List<DiariaQuarto> rooms;
	
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

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public List<DiariaQuarto> getRooms() {
		return rooms;
	}
	public void setRooms(List<DiariaQuarto> list) {
		this.rooms = list;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
}
