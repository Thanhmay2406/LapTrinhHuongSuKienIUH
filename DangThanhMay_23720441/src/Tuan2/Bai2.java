package Tuan2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Bai2 extends JFrame implements ActionListener {
	private JButton sochan, sole, prime, boto, del, sum, inp, exit;
	private JTextField txt;
	private JCheckBox soam;
	private DefaultListModel<Integer> ls1;
	private JList<Integer> ls;
	private JScrollPane arr;
	public Bai2() {
		/*------------------------------------------------------*/
		JPanel pnNorth = new JPanel();
		JLabel title = new JLabel("Thao tác trên JList - Checkbox");
		title.setFont(new Font("Arial", Font.BOLD, 25));
		title.setForeground(Color.BLUE);
		pnNorth.add(title);
		add(pnNorth, BorderLayout.NORTH);
		
		/*------------------------------------------------------*/
		JPanel pnWest = new JPanel();
		pnWest.setLayout(new BoxLayout(pnWest, BoxLayout.Y_AXIS));
		Border b1 = BorderFactory.createLineBorder(Color.RED);
		pnWest.setBorder(BorderFactory.createTitledBorder(b1, "Chọn tác vụ"));
		sochan = new JButton("Tô đen số chẵn");
		sole = new JButton("Tô đen số lẻ");
		prime = new JButton("Tô đen số nguyên tố");
		boto = new JButton("Bỏ tô đen");
		del = new JButton("Xóa các giá trị đang tô đen");
		sum = new JButton("Tổng giá trị trong JList");
		pnWest.add(sochan);
		pnWest.add(sole);
		pnWest.add(prime);
		pnWest.add(boto);
		pnWest.add(del);
		pnWest.add(sum);
		
		sochan.addActionListener(this);
		sole.addActionListener(this);
		prime.addActionListener(this);
		boto.addActionListener(this);
		del.addActionListener(this);
		sum.addActionListener(this);
		add(pnWest, BorderLayout.WEST);
		
		/*------------------------------------------------------*/
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		pnCenter.setBorder(BorderFactory.createTitledBorder(b1, "Nhập thông tin:"));
		JPanel sub1 = new JPanel();
		inp = new JButton("Nhập");
		txt = new JTextField(10);
		soam = new JCheckBox("Cho nhập số âm");
		sub1.add(inp);
		sub1.add(txt);
		sub1.add(soam);
		inp.addActionListener(this);
		pnCenter.add(sub1, BorderLayout.NORTH);
		JPanel sub2 = new JPanel();
		ls1 = new DefaultListModel<Integer>();
		ls = new JList<Integer>(ls1);
		ls.setFixedCellWidth(300);
		arr = new JScrollPane(ls);
		sub2.add(arr);
		pnCenter.add(sub2, BorderLayout.CENTER);
		add(pnCenter, BorderLayout.CENTER);
		
		/*------------------------------------------------------*/
		JPanel pnSouth = new JPanel();
		pnSouth.setBackground(Color.GRAY);
		pnSouth.setBorder(b1);
		exit = new JButton("Đóng chương trình");
		pnSouth.add(exit);
		exit.addActionListener(this);
		add(pnSouth, BorderLayout.SOUTH);
		
		/*------------------------------------------------------*/
		setTitle("Thao tác trên JList");
		setSize(600, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Bai2();
	}
	@Override
	public void actionPerformed(ActionEvent ex) {
		// TODO Auto-generated method stub
		Object o = ex.getSource();
		if (o.equals(exit)) {
			System.exit(0);
		}
		else if (o.equals(sochan)) {
			fillsochan();
		}
		else if (o.equals(sole)) {
			fillsole();
		}
		else if (o.equals(prime)) {
			fillprime();
		}
		else if (o.equals(boto)) {
			ls.clearSelection();
		}
		else if (o.equals(del)) {
			remove();
		}
		else if (o.equals(sum)) {
			tong();
		}
		else if (o.equals(inp)) {
			nhap();
		}
	}
	
	private void fillsochan() {
		ls.clearSelection();
		for (int i=0 ; i<ls1.getSize() ; i++) {
			if (ls1.getElementAt(i) % 2 == 0) {
				ls.addSelectionInterval(i, i);
			}
		}
	}
	
	private void fillsole() {
		ls.clearSelection();
		for (int i=0 ; i<ls1.getSize() ; i++) {
			if (ls1.getElementAt(i) % 2 == 1) {
				ls.addSelectionInterval(i, i);
			}
		}
	}
	
	private void fillprime() {
		ls.clearSelection();
		for (int i=0 ; i<ls1.getSize() ; i++) {
			if (check_prime(ls1.getElementAt(i))) {
				ls.addSelectionInterval(i, i);
			}
		}
	}
	
	private boolean check_prime(int n) {
		if (n < 2) return false;
		for (int i=2 ; i*i<=n ; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	private void remove() {
		Object[] items = ls.getSelectedValues();
		for (Object val : items) {
			ls1.removeElement(val);
		}
	}
	
	private void tong() {
		int res = 0;
		for (int i=0 ; i<ls1.getSize() ; i++) {
			res += ls1.getElementAt(i);
		}
		JOptionPane.showMessageDialog(this, "tổng giá trị trong list: " + res);
	}
	
	private void nhap() {
		String s = txt.getText();
		if (s.equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa nhập liệu");
			return;
		}
		
		int n;
		
		try {
			n = Integer.parseInt(s);
			if (n >= 0) {
				ls1.addElement(n);
				txt.setText("");
				txt.requestFocus();
			}
			else {
				if (soam.isSelected()) {
					ls1.addElement(n);
					txt.setText("");
					txt.requestFocus();
				}
				else {
					JOptionPane.showConfirmDialog(this, "chưa chọn 'cho nhập số âm'");
					txt.setText("");
					txt.requestFocus();
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Nhập sai định dạng");
			txt.setText("");
			txt.requestFocus();
		}
	}
	
}
