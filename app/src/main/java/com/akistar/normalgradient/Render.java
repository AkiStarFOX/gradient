package com.akistar.normalgradient;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES10.GL_ALPHA_TEST;
import static android.opengl.GLES10.GL_ONE_MINUS_SRC_ALPHA;
import static android.opengl.GLES20.GL_BLEND;
import static android.opengl.GLES20.GL_SRC_ALPHA;
import static android.opengl.GLES20.glBlendFunc;
import static android.opengl.GLES20.glEnable;
import static java.lang.Math.PI;
import static java.lang.Math.pow;
import static java.lang.Math.random;
import static java.lang.Math.sqrt;

/**
 * Created by AkiStar on 05.03.2018.
 */

public class Render implements GLSurfaceView.Renderer {
    public static float texCoords[] = {
            0.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f,
    };

    public static short inds[] = {
            1, 0, 3,
            1, 3, 2,

    };

    private int _programId;
    public static Context _context;
    float overviewScale;
    float overviewScaleY;
    float translationX;
    float translationY;
    public static int _imgW;
    public static int _imgH;

    public static int _glViewWidth;
    public static int _glViewHeight;
    private int _aPositionLocation;
    private int _uProjMLocation;
    private int _uModelMLocation;
    public FloatBuffer _vertices;
    public FloatBuffer _texCoords;
    public ShortBuffer _indices;

    private float[] _screenProjM = new float[16];
    private float[] _modelM = new float[16];
    private float _tickTimeXtemp=0;


    public Render(Context context) {
        _context = context;
    }

    public static Bitmap image;

    static void setImage(Bitmap bitmap) {
        image = bitmap;
    }

    private int _aTexCoordLocation;
    private int _uTextureLocation;
    private int _textureId;

    private int _disp1;
    private int _disp2;
    private int _u1;
    private int _u2;
    private int randomColor1;
    private int randomColor2;
    private int randomColor3;
    private float _disp1Default = 0.1f;
    private float _disp2Default = 0.1f;

    float dvijenieX = 0.5f;
    float dvijenieY = 0.5f;
    boolean tuda = true;
    boolean vverx = true;
    public float randomColorValue1;
    public float randomColorValue2;
    public float randomColorValue3;
    boolean changeColor1 = true;
    boolean changeColor2 = true;
    boolean changeColor3 = false;


    private int _uFirstColorRed;
    private int _uFirstColorGreen;
    private int _uFirstColorBlue;
    private int _uSecondColorRed;
    private int _uSecondColorGreen;
    private int _uSecondColorBlue;
    private int _uRazColorRed;
    private int _uRazColorGreen;
    private int _uRazColorBlue;
    private int _uPulse;

    private float _firstColorRed;
    private float _firstColorGreen;
    private float _firstColorBlue;
    private float _secondColorRed;
    private float _secondColorGreen;
    private float _secondColorBlue;
    private float _razColorRed;
    private float _razColorGreen;
    private float _razColorBlue;
    private float _pulse;
    boolean pulseGo = true;


    private int _gradient2_disp1;
    private int _gradient2_disp2;
    private int _gradient2_u1;
    private int _gradient2_u2;

    private int _gradient3_disp1;
    private int _gradient3_disp2;
    private int _gradient3_u1;
    private int _gradient3_u2;

    private int _gradient4_disp1;
    private int _gradient4_disp2;
    private int _gradient4_u1;
    private int _gradient4_u2;

    private float _gradient2_disp1Default = 0.01f;
    private float _gradient2_disp2Default = 0.01f;

    private float _gradient3_disp1Default = 0.25f;
    private float _gradient3_disp2Default = 0.25f;

    private float _gradient4_disp1Default = 0.25f;
    private float _gradient4_disp2Default = 0.25f;

    float _gradient2_dvijenieX;
    float _gradient2_dvijenieY;

    float _gradient3_dvijenieX;
    float _gradient3_dvijenieY;

    float _gradient4_dvijenieX ;
    float _gradient4_dvijenieY ;
    float _gradient2_dvijenieX_default = 1.0f;
    float _gradient2_dvijenieY_default = 0.1f;

    float _gradient3_dvijenieX_default = 1.0f;
    float _gradient3_dvijenieY_default = 1.0f;

    float _gradient4_dvijenieX_default = 0.1f;
    float _gradient4_dvijenieY_default = 1.0f;

    private int _gradient2_uFirstColorRed;
    private int _gradient2_uFirstColorGreen;
    private int _gradient2_uFirstColorBlue;
    private int _gradient2_uSecondColorRed;
    private int _gradient2_uSecondColorGreen;
    private int _gradient2_uSecondColorBlue;
    private int _gradient2_uRazColorRed;
    private int _gradient2_uRazColorGreen;
    private int _gradient2_uRazColorBlue;
    private int _gradient2_uPulse;

    private float _gradient2_firstColorRed;
    private float _gradient2_firstColorGreen;
    private float _gradient2_firstColorBlue;
    private float _gradient2_secondColorRed;
    private float _gradient2_secondColorGreen;
    private float _gradient2_secondColorBlue;
    private float _gradient2_razColorRed;
    private float _gradient2_razColorGreen;
    private float _gradient2_razColorBlue;


    private int _gradient3_uFirstColorRed;
    private int _gradient3_uFirstColorGreen;
    private int _gradient3_uFirstColorBlue;
    private int _gradient3_uSecondColorRed;
    private int _gradient3_uSecondColorGreen;
    private int _gradient3_uSecondColorBlue;
    private int _gradient3_uRazColorRed;
    private int _gradient3_uRazColorGreen;
    private int _gradient3_uRazColorBlue;
    private int _gradient3_uPulse;

    private float _gradient3_firstColorRed;
    private float _gradient3_firstColorGreen;
    private float _gradient3_firstColorBlue;
    private float _gradient3_secondColorRed;
    private float _gradient3_secondColorGreen;
    private float _gradient3_secondColorBlue;
    private float _gradient3_razColorRed;
    private float _gradient3_razColorGreen;
    private float _gradient3_razColorBlue;


    private int _gradient4_uFirstColorRed;
    private int _gradient4_uFirstColorGreen;
    private int _gradient4_uFirstColorBlue;
    private int _gradient4_uSecondColorRed;
    private int _gradient4_uSecondColorGreen;
    private int _gradient4_uSecondColorBlue;
    private int _gradient4_uRazColorRed;
    private int _gradient4_uRazColorGreen;
    private int _gradient4_uRazColorBlue;
    private int _gradient4_uPulse;

    private float _gradient4_firstColorRed;
    private float _gradient4_firstColorGreen;
    private float _gradient4_firstColorBlue;
    private float _gradient4_secondColorRed;
    private float _gradient4_secondColorGreen;
    private float _gradient4_secondColorBlue;
    private float _gradient4_razColorRed;
    private float _gradient4_razColorGreen;
    private float _gradient4_razColorBlue;


    private int u_kolichestvoGrad;
    private int _kolichestvoGrad;


    private int _gradient2_randomColor1;
    private int _gradient2_randomColor2;
    private int _gradient2_randomColor3;
    public float _gradient2_randomColorValue1;
    public float _gradient2_randomColorValue2;
    public float _gradient2_randomColorValue3;
    boolean _gradient2_changeColor1 = true;
    boolean _gradient2_changeColor2 = true;
    boolean _gradient2_changeColor3 = false;

    private int _gradient3_randomColor1;
    private int _gradient3_randomColor2;
    private int _gradient3_randomColor3;
    public float _gradient3_randomColorValue1;
    public float _gradient3_randomColorValue2;
    public float _gradient3_randomColorValue3;
    boolean _gradient3_changeColor1 = true;
    boolean _gradient3_changeColor2 = true;
    boolean _gradient3_changeColor3 = false;

