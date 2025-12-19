package siakad;

import java.util.Scanner;

public class Siakad {
    static Mahasiswa[] mahasiswas = new Mahasiswa[100];
    static Dosen[] dosens = new Dosen[10];
    static int jumlahMahasiswa = 0;
    static int jumlahDosen = 0;
    static boolean data = false;
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        // Inisialisasi beberapa dosen
        inisialisasiDosen();
        
        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Lihat Daftar Mahasiswa");
            System.out.println("2. Cari Mahasiswa berdasarkan NIM");
            System.out.println("3. Hitung Rata-rata IPK");
            System.out.println("4. Ganti Dosen Wali");
            System.out.println("5. Tambah Mahasiswa Baru");
            System.out.println("6. Kelola KRS Mahasiswa");
            System.out.println("7. Absen Dosen");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            
            int pilihan = scanner.nextInt();
            scanner.nextLine();
            
            switch (pilihan) {
                case 1 -> lihatDaftarMahasiswa();
                case 2 -> cariMahasiswa();
                case 3 -> hitungRataIPK();
                case 4 -> gantiDosenWali();
                case 5 -> tambahMahasiswa();
                case 6 -> kelolaKRS();
                case 7 -> absenDosen();
                case 0 -> {
                    System.out.println("ALERT       : Terima kasih!");
                    return;
                }
                default -> System.out.println("ALERT       : Menu tidak valid!");
            }
        }
    } 

    static void absenDosen() {
        System.out.println("\nDaftar Dosen:");
        for (int i = 0; i < jumlahDosen; i++) {
            dosens[i].absen();
        }
    }

    static void inisialisasiDosen() {
        dosens[0] = new DosenTetap("Danny Aidil", "12345003");
        dosens[1] = new DosenTetap("Indra", "12345004");
        dosens[2] = new DosenLB("Siti", "kontrak-001");
        jumlahDosen = 3;
    }

    static void dataMahasiswa() {
        if (data) return;

        Mahasiswa mhs1 = new MahasiswaReguler("2310001", "Andi Pratama", "Teknik Informatika", 4, 3.75);
        mhs1.nim = "2310001";
        mhs1.nama = "Andi Pratama";
        mhs1.prodi = "Teknik Informatika";
        mhs1.semester = 4;
        mhs1.ipk = 3.75;
        mhs1.sks = 84;
        mhs1.setDosenWali(dosens[0]);

        try {
            MataKuliah mk1 = new MataKuliah();
            mk1.setKode_matkul("IF101");
            mk1.setnamaMk("Pemrograman Dasar");
            mk1.setSks(3);
            mhs1.tambahMataKuliah(mk1);

            MataKuliah mk2 = new MataKuliah();
            mk2.setKode_matkul("IF102");
            mk2.setnamaMk("Pemrograman Berorientasi Objek");
            mk2.setSks(3);
            mhs1.tambahMataKuliah(mk2);
        } catch (IllegalArgumentException ignored) {}
        
        if (jumlahMahasiswa < mahasiswas.length) mahasiswas[jumlahMahasiswa++] = mhs1;

        Mahasiswa mhs2 = new MahasiswaReguler("24552011212", "Aldi alfariz", "Teknik Informatika", 3, 3.43);
        mhs2.nim = "24552011212";
        mhs2.nama = "Aldi alfariz";
        mhs2.prodi = "Teknik Informatika";
        mhs2.semester = 3;
        mhs2.ipk = 3.43;
        mhs2.sks = 64;
        mhs2.setDosenWali(dosens[1]);
        if (jumlahMahasiswa < mahasiswas.length) mahasiswas[jumlahMahasiswa++] = mhs2;

        data = true;
        System.out.println("\nData mahasiswa awal telah dimasukkan.");
    }

    static void lihatDaftarMahasiswa() {
        if (jumlahMahasiswa == 0 && !data) {
            dataMahasiswa();
        }

        System.out.println("\nDaftar Mahasiswa:");
        for (int i = 0; i < jumlahMahasiswa; i++) {
            mahasiswas[i].infoSingkat();
        }
    }
    
    static void cariMahasiswa() {
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();
        
        for (int i = 0; i < jumlahMahasiswa; i++) {
            if (mahasiswas[i].getNim().equals(nim)) {
                mahasiswas[i].tampilData();
                return;
            }
        }
        System.out.println("\nMahasiswa dengan NIM " + nim + " tidak ditemukan.");
    }
    
    static void hitungRataIPK() {
        if (jumlahMahasiswa == 0) {
            System.out.println("\nBelum ada data mahasiswa.");
            return;
        }
        
        double totalIPK = 0;
        for (int i = 0; i < jumlahMahasiswa; i++) {
            totalIPK += mahasiswas[i].getIpk();
        }
        
        double rataIPK = totalIPK / jumlahMahasiswa;
        System.out.printf("\nRata-rata IPK: %.2f\n", rataIPK);
    }
    
    static void gantiDosenWali() {
        System.out.print("\nMasukkan NIM mahasiswa: ");
        String nim = scanner.nextLine();
        
        int indexMhs = -1;
        for (int i = 0; i < jumlahMahasiswa; i++) {
            if (mahasiswas[i].getNim().equals(nim)) {
                indexMhs = i;
                break;
            }
        }
        
        if (indexMhs == -1) {
            System.out.println("\nMahasiswa tidak ditemukan.");
            return;
        }
        
        System.out.println("\nDaftar Dosen:");
        for (int i = 0; i < jumlahDosen; i++) {
            System.out.println((i+1) + ". " + dosens[i].getNama());
        }
        
        System.out.print("Pilih dosen wali (1-" + jumlahDosen + "): ");
        int pilihan = scanner.nextInt();
        
        if (pilihan < 1 || pilihan > jumlahDosen) {
            System.out.println("Pilihan tidak valid!");
            return;
        }
        
        mahasiswas[indexMhs].setDosenWali(dosens[pilihan-1]);
        System.out.println("\nDosen wali berhasil diubah!");
    }
    
    static void tambahMahasiswa() {
        if (jumlahMahasiswa >= mahasiswas.length) {
            System.out.println("\nKapasitas mahasiswa penuh!");
            return;
        }
        
        System.out.print("NIM: ");
        String nim = scanner.nextLine();
        
        for (int i = 0; i < jumlahMahasiswa; i++) {
            if (mahasiswas[i].getNim().equals(nim)) {
                System.out.println("NIM sudah terdaftar!");
                return;
            }
        }

        System.out.print("Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Program Studi: ");
        String prodi = scanner.nextLine();
        
        double ipk;
        do {
            System.out.print("IPK (0.0 - 4.0): ");
            ipk = scanner.nextDouble();
            if (ipk < 0.0 || ipk > 4.0) {
                System.out.println("IPK harus dalam rentang 0.0 - 4.0!");
            }
        } while (ipk < 0.0 || ipk > 4.0);
        
        scanner.nextLine(); 

        System.out.print("Semester(1 - 15): ");
        int semester = scanner.nextInt();
        System.out.print("SKS(0 - 150): ");
        int sks = scanner.nextInt();
        
        System.out.println("\nPilih Dosen Wali:");
        for (int i = 0; i < jumlahDosen; i++) {
            System.out.println((i+1) + ". " + dosens[i].getNama());
        }
        
        System.out.print("Pilihan (1-" + jumlahDosen + "): ");
        int pilihan = scanner.nextInt();
        
        mahasiswas[jumlahMahasiswa] = new MahasiswaReguler(nim, nama, prodi, semester, ipk);
        if (pilihan < 1 || pilihan > jumlahDosen) {
            mahasiswas[jumlahMahasiswa].setDosenWali(dosens[0]);
        } else {
            mahasiswas[jumlahMahasiswa].setDosenWali(dosens[pilihan-1]);
        }

        mahasiswas[jumlahMahasiswa].semester = semester;
        mahasiswas[jumlahMahasiswa].sks = sks;

        jumlahMahasiswa++;
        System.out.println("\n Mahasiswa berhasil ditambahkan!");
    }
    
    static void kelolaKRS() {
        if (jumlahMahasiswa == 0 && !data) dataMahasiswa();

        System.out.print("\nMasukkan NIM mahasiswa: ");
        String nim = scanner.nextLine();

        int idx = -1;
        for (int i = 0; i < jumlahMahasiswa; i++) {
            if (mahasiswas[i].getNim().equals(nim)) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            System.out.println("Mahasiswa tidak ditemukan.");
            return;
        }

        Mahasiswa m = mahasiswas[idx];

        while (true) {
            System.out.println("\n=== KELOLA KRS (" + m.getNim() + " - " + m.getNama() + ") ===");
            System.out.println("1. Tambah Mata Kuliah ke KRS");
            System.out.println("2. Hapus Mata Kuliah dari KRS");
            System.out.println("3. Lihat KRS Mahasiswa");
            System.out.println("0. Kembali");
            System.out.print("Pilih menu: ");

            int pilihan;
            try {
                pilihan = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Input tidak valid");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine(); 

            if (pilihan == 0) return;

            switch (pilihan) {
                case 1 -> {
                    MataKuliah[] daftarMatkul = MataKuliah.getDaftarMataKuliah();
                    for (int i = 0; i < daftarMatkul.length; i++) {
                        MataKuliah mk = daftarMatkul[i];
                        System.out.printf("%d. %s - %s (%d SKS)\n", i + 1, mk.getKode_matkul(), mk.getNamaMk(), mk.getSks());
                    }
                    System.out.print("\nPilih nomor mata kuliah: ");
                    int pilihMk = scanner.nextInt();
                    scanner.nextLine();
                    m.tambahMataKuliah(MataKuliah.getMataKuliah(pilihMk - 1));
                }
                case 2 -> {
                    System.out.print("Masukkan kode mata kuliah: ");
                    m.hapusMataKuliah(scanner.nextLine());
                }
                case 3 -> m.tampilKRS();
                default -> System.out.println("Pilihan tidak valid");
            }
        }
    }
}