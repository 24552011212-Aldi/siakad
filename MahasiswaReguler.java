package siakad;

public final class MahasiswaReguler extends Mahasiswa{
    public int IdKategoriKelas;


    public MahasiswaReguler(String nim, String nama, String prodi, double ipk, double dosens){
        super(nim, nama, prodi, ipk);
        setIdKategoriKelas(IdKategoriKelas);
    }

    public MahasiswaReguler(String nim, String nama, String prodi, int semester, double ipk) {
        super(nim, nama, prodi, ipk);
        this.semester = semester;
    }

    public int IdKategoriKelas(){
        return IdKategoriKelas;
    }

    public void setIdKategoriKelas(int IdKategoriKelas){
        this.IdKategoriKelas = IdKategoriKelas;
    }

    @Override
    public void tampilData(){
        System.out.println("\nDATA MAHASISWA: ");
        System.out.println("NIM         : " + nim);
        System.out.println("Nama        : " + nama);
        System.out.println("Prodi       : " + prodi);
        System.out.println("Semester    : " + semester);
        System.out.println("IPK         : " + ipk);
        predikatKelulusan();
        cekSks();
        cekStatus();
        if (dosenWali != null) {
        System.out.println("Dosen Wali: " + dosenWali.getNama());
    }
}
}
