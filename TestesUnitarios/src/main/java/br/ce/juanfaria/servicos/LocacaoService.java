package br.ce.juanfaria.servicos;

import static br.ce.juanfaria.utils.DataUtils.adicionarDias;
import static br.ce.juanfaria.utils.DataUtils.isMesmaData;

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


		for (int i = 0; i < filmes.size(); i++) {
			double valorPagar = buscarValor(filmes, i);

			valorLocacao += valorPagar;

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

	private double buscarValor(List<Filme> filmes, int i) {
		if (filmes.size() == 3 && i == 2){
			return filmes.get(i).getPrecoLocacao() - (0.25 * filmes.get(i).getPrecoLocacao());
		}
		else if (filmes.size() == 4 && i == 3){
			return filmes.get(i).getPrecoLocacao() - (0.50 * filmes.get(i).getPrecoLocacao());
		}
		else if (filmes.size() == 5 && i == 4){
			return filmes.get(i).getPrecoLocacao() - (0.75 * filmes.get(i).getPrecoLocacao());
		}
		else if (filmes.size() == 6 && i == 5){
			return filmes.get(i).getPrecoLocacao() - (1.0 * filmes.get(i).getPrecoLocacao());
		}
		else {
			return filmes.get(i).getPrecoLocacao();
		}
	}

}