package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import logika.Igralec;
import splosno.KdoIgra;
import vodja.Vodja;
import vodja.VrstaIgralca;

@SuppressWarnings("serial")
public class Okno extends JFrame implements ActionListener {
	
	private Platno platno;
	
	private JLabel status;
	private JMenuItem igraClovekRacunalnik;
	private JMenuItem igraRacunalnikClovek;
	private JMenuItem igraClovekClovek;
	private JMenuItem igraRacunalnikRacunalnik;
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
		//Vodja.igra = new Igra();
		
		JMenuBar menu_bar = new JMenuBar();
		this.setJMenuBar(menu_bar);
		JMenu igra_menu = new JMenu("Nova igra");
		menu_bar.add(igra_menu);
		
		igraClovekRacunalnik = new JMenuItem("Človek – računalnik");
		igra_menu.add(igraClovekRacunalnik);
		igraClovekRacunalnik.addActionListener(this);
		
		igraRacunalnikClovek = new JMenuItem("Računalnik – človek");
		igra_menu.add(igraRacunalnikClovek);
		igraRacunalnikClovek.addActionListener(this);
		
		igraClovekClovek = new JMenuItem("Človek – človek");
		igra_menu.add(igraClovekClovek);
		igraClovekClovek.addActionListener(this);
		
		igraRacunalnikRacunalnik = new JMenuItem("Računalnik – računalnik");
		igra_menu.add(igraRacunalnikRacunalnik);
		igraRacunalnikRacunalnik.addActionListener(this);
		
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

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == igraClovekRacunalnik) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.vrstaIgralca.put(Igralec.CRN, VrstaIgralca.C); 
			Vodja.vrstaIgralca.put(Igralec.BEL, VrstaIgralca.R);
			Vodja.stanjeZaslona = StanjeZaslona.IGRA;
			Vodja.igramoNovoIgro();
		} else if (e.getSource() == igraRacunalnikClovek) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.vrstaIgralca.put(Igralec.CRN, VrstaIgralca.R); 
			Vodja.vrstaIgralca.put(Igralec.BEL, VrstaIgralca.C);
			Vodja.stanjeZaslona = StanjeZaslona.IGRA;
			Vodja.igramoNovoIgro();
		} else if (e.getSource() == igraClovekClovek) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.vrstaIgralca.put(Igralec.CRN, VrstaIgralca.C); 
			Vodja.vrstaIgralca.put(Igralec.BEL, VrstaIgralca.C);
			Vodja.stanjeZaslona = StanjeZaslona.IGRA;
			Vodja.igramoNovoIgro();
		} else if (e.getSource() == igraRacunalnikRacunalnik) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.vrstaIgralca.put(Igralec.CRN, VrstaIgralca.R); 
			Vodja.vrstaIgralca.put(Igralec.BEL, VrstaIgralca.R);
			Vodja.stanjeZaslona = StanjeZaslona.IGRA;
			Vodja.igramoNovoIgro();
		}
		
	}
	
	public void osveziGUI() {
		if (Vodja.igra == null) {
			status.setText("Igra ni v teku.");
		}
		else {
			switch(Vodja.igra.stanjeIgre()) {
			case NEODLOCENO: status.setText("Neodločeno!"); break;
			case V_TEKU: 
				status.setText(Vodja.igra.naVrsti + 
						" - " + Vodja.vrstaIgralca.get(Vodja.igra.naVrsti)); 
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


