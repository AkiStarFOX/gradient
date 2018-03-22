package com.akistar.normalgradient;

import android.support.v4.graphics.ColorUtils;

public class ColorM {
    public static class RGB {
        public float r;
        public float g;
        public float b;

        public RGB(float r, float g, float b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }

        public void set(float r, float g, float b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }

        public HSL toHSL() {
            float[] hsl = new float[3];
            ColorUtils.RGBToHSL((int)(255f * r), (int)(255f * g), (int)(255f * b), hsl);
            return new HSL(hsl[0], hsl[1], hsl[2]);
        }
    }

    public static class HSL {
        public float h; // 0..360
        public float s; // 0..1
        public float l; // 0..1

        public HSL(int color) {
            float[] hsl = new float[3];
            ColorUtils.colorToHSL(color, hsl);
            h = hsl[0];
            s = hsl[1];
            l = hsl[2];
        }

        public HSL(float h, float s, float l) {
            this.h = h;
            this.s = s;
            this.l = l;
        }

        public boolean equal(HSL c) {
            return c.h == h && c.s == s && c.l == l;
        }

        public RGB toRGB() {
            int color = ColorUtils.HSLToColor(new float[] {h, s, l});
            float r = (float)((color >> 16) & 0xFF) / 255f;
            float g = (float)((color >> 8) & 0xFF) / 255f;
            float b = (float)(color & 0xFF) / 255f;

            return new RGB(r, g, b);
        }

        public int toColor() {
            return ColorUtils.HSLToColor(new float[] {h, s, l});
        }

        public static int toColor(float h, float s, float l) {
            return ColorUtils.HSLToColor(new float[] {h, s, l});
        }

        public void set(HSL hsl) {
            h = hsl.h;
            s = hsl.s;
            l = hsl.l;
        }

        public void set(float h, float s, float l) {
            this.h = h;
            this.s = s;
            this.l = l;
        }

        public void set(int color) {
            float[] hsl = new float[3];
            ColorUtils.colorToHSL(color, hsl);
            h = hsl[0];
            s = hsl[1];
            l = hsl[2];
        }

        public HSL clone() {
            return new HSL(h, s, l);
        }
    }
}
