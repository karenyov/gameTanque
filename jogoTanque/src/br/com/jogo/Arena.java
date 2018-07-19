package br.com.jogo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

import br.com.jogo.componentes.Tanque;
import br.com.jogo.componentes.Tiro;
import br.com.jogo.config.Cores;
import br.com.jogo.config.Jogo;

/**
 * @author Karen 1 de jul de 2018
 */

@SuppressWarnings("serial")
public class Arena extends JComponent implements KeyListener, ActionListener {
	private int largura, altura;
	private HashSet<Tanque> tanques;
	private List<Tiro> tiros;
	private Timer contador;

	public Arena(int largura, int altura) {
		this.largura = largura;
		this.altura = altura;
		tanques = new HashSet<Tanque>();
		contador = new Timer(500, this);
		tiros = new ArrayList<Tiro>();
		contador.start();
	}

	public void adicionaTanque(Tanque t) {
		tanques.add(t);
	}

	public Dimension getMaximumSize() {
		return getPreferredSize();
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	public Dimension getPreferredSize() {
		return new Dimension(largura, altura);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(new Color(245, 245, 255));
		g2d.fillRect(0, 0, largura, altura);
		g2d.setColor(new Color(220, 220, 220));
		for (int _largura = 0; _largura <= largura; _largura += 20)
			g2d.drawLine(_largura, 0, _largura, altura);
		for (int _altura = 0; _altura <= altura; _altura += 20)
			g2d.drawLine(0, _altura, largura, _altura);
		// Desenhamos todos os tanques
		for (Tanque t : tanques)
			t.draw(g2d);
		for (Tiro tiro : tiros)
			tiro.draw(g2d);
	}

	public void actionPerformed(ActionEvent e) {
		for (Tanque t : tanques)
			t.mover();
		colisao();
		
		for (Tiro tiro : tiros)
			tiro.mover();
		repaint();
	}

	public void atirar(Tanque tiro) {
		tiros.add(new Tiro(tiro.getX(), tiro.getY(), tiro.getAngulo(), tiro.getCor()));
	}

	public void colisao() {
		for (Tiro tiro : tiros) {
			for (Tanque t : tanques) {
				double distancia = Math.sqrt(Math.pow(tiro.getX() - t.getX(), 2) + Math.pow(tiro.getY() - t.getY(), 2));
				if ((distancia <= 30) && (t.getEstaAtivo() == false)) {
					tanques.remove(t);
					break;
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		for (Tanque t : tanques) {

			if (t.getEstaAtivo()) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					t.girarAntiHorario(10);
					break;
				case KeyEvent.VK_RIGHT:
					t.girarHorario(10);
					break;
				case KeyEvent.VK_UP:
					t.aumentarVelocidade();
					break;
				case KeyEvent.VK_DOWN:
					break;
				case KeyEvent.VK_SPACE:
					atirar(t);
					break;
				}
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	public static void main(String args[]) {
		Arena arena = new Arena(600, 400);
		arena.adicionaTanque(new Tanque(100, 200, 0, Color.BLUE));
		arena.adicionaTanque(new Tanque(200, 200, 45, Color.RED));
		arena.adicionaTanque(new Tanque(470, 360, 90, Color.GREEN));
		arena.adicionaTanque(new Tanque(450, 50, 157, Color.YELLOW));
		JFrame janela = new JFrame("Tanques");
		janela.getContentPane().add(arena);
		janela.addKeyListener(arena);
		janela.pack();
		janela.setVisible(true);
		janela.setDefaultCloseOperation(3);
	}

	/**
	 * Método que seta a configuração inicial do jogo
	 * 
	 * @param arena
	 * @param jogo
	 */
	public void setConfig(Jogo jogo) {

		// Configura o Nivel
		switch (jogo.getNivel().toString()) {
		case "FACIL":
			setConfigFacil(this, jogo);
			break;
		case "MEDIO":
			setConfigMedio(this, jogo);
			break;
		case "DIFICIL":
			setConfigDificil(this, jogo);
			break;
		default:
			break;
		}

		JFrame janela = new JFrame("Tanques");
		janela.getContentPane().add(this);
		janela.addKeyListener(this);
		janela.pack();
		janela.setVisible(true);
		janela.setDefaultCloseOperation(3);
	}

	/**
	 * Configuração para NÍVEL=> FACIL
	 * 
	 * - Apenas um Tanque inimigo
	 */
	public static void setConfigFacil(Arena arena, Jogo jogo) {
		for (Cores cor : Cores.values()) {
			if (jogo.getCorTanque() != cor) {
				arena.adicionaTanque(new Tanque(450, 50, 157, getCor(cor.toString())));
				break;
			}
		}
	}

	public static void setConfigMedio(Arena arena, Jogo jogo) {
		int cont = 0, angulo = 0, x = 100, y = 300;
		for (Cores cor : Cores.values()) {
			if (jogo.getCorTanque() != cor) {
				cont++;
				x += 100;
				y -= 50;
				angulo += 45;
				arena.adicionaTanque(new Tanque(x, y, angulo, getCor(cor.toString())));
				if (cont == 2)
					break;
			}
		}
	}

	public static void setConfigDificil(Arena arena, Jogo jogo) {
		int cont = 0, angulo = 0, x = 100, y = 300;
		for (Cores cor : Cores.values()) {
			if (jogo.getCorTanque() != cor) {
				cont++;
				x += 100;
				y -= 50;
				angulo += 45;
				arena.adicionaTanque(new Tanque(x, y, angulo, getCor(cor.toString())));
				if (cont == 3)
					break;
			}
		}
	}

	@SuppressWarnings("static-access")
	public static Color getCor(String corSelecionada) {
		Color cor = null;
		switch (corSelecionada) {
		case "AZUL":
			return cor.BLUE;
		case "VERMELHO":
			return cor.RED;
		case "VERDE":
			return cor.GREEN;
		case "AMARELO":
			return cor.YELLOW;
		default:
			return cor.GRAY;
		}
	}

}