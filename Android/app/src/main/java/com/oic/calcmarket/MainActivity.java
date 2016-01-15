package com.oic.calcmarket;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mirasense.scanditsdk.interfaces.ScanditSDKOverlay;
import com.oic.calcmarket.common.network.BarcodeMng;
import com.oic.calcmarket.common.views.edittext.CTextView;
import com.oic.calcmarket.common.views.market.BillLayout;
import com.oic.calcmarket.common.views.residemenu.ResideMenu;
import com.oic.calcmarket.common.views.residemenu.ResideMenuItem;
import com.oic.calcmarket.models.BarcodeRsp;
import com.scandit.barcodepicker.BarcodePicker;
import com.scandit.barcodepicker.OnScanListener;
import com.scandit.barcodepicker.ScanSession;
import com.scandit.barcodepicker.ScanSettings;
import com.scandit.barcodepicker.ScanditLicense;
import com.scandit.recognition.Barcode;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnScanListener, BillLayout.OnBillChangedListener {

    public static final String titles[] = {"Shopping", "History", "Settings"};
    public static final int icon[] = {R.drawable.ic_menu_home, R.drawable.ic_menu_profile, R.drawable.ic_menu_setting};

    ResideMenu resideMenu;

    Toolbar toolbar;

    MenuItem scanMenu;

    private BarcodePicker mPicker;

    @Bind(R.id.scanLayout)
    public FrameLayout scanLayout;

    @Bind(R.id.layoutProducts)
    public LinearLayout layoutProducts;

    @Bind(R.id.tvTotal)
    public CTextView tvTotal;

    private boolean isRequesting = false;

    Call<BarcodeRsp> response;

    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        resideMenu = new ResideMenu(this);

        // Set up your ActionBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.toggle();
            }
        });

        ButterKnife.bind(this);

        setupResideMenu();

        int childCount = layoutProducts.getChildCount();
        for(int i=0;i<childCount;i++){
            BillLayout item = (BillLayout)layoutProducts.getChildAt(i);
            item.reset();
            item.setOnBillChangedListener(this);
        }
    }

    private void setupResideMenu() {
        // attach to current activity;
        resideMenu.setBackground(R.drawable.ic_menu_bg);
        resideMenu.attachToActivity(this);

        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        resideMenu.addIgnoredView(layoutProducts);

        // create menu items;
        for (int i = 0; i < titles.length; i++) {
            ResideMenuItem item = new ResideMenuItem(this, icon[i], titles[i]);
            item.setTag(titles[i]);
            item.setOnClickListener(this);
            resideMenu.addMenuItem(item, ResideMenu.DIRECTION_LEFT);
        }
    }

    private void setupScandit(){
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


        this.mPicker = new BarcodePicker(this, settings);
        this.mPicker.setOnScanListener(this);

        RelativeLayout scanView = this.mPicker.getOverlayView();
        if (scanView instanceof ScanditSDKOverlay) {
            ((ScanditSDKOverlay) scanView).setTorchEnabled(false);
        }

        scanLayout.removeAllViews();
        scanLayout.addView(scanView.getRootView());

        // Reduce the size of the white viewfinder rectangle.
        // This only affects the rectangle - not the active scanning area!
        mPicker.getOverlayView().setViewfinderDimension(0.8f, 0.3f, 0.8f, 0.3f);
        mPicker.getOverlayView().drawViewfinder(false);
        mPicker.getOverlayView().setBeepEnabled(false);
    }

    @Override
    public void onClick(View v) {
        Log.e("TAG", v.getTag().toString());
        if (v.getTag().toString().equalsIgnoreCase(titles[0])) {  // home
        } else if (v.getTag().toString().equalsIgnoreCase(titles[1])) { //profile

        } else if (v.getTag().toString().equalsIgnoreCase(titles[2])) { //setting

        }
        resideMenu.toggle();
    }

    @Override
    public void onBackPressed() {
        if(resideMenu.isOpened()){
            resideMenu.closeMenu();
            return;
        }

        if(scanLayout.getAlpha() != 0){
            toggleScan();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        scanMenu = menu.findItem(R.id.menuScan);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuSubmit) {
            return true;
        }
        else if(id == R.id.menuScan) {
            Log.e("TAG", "submit clicked");
            toggleScan();
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggleScan(){
        if(this.mPicker == null){
            setupScandit();
        }
        if(scanLayout.getAlpha()==0){
            mPicker.startScanning();
//            scanLayout.setVisibility(View.VISIBLE);
            scanMenu.setIcon(R.drawable.ic_scan_selected);
            scanLayout.animate().alpha(1).setDuration(2000).start();
        }else{
            mPicker.stopScanning();
//            scanLayout.setVisibility(View.GONE);
            scanMenu.setIcon(R.drawable.ic_scan);
            scanLayout.animate().alpha(0).setDuration(2000).start();
        }
    }

    @Override
    protected void onResume() {
        if(mPicker != null) {
            mPicker.startScanning();
        }
        super.onResume();
    }
    @Override
    protected void onPause() {
        if(mPicker != null) {
            mPicker.stopScanning();
        }
        super.onPause();
    }

    @Override
    public void didScan(ScanSession scanSession) {
        if(isRequesting){
            Log.e("TAG","found barcode but requesting. cancel request first");
            if(response!=null){
                response.cancel();
            }
        }
        String result = scanSession.getNewlyRecognizedCodes().get(0).getData();
        Log.e("TAG", "scanDit: " + result);

        response = BarcodeMng.getApi().findProduct(getString(R.string.upc_api_key), result);
        isRequesting = true;
        response.enqueue(new Callback<BarcodeRsp>() {

            @Override
            public void onResponse(Response<BarcodeRsp> response, Retrofit retrofit) {
                BarcodeRsp barcode = response.body();
                BillLayout item = new BillLayout(MainActivity.this);
                item.setDataContext(barcode);
                item.setOnBillChangedListener(MainActivity.this);
                layoutProducts.addView(item);
                pendingFinish();

            }

            @Override
            public void onFailure(Throwable t) {
                pendingFinish();
            }

            public void pendingFinish(){
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isRequesting = false;
                    }
                }, 500);
            }
        });
    }

    @Override
    public void remove(ViewGroup parent,BillLayout item) {
        if(parent.getChildCount()<4){
            item.reset();
            parent.addView(item);
        }
    }

    @Override
    public void change(float cost, int quantity) {
        float total = 0.0f;
        int childCount = layoutProducts.getChildCount();
        for(int i=0;i<childCount;i++){
            BillLayout item = (BillLayout)layoutProducts.getChildAt(i);
            total+=item.getCost();
        }
        tvTotal.setText(total+"");
    }
}
