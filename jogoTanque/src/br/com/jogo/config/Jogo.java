package br.com.jogo.config;

import br.com.jogo.Jogador;

/**
 * @author Karen 1 de jul de 2018
 */
public class Jogo {

	private Jogador jogador;
	private Niveis nivel;
	private Cores corTanque;

	/**
	 * @return the jogador
	 */
	public Jogador getJogador() {
		return jogador;
	}

	/**
	 * @param jogador
	 *            the jogador to set
	 */
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	/**
	 * @return the nivel
	 */
	public Niveis getNivel() {
		return nivel;
	}

	/**
	 * @param nivel
	 *            the nivel to set
	 */
	public void setNivel(Niveis nivel) {
		this.nivel = nivel;
	}

	/**
	 * @return the corTanque
	 */
	public Cores getCorTanque() {
		return corTanque;
	}

	/**
	 * @param corTanque
	 *            the corTanque to set
	 */
	public void setCorTanque(Cores corTanque) {
		this.corTanque = corTanque;
	}

}
