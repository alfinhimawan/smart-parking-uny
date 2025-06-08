# Smart Parking UNY

Smart Parking UNY adalah aplikasi desktop modern berbasis JavaFX yang dirancang untuk mengelola sistem parkir secara cerdas, praktis, dan efisien di lingkungan kampus Universitas Negeri Yogyakarta (UNY) atau institusi serupa.

---

## ✨ Fitur Utama
- **Scan QR/Barcode Tiket Masuk & Keluar**
- **Manajemen Slot Parkir Otomatis**
- **Riwayat Parkir Lengkap**
- **Pembuatan & Download Tiket Barcode**
- **Validasi Jenis Pengguna (Dosen, Mahasiswa, Staff, Umum)**
- **Antarmuka Modern & Mudah Digunakan (JavaFX)**
- **Shortcut Desktop, Bisa Dibuka Tanpa Terminal**

---

## 📦 Struktur Project
- `src/` : Kode sumber aplikasi (Java, FXML, CSS, util, service, model, controller)
- `lib/` : Library eksternal (JavaFX, OpenCV, ZXing, dll)
- `bin/` : Hasil kompilasi dan native library
- `SmartParking.bat` : Script launcher aplikasi (Windows)
- `smart-parking-uny.vbs` : Shortcut launcher tanpa terminal
- `logo-smart-parking.ico` : Ikon aplikasi (bisa dipakai untuk shortcut)

---

## 🚀 Cara Menjalankan & Konfigurasi

### 1. **Persiapan Lingkungan**
- Pastikan **Java JDK 11 atau lebih baru** sudah terinstall dan sudah masuk ke PATH.
  - Cek dengan perintah: `java -version`
- Pastikan folder `lib/` berisi:
  - JavaFX SDK (`javafx-sdk-24.0.1`)
  - OpenCV JAR & DLL (`opencv-4110.jar`, `opencv_java4110.dll`)
  - ZXing (`core-3.5.2.jar`, `javase-3.5.2.jar`)
- Pastikan folder `bin/` berisi hasil kompilasi dan file native OpenCV (`opencv_java4110.dll`).

### 2. **Konfigurasi Path Library (Sudah Otomatis di .bat)**
- Semua path library sudah diatur otomatis di `SmartParking.bat`.
- Tidak perlu mengedit path secara manual jika struktur folder tidak diubah.

### 3. **Menjalankan Aplikasi**
- **Tanpa Terminal (Rekomendasi User):**
  1. Klik dua kali `smart-parking-uny.vbs` (atau shortcut desktop ke file ini).
  2. Aplikasi akan terbuka tanpa jendela terminal.
- **Debug/Error (Developer):**
  1. Jalankan `SmartParking.bat` langsung (double click atau lewat cmd).
  2. Jika ingin melihat error Java, edit `SmartParking.bat` dan ganti `javaw` menjadi `java`.

### 4. **Konfigurasi Kamera**
- Pastikan kamera laptop/PC aktif dan tidak digunakan aplikasi lain.
- Jika aplikasi tidak bisa mengakses kamera:
  - Cek pengaturan Windows: Settings → Privacy → Camera → Allow desktop apps to access your camera.
  - Pastikan driver kamera sudah terinstall.

### 5. **Custom Shortcut & Icon**
- Untuk shortcut desktop dengan ikon khusus:
  1. Klik kanan `smart-parking-uny.vbs` → Send to → Desktop (create shortcut).
  2. Klik kanan shortcut di desktop → Properties → Change Icon → Browse ke `logo-smart-parking.ico`.
  3. Rename shortcut menjadi "Smart Parking UNY".

### 6. **Troubleshooting**
- Jika aplikasi tidak berjalan:
  - Pastikan Java sudah terinstall dan path benar.
  - Pastikan semua file library dan native ada di folder yang sesuai.
  - Jalankan `SmartParking.bat` lewat cmd untuk melihat pesan error.
- Jika kamera tidak aktif:
  - Pastikan modul `javafx.swing` sudah ditambahkan di `SmartParking.bat` (lihat `--add-modules`).
  - Pastikan file `opencv_java4110.dll` ada di folder `bin/`.

---

## 🛠️ Teknologi yang Digunakan
- JavaFX (UI/UX)
- OpenCV (Kamera & Image Processing)
- ZXing (Barcode/QR Code)
- VBS & Batch (Launcher Windows)

---

## 👨‍💻 Pengembangan & Kontribusi
- Struktur kode sudah modular (MVC, service, util, dsb)
- Siap dikembangkan lebih lanjut untuk kebutuhan parkir modern
- Silakan fork, modifikasi, dan gunakan untuk institusi Anda!

---

## 📋 Lisensi
Aplikasi ini dikembangkan untuk kebutuhan edukasi dan riset. Silakan gunakan, modifikasi, dan distribusikan sesuai kebutuhan non-komersial.

---

**Smart Parking UNY — Solusi Parkir Modern, Praktis, dan Aman!**
