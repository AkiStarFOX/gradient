package com.akistar.normalgradient;


        import android.opengl.GLES20;
        import android.opengl.GLES30;

/**
 * Created by NERV on 06.03.2018.
 */

/**
 * Формат пикселя для текстуры
 */
public enum PixelFormat {
    RED(GLES30.GL_R8, GLES30.GL_RED), // 1 компонента
    RG(GLES30.GL_RG8, GLES30.GL_RG), // 2 компоненты
    RGB(GLES30.GL_RGB, GLES30.GL_RGB), // 3 компоненты
    RGBA(GLES30.GL_RGBA, GLES30.GL_RGBA); // 4 компоненты

    public int iternalFormat; // glTexImage2D internalFormat
    public int format; // glTexImage2D format

    PixelFormat(int iternalFormat, int format) {
        this.iternalFormat = iternalFormat;
        this.format = format;
    }
}