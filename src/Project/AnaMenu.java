package Project;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import static Project.TryCatch.*;

public class AnaMenu implements AnaManuInterFace{
    public static final String M = "\u001B[35m";
    public static final String G = "\u001B[32m";
    public static final String Y = "\u001B[33m";
    public static final String CB = "\u001B[34m";
    public static final String W = "\u001B[37m";
    public static final String ITALIC = "\u001B[3m";
  static HashMap<Integer,Urun> urunler=new HashMap<>();
   static int id = 1000;
   Urun urun=new Urun();

   public  void girisEkrani(){
       System.out.println(M + "========================== İŞLEMLER =======================\r\n"
               + "   ____________________             ____________________    \n"
               + "   | 1-URUN TANIMLAMA  |            | 2-URUN GİRİSİ|  \n"
               + "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯             ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    \n"
               + "   ____________________             ____________________    \n"
               + "   | 3-RAF GUNCELLEME  |            | 4-URUN CIKISI     |   \n"
               + "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯             ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    \n"
               + "   ____________________             ____________________    \n"
               + "   | 5-URUN LİSTELE    |            | 6-CIKIS           |   \n"
               + "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯             ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯       " +Y+ITALIC);
       System.out.println("ISLEM SECIMI YAPINIZ");
       int secim=intGirisi();
       switch (secim){
           case 1:
               urunTanimlama();
               girisEkrani();
               break;
           case 2:
               urunGirisi();
               girisEkrani();
               break;
           case 3:
               urunuRafaKoy();
               girisEkrani();
               break;
           case 4:
               urunCikisi();
               girisEkrani();
               break;
           case 5:
               urunListeleme();
               girisEkrani();
               break;
           case 6:
               cikis();
               break;
           default:
               System.out.println("Yanlış tusa bastınız..");
              girisEkrani();
       }
   }



    private void cikis() {
        System.out.println(G+"Çıkış işlemi gerçekleşti");
        System.exit(0);
    }

    @Override
   public   void urunTanimlama() {


        System.out.println("Urunun ismini giriniz");
        String urunIsmi=stringGirisi();
        System.out.println("Urunun uretici markasını giriniz");
        String uretici=stringGirisi();
        System.out.println("Urunun birimini giriniz");
        String birim=stringGirisi();

        int miktar=0;
        String raf=null;
        Urun urun=new Urun(urunIsmi,uretici,birim);

        // Ürünü HashMap'e ekleme
       urunler.put(id, urun);
        id++;

        System.out.println("Ürün başarıyla tanımlandı: " + urun);
        //urunListeleme();
        girisEkrani();
    }

    @Override
    public void urunListeleme() {

        System.out.println(CB+"id\t\tismi\turetcisi\t\tmiktarı\t\tbirimi\t\traf" +
                "\n-------------------------------------------------------------"+W);
                Urun yeniUrun=new Urun();
                for (Map.Entry<Integer, Urun> entry : urunler.entrySet()) {
                    System.out.println(entry.getKey() + "\t\t" + entry.getValue().getUrunIsmi() + "\t\t" + entry.getValue().getUretici() +
                            "\t\t" + entry.getValue().getMiktar() + "\t\t" + entry.getValue().getBirim() + "\t\t" + entry.getValue().getRaf());
                }





    }

    @Override
    public void urunGirisi() {
       urunListeleme();
        System.out.println("Eklemek istediğiniz ürünün Id sini giriniz");
       int Id=intGirisi();

        if (urunler.keySet().contains(Id)) {
            System.out.println("Kaç tane ekleme yapmak istiyorsunuz");
                int yeniMiktar=intGirisi();
            Urun urun = urunler.get(Id); // Ilgili urunu alıyorum
            int eskiMiktar = urun.getMiktar(); // Mevcut miktarı yazdırıyorum
            int toplamMiktar = eskiMiktar + yeniMiktar; // eskimik+yeni miktar

            urun.setMiktar(toplamMiktar); // Yeni miktarı atama yaptım
            System.out.println("Ürün girişi başarılı! Yeni miktar: " + toplamMiktar);



        } else {
            System.out.println("Ürün bulunamadı!");
        }

    }

    @Override
    public  void urunuRafaKoy() {
       urunListeleme();
        System.out.println("Rafa eklenecek ürün Id si giriniz:");
        int id=intGirisi();
        if (urunler.keySet().contains(id)) {
            System.out.println("Hangi rafa ekleme yapmak istiyorsunuz: " + urun);
            String raf=stringGirisi();
            urunler.get(id).setRaf(raf);
            System.out.println("Ürün rafa ekleme işlemi başarılı!");
        }else System.out.println("Bu Id de ürün yok");


    }

    @Override
    public  void urunCikisi() {
       urunListeleme();
        System.out.println("Hangi Urunu sistemden çıkartmak istiyorsunuz");
        int id=intGirisi();
        if (urunler.keySet().contains(id)) {
            System.out.println("Kaç adet urun çıkartmak istiyorsunuz");
            int cikarilanMiktar=intGirisi();
            if (cikarilanMiktar<0){
                System.out.println("Eksili değer çıkartamazsınız..");
            }else {
                Urun urun = urunler.get(id);
                int yeniMiktar = urun.getMiktar() - cikarilanMiktar;

                if (yeniMiktar >= 0) {
                    urun.setMiktar(yeniMiktar);
                    System.out.println("Urun cikisi gerçekleştirildi");
                } else System.out.println("Sistemde olan " + urun.getMiktar() + "fazla urun çıkısı yapamzsınız");
            }
        }else System.out.println("Bu Id de ürün yok");



    }


}
