package siakad;

public class AdminAkademik extends User implements KelolaDataAkademik {
    public AdminAkademik(String username) {
        super(username);
    }

    @Override
    public void tambahData() {
    }

    @Override
    public void ubahData() {
    }

    @Override
    public void tampilMenu() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\n=== MENU ADMIN AKADEMIK ===");
            System.out.println("1. Tambah Data Akademik");
            System.out.println("2. Ubah Data Akademik");
            System.out.println("3. Tampil Menu");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            int pilih = scanner.nextInt();
            scanner.nextLine();
            switch (pilih) {
                case 3 -> tambahData();
                case 4 -> ubahData();
                case 5 -> siakad.Siakad.tampilMenuMahasiswa();
                case 0 -> running = false;
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }

    @Override
    public void tambahUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tambahUser'");
    }

    @Override
    public void hapusUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hapusUser'");
    }
}


