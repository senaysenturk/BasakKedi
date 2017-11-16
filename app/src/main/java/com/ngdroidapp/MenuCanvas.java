package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.core.NgMediaPlayer;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;

/**
 * Created by noyan on 27.06.2016.
 * Nitra Games Ltd.
 */

public class MenuCanvas extends BaseCanvas {
    private Bitmap arkaplan , playbutton, ayarlarbutton, ayarlarpanel, ban, arkaplankedi, arkaplanoyunadi;

    private Rect playbuttonhedef , playbuttonkaynak  ;
    private Rect sesbuttonhedef , muzikbuttonhedef, hakkindabuttonhedef;
    private Rect ayarlarbuttonhedef, ayarlarbuttonkaynak ;
    private Rect arkaplankaynak, arkaplanhedef ;
    private Rect ayarlarpanelkaynak, ayarlarpanelhedef ;
    private Rect arkaplankedikaynak, arkaplankedihedef;
    private Rect arkaplanoyunadikaynak, arkaplanoyunadihedef;
    private boolean ayarlartiklandi;
    private Paint renk, gamelabrenk ;

    private boolean sesacik, muzikacik;
    private Rect bankaynak ;
    private NgMediaPlayer menumuzik;



    public MenuCanvas(NgApp ngApp) {
        super(ngApp);
    }

    public void setup() {
        renk = new Paint();
        renk.setARGB(255,255,0,0);
        gamelabrenk = new Paint();
        gamelabrenk.setARGB(255, 0, 192, 255);
        gamelabrenk.setTextAlign(Paint.Align.CENTER);
        gamelabrenk.setTextSize(oranla(40));
        arkaplan = Utils.loadImage(root,"images/background.png");
        arkaplankaynak = new Rect(0, 0, arkaplan.getWidth(), arkaplan.getHeight());
        arkaplanhedef = new Rect(0, 0, getWidth(), getHeight());
        arkaplankedi = Utils.loadImage(root, "images/menu_basakkedi_qhd.png");
        arkaplankedikaynak = new Rect(0, 0, arkaplankedi.getWidth(), arkaplankedi.getHeight());
        arkaplankedihedef = new Rect(oranla(50), getHeight() - oranla(391), oranla(579), getHeight() - oranla(50));
        arkaplanoyunadi = Utils.loadImage(root, "images/menu_oyunismi.png");
        arkaplanoyunadikaynak = new Rect(0, 0, arkaplanoyunadi.getWidth(), arkaplanoyunadi.getHeight());
        arkaplanoyunadihedef = new Rect(getWidthHalf() - oranla(663), oranla(75), getWidthHalf() + oranla(663), oranla(257));
        playbutton = Utils.loadImage(root,"images/gui/playbutton.png");
        playbuttonkaynak = new Rect(0, 0, playbutton.getWidth(), playbutton.getHeight());
        playbuttonhedef = new Rect(getWidthHalf() - oranla(200), getHeightHalf() - oranla(200), getWidthHalf() + oranla(200), getHeightHalf() + oranla(200));
        ayarlarbutton = Utils.loadImage(root, "images/gui/ayarlarbutton.png");
        ayarlarbuttonkaynak = new Rect(0, 0, ayarlarbutton.getWidth(), ayarlarbutton.getHeight());
        ayarlarbuttonhedef = new Rect(getWidth() - oranla(220), getHeight() - oranla(220), getWidth()-oranla(20), getHeight()-oranla(20));
        ayarlarpanel = Utils.loadImage(root, "images/gui/ayarlar.png");
        ayarlarpanelkaynak = new Rect(0, 0, ayarlarpanel.getWidth() , ayarlarpanel.getHeight());
        ayarlarpanelhedef = new Rect(getWidth()- oranla(220), getHeight() - oranla(880),
                getWidth() - oranla(20), getHeight()- oranla(20));
        ayarlartiklandi = false ;
        sesbuttonhedef = new Rect(ayarlarpanelhedef.left + oranla(24),
                ayarlarpanelhedef.top + oranla(52),
                ayarlarpanelhedef.left + oranla(176),
                ayarlarpanelhedef.top + oranla(203));
        muzikbuttonhedef = new Rect(ayarlarpanelhedef.left + oranla(24),
                ayarlarpanelhedef.top + oranla(239),
                ayarlarpanelhedef.left + oranla(176),
                ayarlarpanelhedef.top + oranla(391));
        hakkindabuttonhedef = new Rect(ayarlarpanelhedef.left + oranla(24),
                ayarlarpanelhedef.top + oranla(426),
                ayarlarpanelhedef.left + oranla(176),
                ayarlarpanelhedef.top + oranla(578));

        sesacik = true ;
        muzikacik = true ;
        ban = Utils.loadImage(root, "images/gui/ban.png");
        bankaynak = new Rect(0,0, ban.getWidth(), ban.getHeight());
        menumuzik = new NgMediaPlayer(root);
        menumuzik.load("sounds/menumuzigi.mp3");
        menumuzik.prepare();
        menumuzik.setLooping(true);
        menumuzik.start();
    }

