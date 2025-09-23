package Tuan1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class Bai3_CongTruNhanChia extends JFrame implements ActionListener {
	
	private JButton btnCal, btnDel, btnExit;
	private JTextField inp1, inp2, output;
	private JRadioButton cong, tru, nhan, chia;
	
	public Bai3_CongTruNhanChia() {
		JPanel pmain = new JPanel();
		pmain.setLayout(new BorderLayout());
		
		// north
		JPanel pnNorth = new JPanel();
		JLabel title = new JLabel("Cộng Trừ Nhân Chia");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		title.setForeground(Color.BLUE);
		pnNorth.add(title);
		pmain.add(pnNorth, BorderLayout.NORTH);
		
		//west
		JPanel pnWest = new JPanel();
		pnWest.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));
		pnWest.setLayout(new BoxLayout(pnWest, BoxLayout.Y_AXIS));
		pnWest.add(Box.createRigidArea(new Dimension(40, 0)));
		btnCal = new JButton("  Giải  ");
		btnCal.addActionListener(this);
		btnDel = new JButton("  Xóa   ");
		btnDel.addActionListener(this);
		btnExit = new JButton("Thoát ");
		btnExit.addActionListener(this);
		pnWest.add(Box.createVerticalStrut(10));
		pnWest.add(btnCal);
		pnWest.add(Box.createVerticalStrut(20));
		pnWest.add(btnDel);
		pnWest.add(Box.createVerticalStrut(20));
		pnWest.add(btnExit);
		pmain.add(pnWest, BorderLayout.WEST);
		
		// center
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		pnCenter.setBorder(BorderFactory.createTitledBorder("Tính toán"));
		
		JPanel r1 = new JPanel();
		inp1 = new JTextField(32);
		inp1.addActionListener(this);
		r1.add(new JLabel("Nhập a: "));
		r1.add(inp1);
		pnCenter.add(r1);
		
		JPanel r2 = new JPanel();
		inp2 = new JTextField(32);
		inp2.addActionListener(this);
		r2.add(new JLabel("Nhập b: "));
		r2.add(inp2);
		pnCenter.add(r2);
		
		JPanel r3 = new JPanel();
		JPanel pheptoan = new JPanel();
		pheptoan.setBorder(BorderFactory.createTitledBorder("Phép toán"));
		ButtonGroup gr = new ButtonGroup();
		cong = new JRadioButton("cộng");
		cong.setSelected(true);
		tru = new JRadioButton("Trừ");
		nhan = new JRadioButton("Nhân");
		chia = new JRadioButton("Chia");
		gr.add(cong);
		gr.add(tru);
		gr.add(nhan);
		gr.add(chia);
		pheptoan.setLayout(new GridLayout(2, 4));
		pheptoan.add(cong);
		pheptoan.add(Box.createHorizontalStrut(50));
		pheptoan.add(tru);
		pheptoan.add(Box.createHorizontalStrut(50));
		pheptoan.add(nhan);
		pheptoan.add(Box.createHorizontalStrut(50));
		pheptoan.add(chia);
		pheptoan.add(Box.createHorizontalStrut(50));
		r3.add(pheptoan);
		
		pnCenter.add(r3);
		
		JPanel r4 = new JPanel();
		r4.add(new JLabel("Kết quả: "));
		output = new JTextField(32);
		output.addActionListener(this);
		output.setEditable(false);
		r4.add(output);
		pnCenter.add(r4);
		
		pmain.add(pnCenter, BorderLayout.CENTER);
		
		JPanel pnSouth = new JPanel();
		pnSouth.setBackground(Color.PINK);
		pnSouth.add(Box.createRigidArea(new Dimension(0, 50)));
		
		JButton x1 = new JButton();
		x1.add(Box.createRigidArea(new Dimension(5, 25)));
		x1.setBackground(Color.BLUE);
		pnSouth.add(x1);
		JButton x2 = new JButton();
		x2.add(Box.createRigidArea(new Dimension(5, 25)));
		x2.setBackground(Color.RED);
		pnSouth.add(x2);
		JButton x3 = new JButton();
		x3.add(Box.createRigidArea(new Dimension(5, 25)));
		x3.setBackground(Color.YELLOW);
		pnSouth.add(x3);
		
		pmain.add(pnSouth, BorderLayout.SOUTH);
		
		add(pmain);
		setTitle("Cộng-Trừ-Nhân-Chia");
		setSize(600, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Bai3_CongTruNhanChia();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		
		
		if (obj.equals(btnCal)) {
			double x1 = 0, x2 = 0;
			String s1 = inp1.getText(), s2 = inp2.getText();
			
			if (s1.equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập A");
				inp1.requestFocus();
			}
			else {
				if (s2.equals("")) {
					JOptionPane.showMessageDialog(this, "Bạn chưa nhập B");
				}
				else {
					try {
						x1 = Double.parseDouble(s1);
					}
					catch (Exception ex) {
						JOptionPane.showMessageDialog(this, "Nhập sai định dạng");
						inp1.selectAll();
						inp1.requestFocus();
					}
					
					try {
						x2 = Double.parseDouble(s2);
					}
					catch (Exception ex) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(this, "Nhập sai định dạng");
						inp2.selectAll();
						inp2.requestFocus();
					}
				}
			}
			if (cong.isSelected()) {
				output.setText("" + (x1 + x2));
			}
			else if (tru.isSelected()) {
				output.setText("" + (x1 - x2));
			}
			else if (nhan.isSelected()) {
				output.setText("" + (x1 * x2));
			}
			else {
				output.setText("" + (x1 / x2));
			}
		}
		else if (obj.equals(btnDel)) {
			inp1.setText("");
			inp2.setText("");
			output.setText("");
			inp1.requestFocus();
		}
		else if (obj.equals(btnExit)) {
			System.exit(0);
		}
	}

}
