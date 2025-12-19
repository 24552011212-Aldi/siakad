package siakad;

public abstract class Mahasiswa {
    String nim;
    String nama;

     //TODO 1. Tambahkan variabel prodi dan ipk (perhatikan type data)
    String prodi;
    int semester;
    double ipk;
    int sks;
    Dosen dosenWali;
    MataKuliah[] krs;
    int jumlahKrs;
    int umur;

    // Batasan SKS per semester (opsional)
    private static final int MAX_SKS_PER_SEMESTER = 24;
    
    public Mahasiswa() {
        this.nim = "";
        this.nama = "";
        this.prodi = "";
        this.ipk = 0.0;
        this.dosenWali = null;
        this.krs = new MataKuliah[0];
        this.jumlahKrs = 0;
        this.umur = 0;
    }
    
    // Constructor dengan parameter 
    public Mahasiswa(String nim, String nama, String prodi, double ipk) {
        this.nim = nim;
        this.nama = nama;
        this.prodi = prodi;
        this.ipk = ipk;
        this.dosenWali = null;
        this.krs = new MataKuliah[0];
        this.jumlahKrs = 0;
        this.umur = 0;
    }
    
    // Constructor overloading dengan parameter Dosen
    public Mahasiswa(String nim, String nama, String prodi, double ipk, Dosen dosenWali) {
        this.nim = nim;
        this.nama = nama;
        this.prodi = prodi;
        this.ipk = ipk;
        this.dosenWali = dosenWali;
        this.krs = new MataKuliah[0];
        this.jumlahKrs = 0;
        this.umur = 0;
    }
    
    // Method untuk menetapkan dosenWali dengan objek Dosen
    public void setDosenWali(Dosen dosen) {
        this.dosenWali = dosen;
    }
  
    abstract void tampilData();

    
    //TODO 5. Tambahkan Method baru predikatIPK() > predikatKelulusan()
    void predikatKelulusan() {
        if (ipk < 0.0 || ipk > 4.0) {
            System.out.println("Predikat    : IPK tidak valid");
        } else if (ipk >= 3.5) {
            System.out.println("Predikat    : Cumlaude");
        } else if (ipk >= 3.0) {
            System.out.println("Predikat    : Sangat Memuaskan");
        } else if (ipk <= 2.5) {
            System.out.println("Predikat    : Cukup");
        }
    }

        // Getter dan setter untuk KRS
    public MataKuliah[] getKrs() {
        return krs;
    }

    public void setKrs(MataKuliah[] krs) {
        if (krs == null) {
            this.krs = new MataKuliah[0];
            this.jumlahKrs = 0;
        } else {
            this.krs = krs;
            this.jumlahKrs = krs.length;
        }
    }

    public int getJumlahKrs() {
        return jumlahKrs;
    }

