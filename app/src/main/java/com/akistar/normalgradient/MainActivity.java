package com.akistar.normalgradient;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {
    GLSurfaceView glView;
    SeekBar disp1SB, disp2SB, speedMoveSB, speedPulsSB, speedColorSB, alphaSB,rCenterSB;
    public static float disp1Value, disp2Value, speedMoveValue, speedPulsValue, speedColorValue,alphaValue,typeofGrad=1,rCenterValue=0.1f;
    public static float gradient2_disp1Value, gradient2_disp2Value, gradient2_speedMoveValue, gradient2_speedPulsValue,
            gradient2_speedColorValue,gradient2_alphaValue,gradient2_typeMove=1,gradient2_typeOfGrad=1,
            gradient2_rCenterValue=0.1f;

    public static float gradient3_disp1Value, gradient3_disp2Value, gradient3_speedMoveValue, gradient3_speedPulsValue,
            gradient3_speedColorValue,gradient3_alphaValue,gradient3_typeOfGrad=1,
             gradient3_rCenterValue;

    public static float gradient4_disp1Value, gradient4_disp2Value, gradient4_speedMoveValue, gradient4_speedPulsValue,
            gradient4_speedColorValue,gradient4_alphaValue,gradient4_typeOfGrad=1,
             gradient4_rCenterValue;

    TextView color1, color2, txtDisp1, txtDisp2, txtSpeedMove, txtSpeedPulse, txtSpeedColor;
    public static String color1Value, color2Value;
    public static String gradient2_color1Value, gradient2_color2Value;
    public static String gradient3_color1Value, gradient3_color2Value;
    public static String gradient4_color1Value, gradient4_color2Value;
    RadioButton rbGradient1, rbGradient2, rbGradient3, rbGradient4, rbClockMove, rbRandomMove, rbGausseType,rbTanType,rbAlphaType,rbCircleMove,rbCounterCircleMove;
    Button btnOk, btnAdd, btnDel;
    public static boolean GRADIENT_2 = false;
    public static boolean GRADIENT_3 = false;
    public static boolean GRADIENT_4 = false;
    public static int kolichestvoGradienotv = 1;
    private BottomSheetBehavior mBottomSheetBehavior;
    boolean isClick=false;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        disp1SB = (SeekBar) findViewById(R.id.disp1SB);
        disp2SB = (SeekBar) findViewById(R.id.disp2SB);
        speedMoveSB = (SeekBar) findViewById(R.id.speedMoveSB);
        speedPulsSB = (SeekBar) findViewById(R.id.speedPulsSB);
        speedColorSB = (SeekBar) findViewById(R.id.speedColorSB);
        color1 = (TextView) findViewById(R.id.txtColor1);
        color2 = (TextView) findViewById(R.id.txtColor2);
        rCenterSB = findViewById(R.id.rCircleSB);
        rCenterSB.setOnSeekBarChangeListener(rCenterChangeListener);

        btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);

        alphaSB = findViewById(R.id.alphaSB);
        alphaSB.setOnSeekBarChangeListener(alphaChangeListener);

        rbGradient1 = findViewById(R.id.rbGradient1);
        rbGradient2 = findViewById(R.id.rbGradient2);
        rbGradient3 = findViewById(R.id.rbGradient3);
        rbGradient4 = findViewById(R.id.rbGradient4);
        rbGradient1.setOnClickListener(this);
        rbGradient2.setOnClickListener(this);
        rbGradient3.setOnClickListener(this);
        rbGradient4.setOnClickListener(this);
        rbGradient1.setChecked(true);
        rbGradient2.setVisibility(View.INVISIBLE);
        rbGradient3.setVisibility(View.INVISIBLE);
        rbGradient4.setVisibility(View.INVISIBLE);
        rbClockMove =findViewById(R.id.rbClockMove);
        rbRandomMove =findViewById(R.id.rbRandomMove);
        rbClockMove.setOnClickListener(this);
        rbRandomMove.setOnClickListener(this);
        rbGausseType = findViewById(R.id.rbGauseType);
        rbTanType = findViewById(R.id.rbTanType);
        rbGausseType.setOnClickListener(this);
        rbTanType.setOnClickListener(this);
        rbAlphaType=findViewById(R.id.rbAlphaType);
        rbAlphaType.setOnClickListener(this);
        rbCircleMove = findViewById(R.id.rbCircleMove);
        rbCircleMove.setOnClickListener(this);
        rbCounterCircleMove = findViewById(R.id.rbCounterCircleMove);
        rbCounterCircleMove.setOnClickListener(this);

        btnAdd = findViewById(R.id.btnAdd);
        btnDel = findViewById(R.id.btnDel);
        btnAdd.setOnClickListener(this);
        btnDel.setOnClickListener(this);

        disp1SB.setOnSeekBarChangeListener(disp1ChangeListener);
        disp2SB.setOnSeekBarChangeListener(disp2ChangeListener);
        speedMoveSB.setOnSeekBarChangeListener(speedMoveChangeListener);
        speedPulsSB.setOnSeekBarChangeListener(speedPulseChangeListener);
        speedColorSB.setOnSeekBarChangeListener(speedColorChangeListener);

        txtDisp1 = findViewById(R.id.txtDisp1);
        txtDisp2 = findViewById(R.id.txtDisp2);
        txtSpeedMove = findViewById(R.id.txtSpeedMove);
        txtSpeedPulse = findViewById(R.id.txtSpeedPulse);
        txtSpeedColor = findViewById(R.id.txtSpeedColor);

        View bottomSheet = findViewById(R.id.bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        Button button123 = findViewById(R.id.btnSheetMenu);
        button123.setOnClickListener(this);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });






        color1Value = "#82119e";
        color2Value = "#ff7300";
        gradient2_color1Value = "#d41919";
        gradient2_color2Value = "#1929d4";
        gradient3_color1Value = "#18de0d";
        gradient3_color2Value = "#24d624";
        gradient4_color1Value = "#f5f507";
        gradient4_color2Value = "#07f5f5";

        color1.setText(color1Value);
        color2.setText(color2Value);


        if (!supportES2()) {
            finish();
            return;
        }
        glView = (GLSurfaceView) findViewById(R.id.glView);
        glView.setEGLContextClientVersion(2);
        glView.setPreserveEGLContextOnPause(true);
        glView.setRenderer(new Render(this));



    }

    @Override
    protected void onPause() {
        super.onPause();
        glView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        glView.onResume();
    }

    private boolean supportES2() {
        ActivityManager activityManager =
                (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();

        return (configurationInfo.reqGlEsVersion >= 0x20000);
    }

    private SeekBar.OnSeekBarChangeListener disp1ChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            if(rbGradient1.isChecked()) {
                disp1Value = (float) i;
            }
            if(rbGradient2.isChecked()){
                gradient2_disp1Value = (float) i;
            }
            if(rbGradient3.isChecked()){
                gradient3_disp1Value = (float) i;
            }
            if(rbGradient4.isChecked()){
                gradient4_disp1Value = (float) i;
            }

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    private SeekBar.OnSeekBarChangeListener disp2ChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            if(rbGradient1.isChecked()) {
                disp2Value = (float) i;
            }
            if(rbGradient2.isChecked()){
                gradient2_disp2Value = (float) i;
            }
            if(rbGradient3.isChecked()){
                gradient3_disp2Value = (float) i;
            }
            if(rbGradient4.isChecked()){
                gradient4_disp2Value = (float) i;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    private SeekBar.OnSeekBarChangeListener speedMoveChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            if(rbGradient1.isChecked()) {
                speedMoveValue = (float) i;
            }
            if(rbGradient2.isChecked()){
                gradient2_speedMoveValue = (float) i;
            }
            if(rbGradient3.isChecked()){
                gradient3_speedMoveValue = (float) i;
            }
            if(rbGradient4.isChecked()){
                gradient4_speedMoveValue = (float) i;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    private SeekBar.OnSeekBarChangeListener speedPulseChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            if(rbGradient1.isChecked()) {
                speedPulsValue = (float) i;
            }
            if(rbGradient2.isChecked()){
                gradient2_speedPulsValue = (float) i;
            }
            if(rbGradient3.isChecked()){
                gradient3_speedPulsValue = (float) i;
            }
            if(rbGradient4.isChecked()){
                gradient4_speedPulsValue = (float) i;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    private SeekBar.OnSeekBarChangeListener speedColorChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            if(rbGradient1.isChecked()) {
                speedColorValue = (float) i;
            }
            if(rbGradient2.isChecked()){
                gradient2_speedColorValue = (float) i;
            }
            if(rbGradient3.isChecked()){
                gradient3_speedColorValue = (float) i;
            }
            if(rbGradient4.isChecked()){
                gradient4_speedColorValue = (float) i;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    private SeekBar.OnSeekBarChangeListener alphaChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            if(rbGradient1.isChecked()) {
                alphaValue = (float) i;
            }
            if(rbGradient2.isChecked()){
                gradient2_alphaValue = (float)  i;
            }
            if(rbGradient3.isChecked()){
                gradient3_alphaValue = (float)  i;
            }
            if(rbGradient4.isChecked()){
                gradient4_alphaValue = (float)  i;
            }

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    private SeekBar.OnSeekBarChangeListener rCenterChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            if(rbGradient1.isChecked()) {
                rCenterValue = (float) i;
            }
            if(rbGradient2.isChecked()){
                gradient2_rCenterValue = (float)  i;
            }
            if(rbGradient3.isChecked()){
                gradient3_rCenterValue = (float)  i;
            }
            if(rbGradient4.isChecked()){
                gradient4_rCenterValue = (float)  i;
            }

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };





    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnAdd:
                kolichestvoGradienotv++;
                if(kolichestvoGradienotv==4){
                    rbGradient4.setVisibility(View.VISIBLE);
                    GRADIENT_4=true;
                }
                if(kolichestvoGradienotv>=2){
                    rbGradient2.setVisibility(View.VISIBLE);
                    GRADIENT_2=true;
                }
                if(kolichestvoGradienotv>=3){
                    rbGradient3.setVisibility(View.VISIBLE);
                    GRADIENT_3=true;
                }
                if(kolichestvoGradienotv>4){
                    kolichestvoGradienotv=4;
                }
                break;
            case R.id.btnDel:
                kolichestvoGradienotv--;
                if(kolichestvoGradienotv<=1){
                    kolichestvoGradienotv=1;
                }
                if(kolichestvoGradienotv<=3){
                    rbGradient4.setVisibility(View.INVISIBLE);
                    GRADIENT_4=false;
                }
                if(kolichestvoGradienotv<=2){
                    rbGradient3.setVisibility(View.INVISIBLE);
                    GRADIENT_3=false;
                }
                if(kolichestvoGradienotv==1){
                    rbGradient2.setVisibility(View.INVISIBLE);
                    GRADIENT_2=false;
                }
                break;
            case R.id.rbGradient1:
                color1.setText(color1Value);
                color2.setText(color2Value);
                disp1SB.setProgress((int)disp1Value);
                disp2SB.setProgress((int)disp2Value);
                speedMoveSB.setProgress((int)speedMoveValue);
                speedPulsSB.setProgress((int)speedPulsValue);
                speedColorSB.setProgress((int)speedColorValue);
                alphaSB.setProgress((int)alphaValue);
                rCenterSB.setProgress((int)rCenterValue);

                if(typeofGrad==1){
                    rbGausseType.setChecked(true);
                }
                if(typeofGrad==2){
                    rbTanType.setChecked(true);
                }
                if(typeofGrad==3){
                    rbAlphaType.setChecked(true);
                }


                break;
            case R.id.rbGradient2:
                color1.setText(gradient2_color1Value);
                color2.setText(gradient2_color2Value);
                disp1SB.setProgress((int)gradient2_disp1Value);
                disp2SB.setProgress((int)gradient2_disp2Value);
                speedMoveSB.setProgress((int)gradient2_speedMoveValue);
                speedPulsSB.setProgress((int)gradient2_speedPulsValue);
                speedColorSB.setProgress((int)gradient2_speedColorValue);
                alphaSB.setProgress((int)gradient2_alphaValue);
                rCenterSB.setProgress((int)gradient2_rCenterValue);
                if(gradient2_typeOfGrad==1){
                    rbGausseType.setChecked(true);
                }
                if(gradient2_typeOfGrad==2){
                    rbTanType.setChecked(true);
                }
                if(gradient2_typeOfGrad==3){
                    rbAlphaType.setChecked(true);
                }
                break;
            case R.id.rbGradient3:
                color1.setText(gradient3_color1Value);
                color2.setText(gradient3_color2Value);
                disp1SB.setProgress((int)gradient3_disp1Value);
                disp2SB.setProgress((int)gradient3_disp2Value);
                speedMoveSB.setProgress((int)gradient3_speedMoveValue);
                speedPulsSB.setProgress((int)gradient3_speedPulsValue);
                speedColorSB.setProgress((int)gradient3_speedColorValue);
                alphaSB.setProgress((int)gradient3_alphaValue);
                rCenterSB.setProgress((int)gradient3_rCenterValue);

                if(gradient3_typeOfGrad==1){
                    rbGausseType.setChecked(true);
                }
                if(gradient3_typeOfGrad==2){
                    rbTanType.setChecked(true);
                }

                if(gradient3_typeOfGrad==3){
                    rbAlphaType.setChecked(true);
                }
                break;
            case R.id.rbGradient4:
                color1.setText(gradient4_color1Value);
                color2.setText(gradient4_color2Value);
                disp1SB.setProgress((int)gradient4_disp1Value);
                disp2SB.setProgress((int)gradient4_disp2Value);
                speedMoveSB.setProgress((int)gradient4_speedMoveValue);
                speedPulsSB.setProgress((int)gradient4_speedPulsValue);
                speedColorSB.setProgress((int)gradient4_speedColorValue);
                alphaSB.setProgress((int)gradient4_alphaValue);
                rCenterSB.setProgress((int)gradient4_rCenterValue);

                if(gradient4_typeOfGrad==1){
                    rbGausseType.setChecked(true);
                }
                if(gradient4_typeOfGrad==2){
                    rbTanType.setChecked(true);
                }

                if(gradient4_typeOfGrad==3){
                    rbAlphaType.setChecked(true);
                }
                break;
            case R.id.btnOk:
                if(rbGradient1.isChecked()) {
                    color1Value = color1.getText().toString();
                    color2Value = color2.getText().toString();
                }
                if(rbGradient2.isChecked()) {
                    gradient2_color1Value = color1.getText().toString();
                    gradient2_color2Value = color2.getText().toString();
                }
                if(rbGradient3.isChecked()) {
                    gradient3_color1Value = color1.getText().toString();
                    gradient3_color2Value = color2.getText().toString();
                }
                if(rbGradient4.isChecked()) {
                    gradient4_color1Value = color1.getText().toString();
                    gradient4_color2Value = color2.getText().toString();
                }

                break;

            case R.id.rbGauseType:
                if(rbGradient1.isChecked()) {
                    typeofGrad=1;
                }
                if(rbGradient2.isChecked()) {
                    gradient2_typeOfGrad=1;
                }
                if(rbGradient3.isChecked()) {
                    gradient3_typeOfGrad=1;
                }
                if(rbGradient4.isChecked()) {
                    gradient4_typeOfGrad=1;
                }
                break;
            case R.id.rbTanType:
                if(rbGradient1.isChecked()) {
                    typeofGrad=2;
                }
                if(rbGradient2.isChecked()) {
                    gradient2_typeOfGrad=2;
                }
                if(rbGradient3.isChecked()) {
                    gradient3_typeOfGrad=2;
                }
                if(rbGradient4.isChecked()) {
                    gradient4_typeOfGrad=2;
                }
                break;
            case R.id.rbAlphaType:
                if(rbGradient1.isChecked()) {
                    typeofGrad=3;
                }
                if(rbGradient2.isChecked()) {
                    gradient2_typeOfGrad=3;
                }
                if(rbGradient3.isChecked()) {
                    gradient3_typeOfGrad=3;
                }
                if(rbGradient4.isChecked()) {
                    gradient4_typeOfGrad=3;
                }
                break;
            case R.id.btnSheetMenu:
                if(isClick==false)
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                else
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                isClick=!isClick;

                break;





        }

    }
}
