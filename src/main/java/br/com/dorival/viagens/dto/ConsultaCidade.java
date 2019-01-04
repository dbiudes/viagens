package br.com.dorival.viagens.dto;

public class ConsultaCidade extends Consulta {
	
	String cityCode;
	Consulta consulta;	
	
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode.trim();
	}
	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

}
