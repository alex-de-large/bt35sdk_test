package com.example.bt35_sdk_test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.SurfaceView;

import androidx.appcompat.app.AppCompatActivity;

import com.epson.moverio.hardware.camera.CameraDevice;
import com.epson.moverio.hardware.camera.CameraManager;
import com.epson.moverio.hardware.camera.CameraProperty;
import com.epson.moverio.hardware.camera.CaptureDataCallback;
import com.epson.moverio.hardware.camera.CaptureStateCallback;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private CameraManager mCameraManager;
    private CameraDevice mCameraDevice;
    private CameraProperty cameraProperty;
    private SurfaceView mSurfaceView;

    private QrCodeReader qrCodeReader;

    private int width = 2592;
    private int height = 1944;
    private int fps = 15;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSurfaceView = findViewById(R.id.surface_view);
        mCameraManager = new CameraManager(this);
        try {
            mCameraDevice = mCameraManager.open(mCaptureStateCallback, null, mSurfaceView.getHolder());
        } catch (IOException e) {
            e.printStackTrace();
        }
        cameraProperty = mCameraDevice.getProperty();
        cameraProperty.setCaptureSize(width, height);
        cameraProperty.setCaptureFps(fps);
        mCameraDevice.setProperty(cameraProperty);

        qrCodeReader = new QrCodeReader();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCameraDevice.startCapture();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCameraDevice.stopPreview();
        mCameraDevice.stopCapture();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCameraManager.close(mCameraDevice);
    }

    private CaptureDataCallback captureDataCallback = new CaptureDataCallback() {
        @Override
        public void onCaptureData(long l, byte[] bytes) {
            Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            Bitmap mutableBitmap = bmp.copy(Bitmap.Config.RGB_565, true);
        }
    };

    private CaptureStateCallback mCaptureStateCallback = new CaptureStateCallback() {
        @Override
        public void onCaptureStarted() {
            mCameraDevice.startPreview();
        }

        @Override
        public void onCaptureStopped() {

        }

        @Override
        public void onPreviewStarted() {

        }

        @Override
        public void onPreviewStopped() {

        }

        @Override
        public void onRecordStarted() {

        }

        @Override
        public void onRecordStopped() {

        }

        @Override
        public void onPictureCompleted() {

        }
    };

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }
}