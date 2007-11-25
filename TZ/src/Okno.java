

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;



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
public class Okno extends javax.swing.JFrame {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JDialog jDialog1;
	private JButton jButton2;
	private JLabel jLabel2;
	private JButton jButton1;
	private JTextField jTextField4;
	private JTextField jTextField3;
	private JLabel jLabel4;
	private JButton Izhod;
	private JButton Zbrisi;
	private JLabel jLabel3;
	private JTextField jTextField2;
	private JLabel jLabel1;
	private JTextField jTextField1;

	private Absyn.Exp exp;
	private String[] spremenljivke;
	private String izraz;
	private float[] verjetnosti=null;
	private int[]	zac_vr=null;
	private String filename=null;

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
			getContentPane().setLayout(null);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().add(getJButton2());
			getContentPane().add(getJLabel2());
			getContentPane().add(getJTextField1());
			getContentPane().add(getZbrisi());
			getContentPane().add(getIzhod());
			pack();
			this.setSize(376, 225);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JDialog getJDialog1() {
		if(jDialog1 == null) {
			jDialog1 = new JDialog(this);
			jDialog1.getContentPane().setLayout(null);
			jDialog1.setPreferredSize(new java.awt.Dimension(327, 190));
			jDialog1.getContentPane().add(getJButton1());
			jDialog1.getContentPane().add(getJLabel1());
			jDialog1.getContentPane().add(getJTextField2());
			jDialog1.getContentPane().add(getJLabel3());
			jDialog1.getContentPane().add(getJLabel4());
			jDialog1.getContentPane().add(getJTextField3());
			jDialog1.getContentPane().add(getJTextField4());
			jDialog1.setSize(327, 190);
		}
		return jDialog1;
	}

	private JButton getJButton2() {
		if(jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("Izracunaj");
			jButton2.setBounds(87, 100, 86, 34);
			jButton2.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					jButton2MouseClicked(evt);
				}
			});

		}
		return jButton2;
	}

	private void jButton2MouseClicked(MouseEvent evt) { //button v glavnem oknu
		boolean ok = true;
		izraz = jTextField1.getText();
		if(izraz.isEmpty()){
			JOptionPane.showMessageDialog(this, "Vnesti moras izraz", "Notification", JOptionPane.INFORMATION_MESSAGE);
			ok = false;
		}
		if(ok){
			Parse parse = new Parse(izraz);
			exp = parse.absyn;

			spremenljivke = new String[parse.spremenljivke.size()];
			int i = 0;
			for(String sprTmp : parse.spremenljivke){
				spremenljivke[i] = sprTmp;
				i++;
			}
			//	System.out.println("Izraz je: "+izraz);
			//dialog za vnos ime datoteke, verjetnosti in zacetnih vrednosti
			getJDialog1().pack();
			getJDialog1().setLocationRelativeTo(null);
			getJDialog1().setVisible(true);
		}


	}

	private JLabel getJLabel2() {
		if(jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Vnesi izraz:");
			jLabel2.setBounds(12, 32, 65, 19);
		}
		return jLabel2;
	}

	private JTextField getJTextField1() {
		if(jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setBounds(77, 29, 186, 22);
			jTextField1.setToolTipText("Operatori so: or and not()");
		}
		return jTextField1;
	}

	private JButton getJButton1() {
		if(jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("OK");
			jButton1.setBounds(248, 116, 53, 23);
			jButton1.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					jButton1MouseClicked(evt);
				}
			});
		}
		return jButton1;
	}

	private JLabel getJLabel1() {
		if(jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Ime datoteke:");
			jLabel1.setBounds(0, 11, 70, 15);
		}
		return jLabel1;
	}

	private JTextField getJTextField2() {
		if(jTextField2 == null) {
			jTextField2 = new JTextField();
			jTextField2.setBounds(70, 8, 107, 18);
			jTextField2.setToolTipText("Vnesi ime datoteke, privzeto ime je out.txt");
		}
		return jTextField2;
	}

	private JLabel getJLabel3() {
		if(jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Verjetnosti:");
			jLabel3.setBounds(0, 32, 56, 14);
		}
		return jLabel3;
	}

	private JLabel getJLabel4() {
		if(jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("Zac. vred.:");
			jLabel4.setBounds(5, 52, 54, 14);
		}
		return jLabel4;
	}

	private JTextField getJTextField3() {
		if(jTextField3 == null) {
			jTextField3 = new JTextField();
			jTextField3.setBounds(70, 29, 107, 17);
			jTextField3.setToolTipText("Vnesi verjetnosti locene z presledki");
		}
		return jTextField3;
	}

	private JTextField getJTextField4() {
		if(jTextField4 == null) {
			jTextField4 = new JTextField();
			jTextField4.setBounds(69, 49, 108, 17);
			jTextField4.setToolTipText("Vnesi zacetne vrednosti spremenljivk, locene z presledki");
		}
		return jTextField4;
	}

	private void jButton1MouseClicked(MouseEvent evt) { //button v jdialog
		boolean ok = true;
		filename = jTextField2.getText();
		if(filename.isEmpty()){
			filename = "out.txt";
		}

		String[] ver = jTextField3.getText().split(" ");
		if(ver.length != spremenljivke.length){
			JOptionPane.showMessageDialog(this, "Vnesti moras :"+spremenljivke.length+" verjetnosti", "Vnesel si napacno stevilo verjetnosti", JOptionPane.ERROR_MESSAGE);
			ok = false;
		}

			

		String[] vr = jTextField4.getText().split(" ");
		if( vr.length != spremenljivke.length){
			JOptionPane.showMessageDialog(this, "Vnesti moras :"+spremenljivke.length+" zacetnih vrednosti", "Vnesel si napacno stevilo zacetnih vrednosti", JOptionPane.ERROR_MESSAGE);
			ok = false;
		}
		
		

		if(ok){
			verjetnosti = new float[ver.length];
			for(int i = 0; i < ver.length; i++){
				verjetnosti[i] = (float)Double.parseDouble(ver[i]);
			}
			
			zac_vr = new int[vr.length];
			for(int i = 0; i < vr.length; i++){
				zac_vr[i] = Integer.parseInt(vr[i]);
			}
			
			Izracun izracun = new Izracun(spremenljivke, verjetnosti, zac_vr, exp);
			Izpis izpis = new Izpis(izracun);
			izpis.izpisi(filename);
			getJDialog1().dispose();
			jTextField2.setText("");
			jTextField3.setText("");
			jTextField4.setText("");
		}
		else{
			jTextField2.setText("");
			jTextField3.setText("");
			jTextField4.setText("");
		}

	}

	private JButton getZbrisi() {
		if(Zbrisi == null) {
			Zbrisi = new JButton();
			Zbrisi.setText("Zbrisi");
			Zbrisi.setBounds(273, 29, 57, 23);
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

	private JButton getIzhod() {
		if(Izhod == null) {
			Izhod = new JButton();
			Izhod.setText("Izhod");
			Izhod.setBounds(192, 101, 86, 34);
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

}
