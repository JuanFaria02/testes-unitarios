package br.ce.juanfaria.servicos;

import static br.ce.juanfaria.utils.DataUtils.adicionarDias;

import java.util.Date;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.stream.Collectors;

import br.ce.juanfaria.entidades.Filme;
import br.ce.juanfaria.entidades.Locacao;
import br.ce.juanfaria.entidades.Usuario;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws Exception {
		if (filmes == null || filmes.isEmpty()){
			throw new Exception("Lista vazia");
		}
		if (usuario == null){
			throw new Exception("Usuario vazio");
		}

		for (Filme f:
			 filmes) {
			if (f.getEstoque() == 0){
				throw new Exception("Filme fora de estoque");
			}
		}


		Locacao locacao = new Locacao();
		filmes.forEach(filme->locacao.getFilmes().add(filme));
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		Double valorLocacao = 0d;
		for (Filme f:
			 filmes) {
			valorLocacao+=f.getPrecoLocacao();
		}
		locacao.setValor(valorLocacao);
		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);

		//Salvando a locacao...
		//TODO adicionar método para salvar

		return locacao;
	}

}