package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.core.NgMediaPlayer;
import istanbul.gamelab.ngdroid.util.Utils;

/**
 * Created by dursu on 11.11.2017.
 */

public class InfoCanvas extends BaseCanvas {
    private Bitmap arkaplan;
    private Rect arkaplankaynak, arkaplanhedef;
    private String hakkinda[];
    private Paint font;
    private int konum, kaymahizi, satiraraligi;
    private NgMediaPlayer mediaplayer;

    public InfoCanvas(NgApp ngApp) {
        super(ngApp);

    }

    @Override
    public void setup() {
        mediaplayer = new NgMediaPlayer(root);
        mediaplayer.load("sounds/menumuzigi.mp3");
        mediaplayer.setLooping(true);
        mediaplayer.prepare();
        if (root.muzikacik) {
            mediaplayer.start();
        }
        arkaplan = Utils.loadImage(root, "images/space_1.png");
        arkaplankaynak = new Rect(0, 0, arkaplan.getWidth(), arkaplan.getHeight());
        arkaplanhedef = new Rect(0, 0, getWidth(), getHeight());
        font = new Paint();
        font.setTextSize(oranla(50));
        font.setTextAlign(Paint.Align.CENTER);
        font.setARGB(255, 255, 255, 255);
        hakkinda = new String[21];
        hakkinda[0] = "EĞİTMENLER";
        hakkinda[1] = "";
        hakkinda[2] = "Ramazan Noyan Culum";
        hakkinda[3] = "Dursun Ali Yaman";
        hakkinda[4] = "";
        hakkinda[5] = "KATILIMCILAR";
        hakkinda[6] = "Ayşe Nur Koç";
        hakkinda[7] = "Merve Genç";
        hakkinda[8] = "Mustafa Berat Aru";
        hakkinda[9] = "Abdulkadir Ballı";
        hakkinda[10] = "Songül Atam";
        hakkinda[11] = "Elif Koç";
        hakkinda[12] = "Şenay Şenturk";
        hakkinda[13] = "Mukaddes Demirtaş";
        hakkinda[14] = "İrem Nur Küçükenez";
        hakkinda[15] = "Gülşah Karataş";
        hakkinda[16] = "Zeynep Düzyurt";
        hakkinda[17] = "Beyza Barut";
        hakkinda[18] = "";
        hakkinda[19] = "Başakşehir Living Lab'e";
        hakkinda[20] = "Teşekkür Ederiz!";
        konum = getHeight() + oranla(100);
        kaymahizi = oranla(10);
        satiraraligi = oranla(70);
    }

    @Override
    public void update() {
        konum -= kaymahizi;
        if (konum <= - oranla(1400)) {
            konum = getHeight() + oranla(100);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(arkaplan, arkaplankaynak, arkaplanhedef, null);
        for (int i = 0; i < 19; i++){
            canvas.drawText(hakkinda[i], getWidthHalf(), konum + i * satiraraligi, font);
        }
    }

    @Override
    public void keyPressed(int key) {

    }

    @Override
    public void keyReleased(int key) {

    }

    @Override
    public boolean backPressed() {
        root.canvasManager.setCurrentCanvas(new MenuCanvas(root));
        return true;

    }

    @Override
    public void touchDown(int x, int y) {

    }

    @Override
    public void touchMove(int x, int y) {

    }

    @Override
    public void touchUp(int x, int y) {

    }

    @Override
    public void surfaceChanged(int width, int height) {

    }

    @Override
    public void surfaceCreated() {

    }

    @Override
    public void surfaceDestroyed() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void reloadTextures() {

    }

    @Override
    public void showNotify() {

    }

    @Override
    public void hideNotify() {

    }
}
