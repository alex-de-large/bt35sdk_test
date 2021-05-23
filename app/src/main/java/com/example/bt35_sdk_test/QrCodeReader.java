package com.example.bt35_sdk_test;

import android.graphics.Bitmap;

import com.google.mlkit.vision.barcode.Barcode;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.common.InputImage;

public class QrCodeReader {

    private BarcodeScanner barcodeScanner;
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
        barcodeScanner = BarcodeScanning.getClient(options);
    }

    public void scan(Bitmap bitmap, int rotation) {

    }

    private InputImage getImage(Bitmap bitmap, int rotation) {
        return InputImage.fromBitmap(bitmap, rotation);
    }
}
