import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main extends JFrame {
	String mode;
	double r2x, g2x, b2x, r2y, g2y, b2y;
	
	public Main() {
		
		ArrayList<Image> list = new ArrayList<Image>();
		String[] modeList = {"Multiply", "Normal", "Overlay", "Screen", "Shade"};
		mode = "Normal";
		double r2x, g2x, b2x, r2y, g2y, b2y = 0;
		ImageIcon icon = new ImageIcon("peri16.png");
		ImageIcon icon2 = new ImageIcon("peri48.png");
		Image p1 = icon.getImage();
		Image p2 = icon2.getImage();
		list.add(p1);
		list.add(p2);
		setIconImages(list);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainContent = new JPanel();
		mainContent.setLayout(new FlowLayout());
		JPanel content = new JPanel();
		content.setLayout(new FlowLayout());
		JPanel content2 = new JPanel();
		content2.setLayout(new FlowLayout());
		JPanel content3 = new JPanel();
		content3.setLayout(new FlowLayout());
		//content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		//content.setLayout(new GridLayout(5,1));
		//content.setLayout(new FlowLayout());
		
		JPanel subpanel1 = new JPanel();
		subpanel1.setBorder(BorderFactory.createTitledBorder("[1] Base color"));
		BoxLayout sp1 = new BoxLayout(subpanel1, BoxLayout.X_AXIS);
		subpanel1.setLayout(sp1);
		JTextField r1b = new JTextField(3);
		JTextField g1b = new JTextField(3);
		JTextField b1b = new JTextField(3);
		JButton set1 = new JButton("Set");
		subpanel1.add(r1b);
		subpanel1.add(g1b);
		subpanel1.add(b1b);
		subpanel1.add(set1);
		
		JPanel subpanel2 = new JPanel();
		subpanel2.setBorder(BorderFactory.createTitledBorder("Composite color"));
		BoxLayout sp2 = new BoxLayout(subpanel2, BoxLayout.X_AXIS);
		subpanel2.setLayout(sp2);
		JTextField r1t = new JTextField(3);
		JTextField g1t = new JTextField(3);
		JTextField b1t = new JTextField(3);
		JButton set2 = new JButton("Set");
		subpanel2.add(r1t);
		subpanel2.add(g1t);
		subpanel2.add(b1t);
		subpanel2.add(set2);
		
		JComboBox modes = new JComboBox(modeList);
		modes.setSelectedIndex(1);

		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
		slider.setMajorTickSpacing(50);
		slider.setMinorTickSpacing(10);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		//---//
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(0, new JLabel("0"));
		//labelTable.put(50, new JLabel("0.5"));
		labelTable.put(100, new JLabel("1"));
		slider.setLabelTable(labelTable);
		slider.setPaintLabels(true);
		//---//
		JLabel value = new JLabel();
		value.setHorizontalAlignment(JLabel.CENTER);
		value.setText(Integer.toString(100) + "%");
		
		JPanel subpanel3 = new JPanel();
		subpanel3.setBorder(BorderFactory.createTitledBorder("[2] Base color"));
		BoxLayout sp3 = new BoxLayout(subpanel3, BoxLayout.X_AXIS);
		subpanel3.setLayout(sp3);
		JTextField r2b = new JTextField(3);
		JTextField g2b = new JTextField(3);
		JTextField b2b = new JTextField(3);
		JButton set3 = new JButton("Set");
		subpanel3.add(r2b);
		subpanel3.add(g2b);
		subpanel3.add(b2b);
		subpanel3.add(set3);
		
		JPanel subpanel4 = new JPanel();
		subpanel4.setBorder(BorderFactory.createTitledBorder("Composite color"));
		BoxLayout sp4 = new BoxLayout(subpanel4, BoxLayout.X_AXIS);
		subpanel4.setLayout(sp4);
		JTextField r2t = new JTextField(3);
		JTextField g2t = new JTextField(3);
		JTextField b2t = new JTextField(3);
		JButton set4 = new JButton("Set");
		subpanel4.add(r2t);
		subpanel4.add(g2t);
		subpanel4.add(b2t);
		subpanel4.add(set4);
		
		set1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					double r1a = Double.parseDouble(r1b.getText());
					double g1a = Double.parseDouble(g1b.getText());
					double b1a = Double.parseDouble(b1b.getText());
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
		});
		
		modes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JComboBox cb = (JComboBox) event.getSource();
				mode = (String) cb.getSelectedItem();
			}
		});
		
		slider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent event) {
				JSlider source = (JSlider) event.getSource();
				String output = "";
				if (source.getValue() < 10)
					output += " ";
				if (source.getValue() < 100)
					output += "‏‏‎ ";
				value.setText(output + Integer.toString((int) source.getValue()) + "%");
			}
		});
		
		
		//---------------------\\
		content.add(subpanel1);
		content.add(subpanel2);
		content2.add(modes);
		content2.add(slider);
		content2.add(value);
		content3.add(subpanel3);
		content3.add(subpanel4);
		mainContent.add(content);
		mainContent.add(content2);
		mainContent.add(content3);
		add(mainContent);
		
		setTitle("Color Analyzer");
		setSize(500, 300);
		setLocation(0, 0);
		setVisible(true);
		
		//v2
		/*content.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JTextField t1 = new JTextField(10);
		t1.setVisible(true);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		content.add(t1, gbc);
		add(content);*/
		
		//v1
		//Container contentPane = getContentPane();
		/*JButton myButton = new JButton("Button");
		content.add(myButton);
		setContentPane(content);*/
		
		/*Panel displayPanel = new Panel(new FlowLayout());
		TextField r = new TextField("", 4);
		TextField b = new TextField("", 4);
		TextField g = new TextField("", 4);
		displayPanel.add(r);
		displayPanel.add(g);
		displayPanel.add(b);
		add(displayPanel);
		setVisible(true);*/
	}
	
	public void select1(double r, double g, double b, double a) {
		if (mode.equals("Multiply")) {
		}
			
	}
	
	public static double overlay(double v1, double v3, double alpha) {
		double val = 0;
		if (v1 < 128)
			val = 255 * (1 - (1 - v3 / (2 * v1)) / alpha);
		else
			val = 255 * (((v3 - 255) + (1 - alpha) * (v3 - v1) / alpha) / (2 * (255 - v1)) + 1);	  
		return val;
	}
		
	public static void main(String[] args) {
		JFrame app = new Main();
		app.show();
		
		ArrayList<Double> arr = new ArrayList<Double>();
		Scanner scan = new Scanner(System.in);
		String type = scan.nextLine();
		double val;
		double r2 = 0;
		double g2 = 0;
		double b2 = 0;
		for (int i = 0; i < 7; i++) {
			val = Double.valueOf(scan.nextLine());
			arr.add(val);
		}
		
		double r1 = arr.get(0);
		double g1 = arr.get(1);
		double b1 = arr.get(2);
		double r3 = arr.get(3);
		double g3 = arr.get(4);
		double b3 = arr.get(5);
		double alpha = arr.get(6);
		
		if (type.equals("n")) {
			r2 = r1 + (r3-r1)/alpha;
			g2 = g1 + (g3-g1)/alpha;
			b2 = b1 + (b3-b1)/alpha;
		}
		else if (type.equals("m")) {
			r2 = 255 * (1 - (1 - r3 / r1) / alpha);
			g2 = 255 * (1 - (1 - g3 / g1) / alpha);
			b2 = 255 * (1 - (1 - b3 / b1) / alpha);
		}
		else if (type.equals("o")) {
			r2 = overlay(r1, r3, alpha);
			g2 = overlay(g1, g3, alpha);
			b2 = overlay(b1, b3, alpha);
		}
		else if (type.equals("s")) {
			if (r3 == 0 || g3 == 0 || b3 == 0)
				System.out.println("Error: Values below 0");
			r2 = (255 * alpha - r1 + r3) / alpha;
			g2 = (255 * alpha - g1 + g3) / alpha;
			b2 = (255 * alpha - b1 + b3) / alpha;
		}
		System.out.println(String.format("RGB: %f, %f, %f (%f)", r2, g2, b2, alpha));
	}
}
