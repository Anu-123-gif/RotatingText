package com.sdsmdg.harjot.rotatingtext.models;

import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Typeface;
import android.util.Log;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Harjot on 01-May-17.
 */

public class Rotatable {
    private int color = Color.BLACK;
    private String[] text;
    private int updateDuration = 2000;
    private int animationDuration = 1000;
    private int currentWordNumber;

    private float size = 24.0f;
    private int strokeWidth = -1;

    private Path pathIn, pathOut;

    private Interpolator interpolator = new LinearInterpolator();

    private Typeface typeface;

    private boolean isCenter = false;

    private boolean isUpdated = false;

    private int FPS = 60;

    private static final String TAG = "Rotatable";

    private int[] colorArray = {Color.parseColor("#123456"), Color.parseColor("#FFA036"), Color.parseColor("#FFA036")};    //initialized colorArray[] and set to null

    private String[] array;

    public Rotatable(int updateDuration, String... text) {
        this.updateDuration = updateDuration;
        this.text = text;
        currentWordNumber = -1;
    }

    public Rotatable(int[] colorArray, int updateDuration, String... text) {    //replaced int color with int colorArray as first argument
        this.colorArray = colorArray;


        Log.d(TAG, "Rotatable: colorArray was alloted in Rotatable");


        this.updateDuration = updateDuration;
        this.text = text;
        currentWordNumber = -1;
    }

    public Rotatable(int color, int updateDuration, String... text) {
        this.color = color;
        this.updateDuration = updateDuration;
        this.text = text;
        currentWordNumber = -1;
    }

    public int getColor() {
        return color;
    }

    public int[] getColorArray(){
        return colorArray;
    }

    public void setColor(int color) {
        this.color = color;
        setUpdated(true);
    }

    public String[] getText() {
        return text;
    }

    public int getWordCount() {
        return text.length;
    }

    public void setText(String... text) {
        this.text = text;
    }

    public String getTextAt(int index) {
        if (index >= text.length) {
            throw new ArrayIndexOutOfBoundsException("index exceeded number of words!!");
        } else {
            return text[index];
        }
    }

    public void setTextAt(int index, String word) {
        if (index >= text.length) {
            throw new ArrayIndexOutOfBoundsException("index exceeded number of words!!");
        } else {
            text[index] = word;
        }
    }

    public String[] peekNewTextAt(int index, String word) {
        if (index >= text.length) {
            throw new ArrayIndexOutOfBoundsException("index exceeded number of words!!");
        } else {
            String[] newSet = Arrays.copyOf(text, text.length);
            newSet[index] = word;
            return newSet;
        }
    }

    public int getUpdateDuration() {
        return updateDuration;
    }

    public void setUpdateDuration(int updateDuration) {
        this.updateDuration = updateDuration;
        setUpdated(true);
    }

    public int getNextWordNumber() {
        //provides next word number circularly
        currentWordNumber = (currentWordNumber + 1) % text.length;
        return currentWordNumber;
    }

    public String peekNextWord() {
        //doesnot increases currentwordnumber
        return text[(currentWordNumber + 1) % text.length];
    }

    public String getNextWord() {
        return text[getNextWordNumber()];
    }

    public String getCurrentWord() {
        return text[currentWordNumber];
    }

    public String getPreviousWord() {
        if (currentWordNumber <= 0)
            return text[(text.length - 1)];
        else
            return text[currentWordNumber - 1];
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
        setUpdated(true);
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public int getAnimationDuration() {
        return animationDuration;
    }

    public void setAnimationDuration(int animationDuration) {
        this.animationDuration = animationDuration;
        setUpdated(true);
    }

    public Path getPathIn() {
        return pathIn;
    }

    public void setPathIn(Path pathIn) {
        this.pathIn = pathIn;
    }

    public Path getPathOut() {
        return pathOut;
    }

    public void setPathOut(Path pathOut) {
        this.pathOut = pathOut;
    }

    public Interpolator getInterpolator() {
        return interpolator;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
        setUpdated(true);
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
        setUpdated(true);
    }

    public String getLargestWord() {
        String largest = "";
        for (String s : text) {
            if (s.length() > largest.length()) {
                largest = s;
            }
        }
        return largest;
    }

    public String getLargestWordWithSpace() {
        String largest = "";
        for (String s : text) {
            if (s.length() > largest.length()) {
                largest = s;
            }
        }
        return largest + " ";
    }

    public String peekLargestWord(int index, String newWord) {
        String[] newSet = peekNewTextAt(index, newWord);
        String largest = "";
        for (String s : newSet) {
            if (s.length() > largest.length()) {
                largest = s;
            }
        }
        return largest + " ";
    }

    public boolean isCenter() {
        return isCenter;
    }

    public void setCenter(boolean center) {
        isCenter = center;
        setUpdated(true);
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setUpdated(boolean updated) {
        isUpdated = updated;
    }

    public int getFPS() {
        return FPS;
    }

    public void setFPS(int FPS) {
        this.FPS = FPS;
    }

}
