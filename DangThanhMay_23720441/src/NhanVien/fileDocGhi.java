package NhanVien;

import java.io.*;

public class fileDocGhi {
    public static void writeToFile(DanhSachNhanVien dsnv, String file) throws Exception {
        ObjectOutputStream out = null;
        out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(dsnv);
        out.close();
    }
    
    public Object readFromFile(String file) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Object list = ois.readObject();
        ois.close();
        return list;
    }
}