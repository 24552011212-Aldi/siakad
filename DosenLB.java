
package siakad;

public class DosenLB extends Dosen {
    private String noKontrak;

    public DosenLB(String nama, String noKontrak) {
        super(nama);
        this.noKontrak = noKontrak;
    }

    public String getNoKontrak() {
        return noKontrak;
    }

    public void setNoKontrak(String noKontrak) {
        this.noKontrak = noKontrak;
    }

    @Override
    public void absen() {
        System.out.println("Dosen LB " + getNama() + " (No Kontrak: " + noKontrak + ") absen.");
    }
}
