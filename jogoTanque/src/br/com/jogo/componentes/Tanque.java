package br.com.jogo.componentes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

/**
 * @author Karen 1 de jul de 2018
 */

public class Tanque {
	private double x, y;
	private double angulo;
	private double velocidade;
	private Color cor;
	private boolean estaAtivo;

	public Tanque(int x, int y, int angulo, Color cor) {
		this.x = x;
		this.y = y;
		this.angulo = 90 - angulo;
		this.cor = cor;
		velocidade = 3;
		this.estaAtivo = false;

	}

	public void aumentarVelocidade() {
		velocidade++;
	}

	public void girarHorario(int a) {
		angulo += a;
	}

	public void girarAntiHorario(int a) {
		angulo -= a;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @return the angulo
	 */
	public double getAngulo() {
		return angulo;
	}

	/**
	 * @param angulo
	 *            the angulo to set
	 */
	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	/**
	 * @return the velocidade
	 */
	public double getVelocidade() {
		return velocidade;
	}

	/**
	 * @param velocidade
	 *            the velocidade to set
	 */
	public void setVelocidade(double velocidade) {
		this.velocidade = velocidade;
	}

	/**
	 * @return the cor
	 */
	public Color getCor() {
		return cor;
	}

	/**
	 * @param cor
	 *            the cor to set
	 */
	public void setCor(Color cor) {
		this.cor = cor;
	}

	public void mover() {
		x = x + Math.sin(Math.toRadians(angulo)) * velocidade;
		y = y - Math.cos(Math.toRadians(angulo)) * velocidade;

		// Delimitando a área de movimentacao do tanque

		if (x < 20 || x > 780 || y < 20 || y > 580) {

			if (x < 300 && y < 200) {
				girarAntiHorario(180);
			}

			if (x < 300 && y >= 200) {
				girarHorario(180);
			}

			if (x > 300 && y < 200) {
				girarAntiHorario(180);
			}

			if (x > 300 && y >= 200) {
				girarHorario(180);
			}
		}
	}

	public boolean getEstaAtivo() {
		return this.estaAtivo;
	}

	public void setEstaAtivo(boolean estaAtivo) {
		this.estaAtivo = estaAtivo;
	}

	public void draw(Graphics2D g2d) {
		// Armazenamos o sistema de coordenadas original.
		AffineTransform antes = g2d.getTransform();
		// Criamos um sistema de coordenadas para o tanque.
		AffineTransform depois = new AffineTransform();
		depois.translate(x, y);
		depois.rotate(Math.toRadians(angulo));
		// Aplicamos o sistema de coordenadas.
		g2d.transform(depois);
		// Desenhamos o tanque. Primeiro o corpo
		g2d.setColor(cor);
		g2d.fillRect(-10, -12, 20, 24);
		// Agora as esteiras
		for (int i = -12; i <= 8; i += 4) {
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(-15, i, 5, 4);
			g2d.fillRect(10, i, 5, 4);
			g2d.setColor(Color.BLACK);
			g2d.fillRect(-15, i, 5, 4);
			g2d.fillRect(10, i, 5, 4);
		}
		// O canhão
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRect(-3, -25, 6, 25);
		g2d.setColor(cor);
		g2d.drawRect(-3, -25, 6, 25);
		// Se o tanque estiver ativo
		// Desenhamos uma margem
		if (estaAtivo) {
			g2d.setColor(new Color(120, 120, 120));
			Stroke linha = g2d.getStroke();
			g2d.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[] { 8 }, 0));
			g2d.drawRect(-24, -32, 48, 55);
			g2d.setStroke(linha);
		}
		// Aplicamos o sistema de coordenadas
		g2d.setTransform(antes);
	}

	public Shape getRectEnvolvente() {
		AffineTransform at = new AffineTransform();
		at.translate(x, y);
		at.rotate(Math.toRadians(angulo));
		Rectangle rect = new Rectangle(-24, -32, 48, 55);
		return at.createTransformedShape(rect);
	}
}
