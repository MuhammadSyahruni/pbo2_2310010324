// Nama: ISI NAMA KAMU
// NPM: ISI NPM KAMU

package id.uniska.pbo2.uts;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class BarangTableModel extends AbstractTableModel {

    private final List<Barang> daftarBarang = new ArrayList<>();

    private final String[] kolom = {
        "Nama Barang",
        "Kategori",
        "Harga",
        "Jumlah",
        "Subtotal"
    };

    @Override
    public int getRowCount() {
        return daftarBarang.size();
    }

    @Override
    public int getColumnCount() {
        return kolom.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolom[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Barang barang = daftarBarang.get(row);

        switch (column) {
            case 0:
                return barang.getNama();
            case 1:
                return barang.getKategori();
            case 2:
                return barang.getHarga();
            case 3:
                return barang.getJumlah();
            case 4:
                return barang.getSubtotal();
            default:
                return null;
        }
    }

    public void tambahBarang(Barang barang) {
        daftarBarang.add(barang);
        fireTableRowsInserted(
            daftarBarang.size() - 1,
            daftarBarang.size() - 1
        );
    }

    public boolean sudahAda(String nama) {
        for (Barang barang : daftarBarang) {
            if (barang.getNama().equalsIgnoreCase(nama.trim())) {
                return true;
            }
        }
        return false;
    }

    public int getTotal() {
        int total = 0;

        for (Barang barang : daftarBarang) {
            total += barang.getSubtotal();
        }

        return total;
    }

    public int getDiskon() {
        if (getTotal() >= 100000) {
            return (int) (getTotal() * 0.10);
        }

        return 0;
    }

    public int getBayar() {
        return getTotal() - getDiskon();
    }
    public void hapusBarang(int index) {
    if (index >= 0 && index < daftarBarang.size()) {
        daftarBarang.remove(index);
        fireTableRowsDeleted(index, index);
    }
}
}