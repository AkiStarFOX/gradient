package com.akistar.normalgradient;

import android.opengl.GLES20;
import android.opengl.GLES30;



/**
 * Created by NERV on 19.12.2017.
 */

/**
 * Frame Buffer Object
 */
public class FBO {
    private int _fboId = -1; // идентификатор фрейм буфера
    private int _fboWidth = 1; // ширина изображения (тектуры или рендербуфера) фреймбуфера
    private int _fboHeight = 1; // высота

    private boolean _msaa = false; // использовать ли MSAA
    private int _msFBOId; // идентификатор MSAA фреймбуфера
    private int _msRBId; // идентификатора рендербуфера MSAA фреймбуфера
    private PixelFormat _pixelFormat; // формат пикселя
    private int[] _textures; // текстуры, в которые происходит рендер
    private int[] _attachments; // массив GL_COLOR_ATTACHMENT, к которым прикрепляются текстуры из _textures

    public FBO() {}

    /**
     * Возвращает текстуру FBO
     * @return - текстура GL_COLOR_ATTACHMENT0
     */
    public int getTexture() {
        return _textures[0];
    }

    /**
     * Возвращает текстуру FBO
     * @param number - номер текстуры
     * @return - текстура GL_COLOR_ATTACHMENT[number]
     */
    public int getTexture(int number) {
        return _textures[number];
    }

    public boolean isAntialiased() {
        return _msaa;
    }

    /**
     * Возвращает формат пикселя текстур FBO
     * @return - формат пикселя
     */
    public PixelFormat getPixelFormat() {
        return _pixelFormat;
    }

    /**
     * Кол-во текстур FBO
     * @return
     */
    public int getTexturesCount() {
        return _textures.length;
    }

    /**
     * Инициализировать FBO
     * @param w - ширина
     * @param h - высота
     * @param pixelFormat - формат пикселя
     * @param numberOfTextures - кол-во текстур
     */
    public void init(int w, int h, PixelFormat pixelFormat, int numberOfTextures) {
        if (numberOfTextures == 1) {
            init(w, h, pixelFormat, false);
            return;
        }

        _fboWidth = w;
        _fboHeight = h;
        _pixelFormat = pixelFormat;

        int[] oldBufs = new int[2];
        GLES20.glGetIntegerv(GLES20.GL_FRAMEBUFFER_BINDING, oldBufs, 0);
        GLES20.glGetIntegerv(GLES20.GL_RENDERBUFFER_BINDING, oldBufs, 1);

        int[] temp = new int[1];

        GLES20.glGenFramebuffers(1, temp, 0);
        _fboId = temp[0];

        _textures = new int[numberOfTextures];
        _attachments = new int[numberOfTextures];

        GLES20.glGenTextures(numberOfTextures, _textures, 0);

        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, _fboId);

