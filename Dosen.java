

package siakad;

public abstract class Dosen {
    protected String nama;

    public Dosen(String nama) {
        this.nama = nama;
    }


    public abstract void absen();

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}