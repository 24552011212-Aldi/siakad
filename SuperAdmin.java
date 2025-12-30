package siakad;

public class SuperAdmin extends User implements KelolaDataAkademik {
    public SuperAdmin(String username) {
        super(username);
    }

    @Override
    public void tambahData() {
        // Implementasi tambah data akademik
    }

    @Override
    public void ubahData() {
        // Implementasi ubah data akademik
    }

    @Override
    public void tambahUser() {
        // Implementasi tambah user
    }

    @Override
    public void hapusUser() {
        // Implementasi hapus user
    }

    @Override
    public void tampilMenu() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\n=== MENU SUPERADMIN ===");
            System.out.println("1. Tambah User");
            System.out.println("2. Hapus User");
            System.out.println("3. Tambah Data Akademik");
            System.out.println("4. Ubah Data Akademik");
            System.out.println("5. Tampil Menu");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            int pilih = scanner.nextInt();
            scanner.nextLine();
            switch (pilih) {
                case 1 -> tambahUser();
                case 2 -> hapusUser();
                case 3 -> tambahData();
                case 4 -> ubahData();
                case 5 -> siakad.Siakad.tampilMenuMahasiswa();
                case 0 -> running = false;
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }
}