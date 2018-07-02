package br.com.jogo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import br.com.jogo.componentes.Tanque;
import br.com.jogo.config.Cores;
import br.com.jogo.config.Jogo;
import br.com.jogo.config.Niveis;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Karen 1 de jul de 2018
 */
public class Principal {

	private JFrame frame;
	private JTextField textNome;
	static Principal window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 479, 386);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Game Tanque");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(149, 11, 178, 69);
		frame.getContentPane().add(lblNewLabel);

		textNome = new JTextField();
		textNome.setBounds(149, 116, 178, 20);
		frame.getContentPane().add(textNome);
		textNome.setColumns(10);

		JLabel lblDigiteSeuNome = new JLabel("Digite seu Nome:");
		lblDigiteSeuNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDigiteSeuNome.setForeground(new Color(255, 255, 255));
		lblDigiteSeuNome.setBounds(149, 91, 178, 14);
		frame.getContentPane().add(lblDigiteSeuNome);

		JLabel lblNvel = new JLabel("N\u00EDvel:");
		lblNvel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNvel.setForeground(new Color(255, 255, 255));
		lblNvel.setBounds(149, 147, 46, 14);
		frame.getContentPane().add(lblNvel);

		JComboBox comboNivel = new JComboBox();
		for (Niveis nivel : Niveis.values()) {
			comboNivel.addItem(nivel);
		}
		comboNivel.setBounds(149, 172, 178, 20);
		frame.getContentPane().add(comboNivel);
		
		JComboBox comboCor = new JComboBox();
		comboCor.setBounds(149, 229, 178, 20);
		for (Cores cor : Cores.values()) {
			comboCor.addItem(cor);
		}
		frame.getContentPane().add(comboCor);

		JButton btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Jogador jogador = new Jogador();
				jogador.setNome(textNome.getText());

				Jogo jogo = new Jogo();
				jogo.setJogador(jogador);
				jogo.setNivel((Niveis) comboNivel.getSelectedItem());

				frame.setVisible(false); // you can't see me!
				frame.dispose(); // Destroy the JFrame object
				
				Arena arena = new Arena(600, 400);
				arena.adicionaTanque(new Tanque(100, 200, 0, Color.BLUE));
				arena.setConfig(arena);
			}
		});
		btnStart.setForeground(new Color(255, 255, 255));
		btnStart.setBackground(new Color(255, 215, 0));
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnStart.setBounds(176, 275, 127, 38);
		frame.getContentPane().add(btnStart);
		
		JLabel lblNewLabel_1 = new JLabel("Cor Tanque:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(149, 207, 178, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
	}
}
