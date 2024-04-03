import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Bagli_Sirala {

    public static class Yigin {

        private int[] yigin;
        private int ust;
        private int uzunluk;

        public Yigin(int boyut) {
            this.uzunluk = boyut;
            yigin = new int[boyut];
            ust = -1;
        }

        public void ekleme(int eleman) {
            if (ust == uzunluk - 1) {
                System.out.println("Yığın dolu !!");
                return;
            }
            yigin[++ust] = eleman;
        }

        public int cikarma() {
            if (BosMu()) {
                System.out.println("Yığın boş !!");
                return -1;
            }
            return yigin[ust--];
        }

        public boolean BosMu() {
            return (ust == -1);
        }

        public int boyut() {
            return ust + 1;
        }
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print("Dosyayı Giriniz => ");

        String dosya = scan.nextLine();

        File file = new File(dosya);

        Yigin SayilarYigini = new Yigin(50);

        Yigin IndekslerYigini = new Yigin(50);

        try {
            DosyayiOkuSirala(file, SayilarYigini, IndekslerYigini);
        } catch (IOException x) {
            HataliDosya(scan);
            return;
        }

        int[] sayilar = new int[SayilarYigini.boyut()];

        int[] indeksler = new int[IndekslerYigini.boyut()];

        YigindanElemanlariCikar(SayilarYigini, IndekslerYigini, sayilar, indeksler);

        int[] siraliSayilar = sayilar.clone();

        int[] siraliIndexler = indeksler.clone();

        DizileriSirala(siraliSayilar, siraliIndexler);

        Yazdir(sayilar, indeksler, siraliSayilar, siraliIndexler);

        scan.close();
    }

    private static void DosyayiOkuSirala(File dosya, Yigin SayilarYigini, Yigin IndekslerYigini) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(dosya));

        String satir;

        while ((satir = bufferedReader.readLine()) != null) {
            int sayi = Integer.parseInt(satir);
            SayilarYigini.ekleme(sayi);
            IndekslerYigini.ekleme(SayilarYigini.boyut() - 1);
        }
        bufferedReader.close();
    }

    private static void YigindanElemanlariCikar(Yigin SayilarYigini, Yigin IndexlerYigini, int[] sayilar,
            int[] indeksler) {

        for (int i = SayilarYigini.boyut() - 1; i >= 0; i--) {
            sayilar[i] = SayilarYigini.cikarma();
            indeksler[i] = IndexlerYigini.cikarma();
        }
    }

    private static void DizileriSirala(int[] sayilar, int[] indeksler) {

        for (int i = 1; i < sayilar.length; i++) {

            int anahtar = sayilar[i];

            int anahtarIndex = indeksler[i];

            int j = i - 1;

            while (j >= 0 && sayilar[j] > anahtar) {
                sayilar[j + 1] = sayilar[j];
                indeksler[j + 1] = indeksler[j];
                j--;
            }
            sayilar[j + 1] = anahtar;
            indeksler[j + 1] = anahtarIndex;
        }
    }

    private static void Yazdir(int[] sayilar, int[] indeksler, int[] SiraliSayilar, int[] SiraliIndexler) {

        System.out.println("   -İlk Sıra-\t\t\t  -Sıralı Sıra-");
        for (int i = 0; i < sayilar.length; i++) {
            System.out.println(
                    sayilar[i] + "\t\t" + indeksler[i] + "\t\t" + SiraliSayilar[i] + "\t\t" + SiraliIndexler[i]);
        }
    }

    private static void HataliDosya(Scanner scanner) {

        System.out.println("Dosya Bulunamadi !!");
        scanner.close();
    }
}