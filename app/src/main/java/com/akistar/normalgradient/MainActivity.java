package com.akistar.normalgradient;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.opengl.GLES20;
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

import java.util.Timer;
import java.util.TimerTask;

import static android.support.v4.graphics.ColorUtils.HSLToColor;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    GLSurfaceView glView;
    SeekBar disp1SB, disp2SB, speedPulsSB, speedColorSB, rCenterSB;
    public static float disp1Value, disp2Value, speedPulsValue, speedColorValue, typeofGrad = 3, rCenterValue = 0.1f;

    public static float gradient2_disp1Value, gradient2_disp2Value, gradient2_speedPulsValue,
            gradient2_speedColorValue, gradient2_typeOfGrad = 3,
            gradient2_rCenterValue = 0.1f;

    public static float gradient3_disp1Value, gradient3_disp2Value, gradient3_speedPulsValue,
            gradient3_speedColorValue, gradient3_typeOfGrad = 3,
            gradient3_rCenterValue;

    public static float gradient4_disp1Value, gradient4_disp2Value, gradient4_speedPulsValue,
            gradient4_speedColorValue, gradient4_typeOfGrad = 3,
            gradient4_rCenterValue;

    TextView color1, color2, txtDisp1, txtDisp2, txtSpeedPulse, txtSpeedColor;
    public static String color1Value, color2Value;
    public static String gradient2_color1Value, gradient2_color2Value;
    public static String gradient3_color1Value, gradient3_color2Value;
    public static String gradient4_color1Value, gradient4_color2Value;
    RadioButton rbGradient1, rbGradient2, rbGradient3, rbGradient4, rbGausseType, rbTanType, rbAlphaType,rbAchromaticType,rbComplementaryType,rbMonochromaticType,
            rbAnalogousType,rbCompoundType,rbTriadicType,rbSquareType, rbFormula4,rbFormula5,rbFormula6;

    Button btnOk, btnAdd, btnDel;
    public static boolean GRADIENT_2 = false;
    public static boolean GRADIENT_3 = false;
    public static boolean GRADIENT_4 = false;
    public static int kolichestvoGradienotv = 1;
    private BottomSheetBehavior mBottomSheetBehavior;
    boolean isClick = false;

    public ColorM.HSL fstColor = new ColorM.HSL(0, 0, 1);
    public ColorM.HSL sndColor = new ColorM.HSL(0, 0, 0);
    public ColorM.HSL trdColor = new ColorM.HSL(0, 0, 0);
    public ColorM.HSL frthColor = new ColorM.HSL(0, 0, 0);

    public static ColorM.RGB firstColorRGB = new ColorM.RGB(0,0,0);
    public static ColorM.RGB secondColorRGB = new ColorM.RGB(0,0,0);
    public static ColorM.RGB thirdColorRGB = new ColorM.RGB(0,0,0);
    public static ColorM.RGB fourthColorRGB = new ColorM.RGB(0,0,0);





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbAchromaticType = findViewById(R.id.rbAchromaticType);
        rbComplementaryType=findViewById(R.id.rbComplementaryType);
        rbMonochromaticType = findViewById(R.id.rbMonochromaticType);
        rbAnalogousType = findViewById(R.id.rbAnalogousType);
        rbCompoundType = findViewById(R.id.rbCompoundType);
        rbTriadicType = findViewById(R.id.rbTriadicType);
        rbSquareType = findViewById(R.id.rbSquareType);
        rbAchromaticType.setOnClickListener(this);
        rbComplementaryType.setOnClickListener(this);
        rbMonochromaticType.setOnClickListener(this);
        rbAnalogousType.setOnClickListener(this);
        rbCompoundType.setOnClickListener(this);
        rbTriadicType.setOnClickListener(this);
        rbSquareType.setOnClickListener(this);

        disp1SB = (SeekBar) findViewById(R.id.disp1SB);
        disp2SB = (SeekBar) findViewById(R.id.disp2SB);
        speedPulsSB = (SeekBar) findViewById(R.id.speedPulsSB);
        speedColorSB = (SeekBar) findViewById(R.id.speedColorSB);
        color1 = (TextView) findViewById(R.id.txtColor1);
        color2 = (TextView) findViewById(R.id.txtColor2);
        rCenterSB = findViewById(R.id.rCircleSB);
        rCenterSB.setOnSeekBarChangeListener(rCenterChangeListener);

        btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);


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

        rbGausseType = findViewById(R.id.rbGauseType);
        rbTanType = findViewById(R.id.rbTanType);
        rbGausseType.setOnClickListener(this);
        rbTanType.setOnClickListener(this);
        rbAlphaType = findViewById(R.id.rbAlphaType);
        rbAlphaType.setOnClickListener(this);
        rbAlphaType.setChecked(true);
        rbFormula4 = findViewById(R.id.rbFormula4);
        rbFormula5 = findViewById(R.id.rbFormula5);
        rbFormula6 = findViewById(R.id.rbFormula6);
        rbFormula4.setOnClickListener(this);
        rbFormula5.setOnClickListener(this);
        rbFormula6.setOnClickListener(this);

        btnAdd = findViewById(R.id.btnAdd);
        btnDel = findViewById(R.id.btnDel);
        btnAdd.setOnClickListener(this);
        btnDel.setOnClickListener(this);

        disp1SB.setOnSeekBarChangeListener(disp1ChangeListener);
        disp2SB.setOnSeekBarChangeListener(disp2ChangeListener);

        speedPulsSB.setOnSeekBarChangeListener(speedPulseChangeListener);
        speedColorSB.setOnSeekBarChangeListener(speedColorChangeListener);

        txtDisp1 = findViewById(R.id.txtDisp1);
        txtDisp2 = findViewById(R.id.txtDisp2);
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
        glView.setEGLContextClientVersion(3);
        glView.setPreserveEGLContextOnPause(true);
        glView.setEGLConfigChooser(8,8,8,8,16,0);

        glView.setRenderer(new Render(this));
        glView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

        int color = Color.parseColor(color1Value);
        firstColorRGB.r =(color >> 16) & 0xff;
        firstColorRGB.g=(color >> 8) & 0xff;
        firstColorRGB.b=(color) & 0xff;
        firstColorRGB.r=firstColorRGB.r/255f;
        firstColorRGB.g=firstColorRGB.g/255f;
        firstColorRGB.b=firstColorRGB.b/255f;
        fstColor=firstColorRGB.toHSL();


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                glView.requestRender();
            }
        },0,30);

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
            if (rbGradient1.isChecked()) {
                disp1Value = (float) i;
            }
            if (rbGradient2.isChecked()) {
                gradient2_disp1Value = (float) i;
            }
            if (rbGradient3.isChecked()) {
                gradient3_disp1Value = (float) i;
            }
            if (rbGradient4.isChecked()) {
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
            if (rbGradient1.isChecked()) {
                disp2Value = (float) i;
            }
            if (rbGradient2.isChecked()) {
                gradient2_disp2Value = (float) i;
            }
            if (rbGradient3.isChecked()) {
                gradient3_disp2Value = (float) i;
            }
            if (rbGradient4.isChecked()) {
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
    private SeekBar.OnSeekBarChangeListener speedPulseChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            if (rbGradient1.isChecked()) {
                speedPulsValue = (float) i;
            }
            if (rbGradient2.isChecked()) {
                gradient2_speedPulsValue = (float) i;
            }
            if (rbGradient3.isChecked()) {
                gradient3_speedPulsValue = (float) i;
            }
            if (rbGradient4.isChecked()) {
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
            if (rbGradient1.isChecked()) {
                speedColorValue = (float) i;
            }
            if (rbGradient2.isChecked()) {
                gradient2_speedColorValue = (float) i;
            }
            if (rbGradient3.isChecked()) {
                gradient3_speedColorValue = (float) i;
            }
            if (rbGradient4.isChecked()) {
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
    private SeekBar.OnSeekBarChangeListener rCenterChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            if (rbGradient1.isChecked()) {
                rCenterValue = (float) i;
            }
            if (rbGradient2.isChecked()) {
                gradient2_rCenterValue = (float) i;
            }
            if (rbGradient3.isChecked()) {
                gradient3_rCenterValue = (float) i;
            }
            if (rbGradient4.isChecked()) {
                gradient4_rCenterValue = (float) i;
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

        switch (view.getId()) {
            case R.id.btnAdd:
                kolichestvoGradienotv++;
                if (kolichestvoGradienotv == 4) {
                    rbGradient4.setVisibility(View.VISIBLE);
                    GRADIENT_4 = true;
                }
                if (kolichestvoGradienotv >= 2) {
                    rbGradient2.setVisibility(View.VISIBLE);
                    GRADIENT_2 = true;
                }
                if (kolichestvoGradienotv >= 3) {
                    rbGradient3.setVisibility(View.VISIBLE);
                    GRADIENT_3 = true;
                }
                if (kolichestvoGradienotv > 4) {
                    kolichestvoGradienotv = 4;
                }
                break;
            case R.id.btnDel:
                kolichestvoGradienotv--;
                if (kolichestvoGradienotv <= 1) {
                    kolichestvoGradienotv = 1;
                }
                if (kolichestvoGradienotv <= 3) {
                    rbGradient4.setVisibility(View.INVISIBLE);
                    GRADIENT_4 = false;
                }
                if (kolichestvoGradienotv <= 2) {
                    rbGradient3.setVisibility(View.INVISIBLE);
                    GRADIENT_3 = false;
                }
                if (kolichestvoGradienotv == 1) {
                    rbGradient2.setVisibility(View.INVISIBLE);
                    GRADIENT_2 = false;
                }
                break;
            case R.id.rbGradient1:
                rbAchromaticType.setEnabled(true);
                rbComplementaryType.setEnabled(true);
                rbMonochromaticType.setEnabled(true);
                rbAnalogousType.setEnabled(true);
                rbCompoundType.setEnabled(true);
                rbTriadicType.setEnabled(true);
                rbSquareType.setEnabled(true);
                color1.setEnabled(true);
                color2.setEnabled(true);
                color1.setText(color1Value);
                color2.setText(color2Value);
                disp1SB.setProgress((int) disp1Value);
                disp2SB.setProgress((int) disp2Value);
                btnOk.setEnabled(true);
                speedPulsSB.setProgress((int) speedPulsValue);
                speedColorSB.setProgress((int) speedColorValue);
                rbGausseType.setEnabled(true);
                rbTanType.setEnabled(true);
                rCenterSB.setProgress((int) rCenterValue);

                if (typeofGrad == 1) {
                    rbGausseType.setChecked(true);
                }
                if (typeofGrad == 2) {
                    rbTanType.setChecked(true);
                }
                if (typeofGrad == 3) {
                    rbAlphaType.setChecked(true);
                }
                if (typeofGrad == 4) {
                    rbFormula4.setChecked(true);
                }
                if (typeofGrad == 5) {
                    rbFormula5.setChecked(true);
                }
                if (typeofGrad == 6) {
                    rbFormula6.setChecked(true);
                }


                break;
            case R.id.rbGradient2:
                rbAchromaticType.setEnabled(false);
                rbComplementaryType.setEnabled(false);
                rbMonochromaticType.setEnabled(false);
                rbAnalogousType.setEnabled(false);
                rbCompoundType.setEnabled(false);
                rbTriadicType.setEnabled(false);
                rbSquareType.setEnabled(false);
                color1.setEnabled(false);
                color2.setEnabled(false);
                color1.setText(gradient2_color1Value);
                color2.setText(gradient2_color2Value);
                disp1SB.setProgress((int) gradient2_disp1Value);
                disp2SB.setProgress((int) gradient2_disp2Value);
                rbGausseType.setEnabled(false);
                rbTanType.setEnabled(false);
                btnOk.setEnabled(false);
                speedPulsSB.setProgress((int) gradient2_speedPulsValue);
                speedColorSB.setProgress((int) gradient2_speedColorValue);
                rCenterSB.setProgress((int) gradient2_rCenterValue);

                if (gradient2_typeOfGrad == 1) {
                    rbGausseType.setChecked(true);
                }
                if (gradient2_typeOfGrad == 2) {
                    rbTanType.setChecked(true);
                }
                if (gradient2_typeOfGrad == 3) {
                    rbAlphaType.setChecked(true);
                }
                if (gradient2_typeOfGrad == 4) {
                    rbFormula4.setChecked(true);
                }
                if (gradient2_typeOfGrad == 5) {
                    rbFormula5.setChecked(true);
                }
                if (gradient2_typeOfGrad == 6) {
                    rbFormula6.setChecked(true);
                }
                break;
            case R.id.rbGradient3:
                rbAchromaticType.setEnabled(false);
                rbComplementaryType.setEnabled(false);
                rbMonochromaticType.setEnabled(false);
                rbAnalogousType.setEnabled(false);
                rbCompoundType.setEnabled(false);
                rbTriadicType.setEnabled(false);
                rbSquareType.setEnabled(false);
                color1.setEnabled(false);
                color2.setEnabled(false);
                color1.setText(gradient3_color1Value);
                color2.setText(gradient3_color2Value);
                disp1SB.setProgress((int) gradient3_disp1Value);
                disp2SB.setProgress((int) gradient3_disp2Value);
                rbGausseType.setEnabled(false);
                rbTanType.setEnabled(false);
                btnOk.setEnabled(false);
                speedPulsSB.setProgress((int) gradient3_speedPulsValue);
                speedColorSB.setProgress((int) gradient3_speedColorValue);

                rCenterSB.setProgress((int) gradient3_rCenterValue);

                if (gradient3_typeOfGrad == 1) {
                    rbGausseType.setChecked(true);
                }
                if (gradient3_typeOfGrad == 2) {
                    rbTanType.setChecked(true);
                }

                if (gradient3_typeOfGrad == 3) {
                    rbAlphaType.setChecked(true);
                }
                if (gradient3_typeOfGrad == 4) {
                    rbFormula4.setChecked(true);
                }
                if (gradient3_typeOfGrad == 5) {
                    rbFormula5.setChecked(true);
                }
                if (gradient3_typeOfGrad == 6) {
                    rbFormula6.setChecked(true);
                }
                break;
            case R.id.rbGradient4:
                rbAchromaticType.setEnabled(false);
                rbComplementaryType.setEnabled(false);
                rbMonochromaticType.setEnabled(false);
                rbAnalogousType.setEnabled(false);
                rbCompoundType.setEnabled(false);
                rbTriadicType.setEnabled(false);
                rbSquareType.setEnabled(false);
                color1.setEnabled(false);
                color2.setEnabled(false);
                color1.setText(gradient4_color1Value);
                color2.setText(gradient4_color2Value);
                disp1SB.setProgress((int) gradient4_disp1Value);
                disp2SB.setProgress((int) gradient4_disp2Value);

                speedPulsSB.setProgress((int) gradient4_speedPulsValue);
                speedColorSB.setProgress((int) gradient4_speedColorValue);

                rCenterSB.setProgress((int) gradient4_rCenterValue);
                rbGausseType.setEnabled(false);
                rbTanType.setEnabled(false);
                btnOk.setEnabled(false);

                if (gradient4_typeOfGrad == 1) {
                    rbGausseType.setChecked(true);
                }
                if (gradient4_typeOfGrad == 2) {
                    rbTanType.setChecked(true);
                }
                if (gradient4_typeOfGrad == 3) {
                    rbAlphaType.setChecked(true);
                }
                if (gradient4_typeOfGrad == 4) {
                    rbFormula4.setChecked(true);
                }
                if (gradient4_typeOfGrad == 5) {
                    rbFormula5.setChecked(true);
                }
                if (gradient4_typeOfGrad == 6) {
                    rbFormula6.setChecked(true);
                }
                break;
            case R.id.btnOk:
                if (rbGradient1.isChecked()) {
                    color1Value = color1.getText().toString();
                    color2Value = color2.getText().toString();
                }
                int color = Color.parseColor(color1Value);
                firstColorRGB.r =((color >> 16) & 0xff);
                firstColorRGB.g=(color >> 8) & 0xff;
                firstColorRGB.b=(color) & 0xff;
                firstColorRGB.r=firstColorRGB.r/255f;
                firstColorRGB.g=firstColorRGB.g/255f;
                firstColorRGB.b=firstColorRGB.b/255f;
                fstColor=firstColorRGB.toHSL();
                    if(rbAchromaticType.isChecked()){
                       applyAchromatic();
                       firstColorRGB=fstColor.toRGB();
                       secondColorRGB=sndColor.toRGB();
                       thirdColorRGB=trdColor.toRGB();
                       fourthColorRGB=frthColor.toRGB();
                    }
                    if(rbComplementaryType.isChecked()){
                        applyComplementary();
                        secondColorRGB=sndColor.toRGB();
                        thirdColorRGB=trdColor.toRGB();
                        fourthColorRGB=frthColor.toRGB();
                    }
                    if(rbMonochromaticType.isChecked()){
                        applyMonochromatic();
                        secondColorRGB=sndColor.toRGB();
                        thirdColorRGB=trdColor.toRGB();
                        fourthColorRGB=frthColor.toRGB();
                    }
                    if(rbAnalogousType.isChecked()){
                        applyAnalogous();
                        secondColorRGB=sndColor.toRGB();
                        thirdColorRGB=trdColor.toRGB();
                        fourthColorRGB=frthColor.toRGB();
                    }
                    if(rbCompoundType.isChecked()){
                        applyCompound();
                        secondColorRGB=sndColor.toRGB();
                        thirdColorRGB=trdColor.toRGB();
                        fourthColorRGB=frthColor.toRGB();
                    }
                    if(rbTriadicType.isChecked()){
                        applyTriadic();
                        secondColorRGB=sndColor.toRGB();
                        thirdColorRGB=trdColor.toRGB();
                        fourthColorRGB=frthColor.toRGB();
                    }
                    if(rbSquareType.isChecked()){
                        applySquare();
                        secondColorRGB=sndColor.toRGB();
                        thirdColorRGB=trdColor.toRGB();
                        fourthColorRGB=frthColor.toRGB();
                    }

                break;
            case R.id.rbGauseType:
                if (rbGradient1.isChecked()) {
                    typeofGrad = 1;
                }
                if (rbGradient2.isChecked()) {
                    gradient2_typeOfGrad = 1;
                }
                if (rbGradient3.isChecked()) {
                    gradient3_typeOfGrad = 1;
                }
                if (rbGradient4.isChecked()) {
                    gradient4_typeOfGrad = 1;
                }
                break;
            case R.id.rbTanType:
                if (rbGradient1.isChecked()) {
                    typeofGrad = 2;
                }
                if (rbGradient2.isChecked()) {
                    gradient2_typeOfGrad = 2;
                }
                if (rbGradient3.isChecked()) {
                    gradient3_typeOfGrad = 2;
                }
                if (rbGradient4.isChecked()) {
                    gradient4_typeOfGrad = 2;
                }
                break;
            case R.id.rbAlphaType:
                if (rbGradient1.isChecked()) {
                    typeofGrad = 3;
                }
                if (rbGradient2.isChecked()) {
                    gradient2_typeOfGrad = 3;
                }
                if (rbGradient3.isChecked()) {
                    gradient3_typeOfGrad = 3;
                }
                if (rbGradient4.isChecked()) {
                    gradient4_typeOfGrad = 3;
                }
                break;
            case R.id.rbFormula4:
                if (rbGradient1.isChecked()) {
                    typeofGrad = 4;
                }
                if (rbGradient2.isChecked()) {
                    gradient2_typeOfGrad = 4;
                }
                if (rbGradient3.isChecked()) {
                    gradient3_typeOfGrad = 4;
                }
                if (rbGradient4.isChecked()) {
                    gradient4_typeOfGrad = 4;
                }
                break;
            case R.id.rbFormula5:
                if (rbGradient1.isChecked()) {
                    typeofGrad = 5;
                }
                if (rbGradient2.isChecked()) {
                    gradient2_typeOfGrad = 5;
                }
                if (rbGradient3.isChecked()) {
                    gradient3_typeOfGrad = 5;
                }
                if (rbGradient4.isChecked()) {
                    gradient4_typeOfGrad = 5;
                }
                break;
            case R.id.rbFormula6:
                if (rbGradient1.isChecked()) {
                    typeofGrad = 6;
                }
                if (rbGradient2.isChecked()) {
                    gradient2_typeOfGrad = 6;
                }
                if (rbGradient3.isChecked()) {
                    gradient3_typeOfGrad = 6;
                }
                if (rbGradient4.isChecked()) {
                    gradient4_typeOfGrad = 6;
                }
                break;

            case R.id.btnSheetMenu:
                if (isClick == false)
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                else
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                isClick = !isClick;
                break;
            case R.id.rbAchromaticType:
                applyAchromatic();
                firstColorRGB=fstColor.toRGB();
                secondColorRGB=sndColor.toRGB();
                thirdColorRGB=trdColor.toRGB();
                fourthColorRGB=frthColor.toRGB();
                break;
            case R.id.rbComplementaryType:
                applyComplementary();
                secondColorRGB=sndColor.toRGB();
                thirdColorRGB=trdColor.toRGB();
                fourthColorRGB=frthColor.toRGB();
                break;
            case R.id.rbMonochromaticType:
                applyMonochromatic();
                secondColorRGB=sndColor.toRGB();
                thirdColorRGB=trdColor.toRGB();
                fourthColorRGB=frthColor.toRGB();
                break;

            case R.id.rbAnalogousType:
                applyAnalogous();
                secondColorRGB=sndColor.toRGB();
                thirdColorRGB=trdColor.toRGB();
                fourthColorRGB=frthColor.toRGB();
                break;

            case R.id.rbCompoundType:
                applyCompound();
                secondColorRGB=sndColor.toRGB();
                thirdColorRGB=trdColor.toRGB();
                fourthColorRGB=frthColor.toRGB();
            break;
            case R.id.rbTriadicType:
                applyTriadic();
                secondColorRGB=sndColor.toRGB();
                thirdColorRGB=trdColor.toRGB();
                fourthColorRGB=frthColor.toRGB();
            break;
            case R.id.rbSquareType:
                applySquare();
                secondColorRGB=sndColor.toRGB();
                thirdColorRGB=trdColor.toRGB();
                fourthColorRGB=frthColor.toRGB();
            break;

        }



    }
    private void applyAchromatic() {
        fstColor.set(0,0,0.75f);
        sndColor.set(0,0,1);
        trdColor.set(0,0,1);
        frthColor.set(0,0,1);
        float l = 1 - fstColor.l;

        sndColor.l = l;

        float l1 = 1- sndColor.l*0.35f;

        trdColor.l = l1;

        float l2 = 1 - trdColor.l*0.75f;

        frthColor.l=l2;
    }
    private void applyComplementary() {
        int color = Color.parseColor(color1Value);
        firstColorRGB.r =((color >> 16) & 0xff);
        firstColorRGB.g=(color >> 8) & 0xff;
        firstColorRGB.b=(color) & 0xff;
        firstColorRGB.r=firstColorRGB.r/255f;
        firstColorRGB.g=firstColorRGB.g/255f;
        firstColorRGB.b=firstColorRGB.b/255f;
        fstColor=firstColorRGB.toHSL();

        float h = fstColor.h + 180f;

        if (h > 360f) {
            h -= 360f;
        }

        sndColor.set(h, fstColor.s, fstColor.l);

        float h1 = sndColor.h+180f;
        if (h1 > 360f) {
            h1 -= 360f;
        }
        trdColor.set(h1,sndColor.s,sndColor.l);

        float h2 = trdColor.h+180f;
        if (h2 > 360f) {
            h2 -= 360f;
        }
        frthColor.set(h2,trdColor.s,trdColor.l);

    }
    private void applyMonochromatic() {
        int color = Color.parseColor(color1Value);
        firstColorRGB.r =((color >> 16) & 0xff);
        firstColorRGB.g=(color >> 8) & 0xff;
        firstColorRGB.b=(color) & 0xff;
        firstColorRGB.r=firstColorRGB.r/255f;
        firstColorRGB.g=firstColorRGB.g/255f;
        firstColorRGB.b=firstColorRGB.b/255f;
        fstColor=firstColorRGB.toHSL();
        float l = fstColor.l * 0.6f;
        sndColor.set(fstColor.h, fstColor.s, l);
        float l1=sndColor.l * 0.6f;
        trdColor.set(sndColor.h, sndColor.s, l1);
        float l2=trdColor.l*0.6f;
        frthColor.set(trdColor.h, trdColor.s, l2);
    }

    private void applyAnalogous() {
        int color = Color.parseColor(color1Value);
        firstColorRGB.r =((color >> 16) & 0xff);
        firstColorRGB.g=(color >> 8) & 0xff;
        firstColorRGB.b=(color) & 0xff;
        firstColorRGB.r=firstColorRGB.r/255f;
        firstColorRGB.g=firstColorRGB.g/255f;
        firstColorRGB.b=firstColorRGB.b/255f;
        fstColor=firstColorRGB.toHSL();
        float h = adjustHUE(fstColor.h, 60);
        sndColor.set(h, fstColor.s, fstColor.l);
        float h1 = adjustHUE(sndColor.h,60);
        trdColor.set(h1, sndColor.s,sndColor.l);
        float h2 = adjustHUE(trdColor.h,60);
        frthColor.set(h2, trdColor.s,trdColor.l);
    }

    private void applyCompound() {
        int color = Color.parseColor(color1Value);
        firstColorRGB.r =((color >> 16) & 0xff);
        firstColorRGB.g=(color >> 8) & 0xff;
        firstColorRGB.b=(color) & 0xff;
        firstColorRGB.r=firstColorRGB.r/255f;
        firstColorRGB.g=firstColorRGB.g/255f;
        firstColorRGB.b=firstColorRGB.b/255f;
        fstColor=firstColorRGB.toHSL();
        float h = adjustHUE(fstColor.h + 180, 30);

        sndColor.set(h, fstColor.s, fstColor.l);

        float h1 = adjustHUE(sndColor.h+180,30);
        trdColor.set(h1,sndColor.s,sndColor.l);
        float h2 = adjustHUE(trdColor.h+180,30);
        frthColor.set(h2,trdColor.s,trdColor.l);
    }

    private void applyTriadic() {
        int color = Color.parseColor(color1Value);
        firstColorRGB.r =((color >> 16) & 0xff);
        firstColorRGB.g=(color >> 8) & 0xff;
        firstColorRGB.b=(color) & 0xff;
        firstColorRGB.r=firstColorRGB.r/255f;
        firstColorRGB.g=firstColorRGB.g/255f;
        firstColorRGB.b=firstColorRGB.b/255f;
        fstColor=firstColorRGB.toHSL();
        float h = adjustHUE(fstColor.h, -90);

        sndColor.set(h, fstColor.s, fstColor.l);

        float h1 = adjustHUE(sndColor.h,-90);
        trdColor.set(h1,sndColor.s,sndColor.l);
        float h2 = adjustHUE(frthColor.h,-90);
        frthColor.set(h2,trdColor.s,trdColor.l);
    }

    private void applySquare() {
        int color = Color.parseColor(color1Value);
        firstColorRGB.r =((color >> 16) & 0xff);
        firstColorRGB.g=(color >> 8) & 0xff;
        firstColorRGB.b=(color) & 0xff;
        firstColorRGB.r=firstColorRGB.r/255f;
        firstColorRGB.g=firstColorRGB.g/255f;
        firstColorRGB.b=firstColorRGB.b/255f;
        fstColor=firstColorRGB.toHSL();
        float h = adjustHUE(fstColor.h, 120);

        sndColor.set(h, fstColor.s, fstColor.l);
        float h1 = adjustHUE(sndColor.h, 120);

        trdColor.set(h1, sndColor.s, sndColor.l);
        float h2 = adjustHUE(trdColor.h, 120);

        frthColor.set(h2, trdColor.s, trdColor.l);
    }

    private float adjustHUE(float hue, float angle) {
        float h = hue + angle;

        if (h > 360) {
            h -= 360;
        }

        return h;
    }

}
