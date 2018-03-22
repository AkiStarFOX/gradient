//package com.akistar.normalgradient;
//
//public class CrystalColor {
//    private static final ColorM.HSL WHITE = new ColorM.HSL(0, 0, 1);
//    private static final ColorM.HSL BLACK = new ColorM.HSL(0, 0, 0);
//
//    private ColorM.HSL lastFC = new ColorM.HSL(0, 0, 1); //white
//    private ColorM.HSL lastSC = new ColorM.HSL(0, 0, 0); //black
//
//    public ColorM.HSL fstColor = new ColorM.HSL(0, 0, 1);
//    public ColorM.HSL sndColor = new ColorM.HSL(0, 0, 0);
//
//    public float opacity = 1;
//    private boolean fstFromAchro = true;
//
//    public ColorScheme curScheme = ColorScheme.ACHROMATIC;
//
//    public void set(CrystalColor color) {
//        lastFC.set(color.lastFC);
//        lastSC.set(color.lastSC);
//
//        fstColor.set(color.fstColor);
//        sndColor.set(color.sndColor);
//
//        opacity = color.opacity;
//        fstFromAchro = color.fstFromAchro;
//        curScheme = color.curScheme;
//    }
//
//    public void setFstColor(ColorM.HSL color) {
//        fstColor.set(color);
//        applyScheme();
//    }
//
//    public void setFstColor(ColorM.HSL color, PaletteSeekBar.ColorPart part) {
//        switch (part) {
//            case HUE:
//                fstColor.h = color.h;
//                break;
//            case SATURATION:
//                fstColor.s = color.s;
//                break;
//            case LIGHTNESS:
//                fstColor.l = color.l;
//                break;
//        }
//
//        applyScheme();
//    }
//
//    public void setSndColor(ColorM.HSL color) {
//        sndColor.set(color);
//    }
//
//    public void setSndColor(ColorM.HSL color, PaletteSeekBar.ColorPart part) {
//        switch (part) {
//            case HUE:
//                sndColor.h = color.h;
//                break;
//            case SATURATION:
//                sndColor.s = color.s;
//                break;
//            case LIGHTNESS:
//                sndColor.l = color.l;
//                break;
//        }
//    }
//
//    public void setScheme(ColorScheme scheme) {
//        if (scheme == ColorScheme.ACHROMATIC) {
//            lastFC.set(fstColor);
//            lastSC.set(fstColor);
//
//            fstColor.set(WHITE);
//            sndColor.set(BLACK);
//        }
//
//        if (scheme != ColorScheme.ACHROMATIC && curScheme == ColorScheme.ACHROMATIC) {
//            if (lastFC.equal(WHITE)) {
//                fstColor.set(Renderer.avrColor);
//
//                if (fstFromAchro) {
//                    applyMonochromatic();
//                    fstFromAchro = false;
//                }
//            } else {
//                fstColor.set(lastFC);
//                sndColor.set(lastSC);
//            }
//        }
//
//        curScheme = scheme;
//
//        applyScheme();
//    }
//
//    private void applyScheme() {
//        switch (curScheme) {
//            case COMPLEMENTARY:
//                applyComplementary();
//                return;
//            case ANALOGUS:
//                applyAnalogous();
//                return;
//            case MONOCHROMATIC:
//                applyMonochromatic();
//                return;
//            case COMPOUND:
//                applyCompound();
//                return;
//            case SQUARE:
//                applySquare();
//                return;
//            case TRIADIC:
//                applyTriadic();
//                return;
//            case ACHROMATIC:
//                applyAchromatic();
//                return;
//            default:
//                return;
//        }
//    }
//
//    private void applyAchromatic() {
//        float l = 1 - fstColor.l;
//        sndColor.l = l;
//    }
//
//    private void applyComplementary() {
//        float h = fstColor.h + 180f;
//
//        if (h > 360f) {
//            h -= 360f;
//        }
//
//        sndColor.set(h, fstColor.s, fstColor.l);
//    }
//
//    private void applyMonochromatic() {
//        float l = fstColor.l * 0.6f;
//
//        sndColor.set(fstColor.h, fstColor.s, l);
//    }
//
//    private void applyAnalogous() {
//        float h = adjustHUE(fstColor.h, 60);
//
//        sndColor.set(h, fstColor.s, fstColor.l);
//    }
//
//    private void applyCompound() {
//        float h = adjustHUE(fstColor.h + 180, 30);
//
//        sndColor.set(h, fstColor.s, fstColor.l);
//    }
//
//    private void applyTriadic() {
//        float h = adjustHUE(fstColor.h, -90);
//
//        sndColor.set(h, fstColor.s, fstColor.l);
//    }
//
//    private void applySquare() {
//        float h = adjustHUE(fstColor.h, 120);
//
//        sndColor.set(h, fstColor.s, fstColor.l);
//    }
//
//    private float adjustHUE(float hue, float angle) {
//        float h = hue + angle;
//
//        if (h > 360) {
//            h -= 360;
//        }
//
//        return h;
//    }
//}
