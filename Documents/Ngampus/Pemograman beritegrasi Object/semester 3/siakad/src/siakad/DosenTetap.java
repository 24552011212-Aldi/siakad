package siakad;

public class DosenTetap extends Dosen {
    private String nip;

    public DosenTetap(String nama, String nip) {
        super(nama);
        this.nip = nip;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    @Override
    public void absen() {
        System.out.println("Dosen Tetap " + getNama() + " (NIP: " + nip + ") absen.");
    }
}
