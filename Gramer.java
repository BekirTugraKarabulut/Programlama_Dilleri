import java.util.Scanner;

public class Gramer {

    public static void main(String[] args) {

        String[] Ozneler = { "Ben", "Sen", "Hasan", "Nurşah", "Elif", "Abdulrezzak", "Şehribanu", "Zeynelabidin",
                "Naki" };
        String[] Nesneler = { "Bahçe", "Okul", "Park", "Sınıf", "Yarın", "Pazartesi", "Salı", "Çarşamba", "Perşembe",
                "Cuma", "Cumartesi", "Pazar", "Merkez", "Ev", "Kitap", "Defter", "Güneş", "Beydağı" };
        String[] Yuklemler = { "Gitmek", "Gelmek", "Okumak", "Yazmak", "Yürümek", "Görmek" };

        Scanner scan = new Scanner(System.in);

        System.out.print("Metni Giriniz => ");

        String metin = scan.nextLine().trim();

        String[] Kelimeler = metin.split("\\s+");

        if (Kelimeler.length == 3) {

            String Ozne = Kelimeler[0];
            String Nesne = Kelimeler[1];
            String Yuklem = Kelimeler[2];

            if (KelimeEslestirme(Ozne, Ozneler) && KelimeEslestirme(Nesne, Nesneler)
                    && KelimeEslestirme(Yuklem, Yuklemler)) {
                System.out.println("EVET");
            } else {
                System.out.println("HAYIR");
            }
        } else {
            System.out.println(
                    "Cümle bir özne bir nesne bir yüklemden oluşur ve kelimeleri doğru yazdığınızdan emin olun...");
        }
    }

    public static boolean KelimeEslestirme(String kelime, String[] dizi) {

        for (String metin : dizi) {
            if (metin.equals(kelime)) {
                return true;
            }
        }
        return false;
    }
}