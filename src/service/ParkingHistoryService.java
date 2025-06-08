package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.RiwayatParkir;

public class ParkingHistoryService {
    private ObservableList<RiwayatParkir> riwayatData = FXCollections.observableArrayList();

    public ObservableList<RiwayatParkir> getAll() {
        return riwayatData;
    }

    public void addEntry(RiwayatParkir entry) {
        riwayatData.add(entry);
    }

    public RiwayatParkir findActiveByKode(String kode) {
        return riwayatData.stream()
                .filter(r -> r.getKodeUnik().equals(kode) && !r.isSudahKeluar())
                .findFirst()
                .orElse(null);
    }

    public void markExit(RiwayatParkir entry, String jamKeluar) {
        entry.setJamKeluar(jamKeluar);
    }

    public boolean allExited() {
        return riwayatData.stream().allMatch(RiwayatParkir::isSudahKeluar);
    }

    public boolean isEmpty() {
        return riwayatData.isEmpty();
    }
}
