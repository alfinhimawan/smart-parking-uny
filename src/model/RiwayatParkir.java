package model;

public class RiwayatParkir {
    private final String jenisPengguna;
    private final String jamMasuk;
    private String jamKeluar;
    private final String kodeUnik;
    private boolean sudahKeluar = false;

    public RiwayatParkir(String jenisPengguna, String jamMasuk, String kodeUnik) {
        this.jenisPengguna = jenisPengguna;
        this.jamMasuk = jamMasuk;
        this.kodeUnik = kodeUnik;
        this.jamKeluar = "-";
    }

    public String getJenisPengguna() {
        return jenisPengguna;
    }

    public String getJamMasuk() {
        return jamMasuk;
    }

    public String getJamKeluar() {
        return jamKeluar;
    }

    public String getKodeUnik() {
        return kodeUnik;
    }

    public boolean isSudahKeluar() {
        return sudahKeluar;
    }

    public void setJamKeluar(String jamKeluar) {
        this.jamKeluar = jamKeluar;
        this.sudahKeluar = true;
    }
}
