package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import service.TicketService;
import service.ScanService;
import util.DialogUtil;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.SlotService;
import service.ParkingHistoryService;
import util.TicketDialogUtil;
import util.UserTypeValidator;
import model.RiwayatParkir;

public class MainController {

    @FXML
    private Label slotInfo;

    @FXML
    private Button scanQRButton;

    @FXML
    private Button scanQROutButton;

    @FXML
    private Label faTestLabel;

    @FXML
    private Button riwayatButton;

    @FXML
    private ImageView mainCameraView;
    @FXML
    private TableView<RiwayatParkir> riwayatTable;
    @FXML
    private TableColumn<RiwayatParkir, String> colJenis;
    @FXML
    private TableColumn<RiwayatParkir, String> colJamMasuk;
    @FXML
    private TableColumn<RiwayatParkir, String> colJamKeluar;
    @FXML
    private ImageView tiketBarcodeView;

    private TicketService ticketService = new TicketService();
    private ScanService scanService;
    private SlotService slotService = new SlotService(5);
    private ParkingHistoryService parkingHistoryService = new ParkingHistoryService();

    @FXML
    public void initialize() {
        updateSlotInfo();
        scanQRButton.setDisable(false);
        scanQROutButton.setDisable(true);
        riwayatButton.setDisable(true);
        scanService = new ScanService(new service.CameraService(), new service.BarcodeService());
        if (faTestLabel != null) {
            faTestLabel.setText("Test");
        }
        colJenis.setCellValueFactory(new PropertyValueFactory<>("jenisPengguna"));
        colJamMasuk.setCellValueFactory(new PropertyValueFactory<>("jamMasuk"));
        colJamKeluar.setCellValueFactory(new PropertyValueFactory<>("jamKeluar"));
        riwayatTable.setItems(parkingHistoryService.getAll());
    }

    private void updateSlotInfo() {
        slotInfo.setText(slotService.getSisaSlot() + " dari " + slotService.getTotalSlot());
        slotInfo.getStyleClass().removeAll("slot-info-green", "slot-info-yellow", "slot-info-red");
        if (slotService.isFull()) {
            slotInfo.getStyleClass().add("slot-info-red");
        } else if (slotService.getSisaSlot() <= slotService.getTotalSlot() * 0.2) {
            slotInfo.getStyleClass().add("slot-info-yellow");
        } else {
            slotInfo.getStyleClass().add("slot-info-green");
        }
    }

    @FXML
    private void handleScanQR() {
        if (slotService.isFull()) {
            showAlert("Slot parkir penuh.");
            scanQRButton.setDisable(true);
            return;
        }
        scanQRButton.setDisable(false);
        mainCameraView.setVisible(true);
        mainCameraView.setManaged(true);
        scanService.startScan(mainCameraView, kode -> {
            if (UserTypeValidator.isValidUserType(kode)) {
                prosesMasuk(kode);
            } else {
                showAlert("Barcode tidak terdaftar!");
                scanService.stopScan();
                mainCameraView.setVisible(false);
                mainCameraView.setManaged(false);
            }
        });
    }

    private void prosesMasuk(String jenisPengguna) {
        scanService.stopScan();
        mainCameraView.setVisible(false);
        mainCameraView.setManaged(false);
        java.time.LocalTime now = java.time.LocalTime.now();
        String jamMasuk = now.toString().substring(0, 8);
        String kodeUnik = jenisPengguna + "-" + System.currentTimeMillis();
        RiwayatParkir parkirMasuk = new RiwayatParkir(jenisPengguna, jamMasuk, kodeUnik);
        parkingHistoryService.addEntry(parkirMasuk);
        slotService.decrement();
        updateSlotInfo();
        scanQROutButton.setDisable(false);
        riwayatButton.setDisable(false);
        String barcodePath;
        try {
            barcodePath = ticketService.generateTicketBarcode(kodeUnik, "barcodes_tiketmasuk");
        } catch (Exception e) {
            showAlert("Gagal membuat barcode: " + e.getMessage());
            return;
        }
        TicketDialogUtil.showTicketDialog(kodeUnik, barcodePath);
    }

    @FXML
    private void handleScanQROut() {
        if (parkingHistoryService.isEmpty()) {
            showAlert("Belum ada pengguna yang masuk!");
            scanQROutButton.setDisable(true);
            return;
        }
        scanService.startScan(mainCameraView, kode -> {
            boolean deleted = ticketService.deleteTicketBarcode(kode, "barcodes_tiketmasuk");
            System.out.println("Barcode file barcodes_tiketmasuk/tiket-" + kode + ".png" + (deleted ? " berhasil dihapus." : " gagal dihapus."));
            RiwayatParkir parkir = parkingHistoryService.findActiveByKode(kode);
            if (parkir != null) {
                String jamKeluar = java.time.LocalTime.now().toString().substring(0, 8);
                parkingHistoryService.markExit(parkir, jamKeluar);
                riwayatTable.refresh();
                slotService.increment();
                updateSlotInfo();
                showInfo("Terima Kasih dan Hati-Hati di Jalan!\nKeluar sebagai: "
                        + parkir.getJenisPengguna() + "\nJam Masuk: " + parkir.getJamMasuk()
                        + "\nJam Keluar: " + parkir.getJamKeluar());
                scanService.stopScan();
                mainCameraView.setVisible(false);
                mainCameraView.setManaged(false);
                if (parkingHistoryService.allExited()) {
                    scanQROutButton.setDisable(true);
                }
            } else {
                showAlert("Barcode keluar tidak valid atau tidak terdaftar!");
                scanService.stopScan();
                mainCameraView.setVisible(false);
                mainCameraView.setManaged(false);
            }
        });
    }

    @FXML
    private void handleLihatRiwayat() {
        boolean visible = !riwayatTable.isVisible();
        riwayatTable.setVisible(visible);
        riwayatTable.setManaged(visible);
    }

    private void showAlert(String message) {
        DialogUtil.showAlert(message);
    }

    private void showInfo(String message) {
        DialogUtil.showInfo(message);
    }
}