package br.com.dorival.viagens.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dorival.viagens.dto.Consulta;
import br.com.dorival.viagens.dto.Diaria;
import br.com.dorival.viagens.dto.Hotel;
import br.com.dorival.viagens.extras.DebugUtil;
import br.com.dorival.viagens.helper.ConsultarPacotesHelper;
import br.com.dorival.viagens.repository.ConsultarPacotesRepository;

@Service
public class ConsultarPacotesService {
	
	@Autowired 
	ConsultarPacotesRepository consultarPrecosRepository;
	
	public List<Diaria> consultaPrecosPorCidade(Consulta consulta) {
	
		List<Hotel> hoteis = consultarPrecosRepository.ConsultaVagasPorCidade(consulta.getCityCode());
		List<Diaria> diarias = hoteis.stream().map(hotel -> ConsultarPacotesHelper.ProcessarHotel(hotel, consulta)).collect(Collectors.toList());
		//parallelStream cerca de 8s a primeira chamada e proximas entre 1.7s a 1.9s
		//stream normal  cerca de 4s a primeira chamada e proximas entre 1.1s a 1.4s
		
		DebugUtil.ImprimeMensagem("Hoteis encontrados: " + diarias.size());
		return (diarias);
	}
	
	public List<Diaria> consultaPrecosPorHotel(Consulta consulta) {
		List<Hotel> hoteis = consultarPrecosRepository.ConsultaVagasPorHotel(consulta.getHotelCode());
		List<Diaria> diarias = hoteis.stream().map(hotel -> ConsultarPacotesHelper.ProcessarHotel(hotel, consulta)).collect(Collectors.toList());
		DebugUtil.ImprimeMensagem("Hoteis encontrados: " + diarias.size());
		return (diarias);
	}
}

