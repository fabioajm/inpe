package br.inpe.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inpe.model.CarrinhoCompras;
import br.inpe.repository.CarrinhoComprasRepository;

@Service
@Transactional
public class CarrinhoComprasService {
	
	@Autowired
	private CarrinhoComprasRepository ccRepository;
	
	public void save(CarrinhoCompras cc){
		ccRepository.save(cc);
	}

	public CarrinhoCompras find(Long id){
		return ccRepository.findById(id);
	}
}