    private int _gradient4_randomColor1;
    private int _gradient4_randomColor2;
    private int _gradient4_randomColor3;
    public float _gradient4_randomColorValue1;
    public float _gradient4_randomColorValue2;
    public float _gradient4_randomColorValue3;
    boolean _gradient4_changeColor1 = true;
    boolean _gradient4_changeColor2 = true;
    boolean _gradient4_changeColor3 = false;

    boolean _gradient2_tuda = true;
    boolean _gradient2_vverx = false;

    boolean _gradient3_tuda = true;
    boolean _gradient3_vverx = false;

    boolean _gradient4_tuda = true;
    boolean _gradient4_vverx = false;


    private float _gradient2_pulse;
    boolean _gradient2_pulseGo = true;

    private float _gradient3_pulse;
    boolean _gradient3_pulseGo = true;

    private float _gradient4_pulse;
    boolean _gradient4_pulseGo = true;

    private int _uAlpha;
    private int _gradient2_uAlpha;
    private int _gradient3_uAlpha;
    private int _gradient4_uAlpha;

    private float _tickTimeX;
    private float _tickTimeY=240;
    private float _gradient2_tickTimeY=0.1f;
    private float _gradient3_tickTimeY;
    private float _gradient4_tickTimeY;
    private float _gradient2_tickTimeX;
    private float _gradient3_tickTimeX;
    private float _gradient4_tickTimeX;

    private int _uGradientType;
    private float _gradientType;

    private int _gradient2_uGradientType;
    private float _gradient2_gradientType;

    private int _gradient3_uGradientType;
    private float _gradient3_gradientType;

    private int _gradient4_uGradientType;
    private float _gradient4_gradientType;
    float xCenter=0.5f;
    float yCenter=0.5f;
    float rCenter=0.2f;
    float dvijenieXprew=0.0f;
    float dvijenieYprew=0.0f;
    float angle=0;
    int kolichestvoKrugov=0;


    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {

        int vertextShaderId = ShaderUtils.createShader(_context, GLES20.GL_VERTEX_SHADER, R.raw.vertex_shader);
        int fragmentShaderId = ShaderUtils.createShader(_context, GLES20.GL_FRAGMENT_SHADER, R.raw.fragment_shader);
        _programId = ShaderUtils.createProgram(vertextShaderId, fragmentShaderId);
        _aPositionLocation = GLES20.glGetAttribLocation(_programId, "a_Position");
        _uProjMLocation = GLES20.glGetUniformLocation(_programId, "u_ProjM");
        _uModelMLocation = GLES20.glGetUniformLocation(_programId, "u_ModelM");
        _aTexCoordLocation = GLES20.glGetAttribLocation(_programId, "a_TexCoord");
        _uTextureLocation = GLES20.glGetUniformLocation(_programId, "u_Texture");
        _disp1 = GLES20.glGetUniformLocation(_programId, "u_Disp1");
        _disp2 = GLES20.glGetUniformLocation(_programId, "u_Disp2");
        _u1 = GLES20.glGetUniformLocation(_programId, "u_u1");
        _u2 = GLES20.glGetUniformLocation(_programId, "u_u2");
        randomColor1 = GLES20.glGetUniformLocation(_programId, "u_color1");
        randomColor2 = GLES20.glGetUniformLocation(_programId, "u_color2");
        randomColor3 = GLES20.glGetUniformLocation(_programId, "u_color3");
        _uFirstColorRed = GLES20.glGetUniformLocation(_programId, "u_FirstColorRed");
        _uFirstColorGreen = GLES20.glGetUniformLocation(_programId, "u_FirstColorGreen");
        _uFirstColorBlue = GLES20.glGetUniformLocation(_programId, "u_FirstColorBlue");
        _uSecondColorRed = GLES20.glGetUniformLocation(_programId, "u_SecondColorRed");
        _uSecondColorGreen = GLES20.glGetUniformLocation(_programId, "u_SecondColorGreen");
        _uSecondColorBlue = GLES20.glGetUniformLocation(_programId, "u_SecondColorBlue");
        _uRazColorRed = GLES20.glGetUniformLocation(_programId, "u_RazColorRed");
        _uRazColorGreen = GLES20.glGetUniformLocation(_programId, "u_RazColorGreen");
        _uRazColorBlue = GLES20.glGetUniformLocation(_programId, "u_RazColorBlue");
        _uPulse = GLES20.glGetUniformLocation(_programId, "u_Pulse");
        _uAlpha = GLES20.glGetUniformLocation(_programId, "u_Alpha");

        _gradient2_disp1 = GLES20.glGetUniformLocation(_programId, "u_gradient2_Disp1");
        _gradient2_disp2 = GLES20.glGetUniformLocation(_programId, "u_gradient2_Disp2");
        _gradient2_u1 = GLES20.glGetUniformLocation(_programId, "u_gradient2_u1");
        _gradient2_u2 = GLES20.glGetUniformLocation(_programId, "u_gradient2_u2");
        _gradient2_uFirstColorRed = GLES20.glGetUniformLocation(_programId, "u_gradient2_FirstColorRed");
        _gradient2_uFirstColorGreen = GLES20.glGetUniformLocation(_programId, "u_gradient2_FirstColorGreen");
        _gradient2_uFirstColorBlue = GLES20.glGetUniformLocation(_programId, "u_gradient2_FirstColorBlue");
        _gradient2_uSecondColorRed = GLES20.glGetUniformLocation(_programId, "u_gradient2_SecondColorRed");
        _gradient2_uSecondColorGreen = GLES20.glGetUniformLocation(_programId, "u_gradient2_SecondColorGreen");
        _gradient2_uSecondColorBlue = GLES20.glGetUniformLocation(_programId, "u_gradient2_SecondColorBlue");
        _gradient2_uRazColorRed = GLES20.glGetUniformLocation(_programId, "u_gradient2_RazColorRed");
        _gradient2_uRazColorGreen = GLES20.glGetUniformLocation(_programId, "u_gradient2_RazColorGreen");
        _gradient2_uRazColorBlue = GLES20.glGetUniformLocation(_programId, "u_gradient2_RazColorBlue");
        _gradient2_uPulse = GLES20.glGetUniformLocation(_programId, "u_gradient2_Pulse");
        _gradient2_randomColor1 = GLES20.glGetUniformLocation(_programId, "u_gradient2_color1");
        _gradient2_randomColor2 = GLES20.glGetUniformLocation(_programId, "u_gradient2_color2");
        _gradient2_randomColor3 = GLES20.glGetUniformLocation(_programId, "u_gradient2_color3");
        _gradient2_uAlpha = GLES20.glGetUniformLocation(_programId, "u_gradient2_Alpha");

        _gradient3_disp1 = GLES20.glGetUniformLocation(_programId, "u_gradient3_Disp1");
        _gradient3_disp2 = GLES20.glGetUniformLocation(_programId, "u_gradient3_Disp2");
        _gradient3_u1 = GLES20.glGetUniformLocation(_programId, "u_gradient3_u1");
        _gradient3_u2 = GLES20.glGetUniformLocation(_programId, "u_gradient3_u2");
        _gradient3_uFirstColorRed = GLES20.glGetUniformLocation(_programId, "u_gradient3_FirstColorRed");
        _gradient3_uFirstColorGreen = GLES20.glGetUniformLocation(_programId, "u_gradient3_FirstColorGreen");
        _gradient3_uFirstColorBlue = GLES20.glGetUniformLocation(_programId, "u_gradient3_FirstColorBlue");
        _gradient3_uSecondColorRed = GLES20.glGetUniformLocation(_programId, "u_gradient3_SecondColorRed");
        _gradient3_uSecondColorGreen = GLES20.glGetUniformLocation(_programId, "u_gradient3_SecondColorGreen");
        _gradient3_uSecondColorBlue = GLES20.glGetUniformLocation(_programId, "u_gradient3_SecondColorBlue");
        _gradient3_uRazColorRed = GLES20.glGetUniformLocation(_programId, "u_gradient3_RazColorRed");
        _gradient3_uRazColorGreen = GLES20.glGetUniformLocation(_programId, "u_gradient3_RazColorGreen");
        _gradient3_uRazColorBlue = GLES20.glGetUniformLocation(_programId, "u_gradient3_RazColorBlue");
        _gradient3_uPulse = GLES20.glGetUniformLocation(_programId, "u_gradient3_Pulse");
        _gradient3_randomColor1 = GLES20.glGetUniformLocation(_programId, "u_gradient3_color1");
        _gradient3_randomColor2 = GLES20.glGetUniformLocation(_programId, "u_gradient3_color2");
        _gradient3_randomColor3 = GLES20.glGetUniformLocation(_programId, "u_gradient3_color3");
        _gradient3_uAlpha = GLES20.glGetUniformLocation(_programId, "u_gradient3_Alpha");

        _gradient4_disp1 = GLES20.glGetUniformLocation(_programId, "u_gradient4_Disp1");
        _gradient4_disp2 = GLES20.glGetUniformLocation(_programId, "u_gradient4_Disp2");
        _gradient4_u1 = GLES20.glGetUniformLocation(_programId, "u_gradient4_u1");
        _gradient4_u2 = GLES20.glGetUniformLocation(_programId, "u_gradient4_u2");
        _gradient4_uFirstColorRed = GLES20.glGetUniformLocation(_programId, "u_gradient4_FirstColorRed");
        _gradient4_uFirstColorGreen = GLES20.glGetUniformLocation(_programId, "u_gradient4_FirstColorGreen");
        _gradient4_uFirstColorBlue = GLES20.glGetUniformLocation(_programId, "u_gradient4_FirstColorBlue");
        _gradient4_uSecondColorRed = GLES20.glGetUniformLocation(_programId, "u_gradient4_SecondColorRed");
        _gradient4_uSecondColorGreen = GLES20.glGetUniformLocation(_programId, "u_gradient4_SecondColorGreen");
        _gradient4_uSecondColorBlue = GLES20.glGetUniformLocation(_programId, "u_gradient4_SecondColorBlue");
        _gradient4_uRazColorRed = GLES20.glGetUniformLocation(_programId, "u_gradient4_RazColorRed");
        _gradient4_uRazColorGreen = GLES20.glGetUniformLocation(_programId, "u_gradient4_RazColorGreen");
        _gradient4_uRazColorBlue = GLES20.glGetUniformLocation(_programId, "u_gradient4_RazColorBlue");
        _gradient4_uPulse = GLES20.glGetUniformLocation(_programId, "u_gradient4_Pulse");
        u_kolichestvoGrad = GLES20.glGetUniformLocation(_programId, "u_kolichestvoGrad");
        _gradient4_randomColor1 = GLES20.glGetUniformLocation(_programId, "u_gradient4_color1");
        _gradient4_randomColor2 = GLES20.glGetUniformLocation(_programId, "u_gradient4_color2");
        _gradient4_randomColor3 = GLES20.glGetUniformLocation(_programId, "u_gradient4_color3");
        _gradient4_uAlpha = GLES20.glGetUniformLocation(_programId, "u_gradient4_Alpha");

        _uGradientType = GLES20.glGetUniformLocation(_programId, "u_gradient1_Type");
        _gradient2_uGradientType = GLES20.glGetUniformLocation(_programId, "u_gradient2_Type");
        _gradient3_uGradientType = GLES20.glGetUniformLocation(_programId, "u_gradient3_Type");
        _gradient4_uGradientType = GLES20.glGetUniformLocation(_programId, "u_gradient4_Type");


        image = BitmapFactory.decodeResource(_context.getResources(), R.drawable.unnamed);
        _imgW = image.getWidth();
        _imgH = image.getHeight();


        // инициализируем буферы вершин, индексов и текстурных координат

//        _textureId = TextureUtils.loadTexture(image, true);


        glEnable(GL_ALPHA_TEST);

        glEnable(GL_BLEND);

        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);


    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        _glViewWidth = width;
        _glViewHeight = height;

