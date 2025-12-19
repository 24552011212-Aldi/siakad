package siakad;

public class MataKuliah {
    private String kode_matkul;
    private int sks;
    private String namaMk;

    // Static array of predefined courses
    private static final MataKuliah[] DAFTAR_MATKUL = {
        createMataKuliah("IF101", "Pemrograman Dasar", 3),
        createMataKuliah("IF102", "Pemrograman Berorientasi Objek", 3),
    };

    private static MataKuliah createMataKuliah(String kode, String nama, int sks) {
        MataKuliah mk = new MataKuliah();
        mk.kode_matkul = kode;
        mk.namaMk = nama;
        mk.sks = sks;
        return mk;
    }

    public static MataKuliah[] getDaftarMataKuliah() {
        return DAFTAR_MATKUL;
    }

    public static MataKuliah getMataKuliah(int index) {
        if (index >= 0 && index < DAFTAR_MATKUL.length) {
            MataKuliah mk = new MataKuliah();
            mk.kode_matkul = DAFTAR_MATKUL[index].kode_matkul;
            mk.namaMk = DAFTAR_MATKUL[index].namaMk;
            mk.sks = DAFTAR_MATKUL[index].sks;
            return mk;
        }
        throw new IllegalArgumentException("Index mata kuliah tidak valid");
    }

    // Getters
    public String getKode_matkul() {
        return kode_matkul;
    }

    public String getNamaMk() {
        return namaMk;
    }

    public int getSks() {
        return sks;
    }

    //Setter
    public void setKode_matkul(String kode_matkul){
        if (kode_matkul == null || kode_matkul.trim().isEmpty()) {
            throw new IllegalArgumentException("kode_matkul tidak boleh kosong");
        }
        this.kode_matkul = kode_matkul;
        
    } 
    public void setSks(int sks){
        if (sks < 1 || sks > 6) {
            throw new IllegalArgumentException("sks harus antara 1 sampai 6");
        }
        this.sks = sks;
    } 
    
    public void setnamaMk(String namaMk){
        this.namaMk = namaMk;
    }

    public void info(){
        System.out.println("IF101 - Pemrograman Dasar (3 SKS)");
        System.out.println("IF102 - Pemrograman Berorientasi Objek (3 SKS)");
    }
}