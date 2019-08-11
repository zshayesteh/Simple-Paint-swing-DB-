package HW9_simplePaint;

import java.awt.AlphaComposite;
import HW9_simplePaint.Person;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DrawPage extends JFrame {
	private JPanel panel;
	private JButton btn_line;
	private JButton btn_circle;
	private JButton btn_rectangle;
	private JButton btn_exit;
	private JComboBox comboBox_colors;
	private JPanel panel_drawing;
	private pageDBManager pDBM;
	private String fileAdr;
	private Page page;

	public DrawPage() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				BufferedImage myPicture;
				if (Person.getUsername().equals("zahra"))
					fileAdr = "E:/java files/maktabPractices/snapshot-zahra";
				else if (Person.getUsername().equals("sara"))
					fileAdr = "E:/java files/maktabPractices/snapshot-sara";
				else if (Person.getUsername().equals("ali"))
					fileAdr = "E:/java files/maktabPractices/snapshot-ali";
				else
					fileAdr = "E:/java files/maktabPractices/snapshot-mohammad";
				page = new Page(fileAdr, Person.getUsername());
				try {
					pDBM = new pageDBManager();
					if (pDBM.getSnapshot(page).equals(""))
						myPicture = ImageIO.read(new File("E:/java files/maktabPractices/empty_snapshot"));
					else
						myPicture = ImageIO.read(new File(pDBM.getSnapshot(page)));
					JLabel picLabel = new JLabel(new ImageIcon(myPicture));
					panel_drawing.add(picLabel);
				} catch (SQLException e) {
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "error IO!");
				}
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				if (Person.getUsername().equals("zahra"))
					fileAdr = "E:/java files/maktabPractices/snapshot-zahra";
				else if (Person.getUsername().equals("sara"))
					fileAdr = "E:/java files/maktabPractices/snapshot-sara";
				else if (Person.getUsername().equals("ali"))
					fileAdr = "E:/java files/maktabPractices/snapshot-ali";
				else
					fileAdr = "E:/java files/maktabPractices/snapshot-mohammad";
				saveComponentAsJPEG(panel_drawing, fileAdr);
				page = new Page(fileAdr, Person.getUsername());
				pDBM = new pageDBManager();
				try {
					pDBM.insertSnapshot(page);
				} catch (SQLException e) {
				}
			}

		});
		setTitle("صفحه نقاشی");
		setBounds(100, 100, 667, 567);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setBackground(new Color(250, 235, 215));
		panel.setBounds(185, 119, 10, 10);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);

		btn_line = new JButton("خط");
		btn_line.setBounds(528, 24, 110, 36);

		btn_line.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btn_line);

		btn_circle = new JButton("دایره/بیضی");
		btn_circle.setBounds(528, 75, 110, 36);
		btn_circle.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btn_circle);

		btn_rectangle = new JButton("مستطیل");
		btn_rectangle.setBounds(528, 125, 110, 36);
		btn_rectangle.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btn_rectangle);

		btn_exit = new JButton("خروج");
		btn_exit.setBounds(528, 481, 110, 36);
		btn_exit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Person.getUsername().equals("zahra"))
					fileAdr = "E:/java files/maktabPractices/snapshot-zahra";
				else if (Person.getUsername().equals("sara"))
					fileAdr = "E:/java files/maktabPractices/snapshot-sara";
				else if (Person.getUsername().equals("ali"))
					fileAdr = "E:/java files/maktabPractices/snapshot-ali";
				else
					fileAdr = "E:/java files/maktabPractices/snapshot-mohammad";
				saveComponentAsJPEG(panel_drawing, fileAdr);
				page = new Page(fileAdr, Person.getUsername());
				pDBM = new pageDBManager();
				try {
					pDBM.insertSnapshot(page);
				} catch (SQLException e) {
				}
				setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				dispose();
				new LoginPageGUI().setVisible(true);
			}
		});
		panel.add(btn_exit);

		comboBox_colors = new JComboBox();
		comboBox_colors.setBounds(528, 176, 110, 36);
		comboBox_colors.setFont(new Font("Tahoma", Font.BOLD, 12));

		comboBox_colors.setName("رنگ");
		comboBox_colors.addItem("مشکی");
		comboBox_colors.addItem("آبی");
		comboBox_colors.addItem("قرمز");
		comboBox_colors.addItem("سبز");

		panel.setLayout(null);
		panel.add(comboBox_colors);

		panel_drawing = new JPanel();

		panel_drawing.setBounds(26, 24, 478, 493);
		panel_drawing.setBorder(new LineBorder(Color.GRAY));
		panel_drawing.setBackground(Color.WHITE);
		panel_drawing.setLayout(new BorderLayout(0, 0));

		panel.add(panel_drawing);
		panel_drawing.add(new PaintSurface());
		setVisible(true);

	}

	public void saveComponentAsJPEG(Component myComponent, String filename) { // for
																				// saving
																				// the
																				// draw
																				// page
																				// after
																				// closing
																				// it
		Dimension size = myComponent.getSize();
		BufferedImage myImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = myImage.createGraphics();
		myComponent.paint(g2);
		try {
			OutputStream out = new FileOutputStream(filename);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(myImage);
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private class PaintSurface extends JComponent {// the main painting class
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		ArrayList<Color> colors = new ArrayList<Color>();
		int shapeFlag;
		Color color = Color.BLACK;
		Point startDragCord, endDragCord;

		public PaintSurface() {
			comboBox_colors.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					if (comboBox_colors.getSelectedItem().equals("مشکی"))
						color = Color.BLACK;
					if (comboBox_colors.getSelectedItem().equals("آبی"))
						color = Color.BLUE;
					if (comboBox_colors.getSelectedItem() == "سبز")
						color = Color.GREEN;
					if (comboBox_colors.getSelectedItem() == "قرمز")
						color = Color.RED;
				}
			});

			btn_line.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					shapeFlag = 0;
				}
			});

			btn_rectangle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					shapeFlag = 1;
				}
			});
			btn_circle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					shapeFlag = 2;
				}
			});
			panel_drawing.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					startDragCord = new Point(e.getX(), e.getY());
					endDragCord = startDragCord;
					repaint();
				}

				public void mouseReleased(MouseEvent e) {
					Shape r;
					try {
						if (shapeFlag == 0)
							r = makeLine(startDragCord.x, startDragCord.y, e.getX(), e.getY());
						else if (shapeFlag == 1)
							r = makeRectangle(startDragCord.x, startDragCord.y, e.getX(), e.getY());
						else
							r = makeEllipse(startDragCord.x, startDragCord.y, e.getX(), e.getY());
						shapes.add(r);
						colors.add(color);
						//startDragCord = null;
						//endDragCord = null;
						repaint();
					} catch (NullPointerException eN) {
					}
				}
			});

			panel_drawing.addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
					endDragCord = new Point(e.getX(), e.getY());
					repaint();
				}
			});
		}

		public void paint(Graphics g) {

			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setStroke(new BasicStroke(2));
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));
			for (int i = 0; i < shapes.size(); i++) {
				try {
					g2.setPaint(colors.get(i));
					g2.draw(shapes.get(i));
					g2.setPaint(color.LIGHT_GRAY);
				} catch (NullPointerException eN) {
				}
			}

			if (startDragCord != null && endDragCord != null) {
				Shape r;
				if (shapeFlag == 0) {
					r = makeLine(startDragCord.x, startDragCord.y, endDragCord.x, endDragCord.y);
					g2.draw(r);
				} else if (shapeFlag == 1) {
					r = makeRectangle(startDragCord.x, startDragCord.y, endDragCord.x, endDragCord.y);
					g2.draw(r);
				} else if (shapeFlag == 2) {
					r = makeEllipse(startDragCord.x, startDragCord.y, endDragCord.x, endDragCord.y);
					g2.draw(r);
				}
				g2.setPaint(color);
			}
		}

		private Line2D.Float makeLine(int x1, int y1, int x2, int y2) {// drawing
																		// a
																		// line
			return new Line2D.Float(x1, y1, x2, y2);
		}

		private Ellipse2D.Float makeEllipse(int x1, int y1, int x2, int y2) {// drawing
																				// an
																				// ellipse
			return new Ellipse2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
		}

		private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {// drawing
																					// a
																					// rectangle
			return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
		}
	}
}