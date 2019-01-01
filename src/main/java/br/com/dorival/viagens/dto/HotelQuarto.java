package br.com.dorival.viagens.dto;

//import org.springframework.format.annotation.NumberFormat;

public class HotelQuarto {
	
	int roomID;
	String categoryName;
	HotelPreco price;
	
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
	public HotelPreco getPrice() {
		return price;
	}
	public void setPrice(HotelPreco price) {
		this.price = price;
	}
}
