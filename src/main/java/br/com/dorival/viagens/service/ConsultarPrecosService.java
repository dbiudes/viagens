package br.com.dorival.viagens.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dorival.viagens.dto.CriteriosConsulta;
import br.com.dorival.viagens.dto.Diaria;
import br.com.dorival.viagens.dto.Hotel;
import br.com.dorival.viagens.repository.ConsultarPrecosRepository;

@Service
public class ConsultarPrecosService {
	
	@Autowired 
	ConsultarPrecosRepository consultarPrecosRepository;
	
	public List<Diaria> consultaPrecosPorCidade(CriteriosConsulta consulta) {
	
		List<Hotel> hoteis = consultarPrecosRepository.PesquisaHoteis(consulta.getCityCode());
		List<Diaria> diarias = hoteis.stream().map(hotel -> CalcularDiariaService.ProcessarHotel(hotel, consulta)).collect(Collectors.toList());
	
		return (diarias);
	}
}

