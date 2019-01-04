package br.com.dorival.viagens.dto;

import java.time.LocalDate;

public class Consulta {

	LocalDate checkin;
	LocalDate checkout;
	int diarias;
	int quantidadeAdultos;
	int quantidadeCriancas;
	

	
	public LocalDate getCheckin() {
		return checkin;
	}
	public void setCheckin(LocalDate checkin) {
		this.checkin = checkin;
	}
	public LocalDate getCheckout() {
		return checkout;
	}
	public void setCheckout(LocalDate checkout) {
		this.checkout = checkout;
	}
	public int getDiarias() {
		return diarias;
	}
	public void setDiarias(int diarias) {
		this.diarias = diarias;
	}
	public int getQuantidadeAdultos() {
		return quantidadeAdultos;
	}
	public void setQuantidadeAdultos(int quantidadeAdultos) {
		this.quantidadeAdultos = quantidadeAdultos;
	}
	public int getQuantidadeCriancas() {
		return quantidadeCriancas;
	}
	public void setQuantidadeCriancas(int quantidadeCriancas) {
		this.quantidadeCriancas = quantidadeCriancas;
	}
	
	
	
}
