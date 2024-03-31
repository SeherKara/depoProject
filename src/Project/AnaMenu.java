package Project;


import java.util.Map;
import static Project.TryCatch.*;

public class AnaMenu implements AnaMenuInterFace {
    public static final String M = "\u001B[35m";
    public static final String G = "\u001B[32m";
    public static final String Y = "\u001B[33m";
    public static final String CB = "\u001B[34m";
    public static final String W = "\u001B[37m";
    public static final String ITALIC = "\u001B[3m";



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
        Urun urun=new Urun();
        System.out.println("Lutfen urunun ismini giriniz");
        urun.setUrunIsmi(stringGirisi());
        System.out.println("Lutfen uretici adini giriniz");
        urun.setUretici(stringGirisi());
        System.out.println("Lutfen birimi giriniz");
        urun.setBirim(stringGirisi());


        boolean urunVarMi = false;
        for (Urun varOlanUrun : Urun.urunList.values()) {
            if (varOlanUrun.getUrunIsmi().equals(urun.getUrunIsmi()) && varOlanUrun.getUretici().equals(urun.getUretici())) {
                urunVarMi = true;
                break;
            }
        }


        if (urunVarMi) {
            System.out.println("Bu ürün zaten ekli.");
        } else {
            int yeniId = Urun.urunList.isEmpty() ? 1000 : Urun.urunList.keySet().stream().max(Integer::compareTo).orElse(1000) + 1;
            urun.setId(yeniId);
            Urun.urunList.put(yeniId, urun);
            System.out.println("Ürün başarıyla tanımlandı: " + urun);
        }
        girisEkrani();
    }

    @Override
    public void urunListeleme() {

        System.out.println(CB+"id\t\tismi\turetcisi\t\tmiktarı\t\tbirimi\t\traf" +
                "\n-------------------------------------------------------------"+W);
                for (Map.Entry<Integer, Urun> entry : Urun.urunList.entrySet()) {
                    System.out.println(entry.getKey() + "\t\t" + entry.getValue().getUrunIsmi() + "\t\t" + entry.getValue().getUretici() +
                            "\t\t" + entry.getValue().getMiktar() + "\t\t" + entry.getValue().getBirim() + "\t\t" + entry.getValue().getRaf());
                }





    }

    @Override
    public void urunGirisi() {
       urunListeleme();
        System.out.println("Eklemek istediğiniz ürünün Id sini giriniz");
       int Id=intGirisi();
        if (Urun.urunList.containsKey(Id)) {
            System.out.println("Kaç tane ekleme yapmak istiyorsunuz");
                int yeniMiktar=intGirisi();
            //Urun urun = Urun.urunList.get(Id); // Ilgili urunu alıyorum
            int eskiMiktar = Urun.urunList.get(Id).getMiktar(); // Mevcut miktarı yazdırıyorum
            int toplamMiktar = eskiMiktar + yeniMiktar; // eskimik+yeni miktar

            Urun.urunList.get(Id).setMiktar(toplamMiktar); // Yeni miktarı atama yaptım
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
        if (Urun.urunList.containsKey(id)) {
            System.out.println("Hangi rafa ekleme yapmak istiyorsunuz: " + Urun.urunList.get(id));
            String raf=stringGirisi();
            Urun.urunList.get(id).setRaf(raf);
            System.out.println("Ürün rafa ekleme işlemi başarılı!");
        }else System.out.println("Bu Id de ürün yok");


    }

    @Override
    public  void urunCikisi() {
       urunListeleme();
        System.out.println("Hangi Urunu sistemden çıkartmak istiyorsunuz");
        int id=intGirisi();
        if (Urun.urunList.containsKey(id)) {
            System.out.println("Kaç adet urun çıkartmak istiyorsunuz");
            int cikarilanMiktar=intGirisi();
            if (cikarilanMiktar<0){
                System.out.println("Eksili değer çıkartamazsınız..");
            }else {
                //Urun urun = Urun.urunList.get(id);
                int yeniMiktar = Urun.urunList.get(id).getMiktar() - cikarilanMiktar;

                if (yeniMiktar >= 0) {
                    Urun.urunList.get(id).setMiktar(yeniMiktar);
                    System.out.println("Urun cikisi gerçekleştirildi");
                } else System.out.println("Sistemde olan " + Urun.urunList.get(id).getMiktar() + "fazla urun çıkısı yapamzsınız");
            }
        }else System.out.println("Bu Id de ürün yok");



    }


}