        Matrix.orthoM(_screenProjM, 0, 0, width, height, 0, 0, 1);

        // вычисляем насколько нужно уменьшить и сдвинуть изображение, чтобы оно влезло в
        // glSurfaceView и было по центру
        overviewScale =(float) width / (float) height ;
        overviewScaleY = (float)(height/_imgH);

        translationX = width / 2.0f - (_imgW / 2.0f) * overviewScale;
        translationY = height / 2.0f - (_imgH / 2.0f) * overviewScale;


        // задаем матрицу модели
        Matrix.setIdentityM(_modelM, 0);
//        Matrix.translateM(_modelM, 0, translationX, translationY, 0);
//        Matrix.scaleM(_modelM, 0,overviewScale, overviewScale, 1.0f);

    }

    private void shaderParams() {
        float[] verts = arrayFromRectF(new RectF(0, 0, _glViewWidth, _glViewHeight));

        _vertices = ByteBuffer.allocateDirect(verts.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        _vertices.put(verts);
        _vertices.position(0);

        _indices = ByteBuffer.allocateDirect(verts.length * 2).order(ByteOrder.nativeOrder()).asShortBuffer();
        _indices.put(inds);
        _indices.position(0);

        _texCoords = ByteBuffer.allocateDirect(texCoords.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        _texCoords.put(texCoords);
        _texCoords.position(0);
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
        GLES20.glTexParameteri(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        GLES20.glTexParameteri(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        GLES20.glTexParameteri(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
        GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, _glViewWidth, _glViewHeight, 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, null);

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);


        GLES20.glUniform1i(_uTextureLocation, 0);


        glEnable(GL_ALPHA_TEST);

        glEnable(GL_BLEND);

        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        GLES20.glUniformMatrix4fv(_uProjMLocation, 1, false, _screenProjM, 0);
        GLES20.glUniformMatrix4fv(_uModelMLocation, 1, false, _modelM, 0);

        GLES20.glEnableVertexAttribArray(_aPositionLocation);
        GLES20.glVertexAttribPointer(_aPositionLocation, 3, GLES20.GL_FLOAT, false, 0, _vertices);

        GLES20.glEnableVertexAttribArray(_aTexCoordLocation);
        GLES20.glVertexAttribPointer(_aTexCoordLocation, 2, GLES20.GL_FLOAT, false, 0, _texCoords);

        GLES20.glUniform1f(_disp1, _disp1Default + MainActivity.disp1Value / 100f + _pulse);
        GLES20.glUniform1f(_disp2, _disp2Default + MainActivity.disp2Value / 100f + _pulse);
        GLES20.glUniform1f(_u1, dvijenieX);
        GLES20.glUniform1f(_u2, dvijenieY);
        GLES20.glUniform1f(randomColor1, randomColorValue1);
        GLES20.glUniform1f(randomColor2, randomColorValue2);
        GLES20.glUniform1f(randomColor3, randomColorValue3);


        GLES20.glUniform1f(_uFirstColorRed, _firstColorRed / 255f);
        GLES20.glUniform1f(_uFirstColorGreen, _firstColorGreen / 255f);
        GLES20.glUniform1f(_uFirstColorBlue, _firstColorBlue / 255f);
        GLES20.glUniform1f(_uSecondColorRed, _secondColorRed / 255f);
        GLES20.glUniform1f(_uSecondColorGreen, _secondColorGreen / 255f);
        GLES20.glUniform1f(_uSecondColorBlue, _secondColorBlue / 255f);
        GLES20.glUniform1f(_uRazColorRed, _razColorRed / 255f);
        GLES20.glUniform1f(_uRazColorGreen, _razColorGreen / 255f);
        GLES20.glUniform1f(_uRazColorBlue, _razColorBlue / 255f);


        GLES20.glUniform1f(_gradient2_disp1, _disp1Default + MainActivity.gradient2_disp1Value / 100f + _gradient2_pulse);
        GLES20.glUniform1f(_gradient2_disp2, _disp2Default + MainActivity.gradient2_disp2Value / 100f + _gradient2_pulse);
        GLES20.glUniform1f(_gradient2_u1,_gradient2_dvijenieX_default- _gradient2_dvijenieX);
        GLES20.glUniform1f(_gradient2_u2, _gradient2_dvijenieY_default+_gradient2_dvijenieY);

        GLES20.glUniform1f(_gradient3_disp1, _disp1Default + MainActivity.gradient3_disp1Value / 100f + _gradient3_pulse);
        GLES20.glUniform1f(_gradient3_disp2, _disp2Default + MainActivity.gradient3_disp2Value / 100f + _gradient3_pulse);
        GLES20.glUniform1f(_gradient3_u1, _gradient3_dvijenieX_default-_gradient3_dvijenieX);
        GLES20.glUniform1f(_gradient3_u2, _gradient3_dvijenieY_default-_gradient3_dvijenieY);

        GLES20.glUniform1f(_gradient4_disp1, _disp1Default + MainActivity.gradient4_disp1Value / 100f + _gradient4_pulse);
        GLES20.glUniform1f(_gradient4_disp2, _disp2Default + MainActivity.gradient4_disp2Value / 100f + _gradient4_pulse);
        GLES20.glUniform1f(_gradient4_u1, _gradient4_dvijenieX_default+_gradient4_dvijenieX);
        GLES20.glUniform1f(_gradient4_u2, _gradient4_dvijenieY_default-_gradient4_dvijenieY);

        GLES20.glUniform1f(_gradient2_uFirstColorRed, _gradient2_firstColorRed / 255f);
        GLES20.glUniform1f(_gradient2_uFirstColorGreen, _gradient2_firstColorGreen / 255f);
        GLES20.glUniform1f(_gradient2_uFirstColorBlue, _gradient2_firstColorBlue / 255f);
        GLES20.glUniform1f(_gradient2_uSecondColorRed, _gradient2_secondColorRed / 255f);
        GLES20.glUniform1f(_gradient2_uSecondColorGreen, _gradient2_secondColorGreen / 255f);
        GLES20.glUniform1f(_gradient2_uSecondColorBlue, _gradient2_secondColorBlue / 255f);
        GLES20.glUniform1f(_gradient2_uRazColorRed, _gradient2_razColorRed / 255f);
        GLES20.glUniform1f(_gradient2_uRazColorGreen, _gradient2_razColorGreen / 255f);
        GLES20.glUniform1f(_gradient2_uRazColorBlue, _gradient2_razColorBlue / 255f);


        GLES20.glUniform1f(_gradient3_uFirstColorRed, _gradient3_firstColorRed / 255f);
        GLES20.glUniform1f(_gradient3_uFirstColorGreen, _gradient3_firstColorGreen / 255f);
        GLES20.glUniform1f(_gradient3_uFirstColorBlue, _gradient3_firstColorBlue / 255f);
        GLES20.glUniform1f(_gradient3_uSecondColorRed, _gradient3_secondColorRed / 255f);
        GLES20.glUniform1f(_gradient3_uSecondColorGreen, _gradient3_secondColorGreen / 255f);
        GLES20.glUniform1f(_gradient3_uSecondColorBlue, _gradient3_secondColorBlue / 255f);
        GLES20.glUniform1f(_gradient3_uRazColorRed, _gradient3_razColorRed / 255f);
        GLES20.glUniform1f(_gradient3_uRazColorGreen, _gradient3_razColorGreen / 255f);
        GLES20.glUniform1f(_gradient3_uRazColorBlue, _gradient3_razColorBlue / 255f);
        GLES20.glUniform1f(_gradient3_uPulse, _gradient3_pulse);

        GLES20.glUniform1f(_gradient4_uFirstColorRed, _gradient4_firstColorRed / 255f);
        GLES20.glUniform1f(_gradient4_uFirstColorGreen, _gradient4_firstColorGreen / 255f);
        GLES20.glUniform1f(_gradient4_uFirstColorBlue, _gradient4_firstColorBlue / 255f);
        GLES20.glUniform1f(_gradient4_uSecondColorRed, _gradient4_secondColorRed / 255f);
        GLES20.glUniform1f(_gradient4_uSecondColorGreen, _gradient4_secondColorGreen / 255f);
        GLES20.glUniform1f(_gradient4_uSecondColorBlue, _gradient4_secondColorBlue / 255f);
        GLES20.glUniform1f(_gradient4_uRazColorRed, _gradient4_razColorRed / 255f);
        GLES20.glUniform1f(_gradient4_uRazColorGreen, _gradient4_razColorGreen / 255f);
        GLES20.glUniform1f(_gradient4_uRazColorBlue, _gradient4_razColorBlue / 255f);


        GLES20.glUniform1i(u_kolichestvoGrad, MainActivity.kolichestvoGradienotv);

        GLES20.glUniform1f(_gradient2_randomColor1, _gradient2_randomColorValue1);
        GLES20.glUniform1f(_gradient2_randomColor2, _gradient2_randomColorValue2);
        GLES20.glUniform1f(_gradient2_randomColor3, _gradient2_randomColorValue3);

        GLES20.glUniform1f(_gradient3_randomColor1, _gradient3_randomColorValue1);
        GLES20.glUniform1f(_gradient3_randomColor2, _gradient3_randomColorValue2);
        GLES20.glUniform1f(_gradient3_randomColor3, _gradient3_randomColorValue3);

        GLES20.glUniform1f(_gradient4_randomColor1, _gradient4_randomColorValue1);
        GLES20.glUniform1f(_gradient4_randomColor2, _gradient4_randomColorValue2);
        GLES20.glUniform1f(_gradient4_randomColor3, _gradient4_randomColorValue3);

        GLES20.glUniform1f(_uAlpha, MainActivity.alphaValue / 100);
        GLES20.glUniform1f(_gradient2_uAlpha, MainActivity.gradient2_alphaValue / 100f);
        GLES20.glUniform1f(_gradient3_uAlpha, MainActivity.gradient3_alphaValue / 100f);
        GLES20.glUniform1f(_gradient4_uAlpha, MainActivity.gradient4_alphaValue / 100f);

        GLES20.glUniform1f(_uGradientType, MainActivity.typeofGrad);
        GLES20.glUniform1f(_gradient2_uGradientType, MainActivity.gradient2_typeOfGrad);
        GLES20.glUniform1f(_gradient3_uGradientType, MainActivity.gradient3_typeOfGrad);
        GLES20.glUniform1f(_gradient4_uGradientType, MainActivity.gradient4_typeOfGrad);


        randomTrans();
        grad2_randomTrans();
        grad3_randomTrans();
        grad4_randomTrans();
        grad1_randomColor();
        grad2_randomColor();
        grad3_randomColor();
        grad4_randomColor();
        randomPulse();
        grad2_randomPulse();
        grad3_randomPulse();
        grad4_randomPulse();


    }


    @Override
    public void onDrawFrame(GL10 gl10) {

        int color1 = Color.parseColor(MainActivity.color1Value);
        _firstColorRed = (color1 >> 16) & 0xff;
        _firstColorGreen = (color1 >> 8) & 0xff;
        _firstColorBlue = (color1) & 0xff;
        int color2 = Color.parseColor(MainActivity.color2Value);
        _secondColorRed = (color2 >> 16) & 0xff;
        _secondColorGreen = (color2 >> 8) & 0xff;
        _secondColorBlue = (color2) & 0xff;

        _razColorRed = _firstColorRed - _secondColorRed;
        _razColorGreen = _firstColorGreen - _secondColorGreen;
        _razColorBlue = _firstColorBlue - _secondColorBlue;

        int _gradient2_color1 = Color.parseColor(MainActivity.gradient2_color1Value);
        _gradient2_firstColorRed = (_gradient2_color1 >> 16) & 0xff;
        _gradient2_firstColorGreen = (_gradient2_color1 >> 8) & 0xff;
        _gradient2_firstColorBlue = (_gradient2_color1) & 0xff;
        int _gradient2_color2 = Color.parseColor(MainActivity.gradient2_color2Value);
        _gradient2_secondColorRed = (_gradient2_color2 >> 16) & 0xff;
        _gradient2_secondColorGreen = (_gradient2_color2 >> 8) & 0xff;
        _gradient2_secondColorBlue = (_gradient2_color2) & 0xff;

        _gradient2_razColorRed = _gradient2_firstColorRed - _gradient2_secondColorRed;
        _gradient2_razColorGreen = _gradient2_firstColorGreen - _gradient2_secondColorGreen;
        _gradient2_razColorBlue = _gradient2_firstColorBlue - _gradient2_secondColorBlue;

        int _gradient3_color1 = Color.parseColor(MainActivity.gradient3_color1Value);
        _gradient3_firstColorRed = (_gradient3_color1 >> 16) & 0xff;
        _gradient3_firstColorGreen = (_gradient3_color1 >> 8) & 0xff;
        _gradient3_firstColorBlue = (_gradient3_color1) & 0xff;
        int _gradient3_color2 = Color.parseColor(MainActivity.gradient3_color2Value);
        _gradient3_secondColorRed = (_gradient3_color2 >> 16) & 0xff;
        _gradient3_secondColorGreen = (_gradient3_color2 >> 8) & 0xff;
        _gradient3_secondColorBlue = (_gradient3_color2) & 0xff;

        _gradient3_razColorRed = _gradient3_firstColorRed - _gradient3_secondColorRed;
        _gradient3_razColorGreen = _gradient3_firstColorGreen - _gradient3_secondColorGreen;
        _gradient3_razColorBlue = _gradient3_firstColorBlue - _gradient3_secondColorBlue;

        int _gradient4_color1 = Color.parseColor(MainActivity.gradient4_color1Value);
        _gradient4_firstColorRed = (_gradient4_color1 >> 16) & 0xff;
        _gradient4_firstColorGreen = (_gradient4_color1 >> 8) & 0xff;
        _gradient4_firstColorBlue = (_gradient4_color1) & 0xff;
        int _gradient4_color2 = Color.parseColor(MainActivity.gradient4_color2Value);
        _gradient4_secondColorRed = (_gradient4_color2 >> 16) & 0xff;
        _gradient4_secondColorGreen = (_gradient4_color2 >> 8) & 0xff;
        _gradient4_secondColorBlue = (_gradient4_color2) & 0xff;

        _gradient4_razColorRed = _gradient4_firstColorRed - _gradient4_secondColorRed;
        _gradient4_razColorGreen = _gradient4_firstColorGreen - _gradient4_secondColorGreen;
        _gradient4_razColorBlue = _gradient4_firstColorBlue - _gradient4_secondColorBlue;


        GLES20.glClearColor(0, 0, 0, 1);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glUseProgram(_programId);
        GLES20.glViewport(0, 0, _glViewWidth, _glViewHeight);
        GLES20.glUseProgram(_programId);
        shaderParams();
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, 6, GLES20.GL_UNSIGNED_SHORT, _indices);


    }

    private float[] arrayFromRectF(RectF rect) {
        float x1 = rect.left;
        float x2 = rect.right;
        float y1 = rect.bottom;
        float y2 = rect.top;


        PointF one = new PointF(x1, y2);
        PointF two = new PointF(x1, y1);
        PointF three = new PointF(x2, y1);
        PointF four = new PointF(x2, y2);

        return new float[]
                {
                        one.x, one.y, 0,
                        two.x, two.y, 0,
                        three.x, three.y, 0,
                        four.x, four.y, 0,
                };
    }


    public void randomTrans() {
        if (MainActivity.typeMove == 2) {
            _tickTimeX++;

            if (tuda) {
                dvijenieX += MainActivity.speedMoveValue / 5000;
            }
            if (!tuda) {
                dvijenieX -= MainActivity.speedMoveValue / 5000;
            }
            if (vverx) {
                dvijenieY += MainActivity.speedMoveValue / 5000;
            }
            if (!vverx) {
                dvijenieY -= MainActivity.speedMoveValue / 5000;
            }
            if (dvijenieX <= 0.0) {
                dvijenieX = 0f;
            }
            if (dvijenieX >= 1.0f) {
                dvijenieX = 1.0f;
            }

            if (dvijenieY <= 0.0) {
                dvijenieY = 0f;
            }
            if (dvijenieY >= 1.0f) {
                dvijenieY = 1.0f;
            }


            if (_tickTimeX == 30.0) {
                _tickTimeX = 0.0f;
                double random = Math.random();
                if (random <= 0.25) {
                    tuda = false;
                }
                if (random <= 0.5 && random > 0.25) {
                    vverx = false;
                }
                if (random <= 0.75 && random > 0.5) {
                    tuda = true;
                }
                if (random <= 1.0 && random > 0.75) {
                    vverx = true;
                }
            }
        }


        if (MainActivity.typeMove == 1) {

//
            if (tuda) {
                dvijenieX += MainActivity.speedMoveValue / 5000;
                if (dvijenieX >= 1.0) {
                    dvijenieX = 1.0f;
                }
            }
            if (!tuda) {
                dvijenieX -= MainActivity.speedMoveValue / 5000;
                if (dvijenieX <= 0) {
                    dvijenieX = 0.0f;
                }
            }
            if (dvijenieY <= 0) {
                tuda = true;
            }
            if (dvijenieY >= 1.0) {
                tuda = false;

            }
            if (vverx) {
                dvijenieY += MainActivity.speedMoveValue / 5000;
                if (dvijenieY >= 1.0) {
                    dvijenieY = 1.0f;
                }
            }
            if (!vverx) {
                dvijenieY -= MainActivity.speedMoveValue / 5000;
                if (dvijenieY <= 0) {
                    dvijenieY = 0.0f;
                }
            }

            if (dvijenieX >= 1.0) {
                vverx = true;
            }
            if (dvijenieX <= 0.0) {
                vverx = false;
            }
        }
        if (MainActivity.typeMove==3 || MainActivity.typeMove==4) {
//            _tickTimeX++;
//
//            dvijenieX = xCenter + rCenter*(float)Math.cos(Math.toRadians(angle));
//            dvijenieY = yCenter + rCenter*(float)Math.sin(Math.toRadians(angle));
//            if(tuda){
//                angle+=1f;
//            }
//            if(!tuda){
//                angle-=1f;
//            }
//            if(angle%360==0){
//                kolichestvoKrugov++;
//            }
//
//
//
//            if(_tickTimeX==90){
//
//                _tickTimeY = Math.abs((kolichestvoKrugov*360)-angle);
//
//                angle=0;
//
//                xCenter=(float)(dvijenieX+rCenter);
//                yCenter=(float)(dvijenieY+rCenter);
//
//                if(_tickTimeY>180){
//                    angle=Math.abs(360+_tickTimeY);
//                }
//                if(_tickTimeY<180){
//                    angle=Math.abs(_tickTimeY+180-);
//                }
//
//                _tickTimeX=0;
//
//
//            }
//            Log.d("TAG","angle = " + angle);
//            Log.d("TAG","ticktamY = " + _tickTimeY);




            rCenter=MainActivity.rCenterValue/100;
            

            dvijenieX = dvijenieX + (float) Math.random()/10* (float) Math.sin(_tickTimeX)* rCenter * lofl1;
            dvijenieY = dvijenieY + (float) Math.random()/10* (float) Math.cos(_tickTimeX)* rCenter * lofl2;

            //dvijenieX = dvijenieX + (float) (1.0 - Math.sin(( rCenter))) * lofl1 * rCenter * 0.01f;
            //dvijenieY = dvijenieY + (float) (1.0 - Math.cos(( rCenter))) * lofl2 * rCenter * 0.01f;
            _tickTimeX += 0.03f;


            if (Math.sin(_tickTimeX) < 0.05) {
                if (lol) {
                    lofl1 = lofl1 * -1.f;
                    lol = false;
                }
            } else {
                lol = true;
            }
            if (Math.cos(_tickTimeX) < 0.05) {
                if (lol2) {
                    lofl2 = lofl2 * -1.f;
                    lol2 = false;
                }
            } else {
                lol2 = true;
            }

            if (_tickTimeX > _tickTimeXtemp) {
                _tickTimeXtemp += 0.5*rCenter*PI;
            }

        }

    }

    public float lofl1 = 1.0f;
    public float lofl2 = 1.0f;
    public float lofl3 = 1.0f;
    public float lofl4 = 1.0f;
    public boolean lol = true;
    public boolean lol2 = true;

    public void grad2_randomTrans() {
        if (MainActivity.gradient2_typeMove == 2) {
            _gradient2_tickTimeX++;

            if (_gradient2_tuda) {
                _gradient2_dvijenieX += MainActivity.gradient2_speedMoveValue / 5000;
            }
            if (!_gradient2_tuda) {
                _gradient2_dvijenieX -= MainActivity.gradient2_speedMoveValue / 5000;
            }
            if (_gradient2_vverx) {
                _gradient2_dvijenieY += MainActivity.gradient2_speedMoveValue / 5000;
            }
            if (!_gradient2_vverx) {
                _gradient2_dvijenieY -= MainActivity.gradient2_speedMoveValue / 5000;
            }
            if (_gradient2_dvijenieX <= 0.0) {
                _gradient2_dvijenieX = 0f;
            }
            if (_gradient2_dvijenieX >= 1.0f) {
                _gradient2_dvijenieX = 1.0f;
            }

            if (_gradient2_dvijenieY <= 0.0) {
                _gradient2_dvijenieY = 0f;
            }
            if (_gradient2_dvijenieY >= 1.0f) {
                _gradient2_dvijenieY = 1.0f;
            }


            if (_gradient2_tickTimeX == 30.0) {
                _gradient2_tickTimeX = 0.0f;
                double random = Math.random();
                if (random <= 0.25) {
                    _gradient2_tuda = false;
                }
                if (random <= 0.5 && random > 0.25) {
                    _gradient2_vverx = false;
                }
                if (random <= 0.75 && random > 0.5) {
                    _gradient2_tuda = true;
                }
                if (random <= 1.0 && random > 0.75) {
                    _gradient2_vverx = true;
                }
            }
        }
        if (MainActivity.gradient2_typeMove == 1) {

            if (_gradient2_tuda) {
                _gradient2_dvijenieX += MainActivity.gradient2_speedMoveValue / 5000;
                if (_gradient2_dvijenieX >= 1.0) {
                    _gradient2_dvijenieX = 1.0f;
                }
            }
            if (!_gradient2_tuda) {
                _gradient2_dvijenieX -= MainActivity.gradient2_speedMoveValue / 5000;
                if (_gradient2_dvijenieX <= 0) {
                    _gradient2_dvijenieX = 0.0f;
                }
            }
            if (_gradient2_dvijenieY <= 0) {
                _gradient2_tuda = true;
            }
            if (_gradient2_dvijenieY >= 1.0) {
                _gradient2_tuda = false;

            }
            if (_gradient2_vverx) {
                _gradient2_dvijenieY += MainActivity.gradient2_speedMoveValue / 5000;
                if (_gradient2_dvijenieY >= 1.0) {
                    _gradient2_dvijenieY = 1.0f;
                }
            }
            if (!_gradient2_vverx) {
                _gradient2_dvijenieY -= MainActivity.gradient2_speedMoveValue / 5000;
                if (_gradient2_dvijenieY <= 0) {
                    _gradient2_dvijenieY = 0.0f;
                }
            }

            if (_gradient2_dvijenieX >= 1.0) {
                _gradient2_vverx = true;
            }
            if (_gradient2_dvijenieX <= 0.0) {
                _gradient2_vverx = false;
            }
        }
        if (MainActivity.gradient2_typeMove==3 || MainActivity.gradient2_typeMove==4) {
            float xCenter = MainActivity.gradient2_xCenterValue / 100;
            float yCenter = MainActivity.gradient2_yCenterValue / 100;
            float rCenter = MainActivity.gradient2_rCenterValue / 100;
            _gradient2_dvijenieX = (float) (xCenter + rCenter * Math.cos(_gradient2_tickTimeY));
            _gradient2_dvijenieY = (float) (yCenter + rCenter * Math.sin(_gradient2_tickTimeY));
            if(MainActivity.gradient2_typeMove==3 ) {
                _gradient2_tickTimeY += MainActivity.gradient2_speedMoveValue / 1000;
            }
            if(MainActivity.gradient2_typeMove==4 ){
                _gradient2_tickTimeY -= MainActivity.gradient2_speedMoveValue / 1000;
            }
        }

    }

    public void grad3_randomTrans() {
        if (MainActivity.gradient3_typeMove == 1) {
            if (_gradient3_tuda) {
                _gradient3_dvijenieX += MainActivity.gradient3_speedMoveValue / 5000;
                if (_gradient3_dvijenieX >= 1.0) {
                    _gradient3_dvijenieX = 1.0f;
                }
            }
            if (!_gradient3_tuda) {
                _gradient3_dvijenieX -= MainActivity.gradient3_speedMoveValue / 5000;
                if (_gradient3_dvijenieX <= 0) {
                    _gradient3_dvijenieX = 0.0f;
                }
            }
            if (_gradient3_dvijenieY <= 0) {
                _gradient3_tuda = true;
            }
            if (_gradient3_dvijenieY >= 1.0) {
                _gradient3_tuda = false;

            }
            if (_gradient3_vverx) {
                _gradient3_dvijenieY += MainActivity.gradient3_speedMoveValue / 5000;
                if (_gradient3_dvijenieY >= 1.0) {
                    _gradient3_dvijenieY = 1.0f;
                }
            }
            if (!_gradient3_vverx) {
                _gradient3_dvijenieY -= MainActivity.gradient3_speedMoveValue / 5000;
                if (_gradient3_dvijenieY <= 0) {
                    _gradient3_dvijenieY = 0.0f;
                }
            }

            if (_gradient3_dvijenieX >= 1.0) {
                _gradient3_vverx = true;
            }
            if (_gradient3_dvijenieX <= 0.0) {
                _gradient3_vverx = false;
            }
        }
        if (MainActivity.gradient3_typeMove == 2) {
            _gradient3_tickTimeX++;

            if (_gradient3_tuda) {
                _gradient3_dvijenieX += MainActivity.gradient3_speedMoveValue / 5000;
            }
            if (!_gradient3_tuda) {
                _gradient3_dvijenieX -= MainActivity.gradient3_speedMoveValue / 5000;
            }
            if (_gradient3_vverx) {
                _gradient3_dvijenieY += MainActivity.gradient3_speedMoveValue / 5000;
            }
            if (!_gradient3_vverx) {
                _gradient3_dvijenieY -= MainActivity.gradient3_speedMoveValue / 5000;
            }
            if (_gradient3_dvijenieX <= 0.0) {
                _gradient3_dvijenieX = 0f;
            }
            if (_gradient3_dvijenieX >= 1.0f) {
                _gradient3_dvijenieX = 1.0f;
            }

            if (_gradient3_dvijenieY <= 0.0) {
                _gradient3_dvijenieY = 0f;
            }
            if (_gradient3_dvijenieY >= 1.0f) {
                _gradient3_dvijenieY = 1.0f;
            }


            if (_gradient3_tickTimeX == 30.0) {
                _gradient3_tickTimeX = 0.0f;
                double random = Math.random();
                if (random <= 0.25) {
                    _gradient3_tuda = false;
                }
                if (random <= 0.5 && random > 0.25) {
                    _gradient3_vverx = false;
                }
                if (random <= 0.75 && random > 0.5) {
                    _gradient3_tuda = true;
                }
                if (random <= 1.0 && random > 0.75) {
                    _gradient3_vverx = true;
                }
            }
        }
        if (MainActivity.gradient3_typeMove==3 || MainActivity.gradient3_typeMove==4) {
            float xCenter = MainActivity.gradient3_xCenterValue / 100;
            float yCenter = MainActivity.gradient3_yCenterValue / 100;
            float rCenter = MainActivity.gradient3_rCenterValue / 100;
            _gradient3_dvijenieX = (float) (xCenter + rCenter * Math.cos(_gradient3_tickTimeY));
            _gradient3_dvijenieY = (float) (yCenter + rCenter * Math.sin(_gradient3_tickTimeY));
            if(MainActivity.gradient3_typeMove==3 ) {
                _gradient3_tickTimeY += MainActivity.gradient3_speedMoveValue / 1000;
            }
            if(MainActivity.gradient3_typeMove==4 ){
                _gradient3_tickTimeY -= MainActivity.gradient3_speedMoveValue / 1000;
            }
        }

    }

    public void grad4_randomTrans() {
        if (MainActivity.gradient4_typeMove == 1) {
            if (_gradient4_tuda) {
                _gradient4_dvijenieX += MainActivity.gradient4_speedMoveValue / 5000;
                if (_gradient4_dvijenieX >= 1.0) {
                    _gradient4_dvijenieX = 1.0f;
                }
            }
            if (!_gradient4_tuda) {
                _gradient4_dvijenieX -= MainActivity.gradient4_speedMoveValue / 5000;
                if (_gradient4_dvijenieX <= 0) {
                    _gradient4_dvijenieX = 0.0f;
                }
            }
            if (_gradient4_dvijenieY <= 0) {
                _gradient4_tuda = true;
            }
            if (_gradient4_dvijenieY >= 1.0) {
                _gradient4_tuda = false;

            }
            if (_gradient4_vverx) {
                _gradient4_dvijenieY += MainActivity.gradient4_speedMoveValue / 5000;
                if (_gradient4_dvijenieY >= 1.0) {
                    _gradient4_dvijenieY = 1.0f;
                }
            }
            if (!_gradient4_vverx) {
                _gradient4_dvijenieY -= MainActivity.gradient4_speedMoveValue / 5000;
                if (_gradient4_dvijenieY <= 0) {
                    _gradient4_dvijenieY = 0.0f;
                }
            }

            if (_gradient4_dvijenieX >= 1.0) {
                _gradient4_vverx = true;
            }
            if (_gradient4_dvijenieX <= 0.0) {
                _gradient4_vverx = false;
            }
        }

        if (MainActivity.gradient4_typeMove == 2) {
            _gradient4_tickTimeX++;

            if (_gradient4_tuda) {
                _gradient4_dvijenieX += MainActivity.gradient4_speedMoveValue / 5000;
            }
            if (!_gradient4_tuda) {
                _gradient4_dvijenieX -= MainActivity.gradient4_speedMoveValue / 5000;
            }
            if (_gradient4_vverx) {
                _gradient4_dvijenieY += MainActivity.gradient4_speedMoveValue / 5000;
            }
            if (!_gradient4_vverx) {
                _gradient4_dvijenieY -= MainActivity.gradient4_speedMoveValue / 5000;
            }
            if (_gradient4_dvijenieX <= 0.0) {
                _gradient4_dvijenieX = 0f;
            }
            if (_gradient4_dvijenieX >= 1.0f) {
                _gradient4_dvijenieX = 1.0f;
            }

            if (_gradient4_dvijenieY <= 0.0) {
                _gradient4_dvijenieY = 0f;
            }
            if (_gradient4_dvijenieY >= 1.0f) {
                _gradient4_dvijenieY = 1.0f;
            }


            if (_gradient4_tickTimeX == 30.0) {
                _gradient4_tickTimeX = 0.0f;
                double random = Math.random();
                if (random <= 0.25) {
                    _gradient4_tuda = false;
                }
                if (random <= 0.5 && random > 0.25) {
                    _gradient4_vverx = false;
                }
                if (random <= 0.75 && random > 0.5) {
                    _gradient4_tuda = true;
                }
                if (random <= 1.0 && random > 0.75) {
                    _gradient4_vverx = true;
                }
            }
        }
        if (MainActivity.gradient4_typeMove==3 || MainActivity.gradient4_typeMove==4 ) {
            float xCenter = MainActivity.gradient4_xCenterValue / 100;
            float yCenter = MainActivity.gradient4_yCenterValue / 100;
            float rCenter = MainActivity.gradient4_rCenterValue / 100;
            _gradient4_dvijenieX = (float) (xCenter + rCenter * Math.cos(_gradient4_tickTimeY));
            _gradient4_dvijenieY = (float) (yCenter + rCenter * Math.sin(_gradient4_tickTimeY));
            if(MainActivity.gradient4_typeMove==3 ) {
                _gradient4_tickTimeY += MainActivity.gradient4_speedMoveValue / 1000;
            }
            if(MainActivity.gradient4_typeMove==4 ){
                _gradient4_tickTimeY -= MainActivity.gradient4_speedMoveValue / 1000;
            }
        }

    }

    public void grad1_randomColor() {

        if (changeColor1) {
            randomColorValue1 += (0.005f * MainActivity.speedColorValue / 100);
        }
        if (!changeColor1) {
            randomColorValue1 -= (0.005f * MainActivity.speedColorValue / 100);
        }
        if (changeColor2) {
            randomColorValue2 += 0.0025f * MainActivity.speedColorValue / 100;
        }
        if (!changeColor2) {
            randomColorValue2 -= 0.0025f * MainActivity.speedColorValue / 100;
        }
        if (changeColor3) {
            randomColorValue3 += 0.0075f * MainActivity.speedColorValue / 100;
        }
        if (!changeColor3) {
            randomColorValue3 -= 0.0075f * MainActivity.speedColorValue / 100;
        }

        if (randomColorValue1 >= 1.0f) {
            changeColor1 = false;
        }
        if (randomColorValue1 <= -1.0f) {
            changeColor1 = true;
        }
        if (randomColorValue2 >= 1.0f) {
            changeColor2 = false;
        }
        if (randomColorValue2 <= -1.0f) {
            changeColor2 = true;
        }
        if (randomColorValue3 >= 1.0) {
            changeColor3 = false;
        }
        if (randomColorValue3 <= -1.0) {
            changeColor3 = true;
        }


//


    }

    public void grad2_randomColor() {

        if (_gradient2_changeColor1) {
            _gradient2_randomColorValue1 += (0.0025f * MainActivity.gradient2_speedColorValue / 100);
        }
        if (!_gradient2_changeColor1) {
            _gradient3_randomColorValue1 -= (0.0025f * MainActivity.gradient2_speedColorValue / 100);
        }
        if (_gradient2_changeColor2) {
            _gradient2_randomColorValue2 += 0.0025f * MainActivity.gradient2_speedColorValue / 100;
        }
        if (!_gradient2_changeColor2) {
            _gradient2_randomColorValue2 -= 0.0025f * MainActivity.gradient2_speedColorValue / 100;
        }
        if (_gradient2_changeColor3) {
            _gradient2_randomColorValue3 += 0.001f * MainActivity.gradient2_speedColorValue / 100;
        }
        if (!_gradient3_changeColor3) {
            _gradient2_randomColorValue3 -= 0.001f * MainActivity.gradient2_speedColorValue / 100;
        }

        if (_gradient2_randomColorValue1 >= 1.0f) {
            _gradient3_changeColor1 = false;
        }
        if (_gradient2_randomColorValue1 <= -1.0f) {
            _gradient3_changeColor1 = true;
        }
        if (_gradient2_randomColorValue2 >= 1.0f) {
            _gradient2_changeColor2 = false;
        }
        if (_gradient2_randomColorValue2 <= -1.0f) {
            _gradient2_changeColor2 = true;
        }
        if (_gradient2_randomColorValue3 >= 1.0) {
            _gradient2_changeColor3 = false;
        }
        if (_gradient2_randomColorValue3 <= -1.0) {
            _gradient2_changeColor3 = true;
        }


//


    }

    public void grad3_randomColor() {

        if (_gradient3_changeColor1) {
            _gradient3_randomColorValue1 += (0.001f * MainActivity.gradient3_speedColorValue / 100);
        }
        if (!_gradient3_changeColor1) {
            _gradient3_randomColorValue1 -= (0.001f * MainActivity.gradient3_speedColorValue / 100);
        }
        if (_gradient3_changeColor2) {
            _gradient3_randomColorValue2 += 0.005f * MainActivity.gradient3_speedColorValue / 100;
        }
        if (!_gradient3_changeColor2) {
            _gradient3_randomColorValue2 -= 0.005f * MainActivity.gradient3_speedColorValue / 100;
        }
        if (_gradient3_changeColor3) {
            _gradient3_randomColorValue3 += 0.0075f * MainActivity.gradient3_speedColorValue / 100;
        }
        if (!_gradient3_changeColor3) {
            _gradient3_randomColorValue3 -= 0.0075f * MainActivity.gradient3_speedColorValue / 100;
        }

        if (_gradient3_randomColorValue1 >= 1.0f) {
            _gradient3_changeColor1 = false;
        }
        if (_gradient3_randomColorValue1 <= -1.0f) {
            _gradient3_changeColor1 = true;
        }
        if (_gradient3_randomColorValue2 >= 1.0f) {
            _gradient3_changeColor2 = false;
        }
        if (_gradient3_randomColorValue2 <= -1.0f) {
            _gradient3_changeColor2 = true;
        }
        if (_gradient3_randomColorValue3 >= 1.0) {
            _gradient3_changeColor3 = false;
        }
        if (_gradient3_randomColorValue3 <= -1.0) {
            _gradient3_changeColor3 = true;
        }


//


    }

    public void grad4_randomColor() {

        if (_gradient4_changeColor1) {
            _gradient4_randomColorValue1 += (0.0025f * MainActivity.gradient4_speedColorValue / 100);
        }
        if (!_gradient4_changeColor1) {
            _gradient4_randomColorValue1 -= (0.0025f * MainActivity.gradient4_speedColorValue / 100);
        }
        if (_gradient4_changeColor2) {
            _gradient4_randomColorValue2 += 0.005f * MainActivity.gradient4_speedColorValue / 100;
        }
        if (!_gradient4_changeColor2) {
            _gradient4_randomColorValue2 -= 0.005f * MainActivity.gradient4_speedColorValue / 100;
        }
        if (_gradient4_changeColor3) {
            _gradient4_randomColorValue3 += 0.005f * MainActivity.gradient4_speedColorValue / 100;
        }
        if (!_gradient4_changeColor3) {
            _gradient4_randomColorValue3 -= 0.005f * MainActivity.gradient4_speedColorValue / 100;
        }

        if (_gradient4_randomColorValue1 >= 1.0f) {
            _gradient4_changeColor1 = false;
        }
        if (_gradient4_randomColorValue1 <= -1.0f) {
            _gradient4_changeColor1 = true;
        }
        if (_gradient4_randomColorValue2 >= 1.0f) {
            _gradient4_changeColor2 = false;
        }
        if (_gradient4_randomColorValue2 <= -1.0f) {
            _gradient4_changeColor2 = true;
        }
        if (_gradient4_randomColorValue3 >= 1.0) {
            _gradient4_changeColor3 = false;
        }
        if (_gradient4_randomColorValue3 <= -1.0) {
            _gradient4_changeColor3 = true;
        }


//


    }

    public void randomPulse() {
        if (pulseGo) {
            _pulse += MainActivity.speedPulsValue / 5000;
        }
        if (!pulseGo) {
            _pulse -= MainActivity.speedPulsValue / 5000;
        }
        if (_pulse >= 0.5) {
            pulseGo = false;
        }
        if (_pulse <= 0.0f) {
            pulseGo = true;
        }
    }

    public void grad2_randomPulse() {
        if (_gradient2_pulseGo) {
            _gradient2_pulse += MainActivity.gradient2_speedPulsValue / 5000;
        }
        if (!_gradient2_pulseGo) {
            _gradient2_pulse -= MainActivity.gradient2_speedPulsValue / 5000;
        }
        if (_gradient2_pulse >= 0.5) {
            _gradient2_pulseGo = false;
        }
        if (_gradient2_pulse <= 0.0f) {
            _gradient2_pulseGo = true;
        }
    }

    public void grad3_randomPulse() {
        if (_gradient3_pulseGo) {
            _gradient3_pulse += MainActivity.gradient3_speedPulsValue / 5000;
        }
        if (!_gradient3_pulseGo) {
            _gradient3_pulse -= MainActivity.gradient3_speedPulsValue / 5000;
        }
        if (_gradient3_pulse >= 0.5) {
            _gradient3_pulseGo = false;
        }
        if (_gradient3_pulse <= 0.0f) {
            _gradient3_pulseGo = true;
        }
    }

    public void grad4_randomPulse() {
        if (_gradient4_pulseGo) {
            _gradient4_pulse += MainActivity.gradient4_speedPulsValue / 5000;
        }
        if (!_gradient4_pulseGo) {
            _gradient4_pulse -= MainActivity.gradient4_speedPulsValue / 5000;
        }
        if (_gradient4_pulse >= 0.5) {
            _gradient4_pulseGo = false;
        }
        if (_gradient4_pulse <= 0.0f) {
            _gradient4_pulseGo = true;
        }
    }
//    public void formula(){
//        float  x = 1.0f/image.getWidth();
//        float y = 1.0f/image.getHeight();
//        float u_Disp1=1.0f;
//        float u_Disp2=1.0f;
//        float u_u1=1.0f/image.getWidth();
//        float u_u2=1.0f/image.getHeight();
//        float u_Disp12= 1.0f;
//        float p = 0.5f;
//        float x1 = (float)((pow((x-u_u1),2.0))/pow(u_Disp1,2.0));
//        float y1 = (float)((pow((y-u_u2),2.0))/pow(u_Disp2,2.0));
//        float xy = (float)(p * ((2.0*(x-u_u1)*(y-u_u2))/(u_Disp1*u_Disp2)));
//        float stepenE = (float)(-1.0*(1.0/(2.0*(1.0-pow(p,2.0)))))*(x1-xy+y1);
//        float e = 2.71828f;
//        float result = (float)((1.0 /(2.0 * 3.14 * u_Disp1 * u_Disp2 *sqrt((1.0-pow(p,2.0))))) * pow(e,stepenE));
//        float result2 = (float)(result/(1.0/(2.0*3.14*u_Disp1*u_Disp2*sqrt(1.0-pow(p,2.0)))));
//        Log.d("TAG","result = " + result );
//        Log.d("TAG","resul2 = " + result2 );
//
//    }
}
