import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// Color Finder v1.0 - Known bugs: Two zero values in the same slot results in loss of color info.

public class Main extends JFrame {
	static String mode;
	static double r2y, g2y, b2y, alpha;
	static JTextField r1b, g1b, b1b, r1t, g1t, b1t, r2b, g2b, b2b, r2t, g2t, b2t;
	static JLabel label1, label2;
	static JPanel sublabel1, sublabel2;
	static JProgressBar bar;
	
	public Main() {
		ArrayList<Image> list = new ArrayList<Image>();
		String[] modeList = {"Multiply", "Normal", "Overlay", "Screen", "Shade"};
		mode = "Normal";
		alpha = 1;
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
		JPanel content4 = new JPanel();
		content4.setLayout(new FlowLayout());
		
		JPanel subpanel1 = new JPanel();
		subpanel1.setBorder(BorderFactory.createTitledBorder("[1] Base color"));
		BoxLayout sp1 = new BoxLayout(subpanel1, BoxLayout.X_AXIS);
		subpanel1.setLayout(sp1);
		r1b = new JTextField(3);
		g1b = new JTextField(3);
		b1b = new JTextField(3);
		JButton set1 = new JButton("Set");
		subpanel1.add(r1b);
		subpanel1.add(g1b);
		subpanel1.add(b1b);
		subpanel1.add(set1);
		
		JPanel subpanel2 = new JPanel();
		subpanel2.setBorder(BorderFactory.createTitledBorder("Composite color"));
		BoxLayout sp2 = new BoxLayout(subpanel2, BoxLayout.X_AXIS);
		subpanel2.setLayout(sp2);
		r1t = new JTextField(3);
		g1t = new JTextField(3);
		b1t = new JTextField(3);
		JButton set2 = new JButton("Set");
		subpanel2.add(r1t);
		subpanel2.add(g1t);
		subpanel2.add(b1t);
		subpanel2.add(set2);
		
		JComboBox modes = new JComboBox(modeList);
		modes.setSelectedIndex(1);

		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
		slider.setMajorTickSpacing(25);
		slider.setMinorTickSpacing(5);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		//---//
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		//labelTable.put(0, new JLabel("0"));
		//labelTable.put(100, new JLabel("1"));
		//slider.setLabelTable(labelTable);
		//slider.setPaintLabels(true);
		JLabel value = new JLabel();
		value.setHorizontalAlignment(JLabel.CENTER);
		value.setText(Integer.toString(100) + "%");
		
		JPanel subpanel3 = new JPanel();
		subpanel3.setBorder(BorderFactory.createTitledBorder("[2] Base color"));
		BoxLayout sp3 = new BoxLayout(subpanel3, BoxLayout.X_AXIS);
		subpanel3.setLayout(sp3);
		r2b = new JTextField(3);
		g2b = new JTextField(3);
		b2b = new JTextField(3);
		JButton set3 = new JButton("Set");
		subpanel3.add(r2b);
		subpanel3.add(g2b);
		subpanel3.add(b2b);
		subpanel3.add(set3);
		
		JPanel subpanel4 = new JPanel();
		subpanel4.setBorder(BorderFactory.createTitledBorder("Composite color"));
		BoxLayout sp4 = new BoxLayout(subpanel4, BoxLayout.X_AXIS);
		subpanel4.setLayout(sp4);
		r2t = new JTextField(3);
		g2t = new JTextField(3);
		b2t = new JTextField(3);
		JButton set4 = new JButton("Set");
		subpanel4.add(r2t);
		subpanel4.add(g2t);
		subpanel4.add(b2t);
		subpanel4.add(set4);
		
		sublabel1 = new JPanel();
		sublabel1.setBorder(BorderFactory.createLineBorder(new Color(184, 207, 229), 2));
		label1 = new JLabel();
		label1.setText(" --, --, -- ");
		sublabel1.add(label1);
		
		sublabel2 = new JPanel();
		sublabel2.setBorder(BorderFactory.createLineBorder(new Color(184, 207, 229), 2));
		label2 = new JLabel();
		label2.setText(" --, --, -- ");
		sublabel2.add(label2);

		bar = new JProgressBar(0, 100);
		bar.setPreferredSize(new Dimension(300, 4));
		Border padding = BorderFactory.createEmptyBorder(0, 50, 0, 50);
		bar.setBorder(padding);

		
		ActionListener setter = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				change();
			}
		};
		
		set1.addActionListener(setter);
		set2.addActionListener(setter);
		set3.addActionListener(setter);
		set4.addActionListener(setter);
		
		modes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JComboBox cb = (JComboBox) event.getSource();
				mode = (String) cb.getSelectedItem();
				change();
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
				alpha = (double) source.getValue() / 100;
				change();
			}
		});
		
		content.add(subpanel1);
		content.add(subpanel2);
		content2.add(modes);
		content2.add(slider);
		content2.add(value);
		content3.add(subpanel3);
		content3.add(subpanel4);
		content4.add(sublabel1);
		content4.add(sublabel2);
		mainContent.add(content);
		mainContent.add(content3);
		mainContent.add(content2); 
		mainContent.add(bar);
		mainContent.add(content4);
		add(mainContent);
		
		setTitle("Color Finder");
		setSize(440, 270);
		setLocation(0, 0);
		setVisible(true);
	}
	
	public static void change() {
		double r1, g1, b1, r3, g3, b3, r2x, g2x, b2x = -1;
		try {
			r1 = Double.parseDouble(r1b.getText());
			g1 = Double.parseDouble(g1b.getText());
			b1 = Double.parseDouble(b1b.getText());
			r3 = Double.parseDouble(r1t.getText());
			g3 = Double.parseDouble(g1t.getText());
			b3 = Double.parseDouble(b1t.getText());
			select(r1, g1, b1, r3, g3, b3);
			r2x = r2y;
			g2x = g2y;
			b2x = b2y;
			label1.setText(String.format("%d, %d, %d", (int) r2x, (int) g2x, (int) b2x));
			try {
				sublabel1.setBorder(BorderFactory.createLineBorder(new Color((int) r2x, (int) g2x, (int) b2x), 2));
				sublabel1.setOpaque(true);
				sublabel1.setBackground(new Color((int) (166 + 0.3 * r2x), (int) (166 + 0.3 * g2x), (int) (166 + 0.3 * b2x)));
			}
			catch (Exception e) {
				sublabel1.setBorder(BorderFactory.createLineBorder(new Color(184, 207, 229), 2));
				sublabel1.setOpaque(false);
			}
			r1 = Double.parseDouble(r2b.getText());
			g1 = Double.parseDouble(g2b.getText());
			b1 = Double.parseDouble(b2b.getText());
			r3 = Double.parseDouble(r2t.getText());
			g3 = Double.parseDouble(g2t.getText());
			b3 = Double.parseDouble(b2t.getText());
			select(r1, g1, b1, r3, g3, b3);
			label2.setText(String.format("%d, %d, %d", (int) r2y, (int) g2y, (int) b2y));
			try {
				sublabel2.setBorder(BorderFactory.createLineBorder(new Color((int) r2y, (int) g2y, (int) b2y), 2));
				sublabel2.setOpaque(true);
				sublabel2.setBackground(new Color((int) (166 + 0.3 * r2y), (int) (166 + 0.3 * g2y), (int) (166 + 0.3 * b2y)));
			}
			catch (Exception e) {
				sublabel2.setBorder(BorderFactory.createLineBorder(new Color(184, 207, 229), 2));
				sublabel2.setOpaque(false);
			}
			System.out.println(r2x);
			//Color c1 = new Color((int) r2x, (int) g2x, (int) b2x); // prevents update of progress bar if invalid color
			//Color c2 = new Color((int) r2y, (int) g2y, (int) b2y);
			double prog = (Math.pow(Math.abs(r2y - r2x) / 3, 2) + Math.pow(Math.abs(g2y - g2x) / 3, 2) + Math.pow(Math.abs(b2y - b2x) / 3, 2)) / 3;
			if (prog < 100 && alpha > 0)
				bar.setValue((int) (100.1 - prog));
			else
				bar.setValue(0);
		}
		catch (Exception e) {
			System.out.println(e);
			//bar.setValue(0);
		}
	};
	
	public static void select(double r1, double g1, double b1, double r3, double g3, double b3) {
		if (alpha == 0) {
			alpha = 0.005;
		}
		if (mode.equals("Multiply")) {
			r2y = multiply(r1, r3, alpha);
			g2y = multiply(g1, g3, alpha);
			b2y = multiply(b1, b3, alpha);
		}
		else if (mode.equals("Normal")) {
			r2y = normal(r1, r3, alpha);
			g2y = normal(g1, g3, alpha);
			b2y = normal(b1, b3, alpha);
		}
		else if (mode.equals("Overlay")) {
			r2y = overlay(r1, r3, alpha);
			g2y = overlay(g1, g3, alpha);
			b2y = overlay(b1, b3, alpha);
		}
		else if (mode.equals("Screen")) {
			r2y = screen(r1, r3, alpha);
			g2y = screen(g1, g3, alpha);
			b2y = screen(b1, b3, alpha);
		}
		else if (mode.equals("Shade")) {
			r2y = shade(r1, r3, alpha);
			g2y = shade(g1, g3, alpha);
			b2y = shade(b1, b3, alpha);
		}		
	}
	
	public static double normal(double v1, double v3, double a) {
		return v1 + (v3-v1)/a;
	}
	
	public static double multiply(double v1, double v3, double a) {
		return 255 * (1 - (1 - v3 / v1) / a);
	}
	
	public static double overlay(double v1, double v3, double a) {
		double val = 0;
		if (v1 < 128)
			val = 255 * (v3 / v1 + a - 1) / (2 * a);
		else
			val = 255 * (((v3 - 255) + (1 - a) * (v3 - v1) / a) / (2 * (255 - v1)) + 1);	  
		return val;
	}
	
	public static double screen(double v1, double v3, double a) {
		return 255 * (((v3 - 255) + (1 - a) * (v3 - v1) / a) / ((255 - v1)) + 1);	  
	}
	
	public static double shade(double v1, double v3, double a) {
		if (v3 == 0)
			System.out.println("Error: Values below 0");
		return (255 * a - v1 + v3) / a;
	}
		
	public static void main(String[] args) {
		new Main();
	}
}
