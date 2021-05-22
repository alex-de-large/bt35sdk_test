package com.example.bt35_sdk_test;

import com.google.mlkit.vision.barcode.Barcode;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;

public class QrCodeReader {

    private BarcodeScannerOptions options;

    public interface OnScanSuccess {
        void onSuccess();
    }

    public QrCodeReader() {
        init();
    }

    private void init() {
        options = new BarcodeScannerOptions.Builder()
                .setBarcodeFormats(
                        Barcode.FORMAT_QR_CODE,
                        Barcode.FORMAT_AZTEC)
                .build();

    }

    public void scan(byte[] data) {

    }
}
