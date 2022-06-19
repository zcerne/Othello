package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EnumMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import logika.Igra;
import logika.Igralec;
import splosno.KdoIgra;
import vodja.Vodja;
import vodja.VrstaIgralca;

@SuppressWarnings("serial")
public class Okno extends JFrame implements ActionListener{
	
	public Platno platno;
	
	public JLabel status;
	private JButton undo;

	private JButton menu;
	//platno ane...
	public Okno() {
		super();
		//ko zapreš okno zapri program
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Othello");
		this.setLayout(new GridBagLayout());
		
		//ustvari novo platno
		platno = new Platno();

		this.add(platno);
		
		JMenuBar menu_bar = new JMenuBar();
		this.setJMenuBar(menu_bar);
		
		/*JMenu igra_menu = new JMenu("Nova igra");
		menu_bar.add(igra_menu);*/
		
		undo = new JButton("UNDO");
		menu_bar.add(undo);
		undo.addActionListener(this);
		
		menu = new JButton("MENU");
		menu_bar.add(menu);
		menu.addActionListener(this);
		
		GridBagConstraints polje_layout = new GridBagConstraints();
		polje_layout.gridx = 0;
		polje_layout.gridy = 0;
		polje_layout.fill = GridBagConstraints.BOTH;
		polje_layout.weightx = 1.0;
		polje_layout.weighty = 1.0;
		getContentPane().add(platno, polje_layout);
		
		// statusna vrstica za sporočila
		status = new JLabel();
		status.setFont(new Font(status.getFont().getName(),
							    status.getFont().getStyle(),
							    20));
		GridBagConstraints status_layout = new GridBagConstraints();
		status_layout.gridx = 0;
		status_layout.gridy = 1;
		status_layout.anchor = GridBagConstraints.WEST;
		getContentPane().add(status, status_layout);
		
		status.setText("Izberite igro!");
		osveziGUI();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (Vodja.stanjeZaslona == StanjeZaslona.IGRA  && e.getSource() == undo) {
			Vodja.gumb(VrstaGumba.UNDO);
		}
		else if (Vodja.stanjeZaslona == StanjeZaslona.IGRA  && e.getSource() == menu) {
			Vodja.gumb(VrstaGumba.MENU);
			osveziGUI();
		}
		
		
	}
	
	public void osveziGUI() {
		if (Vodja.igra == null) {
			status.setText("Izberi igro.");
		}
		else {
			switch(Vodja.igra.stanjeIgre) {
			case NEODLOCENO: status.setText("Neodločeno!"); break;
			case V_TEKU: 
				status.setText(Vodja.igra.naVrsti + 
						" - " + Vodja.vrstaIgralca.get(Vodja.igra.naVrsti) + " : " + Vodja.igra.rezultat());
				
				break;
			case ZMAGA_CRN: 
				status.setText("Zmagal je CRNI - " + Vodja.vrstaIgralca.get(Vodja.igra.naVrsti) + "  " + Vodja.igra.rezultat() );
				break;
			case ZMAGA_BEL: 
				status.setText("Zmagal je BELI - " + Vodja.vrstaIgralca.get(Vodja.igra.naVrsti) + "   " + Vodja.igra.rezultat());
				break;
			}
		}
		platno.repaint();
		
	}
		
}


=======
package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EnumMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import logika.Igra;
import logika.Igralec;
import splosno.KdoIgra;
import vodja.Vodja;
import vodja.VrstaIgralca;

@SuppressWarnings("serial")
public class Okno extends JFrame implements ActionListener{
	
	public Platno platno;
	
	public JLabel status;
	private JButton undo;

	private JButton menu;
	//platno ane...
	public Okno() {
		super();
		//ko zapreš okno zapri program
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Othello");
		this.setLayout(new GridBagLayout());
		
		//ustvari novo platno
		platno = new Platno();

		this.add(platno);
		
		JMenuBar menu_bar = new JMenuBar();
		this.setJMenuBar(menu_bar);
		
		/*JMenu igra_menu = new JMenu("Nova igra");
		menu_bar.add(igra_menu);*/
		
		undo = new JButton("UNDO");
		menu_bar.add(undo);
		undo.addActionListener(this);
		
		menu = new JButton("MENU");
		menu_bar.add(menu);
		menu.addActionListener(this);
		
		GridBagConstraints polje_layout = new GridBagConstraints();
		polje_layout.gridx = 0;
		polje_layout.gridy = 0;
		polje_layout.fill = GridBagConstraints.BOTH;
		polje_layout.weightx = 1.0;
		polje_layout.weighty = 1.0;
		getContentPane().add(platno, polje_layout);
		
		// statusna vrstica za sporočila
		status = new JLabel();
		status.setFont(new Font(status.getFont().getName(),
							    status.getFont().getStyle(),
							    20));
		GridBagConstraints status_layout = new GridBagConstraints();
		status_layout.gridx = 0;
		status_layout.gridy = 1;
		status_layout.anchor = GridBagConstraints.WEST;
		getContentPane().add(status, status_layout);
		
		status.setText("Izberite igro!");
		osveziGUI();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (Vodja.stanjeZaslona == StanjeZaslona.IGRA  && e.getSource() == undo) {
			Vodja.gumb(VrstaGumba.UNDO);
		}
		else if (Vodja.stanjeZaslona == StanjeZaslona.IGRA  && e.getSource() == menu) {
			Vodja.gumb(VrstaGumba.MENU);
			osveziGUI();
		}
		
		
	}
	
	public void osveziGUI() {
		if (Vodja.igra == null) {
			status.setText("Izberi igro.");
		}
		else {
			switch(Vodja.igra.stanjeIgre) {
			case NEODLOCENO: status.setText("Neodločeno!"); break;
			case V_TEKU: 
				status.setText(Vodja.igra.naVrsti + 
						" - " + Vodja.vrstaIgralca.get(Vodja.igra.naVrsti) + " : " + Vodja.igra.rezultat());
				
				break;
			case ZMAGA_CRN: 
				status.setText("Zmagal je CRNI - " + Vodja.vrstaIgralca.get(Vodja.igra.naVrsti) + "  " + Vodja.igra.rezultat() );
				break;
			case ZMAGA_BEL: 
				status.setText("Zmagal je BELI - " + Vodja.vrstaIgralca.get(Vodja.igra.naVrsti) + "   " + Vodja.igra.rezultat());
				break;
			}
		}
		platno.repaint();
		
	}
		
}
