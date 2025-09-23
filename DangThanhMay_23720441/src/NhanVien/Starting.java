package NhanVien;

public class Starting {
	public static void main(String[] args) {
        DanhSachNhanVien dao = new DanhSachNhanVien();
        new frmNhanVien(dao).setVisible(true);
    }
}
