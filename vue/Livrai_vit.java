package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import modele.Plan;
import xml.XMLOpener;
import xml.XMLParser;

import java.awt.FlowLayout;
import java.awt.Button;
import java.awt.Choice;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Canvas;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractListModel;

public class Livrai_vit {

	private JFrame mainFrame;
	
	private JLabel lblInstruction;
	
	private Plan plan;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Livrai_vit window = new Livrai_vit();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Livrai_vit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.plan = new Plan();
		XMLOpener x = new XMLOpener();
		File f = x.open(false);
		try {
			XMLParser.chargerPlan(this.plan, f);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		mainFrame = new JFrame();
		mainFrame.setTitle("Livrai'vit");
		mainFrame.setResizable(false);
		mainFrame.setBounds(100, 100, 1185, 617);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelLivraison = new JPanel();
		panelLivraison.setBackground(Color.YELLOW);
		mainFrame.getContentPane().add(panelLivraison, BorderLayout.EAST);
		panelLivraison.setLayout(new BorderLayout(0, 0));
		
		JPanel panelEdition = new JPanel();
		panelLivraison.add(panelEdition, BorderLayout.NORTH);
		
		JButton btnSupression = new JButton("");
		btnSupression.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Supression Cliqu�");
			}
		});
		btnSupression.setIcon(new ImageIcon(Livrai_vit.class.getResource("/javax/swing/plaf/metal/icons/ocean/paletteClose-pressed.gif")));
		panelEdition.add(btnSupression);
		
		JButton btnNouveau = new JButton("+");
		btnNouveau.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelEdition.add(btnNouveau);
		
		JButton btnMonter = new JButton("");
		btnMonter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnMonter.setIcon(new ImageIcon(Livrai_vit.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-fewer-details.png")));
		panelEdition.add(btnMonter);
		
		JButton btnDescendre = new JButton("");
		btnDescendre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnDescendre.setIcon(new ImageIcon(Livrai_vit.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-more-details.png")));
		panelEdition.add(btnDescendre);
		
		JList listLivraison = new JList();
		listLivraison.setModel(new AbstractListModel() {
			String[] values = new String[] {"Pierre", "Paul", "Jacques"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listLivraison.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JList listLivraison = (JList)e.getSource();
		        int index = listLivraison.locationToIndex(e.getPoint());
		        System.out.println(index);        
			}
		});
		listLivraison.setBackground(new Color(173, 216, 230));
		panelLivraison.add(listLivraison, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		mainFrame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panelInstruction = new JPanel();
		panelInstruction.setBackground(Color.GRAY);
		panel_1.add(panelInstruction, BorderLayout.SOUTH);
		panelInstruction.setLayout(new BorderLayout(0, 0));
		
		this.lblInstruction = new JLabel("New label");
		this.lblInstruction.setForeground(Color.WHITE);
		panelInstruction.add(this.lblInstruction, BorderLayout.CENTER);
		
		JPanel panelValidation = new JPanel();
		panelValidation.setBackground(Color.GRAY);
		panelValidation.setForeground(Color.BLACK);
		panelInstruction.add(panelValidation, BorderLayout.EAST);
		panelValidation.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnValider = new JButton("Valider");
		btnValider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnValider.setEnabled(false);
		panelValidation.add(btnValider);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnAnnuler.setEnabled(false);
		panelValidation.add(btnAnnuler);
		
		JPanel panelToolbar = new JPanel();
		FlowLayout fl_panelToolbar = (FlowLayout) panelToolbar.getLayout();
		fl_panelToolbar.setAlignment(FlowLayout.LEFT);
		panel_1.add(panelToolbar, BorderLayout.NORTH);
		
		JMenuBar menuBar = new JMenuBar();
		panelToolbar.add(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmChargerPlan = new JMenuItem("Charger plan...");
		mntmChargerPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Charged plan");
				lblInstruction.setText("aaa");
				
			}
		});
		
		mnMenu.add(mntmChargerPlan);
		
		JMenuItem mntmChargerLivraison = new JMenuItem("Charger livraison...");
		mntmChargerLivraison.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Charged livraison");
			}
		});
		mnMenu.add(mntmChargerLivraison);
		
		JMenuItem mntmReset = new JMenuItem("Reset");
		mntmReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Reset");
			}
		});
		mnMenu.add(mntmReset);
		
		JMenuItem mntmExporter = new JMenuItem("Exporter feuille de route");
		mntmExporter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Export");
			}
		});
		mnMenu.add(mntmExporter);
		
		JButton btnUndo = new JButton("");
		btnUndo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnUndo.setIcon(new ImageIcon(Livrai_vit.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
		btnUndo.setToolTipText("Annuler (Ctrl-Z)");
		btnUndo.setHorizontalAlignment(SwingConstants.RIGHT);
		menuBar.add(btnUndo);
		
		JButton btnRedo = new JButton("");
		btnRedo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnRedo.setIcon(new ImageIcon(Livrai_vit.class.getResource("/com/sun/javafx/scene/web/skin/Redo_16x16_JFX.png")));
		btnRedo.setToolTipText("R\u00E9p\u00E9ter (Ctrl-Y)");
		btnRedo.setHorizontalAlignment(SwingConstants.RIGHT);
		menuBar.add(btnRedo);
		
		JPanel panelPlan = new JPanel();
		panel_1.add(panelPlan, BorderLayout.CENTER);
		panelPlan.setLayout(null);
		PlanCanvas canvas = new PlanCanvas(this.plan);
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		canvas.setBackground(new Color(255, 255, 255));
		canvas.setBounds(10, 10, 950, 500);

		panelPlan.add(canvas);
		
	}
}
