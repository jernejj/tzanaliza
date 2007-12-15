import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import java.awt.Event;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;






/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
/**
 * Gui
 */
public class Okno extends javax.swing.JFrame {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JButton jButton2;
	private JLabel jLabel2;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JPanel jPanel2;
	private JPanel jPanel1;
	private JLabel[] labelaSprem;
	private JTextField[] textFieldVer;
	private JTextField[] textFieldVr;
	private JButton Izhod;
	private JButton Zbrisi;
	private JLabel jLabel3;
	private JTextField jTextField2;
	private JLabel jLabel1;
	private JTextField jTextField1;

	private Absyn.Exp exp;
	public static String[] spremenljivke;
	public static String izraz;
	public static double[] verjetnosti=null;
	private JSeparator jSeparator2;
	private JSeparator jSeparator1;
	private JLabel statusBar;
	private JButton izracun;
	public static int[]	zac_vr=null;
	private String filename=null;
	private MouseAdapter M_AdapterZbrisiSB = new MouseAdapter() {
		public void mouseClicked(MouseEvent evt) {
			zbrisiStatusBarMouseClicked(evt);
		}
	};
	
	private KeyAdapter K_AdapterIzracunaj = new KeyAdapter() { 
		public void keyPressed(KeyEvent evt){
			if(evt.getKeyCode() == KeyEvent.VK_ENTER){
				izracunaj();
			}
		}
	};

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Okno inst = new Okno();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public Okno() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().add(getJSeparator2(), new AnchorConstraint(353, 1000, 381, 0, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			getContentPane().add(getJSeparator1(), new AnchorConstraint(952, 1000, 979, 0, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			getContentPane().add(getStatusBar(), new AnchorConstraint(952, 1000, 1006, 0, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			getContentPane().add(getJPanel2(), new AnchorConstraint(378, 1000, 952, 0, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			getContentPane().add(getJPanel1(), new AnchorConstraint(1, 1000, 355, 0, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			pack();
			this.setSize(624, 401);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private JButton getJButton2() {
		if(jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("Preberi izraz");
			jButton2.setPreferredSize(new java.awt.Dimension(129, 42));
			jButton2.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					preberiIzraz();
				}
			});

		}
		return jButton2;
	}

	private void preberiIzraz() { //button v glavnem oknu
		boolean ok = true;
		
		statusBar.setText("");
		
		izraz = jTextField1.getText();
		if(izraz.isEmpty()){
			JOptionPane.showMessageDialog(this, "Vnesti moras izraz", "Notification", JOptionPane.INFORMATION_MESSAGE);
			ok = false;
		}
		if(ok){
			Parse parse = new Parse(izraz);
			if(parse.izrazJeOk){
				jPanel2.removeAll();
				jPanel2.add(getIzracun(), new AnchorConstraint(804, 984, 953, 775, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jPanel2.add(getJLabel4(), new AnchorConstraint(462, 153, 611, 17, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jPanel2.add(getJLabel5(), new AnchorConstraint(110, 184, 260, 17, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jPanel2.add(getJLabel3(), new AnchorConstraint(287, 153, 436, 17, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				exp = parse.absyn;
				spremenljivke = new String[parse.spremenljivke.size()];
				int i = 0;
				for(String sprTmp : parse.spremenljivke){
					spremenljivke[i] = sprTmp;
					i++;
				}
				
				int stSprem = spremenljivke.length;
				labelaSprem = new JLabel[stSprem];
				textFieldVer = new JTextField[stSprem];
				textFieldVr = new JTextField[stSprem];
				for(i = 0; i < stSprem; i++){
					labelaSprem[i] = new JLabel();
					labelaSprem[i].setText(spremenljivke[i]);
					labelaSprem[i].setPreferredSize(new java.awt.Dimension(38, 34));
					jPanel2.add(labelaSprem[i], new AnchorConstraint(111, 256+i*77, 260, 183+i*78, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					
					textFieldVer[i] = new JTextField();
					textFieldVer[i].setHorizontalAlignment(JTextField.CENTER);
					textFieldVer[i].setText("0.5");
					textFieldVer[i].setPreferredSize(new java.awt.Dimension(37, 34));
					textFieldVer[i].addMouseListener( M_AdapterZbrisiSB );
					textFieldVer[i].addKeyListener(K_AdapterIzracunaj);
					
					jPanel2.add(textFieldVer[i], new AnchorConstraint(287, 256+i*77, 436, 183+i*78, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					
					textFieldVr[i] = new JTextField();
					textFieldVr[i].setHorizontalAlignment(JTextField.CENTER);
					textFieldVr[i].setPreferredSize(new java.awt.Dimension(37, 34));
					textFieldVr[i].setDocument(new JTextFieldLimit(1));
					textFieldVr[i].addMouseListener( M_AdapterZbrisiSB );
					textFieldVr[i].addKeyListener(K_AdapterIzracunaj);
					jPanel2.add(textFieldVr[i], new AnchorConstraint(462, 256+i*77, 611, 183+i*78, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					
				}
				jPanel2.updateUI();		
				
			}
		}


	}

	private JLabel getJLabel2() {
		if(jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Vnesi izraz:");
			jLabel2.setLocation(new java.awt.Point(1, 29));
			jLabel2.setMaximumSize(new java.awt.Dimension(80, 40));
			jLabel2.setMinimumSize(new java.awt.Dimension(40, 20));
			jLabel2.setPreferredSize(new java.awt.Dimension(87, 43));
		}
		return jLabel2;
	}

	private JTextField getJTextField1() {
		if(jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setToolTipText("Operatorji so: or and neg()");
			jTextField1.setPreferredSize(new java.awt.Dimension(180, 46));
			jTextField1.setFont(new java.awt.Font("Tahoma",0,12));
			jTextField1.addMouseListener(M_AdapterZbrisiSB );
			jTextField1.addKeyListener(new KeyAdapter() { 
				public void keyPressed(KeyEvent evt){
					if(evt.getKeyCode() == KeyEvent.VK_ENTER){
						preberiIzraz();
					}
				}
			});
		}
		return jTextField1;
	}

	private JLabel getJLabel1() {
		if(jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Ime datoteke:");
			jLabel1.setPreferredSize(new java.awt.Dimension(103, 35));
		}
		return jLabel1;
	}

	private JTextField getJTextField2() {
		if(jTextField2 == null) {
			jTextField2 = new JTextField();
			jTextField2.setToolTipText("Vnesi ime datoteke, privzeto ime je izpis.txt");
			jTextField2.setText("izpis.txt");
			jTextField2.setPreferredSize(new java.awt.Dimension(115, 35));
			jTextField2.addMouseListener(M_AdapterZbrisiSB);
		}
		return jTextField2;
	}

	private JLabel getJLabel3() {
		if(jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Verjetnosti:");
			jLabel3.setPreferredSize(new java.awt.Dimension(83, 34));
		}
		return jLabel3;
	}

	private JLabel getJLabel4() {
		if(jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("Zac. vred.:");
			jLabel4.setPreferredSize(new java.awt.Dimension(83, 34));
		}
		return jLabel4;
	}

	private void izracunaj() { //button v jdialog
		boolean ok = true;
		filename = jTextField2.getText();
		if(filename.isEmpty()){
			filename = "izpis.txt";
		}

		verjetnosti = new double[spremenljivke.length];
		zac_vr = new int[spremenljivke.length];

		for(int i = 0; i < spremenljivke.length; i++){

			try {
				verjetnosti[i] = Double.parseDouble(textFieldVer[i].getText());
				if(verjetnosti[i] < 0 || verjetnosti[i] > 1 ){
					JOptionPane.showMessageDialog(this, "Napacen vnos verjetnosti spremenljivke "+spremenljivke[i]+"!\n Verjetnost spremenljivke nesme biti vecja od 1 ali pa negativna", "Napacen vnos", JOptionPane.ERROR_MESSAGE);
					ok = false;
					break;
				}
					
			}catch (Exception e){
				//		e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Napacen vnos verjetnosti spremenljivke "+spremenljivke[i]+"!", "Napacen vnos", JOptionPane.ERROR_MESSAGE);
				ok = false;
				break;
			}

			try {
				zac_vr[i] = Integer.parseInt(textFieldVr[i].getText());
				if(zac_vr[i] < 0 || zac_vr[i] > 1){
					JOptionPane.showMessageDialog(this, "Napacen vnos zacetne vrednosti spremenljivke "+spremenljivke[i]+"!\n Zacetna vrednost je lahko le 0 ali 1.", "Napacen vnos", JOptionPane.ERROR_MESSAGE);
					ok = false;
					break;
				}
			}catch (Exception e){
				//		e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Napacen vnos zacetne vrednosti spremenljivke "+spremenljivke[i]+"!", "Napacen vnos", JOptionPane.ERROR_MESSAGE);
				ok = false;
				break;
			}

		}	

		if(ok){
			Izracun izracun = new Izracun(spremenljivke, verjetnosti, zac_vr, exp);
			Izpis izpis = new Izpis(izracun);
			izpis.izpisi(filename);
			statusBar.setText("Izracun je koncan, rezultati so zapisani v datoteki "+filename);
		}


	}

	private JButton getZbrisi() {
		if(Zbrisi == null) {
			Zbrisi = new JButton();
			Zbrisi.setText("Zbrisi");
			Zbrisi.setPreferredSize(new java.awt.Dimension(125, 46));
			Zbrisi.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					zbrisiMouseClicked(evt);
				}
			});
		}
		return Zbrisi;
	}

	private void zbrisiMouseClicked(MouseEvent evt){
		jTextField1.setText("");
	}
	
	private void zbrisiStatusBarMouseClicked(MouseEvent evt){
		statusBar.setText("");
	}
	

	private JButton getIzhod() {
		if(Izhod == null) {
			Izhod = new JButton();
			Izhod.setText("Izhod");
			Izhod.setPreferredSize(new java.awt.Dimension(127, 46));
			Izhod.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					izhodMouseClicked(evt);
				}
			});
		}
		return Izhod;
	}

	private void izhodMouseClicked(MouseEvent evt){
		this.dispose();
	}

	private JLabel getJLabel5() {
		if(jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("Spremenljivke:");
			jLabel5.setAutoscrolls(true);
			jLabel5.setPreferredSize(new java.awt.Dimension(103, 32));
		}
		return jLabel5;
	}
	
	private JPanel getJPanel1() {
		if(jPanel1 == null) {
			jPanel1 = new JPanel();
			AnchorLayout jPanel1Layout = new AnchorLayout();
			jPanel1.setLayout(jPanel1Layout);
			jPanel1.setPreferredSize(new java.awt.Dimension(608, 160));
			jPanel1.add(getJTextField2(), new AnchorConstraint(675, 357, 942, 170, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			jPanel1.add(getJLabel1(), new AnchorConstraint(675, 171, 942, 4, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			jPanel1.add(getJLabel2(), new AnchorConstraint(66, 160, 313, 17, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			jPanel1.add(getJTextField1(), new AnchorConstraint(54, 462, 318, 166, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			jPanel1.add(getZbrisi(), new AnchorConstraint(54, 810, 318, 604, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			jPanel1.add(getJButton2(), new AnchorConstraint(678, 765, 940, 553, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			jPanel1.add(getIzhod(), new AnchorConstraint(675, 984, 939, 775, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			
		}
		return jPanel1;
	}
	
	private JPanel getJPanel2() {
		if(jPanel2 == null) {
			jPanel2 = new JPanel();
			AnchorLayout jPanel2Layout = new AnchorLayout();
			jPanel2.setLayout(jPanel2Layout);
			jPanel2.setPreferredSize(new java.awt.Dimension(608, 210));
			
		}
		return jPanel2;
	}
	
	
	private JButton getIzracun() {
		if(izracun == null) {
			izracun = new JButton();
			izracun.setText("Izracunaj");
			izracun.setPreferredSize(new java.awt.Dimension(127, 34));
			izracun.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					izracunaj();
				}
			});
		}
		return izracun;
	}
	
	private JLabel getStatusBar() {
		if(statusBar == null) {
			statusBar = new JLabel();
			statusBar.setPreferredSize(new java.awt.Dimension(608, 20));
			statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
		}
		return statusBar;
	}
	
	private JSeparator getJSeparator1() {
		if(jSeparator1 == null) {
			jSeparator1 = new JSeparator();
			jSeparator1.setPreferredSize(new java.awt.Dimension(608, 10));
		}
		return jSeparator1;
	}
	
	private JSeparator getJSeparator2() {
		if(jSeparator2 == null) {
			jSeparator2 = new JSeparator();
			jSeparator2.setPreferredSize(new java.awt.Dimension(608, 10));
		}
		return jSeparator2;
	}

}
