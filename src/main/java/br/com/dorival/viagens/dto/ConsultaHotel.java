package br.com.dorival.viagens.dto;

public class ConsultaHotel extends Consulta {
	
	String hotelCode;
	Consulta consulta;
	
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode.trim();
	}
	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

}
