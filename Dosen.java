
package siakad;

public class Dosen {
    protected String nama;

    public Dosen(String nama) {
        this.nama = nama;
    }

    public void absen() {
        System.out.println("Dosen " + nama + " absen.");
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}