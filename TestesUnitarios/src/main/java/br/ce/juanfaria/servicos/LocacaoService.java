package br.ce.juanfaria.servicos;

import static br.ce.juanfaria.utils.DataUtils.adicionarDias;

import java.util.Date;

import br.ce.juanfaria.entidades.Filme;
import br.ce.juanfaria.entidades.Locacao;
import br.ce.juanfaria.entidades.Usuario;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, Filme filme) {
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}

}