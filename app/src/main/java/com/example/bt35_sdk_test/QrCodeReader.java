package com.example.bt35_sdk_test;

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

    public void scan(byte[] data, int width, int height, int rotation) {

    }

    private InputImage getImage(byte[] data, int width, int height, int rotation) {
        return InputImage.fromByteArray(
                data,
                width,
                height,
                rotation,
                InputImage.IMAGE_FORMAT_NV21
        );
    }
}
