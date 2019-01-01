package br.com.dorival.viagens.dto;

public class HotelPreco {

	double adult;
	double child;
	
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
	
	public double getAdult() {
		return adult;
	}
	public void setAdult(double pricePerDayAdult) {
		this.adult = pricePerDayAdult;
	}
	public double getChild() {
		return child;
	}
	public void setChild(double pricePerDayChild) {
		this.child = pricePerDayChild;
	}	
}
