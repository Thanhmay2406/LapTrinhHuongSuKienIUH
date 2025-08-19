package Tuan1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GiaiPTBac2 extends JFrame implements ActionListener {

	private JTextField inp1, inp2, inp3, output;
	private JButton cal, del, exit;

	public GiaiPTBac2() {

		setTitle("^_^");

		JPanel pnNorth = new JPanel();

		pnNorth.setBackground(Color.decode("#00FFFF"));

		JLabel title = new JLabel("GIẢI PHƯƠNG TRÌNH BẬC HAI");

		title.setFont(new Font("Arial", Font.BOLD, 16));

		pnNorth.add(title);

		add(pnNorth, BorderLayout.NORTH);

		JPanel pnCenter = new JPanel();

		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		pnCenter.add(Box.createRigidArea(new Dimension(0, 26)));

		// row 1
		JPanel row1 = new JPanel();
		inp1 = new JTextField(30);
		pnCenter.add(row1);
		JPanel sp1 = new JPanel();
		sp1.add(new JLabel("Nhập a: "));
		sp1.add(Box.createHorizontalStrut(60));

		row1.add(sp1);
		row1.add(inp1);

		// row 2
		JPanel row2 = new JPanel();
		inp2 = new JTextField(30);
		JPanel sp2 = new JPanel();
		sp2.add(new JLabel("Nhập b: "));
		sp2.add(Box.createHorizontalStrut(60));

		row2.add(sp2);
		row2.add(inp2);
		pnCenter.add(row2);

		// row 3
		JPanel row3 = new JPanel();
		inp3 = new JTextField(30);
		JPanel sp3 = new JPanel();
		sp3.add(new JLabel("Nhập c: "));
		sp3.add(Box.createHorizontalStrut(60));
		row3.add(sp3);
		row3.add(inp3);
		pnCenter.add(row3);

		// answer
		JPanel row4 = new JPanel();
		output = new JTextField(30);
		JPanel sp5 = new JPanel();
		sp5.add(new JLabel("Kết quả: "));
		sp5.add(Box.createHorizontalStrut(60));
		row4.add(sp5);
		row4.add(output);
		pnCenter.add(row4);

		add(pnCenter, BorderLayout.CENTER);

		JPanel pnSouth = new JPanel();

		pnSouth.setBorder(BorderFactory.createTitledBorder(null, "Chọn tác vụ"));

		pnSouth.add(cal = new JButton("Giải"));
		cal.addActionListener(this);
		pnSouth.add(del = new JButton("Xóa rỗng"));
		del.addActionListener(this);
		pnSouth.add(exit = new JButton("Thoát"));
		exit.addActionListener(this);

		add(pnSouth, BorderLayout.SOUTH);

		setSize(500, 350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GiaiPTBac2();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		double a = 0, b = 0, c = 0, delta = 0, x1 = 0, x2 = 0;
		if (obj == cal) {
			a = Double.parseDouble(inp1.getText());
			b = Double.parseDouble(inp2.getText());
			c = Double.parseDouble(inp3.getText());
			
			if (a == 0) {
				if (b == 0) {
					if (c == 0) {
						output.setText("Phương trình vô số nghiệm");
					}
					else {
						output.setText("Phương trình vô nghiệm");
					}
				}
			}
			else {
				delta = b * b - 4 * a * c;
				if (delta < 0) {
					output.setText("Phương trình vô nghiệm");
				}
				else if( delta == 0 ) {
					output.setText("x1 = x2 = " + (-b) / a);
				}
				else {
					x1 = (-b + Math.sqrt(delta)) / (2 * a);
					x2 = (-b - Math.sqrt(delta)) / (2 * a);
					output.setText("x1 = " + x1 + "\tx2 = " + x2 );
				}
			}
		}
		else if (obj == del) {
			inp1.setText("");
			inp2.setText("");
			inp3.setText("");
			output.setText("");
		}
		else {
			System.exit(0);
		}
	}

}
