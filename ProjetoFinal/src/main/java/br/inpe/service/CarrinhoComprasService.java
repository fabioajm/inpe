package br.inpe.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inpe.model.CarrinhoCompras;
import br.inpe.model.Usuario;
import br.inpe.observer.AtualizaEstoqueObserver;
import br.inpe.observer.PreferenciasUsuarioObserver;
import br.inpe.repository.CarrinhoComprasRepository;

@Service
@Transactional
public class CarrinhoComprasService {
	
	@Autowired
	private CarrinhoComprasRepository ccRepository;
	
	@Autowired
	private EstoqueService estoqueService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public void save(CarrinhoCompras cc){
		ccRepository.save(cc);
	}

	public CarrinhoCompras find(Long id){
		return ccRepository.findById(id);
	}
	public CarrinhoCompras criarCarrinho(Usuario u) {
		CarrinhoCompras cc;
		cc = new CarrinhoCompras();
		cc.setUsuario(u);
		cc.adicionarObserver(new AtualizaEstoqueObserver(estoqueService));
		cc.adicionarObserver(new PreferenciasUsuarioObserver(u, usuarioService));
		return cc;
	}
}
