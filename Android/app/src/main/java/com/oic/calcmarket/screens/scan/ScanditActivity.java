package com.oic.calcmarket.screens.scan;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mirasense.scanditsdk.interfaces.ScanditSDKOverlay;
import com.oic.calcmarket.R;
import com.scandit.barcodepicker.BarcodePicker;
import com.scandit.barcodepicker.OnScanListener;
import com.scandit.barcodepicker.ScanSession;
import com.scandit.barcodepicker.ScanSettings;
import com.scandit.barcodepicker.ScanditLicense;
import com.scandit.recognition.Barcode;

/**
 * Created by khacpham on 12/25/15.
 */
public class ScanditActivity extends Activity implements OnScanListener {


    private ImageView flashBtn;
    private boolean flashOn = false;
    private BarcodePicker mPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set your app key
        ScanditLicense.setAppKey(getString(R.string.barcode_key));
        ScanSettings settings = ScanSettings.create();
        settings.setSymbologyEnabled(Barcode.SYMBOLOGY_QR, true);
        settings.setSymbologyEnabled(Barcode.SYMBOLOGY_UPCA, true);
        settings.setSymbologyEnabled(Barcode.SYMBOLOGY_UPCE, true);
        settings.setSymbologyEnabled(Barcode.SYMBOLOGY_EAN8, true);
        settings.setSymbologyEnabled(Barcode.SYMBOLOGY_EAN13, true);
        settings.setSymbologyEnabled(Barcode.SYMBOLOGY_CODE39, true);
        settings.setSymbologyEnabled(Barcode.SYMBOLOGY_CODE128, true);
        settings.setSymbologyEnabled(Barcode.SYMBOLOGY_CODE93, true);
        settings.setSymbologyEnabled(Barcode.SYMBOLOGY_INTERLEAVED_2_OF_5, true);
        settings.setSymbologyEnabled(Barcode.SYMBOLOGY_CODABAR, true);
        settings.setSymbologyEnabled(Barcode.SYMBOLOGY_GS1_DATABAR, true);
        settings.setSymbologyEnabled(Barcode.SYMBOLOGY_GS1_DATABAR_EXPANDED, true);
        settings.setSymbologyEnabled(Barcode.SYMBOLOGY_MSI_PLESSEY, true);
        // reduce the scanning area to 10% of the display height.
        settings.setScanningHotSpotHeight(0.3f);
        settings.setRestrictedAreaScanningEnabled(true);


        BarcodePicker picker = new BarcodePicker(this, settings);
        picker.setOnScanListener(this);

        RelativeLayout contentView = picker.getOverlayView();
        if (contentView instanceof ScanditSDKOverlay) {
            ((ScanditSDKOverlay) contentView).setTorchEnabled(false);
        }
        LayoutInflater.from(this).inflate(R.layout.scan_activity, contentView, true);

        this.mPicker = picker;
        setContentView(mPicker);

        // Reduce the size of the white viewfinder rectangle.
        // This only affects the rectangle - not the active scanning area!
//        mPicker.getOverlayView().setViewfinderDimension(0.8f, 0.3f, 0.8f, 0.3f);
        mPicker.getOverlayView().drawViewfinder(false);
        mPicker.getOverlayView().setBeepEnabled(false);

        flashBtn = (ImageView) findViewById(R.id.flashBtn);
        flashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashOn = !flashOn;

                if (!flashOn) {
                    flashBtn.setImageResource(R.drawable.flash_off);
                } else {
                    flashBtn.setImageResource(R.drawable.flash_on);
                }

                mPicker.switchTorchOn(flashOn);
            }
        });
    }

    @Override
    protected void onResume() {
        mPicker.startScanning();
        super.onResume();
    }
    @Override
    protected void onPause() {
        mPicker.stopScanning();
        super.onPause();
    }

    @Override
    public void didScan(ScanSession scanSession) {

    }
}
