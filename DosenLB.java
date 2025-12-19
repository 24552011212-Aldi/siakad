
package siakad;

public class DosenLB extends Dosen {
    public DosenLB(String nama) {
        super(nama);
    }

    @Override
    public void absen() {
        System.out.println("Dosen LB " + getNama() + " absen.");
    }
}
