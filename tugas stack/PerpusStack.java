import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

class Buku {
    String judul;
    String author;
    String penerbit;
    int kategori;
    int tahun;

    public Buku(String judul, String author, String penerbit, int kategori, int tahun) {
        this.judul = judul;
        this.author = author;
        this.penerbit = penerbit;
        this.kategori = kategori;
        this.tahun = tahun;
    }
}

public class PerpusStack {
    public static void main(String[] args) {
        Stack<Buku> daftarBuku = new Stack<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selamat datang di Perpustakaan!");
        System.out.println("Silakan masukkan informasi buku:");

        while (true) {
            System.out.print("Judul: ");
            String judul = scanner.nextLine();

            System.out.print("Author: ");
            String author = scanner.nextLine();

            System.out.print("Penerbit: ");
            String penerbit = scanner.nextLine();

            int kategori = 0;
            do {
                System.out.print("Kategori (1 = Teknik, 2 = Manajemen, 3 = Fiksi, 4 = Lainnya): ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Masukkan harus berupa angka! Silakan coba lagi.");
                    scanner.next();
                }
                kategori = scanner.nextInt();
                scanner.nextLine();

                if (kategori < 1 || kategori > 4) {
                    System.out.println("Kategori tidak valid! Silakan masukkan kategori yang sesuai.");
                }
            } while (kategori < 1 || kategori > 4);

            int tahun = 0;
            do {
                System.out.print("Tahun: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Tahun harus berupa angka! Silakan coba lagi.");
                    scanner.next();
                }
                tahun = scanner.nextInt();
                scanner.nextLine();
            } while (tahun <= 0);

            Buku buku = new Buku(judul, author, penerbit, kategori, tahun);
            daftarBuku.push(buku);

            System.out.print("Apakah ingin memasukkan buku lain? (y untuk ya / t untuk tidak): ");
            String jawaban = scanner.nextLine();

            if (jawaban.equalsIgnoreCase("t")) {
                break;
            }
        }

        // Menampilkan daftar buku yang telah dimasukkan
        System.out.println();
        System.out.println("======================================================================");
        System.out.println("Daftar Buku Yang Tersedia : ");
        System.out.println("======================================================================");
        System.out.println("No\t\tJudul\t\t\tPengarang \t\tPenerbit\t\tTahun\t\tKategori\t\t");
        System.out.println("======================================================================");
        int number = 1;

        // Menggunakan Stack sementara untuk menampilkan buku-buku dan menghitung
        // statistik
        Stack<Buku> tempStack = new Stack<>();
        while (!daftarBuku.isEmpty()) {
            Buku buku = daftarBuku.pop();
            tempStack.push(buku); // Mendorong ke Stack sementara untuk digunakan nanti

            System.out.print(number + "\t\t");
            System.out.print(buku.judul + "\t\t");
            System.out.print(buku.author + "\t\t");
            System.out.print(buku.penerbit + "\t\t");
            System.out.print(buku.tahun + "\t\t");

            String kategoriString = "";
            if (buku.kategori == 1) {
                kategoriString = "Teknik";
            } else if (buku.kategori == 2) {
                kategoriString = "Manajemen";
            } else if (buku.kategori == 3) {
                kategoriString = "Fiksi";
            } else if (buku.kategori == 4) {
                kategoriString = "Lainnya";
            } else {
                kategoriString = "Tidak Valid";
            }

            System.out.print(kategoriString + "\t\t");
            number++;
            System.out.println();
        }

        // Mengembalikan buku-buku ke Stack asli
        while (!tempStack.isEmpty()) {
            daftarBuku.push(tempStack.pop());
        }

        // Menghitung Statistik Buku
        int bukuTeknik = 0, bukuManajemen = 0, bukuFiksi = 0, bukuLainnya = 0;
        int bukuLawas = 0, bukuBaru = 0;

        // Menggunakan Stack sementara untuk menghitung statistik
        while (!daftarBuku.isEmpty()) {
            Buku buku = daftarBuku.pop();
            tempStack.push(buku); // Mendorong kembali ke Stack sementara untuk mengembalikan urutan

            if (buku.kategori == 1) {
                bukuTeknik++;
            } else if (buku.kategori == 2) {
                bukuManajemen++;
            } else if (buku.kategori == 3) {
                bukuFiksi++;
            } else {
                bukuLainnya++;
            }

            if (buku.tahun <= 2000) {
                bukuLawas++;
            } else {
                bukuBaru++;
            }
        }

        // Memasukkan kembali elemen ke Stack semula
        while (!tempStack.isEmpty()) {
            daftarBuku.push(tempStack.pop());
        }

        // Menampilkan Statistik Buku
        System.out.println();
        System.out.println("Laporan Jumlah Buku:");
        System.out.println("Jumlah buku yang tersedia: " + daftarBuku.size());
        System.out.println("Buku Teknik: " + bukuTeknik);
        System.out.println("Buku Manajemen: " + bukuManajemen);
        System.out.println("Buku Fiksi: " + bukuFiksi);
        System.out.println("Buku Lainnya: " + bukuLainnya);
        System.out.println("Buku Lawas: " + bukuLawas);
        System.out.println("Buku Baru: " + bukuBaru);
        scanner.close();
    }
}
