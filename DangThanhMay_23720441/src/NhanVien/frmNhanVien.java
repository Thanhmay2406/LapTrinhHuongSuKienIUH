package NhanVien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class frmNhanVien extends JFrame implements ActionListener, MouseListener {
    private DanhSachNhanVien dsnv;
    private JTable table;
    private JTextField txtMaNV;
    private JTextField txtHo;
    private JTextField txtTen;
    private JTextField txtTuoi;
    private JRadioButton radNu;
    private JTextField txtTienLuong;
    private JTextField txtTim;
    private JButton btnTim;
    private JButton btnThem;
    private JButton btnXoa;
    private JButton btnLuu;
    private JButton btnXoaTrang;
    private DefaultTableModel tableModel;
    private fileDocGhi fi;
    private JComboBox cboPhong;
    private static final String tenfile = "data//abcd";
    
    public frmNhanVien(DanhSachNhanVien dao) throws Exception {
        setTitle("^_^");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel pnlNorth = new JPanel();
        add(pnlNorth, BorderLayout.NORTH);
        JLabel lblTieuDe = new JLabel("THÔNG TIN NHÂN VIÊN");
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
        lblTieuDe.setForeground(Color.BLUE);
        pnlNorth.add(lblTieuDe);
        
        Box b = Box.createVerticalBox();
        
        Box b1, b2, b3, b4, b5;
        JLabel lblMaNV, lblHo, lblTen, lblTuoi, lblPhai, lblPhong, lblTienLuong;
        
        b.add(b1 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b1.add(lblMaNV = new JLabel("Mã nhân viên: "));
        b1.add(txtMaNV = new JTextField());
        
        b.add(b2 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b2.add(lblHo = new JLabel("Họ: "));
        b2.add(txtHo = new JTextField());
        b2.add(lblTen = new JLabel("Tên nhân viên: "));
        b2.add(txtTen = new JTextField());
        
        b.add(b3 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b3.add(lblTuoi = new JLabel("Tuổi: "));
        b3.add(txtTuoi = new JTextField());
        b3.add(lblPhai = new JLabel("Phái: "));
        b3.add(radNu = new JRadioButton("Nữ"));
        
        b.add(b4 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b4.add(lblPhong = new JLabel("Phòng: "));
        String[] phong = {"Phòng tổ chức", "Phòng kỹ thuật", "Phòng nhân sự", "Phòng tài vụ"};
        b4.add(cboPhong = new JComboBox(phong));
        b4.add(lblTienLuong = new JLabel("Tiền lương: "));
        b4.add(txtTienLuong = new JTextField());
        
        lblHo.setPreferredSize(lblMaNV.getPreferredSize());
        lblPhai.setPreferredSize(lblMaNV.getPreferredSize());
        lblPhong.setPreferredSize(lblMaNV.getPreferredSize());
        lblTuoi.setPreferredSize(lblMaNV.getPreferredSize());
        
        b.add(b5 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        String[] headers = "MaNV;Ho;Ten;Phái;Tuoi;Phòng;TienLuong".split(";");
        tableModel = new DefaultTableModel(headers, 0);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(table = new JTable(tableModel));
        table.setRowHeight(25);
        table.setAutoCreateRowSorter(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        
        b5.add(scroll);
        add(b, BorderLayout.CENTER);
        
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        add(split, BorderLayout.SOUTH);
        JPanel pnlLeft, pnlRight;
        split.add(pnlLeft = new JPanel());
        split.add(pnlRight = new JPanel());
        
        pnlLeft.add(new JLabel("Nhập mã số cần tìm: "));
        pnlLeft.add(txtTim = new JTextField(10));
        pnlLeft.add(btnTim = new JButton("Tìm"));
        
        pnlRight.add(btnThem = new JButton("Thêm"));
        pnlRight.add(btnXoaTrang = new JButton("Xóa trắng"));
        pnlRight.add(btnXoa = new JButton("Xóa"));
        pnlRight.add(btnLuu = new JButton("Lưu"));
        
        btnThem.addActionListener(this);
        btnXoa.addActionListener(this);
        btnXoaTrang.addActionListener(this);
        btnLuu.addActionListener(this);
        table.addMouseListener(this);
        
        dsnv = new DanhSachNhanVien();
        fi = new fileDocGhi();
        try {
            dsnv = (DanhSachNhanVien) fi.readFromFile(tenfile);
        } catch (Exception e) {
            System.out.println("Không tìm thấy file");
        }
        hienTable();
    }
    
    public void hienTable() {
        tableModel.setRowCount(0);
        for (int i = 0; i < dsnv.tong(); i++) {
            NhanVien nv = dsnv.getNhanVien(i);
            String[] dataRow = {nv.getMaNV() + "", nv.getHo(), nv.getTen(), 
                              nv.isPhai() ? "Nữ" : "Nam", nv.getTuoi() + "", 
                              nv.getPhong(), nv.getTienLuong() + ""};
            tableModel.addRow(dataRow);
        }
    }
    
    private void xoaTrangActions() {
        txtMaNV.setText("");
        txtHo.setText("");
        txtTen.setText("");
        txtTuoi.setText("");
        txtTim.setText("");
        txtTienLuong.setText("");
        radNu.setSelected(false);
        txtMaNV.requestFocus();
    }
    
    private void themActions() {
        try {
            NhanVien nv = null;
            int maNV = Integer.parseInt(txtMaNV.getText());
            String ho = txtHo.getText();
            String ten = txtTen.getText();
            boolean phai = radNu.isSelected();
            int tuoi = Integer.parseInt(txtTuoi.getText());
            String phong = (String) cboPhong.getSelectedItem();
            double tienLuong = Double.parseDouble(txtTienLuong.getText());
            
            nv = new NhanVien(maNV, ho, ten, phai, tuoi, phong, tienLuong);
            
            if (dsnv.themNhanVien(nv)) {
                String[] row = {nv.getMaNV() + "", nv.getHo(), nv.getTen(), 
                              phai ? "Nữ" : "Nam", nv.getTuoi() + "", 
                              nv.getPhong(), nv.getTienLuong() + ""};
                tableModel.addRow(row);
                xoaTrangActions();
            } else {
                JOptionPane.showMessageDialog(null, "Trùng mã nhân viên.");
                txtMaNV.selectAll();
                txtMaNV.requestFocus();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi nhập liệu.");
            return;
        }
    }
    
    private void xoaActions() {
        int row = table.getSelectedRow();
        if (row != -1) {
            int maNV = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
            int hoiNhac = JOptionPane.showConfirmDialog(this, "Chắc chắn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            
            if (hoiNhac == JOptionPane.YES_OPTION) {
                if (dsnv.xoaNhanVien(maNV)) {
                    tableModel.removeRow(row);
                }
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThem))
            themActions();
        if (o.equals(btnXoa))
            xoaActions();
        if (o.equals(btnXoaTrang))
            xoaTrangActions();
        if (o.equals(btnLuu)) {
            fi = new fileDocGhi();
            try {
                fi.writeToFile(dsnv, tenfile);
                JOptionPane.showMessageDialog(this, "Lưu thành công!");
            } catch (Exception el) {
                JOptionPane.showMessageDialog(this, "Lỗi khi lưu file!");
                el.printStackTrace();
            }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            txtMaNV.setText(table.getValueAt(row, 0).toString());
            txtHo.setText(table.getValueAt(row, 1).toString());
            txtTen.setText(table.getValueAt(row, 2).toString());
            radNu.setSelected(table.getValueAt(row, 3).equals("Nữ"));
            txtTuoi.setText(table.getValueAt(row, 4).toString());
            cboPhong.setSelectedItem(table.getValueAt(row, 5).toString());
            txtTienLuong.setText(table.getValueAt(row, 6).toString());
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {}
    
    @Override
    public void mouseExited(MouseEvent e) {}
    
    @Override
    public void mousePressed(MouseEvent e) {}
    
    @Override
    public void mouseReleased(MouseEvent e) {}
}