    public void update() {
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(arkaplan, arkaplankaynak, arkaplanhedef, null);
        canvas.drawBitmap(playbutton, playbuttonkaynak, playbuttonhedef, null);
        canvas.drawBitmap(arkaplankedi, arkaplankedikaynak, arkaplankedihedef, null);
        canvas.drawBitmap(arkaplanoyunadi, arkaplanoyunadikaynak, arkaplanoyunadihedef, null);
        canvas.drawText("Gamelab Istanbul", getWidthHalf(), getHeight() - oranla(40), gamelabrenk);

        if (!ayarlartiklandi) {
            canvas.drawBitmap(ayarlarbutton, ayarlarbuttonkaynak, ayarlarbuttonhedef, null);
        }
        if (ayarlartiklandi) {
            canvas.drawBitmap(ayarlarpanel, ayarlarpanelkaynak, ayarlarpanelhedef, null);
        }
        if (!sesacik && ayarlartiklandi){
            canvas.drawBitmap(ban,bankaynak,sesbuttonhedef,null);
        }
        if (!muzikacik && ayarlartiklandi){
            canvas.drawBitmap(ban,bankaynak,muzikbuttonhedef,null);
        }
    }

    public void keyPressed(int key) {
    }

    public void keyReleased(int key) {

    }

    public boolean backPressed() {
        return false;
    }

    public void touchDown(int x, int y) {

        if (playbuttonhedef.contains(x,y)){
            root.canvasManager.setCurrentCanvas(new GameCanvas(root));
        } else if (ayarlarbuttonhedef.contains(x,y)){
            ayarlartiklandi = !ayarlartiklandi ;
        } else if (sesbuttonhedef.contains(x,y) && ayarlartiklandi){
            root.sesefektiacik = !root.sesefektiacik ;
            menumuzik = null ;
            sesacik = !sesacik ;
        } else if (muzikbuttonhedef.contains(x,y) && ayarlartiklandi){
            root.muzikacik = !root.muzikacik;
            muzikacik = !muzikacik ;
            menumuzik.setVolume(muzikacik ? 1:0);
        } else if (hakkindabuttonhedef.contains(x,y) && ayarlartiklandi){
            root.canvasManager.setCurrentCanvas(new InfoCanvas(root));
        } else{
            ayarlartiklandi = false ;
        }
    }

    public void touchMove(int x, int y) {
    }

    public void touchUp(int x, int y) {
    }

    public void surfaceChanged(int width, int height) {
        Log.i("MC", "surfaceChanged");
    }

    public void surfaceCreated() {
        Log.i("MC", "surfaceCreated");
    }

    public void surfaceDestroyed() {
        Log.i("MC", "surfaceDestroyed");
    }

    public void pause() {
        Log.i("MC", "pause");
    }

    public void resume() {
        Log.i("MC", "resume");
    }

    public void reloadTextures() {
        Log.i("MC", "reloadTextures");
    }

    public void showNotify() {
        Log.i("MC", "showNotify");
    }

    public void hideNotify() {
        Log.i("MC", "hideNotify");
    }

}
