package Project;

public class Urun {
    private int id;
    private String urunIsmi;
    private String uretici;
    private int miktar;
    private String birim;

    private String raf;

    public Urun() {
    }

    public Urun(String urunIsmi, String uretici, String birim) {
        this.urunIsmi = urunIsmi;
        this.uretici = uretici;
        this.birim = birim;
    }

    public Urun(int id, String urunIsmi, String uretici, int miktar, String birim, String raf) {
        this.id = id;
        this.urunIsmi = urunIsmi;
        this.uretici = uretici;
        this.miktar = miktar;
        this.birim = birim;
        this.raf = raf;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrunIsmi() {
        return urunIsmi;
    }

    public void setUrunIsmi(String urunIsmi) {
        this.urunIsmi = this.urunIsmi;
    }

    public String getUretici() {
        return uretici;
    }

    public void setUretici(String uretici) {
        this.uretici = this.uretici;
    }

    public int getMiktar() {
        return miktar;
    }

    public void setMiktar(int miktar) {

        this.miktar = miktar;
    }

    public String getBirim() {
        return birim;
    }

    public void setBirim(int birim) {
        this.birim = this.birim;
    }

    public String getRaf() {
        return raf;
    }

    public void setRaf(String raf) {
        this.raf = raf;
    }

    @Override
    public String toString() {
        return "Urun{" +
                "id=" + id +
                ", urunIsmi='" + urunIsmi + '\'' +
                ", uretici='" + uretici + '\'' +
                ", miktar=" + miktar +
                ", birim='" + birim + '\'' +
                ", raf=" + raf +
                '}';
    }
}