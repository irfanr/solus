/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openswing.swing.internationalization.java;

import java.util.Properties;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author irfan
 */
public class IndonesiaOnlyResourceFactory extends ResourcesFactory {

    /**
     * internationalization settings
     */
    private Resources resources = null;

    /**
     * Constructor.
     *
     * @param currencySymbol currency symbol
     * @param additionalDictionary additional descriptions
     * @param showResourceNotFoundWarning warn when no resource key not found
     */
    public IndonesiaOnlyResourceFactory(String currencySymbol, Properties additionalDictionary, boolean showResourceNotFoundWarning) {
        this(currencySymbol, additionalDictionary, showResourceNotFoundWarning, '/');
    }

    /**
     * Constructor.
     *
     * @param currencySymbol currency symbol
     * @param additionalDictionary additional descriptions
     * @param showResourceNotFoundWarning warn when no resource key not found
     * @param dateFormatSeparator date format separator; for example: '-' or '/'
     */
    public IndonesiaOnlyResourceFactory(String currencySymbol, Properties additionalDictionary, boolean showResourceNotFoundWarning, char dateFormatSeparator) {
        Properties dictionary = new Properties();

        dictionary.putAll(additionalDictionary);

        // grid...
        dictionary.setProperty("of", "dari");
        dictionary.setProperty("page", "Halaman");
        dictionary.setProperty("Remove Filter", "Hapus Filter");
        dictionary.setProperty("This column is not sorteable", "Kolom ini tidak dapat diurutkan");
        dictionary.setProperty("Sorting not allowed", "Pengurutan tidak diperbolehkan");
        dictionary.setProperty("Maximum number of sorted columns", "Maksium jumlah kolom terurutkan");
        dictionary.setProperty("Sorting not applicable", "Pengurutan tidak dapat dilakukan");
        dictionary.setProperty("Selected Row", "Baris Terpilih");
        dictionary.setProperty("Selected Rows", "Baris Terpilih");
        dictionary.setProperty("Cancel changes and reload data?", "Batalkan perubahan dan reload data?");
        dictionary.setProperty("Attention", "Perhatian");
        dictionary.setProperty("Loading data...", "Memuat data...");
        dictionary.setProperty("Error while loading data", "Error saat memuat data");
        dictionary.setProperty("Loading Data Error", "Memuat Data Error");
        dictionary.setProperty("Delete Rows?", "Hapus Baris?");
        dictionary.setProperty("Delete Confirmation", "Konfirmasi Hapus");
        dictionary.setProperty("Error while deleting rows.", "Error saat menghapus baris");
        dictionary.setProperty("Deleting Error", "Error Menghapus");
        dictionary.setProperty("Error while saving", "Error saat menyimpan");
        dictionary.setProperty("Saving Error", "Error menyimpan");
        dictionary.setProperty("A mandatory column is empty.", "Kolom Wajib kosong");
        dictionary.setProperty("Value not valid", "Nilai tidak valid");
        dictionary.setProperty("sorting conditions", "Kondisi pengurutan");
        dictionary.setProperty("filtering conditions", "Kondisi filter");
        dictionary.setProperty("filtering and sorting settings", "Setting penyaringan dan pengurutan");
        dictionary.setProperty("Filtering/Sorting data (CTRL+F)", "Penyaringan/pengurutan data (CTRL+F)");
        dictionary.setProperty("upload file", "Unggah File");
        dictionary.setProperty("download file", "Unduh File");

        // export...
        dictionary.setProperty("grid export", "Ekspor Table");
        dictionary.setProperty("export", "Ekspor");
        dictionary.setProperty("exportmnemonic", "X");
        dictionary.setProperty("column", "Kolum");
        dictionary.setProperty("sel.", "Ter.");
        dictionary.setProperty("you must select at least one column", "Anda harus memilih setidaknya satu kolom");
        dictionary.setProperty("columns to export", "Kolom untuk mengekspor");
        dictionary.setProperty("export type", "Tipe Ekspor");

        // import...
        dictionary.setProperty("grid import", "Impor Table");
        dictionary.setProperty("file to import", "File untuk diimpor");
        dictionary.setProperty("import", "Impor");
        dictionary.setProperty("importmnemonic", "M");
        dictionary.setProperty("columns to import", "Kolom untuk diimpor");
        dictionary.setProperty("import type", "Format impor");
        dictionary.setProperty("error while importing data", "Error saat mengimpor data");
        dictionary.setProperty("import completed", "Impor selesai.");

        // quick filter...
        dictionary.setProperty("To value", "Nilai Ke");
        dictionary.setProperty("Filter by", "Disaring berdasar");
        dictionary.setProperty("From value", "Nilai Dari");
        dictionary.setProperty("equals", "sama dengan");
        dictionary.setProperty("contains", "mengandung");
        dictionary.setProperty("starts with", "dimulai dengan");
        dictionary.setProperty("ends with", "diakhiri dengan");

        // select/deselect all
        dictionary.setProperty("select all", "Pilih semua");
        dictionary.setProperty("deselect all", "Tidak Pilih semua");

        // copy/cut/paste
        dictionary.setProperty("copy", "Copy");
        dictionary.setProperty("copymnemonic", "C");
        dictionary.setProperty("cut", "Cut");
        dictionary.setProperty("cutmnemonic", "U");
        dictionary.setProperty("paste", "Paste");
        dictionary.setProperty("pastemnemonic", "P");

        // lookup...
        dictionary.setProperty("Code is not correct.", "Kode tidak benar");
        dictionary.setProperty("Code Validation", "Validasi Kode");
        dictionary.setProperty("Code Selection", "Seleksi Kode");

        dictionary.setProperty("Caps lock pressed", "Capslock tertekan");

        // form...
        dictionary.setProperty("Confirm deliting data?", "Yakin akan menghapus data?");
        dictionary.setProperty("Error while saving: incorrect data.", "Errpr saat menyimpan: data tidak benar");
        dictionary.setProperty("Error while validating data:", "Error saat memvalidasi data:");
        dictionary.setProperty("Validation Error", "Error validasi");
        dictionary.setProperty("Error on deleting:", "Error saat menghapus:");
        dictionary.setProperty("Error on Loading", "Error saat memuat");
        dictionary.setProperty("Error while loading data:", "Error saat memuat data:");
        dictionary.setProperty("Error on setting value to the input control having the attribute name", "Error dalam mensetting nilai ke kontrol input yang mempunyai nama attribut");

        // toolbar buttons...
        dictionary.setProperty("Delete record (CTRL+D)", "Hapus record (CTRL+D)");
        dictionary.setProperty("Edit record (CTRL+E)", "Edit record (CTRL+E)");
        dictionary.setProperty("New record (CTRL+I)", "Tambah record (CTRL+I)");
        dictionary.setProperty("Reload record/Cancel current operation (CTRL+Z)", "Reload record/Batalkan operasi yang ada (CTRL+Z)");
        dictionary.setProperty("Save record (CTRL+S)", "Simpan record (CTRL+S)");
        dictionary.setProperty("Copy record (CTRL+C)", "Copy record (CTRL+C)");
        dictionary.setProperty("Export record (CTRL+X)", "Ekspor records (CTRL+X)");
        dictionary.setProperty("Import records (CTRL+M)", "Impor records (CTRL+M)");
        dictionary.setProperty("Load the first block of records", "Muat blok pertama dari record");
        dictionary.setProperty("Select the previous row in grid", "Pilih baris sebelumnya di tabel");
        dictionary.setProperty("Select the next row in grid", "Pilih baris selanjutnya di tabel");
        dictionary.setProperty("Load the previous block of records", "Muat blok record sebelumnya");
        dictionary.setProperty("Load the next block of records", "Muat blok record setelahnya");
        dictionary.setProperty("Load the last block of records", "Muat blok terakhir dari record");

        dictionary.setProperty("Insert", "Masukkan");
        dictionary.setProperty("Edit", "Edit");
        dictionary.setProperty("Copy", "Copy");
        dictionary.setProperty("Delete", "Hapus");
        dictionary.setProperty("Save", "Simpan");
        dictionary.setProperty("Reload", "Reload");
        dictionary.setProperty("Export", "Ekspor");
        dictionary.setProperty("Filter", "Saring");

        // MDI Frame...
        dictionary.setProperty("file", "File");
        dictionary.setProperty("exit", "Exit");
        dictionary.setProperty("filemnemonic", "F");
        dictionary.setProperty("exitmnemonic", "E");
        dictionary.setProperty("change user", "Ganti Login");
        dictionary.setProperty("changeusermnemonic", "U");
        dictionary.setProperty("changelanguagemnemonic", "L");
        dictionary.setProperty("help", "Help");
        dictionary.setProperty("about", "About");
        dictionary.setProperty("helpmnemonic", "H");
        dictionary.setProperty("aboutmnemonic", "A");
        dictionary.setProperty("are you sure to quit application?", "Anda yakin ingin keluar dari aplikasi?");
        dictionary.setProperty("quit application", "Keluar dari Aplikasi");
        dictionary.setProperty("forcegcmnemonic", "F");
        dictionary.setProperty("Force GC", "Force GC");
        dictionary.setProperty("Java Heap", "Java Heap");
        dictionary.setProperty("used", "used");
        dictionary.setProperty("allocated", "allocated");
        dictionary.setProperty("change language", "Ubah Bahasa");
        dictionary.setProperty("changemnemonic", "L");
        dictionary.setProperty("cancelmnemonic", "C");
        dictionary.setProperty("cancel", "Batal");
        dictionary.setProperty("yes", "Ya");
        dictionary.setProperty("no", "Tidak");
        dictionary.setProperty("Functions", "Fungsi");
        dictionary.setProperty("Error while executing function", "Error saat mengeksekusi fungsi");
        dictionary.setProperty("Error", "Error");
        dictionary.setProperty("infoPanel", "Info");
        dictionary.setProperty("imageButton", "Tentang");
        dictionary.setProperty("Window", "Window");
        dictionary.setProperty("windowmnemonic", "W");
        dictionary.setProperty("Close All", "Tutup Semua");
        dictionary.setProperty("closeallmnemonic", "A");
        dictionary.setProperty("closemnemonic", "C");
        dictionary.setProperty("Press ENTER to find function", "Tekan ENTER untuk mencari fungsi");
        dictionary.setProperty("Find Function", "Cari Fungsi");
        dictionary.setProperty("Operation in progress...", "Operasi sedang berjalan...");
        dictionary.setProperty("close window", "Tutup Window");
        dictionary.setProperty("reduce to icon", "Perkecil menjadi icon");
        dictionary.setProperty("save changes?", "Simpan perubahan?");
        dictionary.setProperty("confirm window closing", "Konfirmasi penutupan jendela");
        dictionary.setProperty("change background", "Ubah background");
        dictionary.setProperty("reset background", "Reset background");

        dictionary.setProperty("switch", "Switch");
        dictionary.setProperty("switchmnemonic", "S");
        dictionary.setProperty("window name", "nama Window");
        dictionary.setProperty("opened windows", "Window terbuka");
        dictionary.setProperty("tile horizontally", "Tile horizontally");
        dictionary.setProperty("tilehorizontallymnemonic", "H");
        dictionary.setProperty("tile vertically", "Tile vertically");
        dictionary.setProperty("tileverticallymnemonic", "V");
        dictionary.setProperty("cascade", "Cascade");
        dictionary.setProperty("cascademnemonic", "C");
        dictionary.setProperty("minimize", "Minimize");
        dictionary.setProperty("minimizemnemonic", "M");
        dictionary.setProperty("minimize all", "Minimize all");
        dictionary.setProperty("minimizeallmnemonic", "A");
        dictionary.setProperty("selected frame", "frame terpilih");

        // server...
        dictionary.setProperty("Client request not supported", "permintaan Client tidak didukung");
        dictionary.setProperty("User disconnected", "User terputus");
        dictionary.setProperty("Updating not performed: the record was previously updated.", "Proses penyuntingan tidak dilakukan: record telah disunting sebelumnya.");

        // wizard...
        dictionary.setProperty("back", "Back");
        dictionary.setProperty("next", "Next");
        dictionary.setProperty("finish", "Finish");

        // image panel...
        dictionary.setProperty("image selection", "Image selection");

        // tip of the day panel...
        dictionary.setProperty("show 'tip of the day' after launching", "Lihat 'tip of the day' setelah launching");
        dictionary.setProperty("previous tip", "Tip sebelumnya");
        dictionary.setProperty("next tip", "Tip selanjutnya");
        dictionary.setProperty("close", "Tutup");
        dictionary.setProperty("tip of the day", "Tip hari ini");
        dictionary.setProperty("select tip", "Pilih tip");
        dictionary.setProperty("tip name", "Nama Tip");
        dictionary.setProperty("tips list", "Daftar Tips");

        // progress panel...
        dictionary.setProperty("progress", "Progress");

        // licence agreement...
        dictionary.setProperty("i accept the terms in the licence agreement", "Saya menerima kondisi dari perjanjuan lisensi");
        dictionary.setProperty("ok", "Ok");
        dictionary.setProperty("i do not accept the terms in the licence agreement", "Saya menolak kondisi dari perjanjuan lisensi");

        // property grid control
        dictionary.setProperty("property name", "Nama");
        dictionary.setProperty("property value", "Nilai");

        // grid profile
        dictionary.setProperty("grid profile management", "Profil manajemen grid");
        dictionary.setProperty("restore default grid profile", "Kembalikan profil grid default");
        dictionary.setProperty("create new grid profile", "Buat profil grid baru");
        dictionary.setProperty("profile description", "Deskripsi profil");
        dictionary.setProperty("remove current grid profile", "Hapus profil grid sekarang");
        dictionary.setProperty("select grid profile", "Pilih profil grid");
        dictionary.setProperty("default profile", "Profil default");

        // search box
        dictionary.setProperty("search", "Cari");
        dictionary.setProperty("not found", "Tidak ditemukan");

        // drag...
        dictionary.setProperty("drag", "Seret");

        // pivot table...
        dictionary.setProperty("pivot table settings", "Setting tabel Pivot");
        dictionary.setProperty("row fields", "Field baris");
        dictionary.setProperty("column fields", "Field kolom");
        dictionary.setProperty("data fields", "Field data");
        dictionary.setProperty("filtering conditions", "Kondisi penyaringan");
        dictionary.setProperty("field", "Field");
        dictionary.setProperty("checked", "Terpilih");
        dictionary.setProperty("at least one data field must be selected", "Setidaknya satu field data harus terpilih.");
        dictionary.setProperty("at least one row field must be selected", "Setidaknya satu baris field harus terpilih.");
        dictionary.setProperty("at least one column field must be selected", "Setidaknya satu kolom field harus terpilih.");
        dictionary.setProperty("expand all", "Buka semua");
        dictionary.setProperty("collapse all", "Tutup semua");

        dictionary.setProperty(Consts.EQ, "Sama dengan");
        dictionary.setProperty(Consts.GE, "Lebih besar dari sama dengan");
        dictionary.setProperty(Consts.GT, "Lebih besar dari");
        dictionary.setProperty(Consts.IS_NOT_NULL, "terisi");
        dictionary.setProperty(Consts.IS_NULL, "tidak terisi");
        dictionary.setProperty(Consts.LE, "Kurang dari sama dengan");
        dictionary.setProperty(Consts.LIKE, "Mengandung");
        dictionary.setProperty(Consts.LT, "Kurang dari");
        dictionary.setProperty(Consts.NEQ, "Tidak sama dengan");
        dictionary.setProperty(Consts.IN, "Mengandung nilai");
        dictionary.setProperty(Consts.ASC_SORTED, "Ascending");
        dictionary.setProperty(Consts.DESC_SORTED, "Descending");  


        resources = new Resources(
                dictionary,
                currencySymbol,
                '.',
                ',',
                Resources.YMD,
                true,
                dateFormatSeparator,
                "HH:mm",
                "EN",
                showResourceNotFoundWarning);
    }

    /**
     * @return internationalization settings, according with the current
     * language
     */
    public final Resources getResources() {
        return resources;
    }

    /**
     * Load dictionary, according to the specified language id.
     *
     * @param langId language id identifier
     */
    public final void setLanguage(String langId) throws UnsupportedOperationException {
        if (!resources.getLanguageId().equals(langId)) {
            throw new UnsupportedOperationException("Language identifier not supported.");
        }
    }

    /**
     * @param langId language id identifier
     * @return internationalization settings, according with the language
     * specified
     */
    public final Resources getResources(String langId) throws UnsupportedOperationException {
        if (!resources.getLanguageId().equals(langId)) {
            throw new UnsupportedOperationException("Language identifier not supported.");
        }
        return resources;
    }
}
