package NhanVien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class frmNhanVien extends JFrame implements ActionListener, MouseListener {
    private DanhSachNhanVien dao;
    private ArrayList<NhanVien> list;
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

    public frmNhanVien(DanhSachNhanVien dao) {
        setTitle("^_^");
        setSize(700, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.dao = dao;
        list = dao.getList();

        JPanel pnlNorth;
        add(pnlNorth = new JPanel(), BorderLayout.NORTH);
        JLabel lblTieuDe;
        pnlNorth.add(lblTieuDe = new JLabel("THÔNG TIN NHÂN VIÊN"));
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
        lblTieuDe.setForeground(Color.BLUE);

        Box b = Box.createVerticalBox();
        Box b1, b2, b3, b4, b5;
        JLabel lblMaNV, lblHo, lblTen, lblTuoi, lblPhai, lblTienLuong;

        // Hàng 1: Mã nhân viên
        b.add(b1 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b1.add(lblMaNV = new JLabel("Mã nhân viên: "));
        b1.add(Box.createHorizontalStrut(20));
        b1.add(txtMaNV = new JTextField());

        // Hàng 2: Họ + Tên
        b.add(b2 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b2.add(lblHo = new JLabel("Họ: "));
        b2.add(txtHo = new JTextField());
        b2.add(lblTen = new JLabel("Tên nhân viên: "));
        b2.add(txtTen = new JTextField());

        // Hàng 3: Tuổi + Phái
        b.add(b3 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b3.add(lblTuoi = new JLabel("Tuổi: "));
        b3.add(txtTuoi = new JTextField());
        b3.add(lblPhai = new JLabel("Phái: "));
        b3.add(radNu = new JRadioButton("Nữ"));

        // Hàng 4: Tiền lương
        b.add(b4 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b4.add(lblTienLuong = new JLabel("Tiền lương: "));
        b4.add(txtTienLuong = new JTextField());

        lblHo.setPreferredSize(lblMaNV.getPreferredSize());
        lblPhai.setPreferredSize(lblMaNV.getPreferredSize());
        lblTienLuong.setPreferredSize(lblMaNV.getPreferredSize());
        lblTuoi.setPreferredSize(lblMaNV.getPreferredSize());

        // Bảng JTable
        b.add(b5 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        String[] headers = "MaNV;Ho;Ten;Phai;Tuoi;TienLuong".split(";");
        tableModel = new DefaultTableModel(headers, 0);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(table = new JTable(tableModel));
        table.setRowHeight(25);
        b5.add(scroll);

        add(b, BorderLayout.CENTER);

        // Panel South: tìm kiếm + nút chức năng
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

        // Add action listeners and mouse listener
        btnThem.addActionListener(this);
        btnXoa.addActionListener(this);
        btnXoaTrang.addActionListener(this);
        btnLuu.addActionListener(this);
        table.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThem)) {
            themActions();
        }
        if (o.equals(btnXoa)) {
            xoaActions();
        }
        if (o.equals(btnXoaTrang)) {
            xoaTrangActions();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.getSelectedRow(); // lay dong dang chon tren table
        txtMaNV.setText(table.getValueAt(row, 0).toString());
        txtHo.setText(table.getValueAt(row, 1).toString());
        txtTen.setText(table.getValueAt(row, 2).toString());
        radNu.setSelected(false);
        if (table.getValueAt(row, 3).toString().equalsIgnoreCase("true")) {
            radNu.setSelected(true);
        }
        txtTuoi.setText(table.getValueAt(row, 4).toString());
        txtTienLuong.setText(table.getValueAt(row, 5).toString());
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    private void xoaTrangActions() {
        txtMaNV.setText("");
        txtHo.setText("");
        txtTen.setText("");
        txtTuoi.setText("");
        txtTienLuong.setText("");
        txtMaNV.requestFocus();
    }

    private void xoaActions() {
        int row = table.getSelectedRow();
        if (row != -1) {
            int maNV = Integer.parseInt((String) table.getModel().getValueAt(row, 0));
            int hoiNhac = JOptionPane.showConfirmDialog(this, "Chắc chắn xóa không? ", "Chú ý", JOptionPane.YES_NO_OPTION);
            if (hoiNhac == JOptionPane.YES_OPTION) {
                if (dao.xoaNhanVien(maNV)) {
                    tableModel.removeRow(row);
                }
            }
        }
    }

    private void themActions() {
        try {
            int maNV = Integer.parseInt(txtMaNV.getText());
            String ho = txtHo.getText();
            String ten = txtTen.getText();
            boolean phai = radNu.isSelected() ? true : false;
            int tuoi = Integer.parseInt(txtTuoi.getText());
            double tienLuong = Double.parseDouble(txtTienLuong.getText());
            NhanVien nv = new NhanVien(maNV, ho, ten, phai, tuoi, tienLuong);
            if (dao.themNhanVien(nv)) {
                String[] row = {Integer.toString(maNV), ho, ten, Boolean.toString(phai), Integer.toString(tuoi), tienLuong + ""};
                tableModel.addRow(row);
                xoaTrangActions();
            } else {
                JOptionPane.showMessageDialog(null, "Trùng mã nhân viên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                txtMaNV.requestFocus();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi nhập liệu.");
            return;
        }
    }
}