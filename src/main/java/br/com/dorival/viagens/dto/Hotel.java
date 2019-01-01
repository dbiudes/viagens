package br.com.dorival.viagens.dto;

import java.util.List;

public class Hotel {
	int id;
	String name;
	String cytyCode;
	String cityName;
	List<HotelQuarto> rooms;
	
	/*
[   {
        "id": 1,
        "name": "Hotel Teste 1",
        "cityCode": 1032,
        "cityName": "Porto Seguro",
        "rooms": [
            {
                "roomID": 0,
                "categoryName": "Standard",
                "price": {
                    "adult": 1372.54,
                    "child": 848.61
                }
            }
        ]
    },
    {
        "id": 4,
        "name": "Hotel Teste 4",
        "cityCode": 1032,
        "cityName": "Porto Seguro",
        "rooms": [
            {
                "roomID": 0,
                "categoryName": "Standard",
                "price": {
                    "adult": 341.76,
                    "child": 782.14
                }
            },
            {
                "roomID": 1,
                "categoryName": "Luxo",
                "price": {
                    "adult": 483.02,
                    "child": 591.33
                }
            }
        ]
    },
	
	*/

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCytyCode() {
		return cytyCode;
	}
	public void setCytyCode(String cytyCode) {
		this.cytyCode = cytyCode;
	}
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
	public List<HotelQuarto> getRooms() {
		return rooms;
	}
	public void setRooms(List<HotelQuarto> rooms) {
		this.rooms = rooms;
	}	
}