        for (int i = 0; i < numberOfTextures; i++) {
            addColorAttachment(_textures[i], i);
        }

        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
        GLES20.glBindRenderbuffer(GLES20.GL_RENDERBUFFER, oldBufs[1]);
        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, oldBufs[0]);
    }

    /**
     * Привязать текстуру к GL_COLOR_ATTACHMENT[attachmentNumber]
     * @param texture
     * @param attachmentNumber
     */
    private void addColorAttachment(int texture, int attachmentNumber) {
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture);

        GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, _pixelFormat.iternalFormat, _fboWidth
                , _fboHeight, 0, _pixelFormat.format, GLES20.GL_UNSIGNED_BYTE, null);

        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);

        int colorAttachment = GLES30.GL_COLOR_ATTACHMENT0;

        switch (attachmentNumber) {
            case 0:
                colorAttachment = GLES30.GL_COLOR_ATTACHMENT0;
                break;
            case 1:
                colorAttachment = GLES30.GL_COLOR_ATTACHMENT1;
                break;
            case 2:
                colorAttachment = GLES30.GL_COLOR_ATTACHMENT2;
                break;
            case 3:
                colorAttachment = GLES30.GL_COLOR_ATTACHMENT3;
                break;
            case 4:
                colorAttachment = GLES30.GL_COLOR_ATTACHMENT4;
                break;
            case 5:
                colorAttachment = GLES30.GL_COLOR_ATTACHMENT5;
                break;
        }

        _attachments[attachmentNumber] = colorAttachment;
        GLES20.glFramebufferTexture2D(GLES20.GL_FRAMEBUFFER, colorAttachment, GLES20.GL_TEXTURE_2D, texture, 0);
    }

    /**
     * Инициализировать FBO
     * @param w - ширина
     * @param h - высота
     * @param pixelFormat - формат пикселя
     * @param msaa - использовать мультисемплинг
     */
    public void init(int w, int h, PixelFormat pixelFormat, boolean msaa) {
        _fboWidth = w;
        _fboHeight = h;
        _pixelFormat = pixelFormat;
        _msaa = msaa;

        int[] oldBufs = new int[2];
        GLES20.glGetIntegerv(GLES20.GL_FRAMEBUFFER_BINDING, oldBufs, 0);
        GLES20.glGetIntegerv(GLES20.GL_RENDERBUFFER_BINDING, oldBufs, 1);

        if (_fboId == -1) {
            int[] temp = new int[1];

            GLES20.glGenFramebuffers(1, temp, 0);
            _fboId = temp[0];

            _textures = new int[1];
            _attachments = new int[1];
            GLES20.glGenTextures(1, _textures, 0);
        }

        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, _fboId);

        addColorAttachment(_textures[0], 0);

        if (_msaa) {
            initMultiFBO();
        }

        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
        GLES20.glBindRenderbuffer(GLES20.GL_RENDERBUFFER, oldBufs[1]);
        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, oldBufs[0]);
    }

    /**
     * Инициализировать FBO
     * @param w - ширина
     * @param h - высота
     * @param msaa - использовать мультисемплинг
     */
    public void init(int w, int h, boolean msaa) {
        init(w, h, PixelFormat.RGBA, msaa);
    }

    public void drawColor(float r, float g, float b, float a) {
        if (!(_fboWidth > 0 && _fboHeight > 0)) {
            return;
        }

        int[] oldFBO = new int[1];
        GLES20.glGetIntegerv(GLES20.GL_FRAMEBUFFER_BINDING, oldFBO, 0);

        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, _fboId);
        GLES20.glViewport(0, 0, _fboWidth, _fboHeight);

        GLES20.glClearColor(r, g, b, a);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, oldFBO[0]);
    }

    private void initMultiFBO() {
        int[] temp = new int[1];

        GLES20.glGenRenderbuffers(1, temp, 0);
        _msRBId = temp[0];

        GLES20.glGenFramebuffers(1, temp, 0);
        _msFBOId = temp[0];

        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, _msFBOId);

        GLES20.glBindRenderbuffer(GLES20.GL_RENDERBUFFER, _msRBId);
        GLES30.glRenderbufferStorageMultisample(GLES20.GL_RENDERBUFFER, 4, GLES30.GL_RGBA8, _fboWidth, _fboHeight);
        GLES20.glFramebufferRenderbuffer(GLES20.GL_FRAMEBUFFER, GLES20.GL_COLOR_ATTACHMENT0, GLES20.GL_RENDERBUFFER, _msRBId);
    }

    public void delete() {
        if (_fboId != -1) {
            GLES20.glDeleteFramebuffers(1, new int[]{_fboId}, 0);
            _fboId = -1;
        }

        if (_textures != null) {
            GLES20.glDeleteTextures(_textures.length, _textures, 0);
            _textures = null;
        }

        if (_msaa) {
            GLES20.glDeleteFramebuffers(1, new int[]{_msFBOId}, 0);
            GLES20.glDeleteRenderbuffers(1, new int[]{_msRBId}, 0);

            _msFBOId = -1;
            _msRBId = -1;
        }
    }

    /**
     * Ренедер в текстуру
     * @param routine - код отрисовки
     */
    public void render(RenderRoutine routine) {
        int[] oldFBO = new int[1];
        GLES20.glGetIntegerv(GLES20.GL_FRAMEBUFFER_BINDING, oldFBO, 0);

        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, _fboId);
        GLES20.glViewport(0, 0, _fboWidth, _fboHeight);

        GLES30.glDrawBuffers(_attachments.length, _attachments, 0);

        routine.render();

        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, oldFBO[0]);
    }

    /**
     * Ренедер в текстуру, с предварительной очисткой прозрачным цветом
     * @param routine - код отрисовки
     */
    public void clearRender(RenderRoutine routine) {
        int[] oldFBO = new int[1];
        GLES20.glGetIntegerv(GLES20.GL_FRAMEBUFFER_BINDING, oldFBO, 0);

        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, _fboId);
        GLES20.glViewport(0, 0, _fboWidth, _fboHeight);

        GLES30.glDrawBuffers(_attachments.length, _attachments, 0);

        GLES20.glClearColor(0, 0, 0, 0);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        routine.render();

        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, oldFBO[0]);
    }

    /**
     * Ресдер в текстуры с использование MSAA
     * @param routine - код отрисовки
     */
    public void renderMS(RenderRoutine routine) {
        int[] oldFBO = new int[1];
        GLES20.glGetIntegerv(GLES20.GL_FRAMEBUFFER_BINDING, oldFBO, 0);

        GLES30.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, _msFBOId);
        GLES20.glViewport(0, 0, _fboWidth, _fboHeight);

        routine.render();

        GLES30.glBindFramebuffer(GLES30.GL_READ_FRAMEBUFFER, _msFBOId);
        GLES30.glBindFramebuffer(GLES30.GL_DRAW_FRAMEBUFFER, _fboId);

        GLES30.glBlitFramebuffer(0, 0, _fboWidth, _fboHeight
                , 0, 0, _fboWidth, _fboHeight, GLES20.GL_COLOR_BUFFER_BIT, GLES20.GL_NEAREST);


        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, oldFBO[0]);
    }
}