    // Getter dan setter dengan validasi untuk atribut lain
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
        this.nama = nama.trim();
    }

    public void setNim(String nim) {
        if (nim == null || nim.trim().isEmpty()) {
            throw new IllegalArgumentException("NIM tidak boleh kosong");
        }
        this.nim = nim.trim();
    }

    public void setIpk(double ipk) {
        if (ipk < 0.0 || ipk > 4.0) {
            throw new IllegalArgumentException("IPK harus antara 0.0 dan 4.0");
        }
        this.ipk = ipk;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        if (umur < 17 || umur > 100) {
            throw new IllegalArgumentException("Umur tidak valid (17-100)");
        }
        this.umur = umur;
    }

    // Hitung total SKS dari KRS
    public int hitungTotalSKS() {
        int total = 0;
        if (krs == null) return 0;
        for (int i = 0; i < jumlahKrs; i++) {
            if (krs[i] != null) total += krs[i].getSks();
        }
        return total;
    }

    // Tambah mata kuliah ke KRS 
    public void tambahMataKuliah(MataKuliah mk) {
        if (mk == null) throw new IllegalArgumentException("Mata kuliah tidak boleh null");

        String kode = mk.getKode_matkul();
        if (kode == null || kode.trim().isEmpty()) {
            throw new IllegalArgumentException("Kode mata kuliah tidak boleh kosong");
        }

        // duplikat kode di KRS mahasiswa
        if (hasMataKuliah(kode)) {
            throw new IllegalArgumentException("Mata kuliah dengan kode " + kode + " sudah terdaftar!");
        }

        int calonTotal = hitungTotalSKS() + mk.getSks();
        if (calonTotal > MAX_SKS_PER_SEMESTER) {
            throw new IllegalArgumentException("Menambah mata kuliah ini akan membuat total SKS (" + calonTotal + ") melebihi batas " + MAX_SKS_PER_SEMESTER);
        }

        MataKuliah[] baru = new MataKuliah[jumlahKrs + 1];
        System.arraycopy(krs, 0, baru, 0, jumlahKrs);
        baru[jumlahKrs] = mk;
        krs = baru;
        jumlahKrs++;
    }

    // Cek apakah mahasiswa sudah memiliki mata kuliah dengan kode tertentu
    public boolean hasMataKuliah(String kode) {
        if (kode == null) return false;
        if (krs == null) return false;
        for (int i = 0; i < jumlahKrs; i++) {
            if (krs[i] != null && kode.trim().equals(krs[i].getKode_matkul())) return true;
        }
        return false;
    }

    // Hapus mata kuliah berdasarkan kode
    public void hapusMataKuliah(String kode) {
        if (kode == null || kode.trim().isEmpty()) throw new IllegalArgumentException("Kode mata kuliah tidak boleh kosong");
        if (jumlahKrs == 0) throw new IllegalArgumentException("Belum ada mata kuliah untuk dihapus");

        int idx = -1;
        for (int i = 0; i < jumlahKrs; i++) {
            if (krs[i] != null && kode.trim().equals(krs[i].getKode_matkul())) {
                idx = i;
                break;
            }
        }
        if (idx == -1) throw new IllegalArgumentException("Mata kuliah dengan kode " + kode + " tidak ditemukan");

        MataKuliah[] baru = new MataKuliah[jumlahKrs - 1];
        for (int i = 0, j = 0; i < jumlahKrs; i++) {
            if (i == idx) continue;
            baru[j++] = krs[i];
        }
        krs = baru;
        jumlahKrs--;
    }

    // Tampilkan KRS mahasiswa
    public void tampilKRS() {
        System.out.println("\nKRS Mahasiswa");
        System.out.println("Nama : " + nama);
        System.out.println("NIM : " + nim);
        System.out.println("\nMata Kuliah Diambil :");
        if (jumlahKrs == 0) {
            System.out.println("- (belum ada mata kuliah)");
            System.out.println("Total SKS: 0");
            return;
        }
        for (int i = 0; i < jumlahKrs; i++) {
            MataKuliah m = krs[i];
            if (m != null) System.out.println("- " + m.getKode_matkul() + " - " + m.getNamaMk() + " (" + m.getSks() + " SKS)");
        }
        System.out.println("Total SKS: " + hitungTotalSKS());
    }
    
   //1. method cekSks, cek apakah mahasiswa sudah bisa mengambil skripsi atau belum
    public void cekSks() {
        System.out.println("SKS         : " + sks);
        if (sks >= 140 && semester >= 8) {
            System.out.println("            : Anda sudah menyelesaikan skripsi.");
        } else if (sks >= 124 && semester >= 7) {
            System.out.println("            : Anda sudah bisa mengambil skripsi.");
        } else {
            System.out.println("            : Anda belum bisa mengambil skripsi.");
        }
    }

    //2. method cekStatus, cek status mahasiswa sudah lulus atau belum
    public void cekStatus() {
        if (semester >= 8 && ipk >= 2.0 && sks >= 144) {
            System.out.println("Status      : Lulus");
        } else {
            System.out.println("Status      : Mahasiswa aktif");
        }
    }
    
    // Method untuk menampilkan info singkat
    public void infoSingkat() {
        System.out.println("Nama        : " + nama);
        System.out.println("NIM         : " + nim);
        System.out.println("IPK         : " + ipk);
        predikatKelulusan();
        System.out.println();
    }
        
    public String getNim() {
        return nim;
    }
    
    public double getIpk() {
        return ipk;
    }
}
