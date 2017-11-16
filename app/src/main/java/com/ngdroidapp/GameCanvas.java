package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;
import java.util.Vector;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.core.NgMediaPlayer;
import istanbul.gamelab.ngdroid.util.Utils;


/**
 * Created by noyan on 24.06.2016.
 * Nitra Games Ltd.
 */


public class GameCanvas extends BaseCanvas {
    private NgMediaPlayer mediaplayer;
    private int patlamasesi, dusmanatessesi, kediatessesi;
    private int ozelgucalmasesi, ozelgucbitmesesi, altinalmasesi, kaybetmesesi;
    private Bitmap[] oyunsonupaneli;
    private Bitmap arkaplan;
    private Bitmap duraklatmamenusu;
    private Bitmap kedi[];
    private Bitmap dusman[][][];
    private Bitmap tuslar[];
    private Bitmap patlamaefekti;
    private Bitmap altin[];
    private Bitmap ozelguc[];
    private Bitmap altinpanel, puanpanel;
    private int altinkareadedi, altinkareno;
    private Rect tushedefleri[];
    private Rect oyunsonupanelkaynak, oyunsonupanelhedef, oyunsonuyenidenbaslamahedef, oyunsonumenubuttonhedef;
    private Rect duraklatmamenukaynak, duraklatmamenuhedef, yenidenbaslamahedef, baslamahedef, menubuttonhedef;
    private Bitmap mermiresmi[];
    private Rect mermihedef;
    private Rect patlamaefektlerikaynak[], patlamaefektihedef;
    private Rect altinhedef, altinkaynak;
    private Rect ozelguckaynak, ozelguchedef;
    private Rect altinpanelkaynak, altinpanelhedef;
    private Rect puanpanelkaynak, puanpanelhedef;
    private Paint puanyazirengi, altinyazirengi, oyunsonuskorrengi, eniyipuanrengi;
    private Paint kedigucrengi;
    private Rect toplamcizgihedef;
    private int ozelgucx, ozelgucy, ozelguckareno, ozelgucgecikmezamani;
    private long ozelgucyokolmazamani;
    private boolean ozelgucekrandavar;
    private int patlamakareadedi, patlamagenislik, patlamayukseklik, patlamaefektikareno, ozelguckareadedi;
    private int tusadedi;
    private Rect arkaplankaynak, arkaplanhedef, tuskaynagi, kedikaynak, kedihedef, dusmankaynak, dusmanhedef;
    private int arkaplankx, ilerlemehizix;
    private int kedix, kediy;
    private int kedikareadedi = 5;
    private int dusmanadedi = 6;
    private int dusmananimasyonadedi = 2;
    private int kedikareno, kedikaresayaci, kedikaresayaclimiti;
    private Canvas grafikCizici;
    private int dikeyhiz, kedidikeyhiz;
    private int ustlimit, altlimit;
    private int dusmanx, dusmany, dusmanhiz;
    private Random rastgelesayiureticisi;
    private boolean ekrandadusmanvar;
    private boolean oyunduraklatildi;
    private long dusmaninyokolmazamani;
    private int dusmangecikmezamani;
    private int dusmancesidi, dusmananimasyoncesidi, dusmananimasyonkareno;
    private static final int ANIMASYON_IDLE = 0;
    private static final int ANIMASYON_SHOOT = 1;
    private static final int KARAKTER_UFO_GREEN = 0;
    private static final int KARAKTER_UFO_BLACK = 1;
    private static final int KARAKTER_UFO_RED = 2;
    private static final int KARAKTER_SUIT_BLACK = 3;
    private static final int KARAKTER_SUIT_ORANGE = 4;
    private static final int KARAKTER_SUIT_PURPLE = 5;
    private static final int TUS_SOL = 0;
    private static final int TUS_SAG = 1;
    private static final int TUS_YUKARI = 2;
    private static final int TUS_ASAGI = 3;
    private static final int TUS_ATES = 4;
    private int dusmananimasyonkareadedi[][];
    private boolean kediyasiyor, dusmanyasiyor;
    private boolean patlamavar;
    private boolean mermieklenecek;
    private int mermiilerlemehizi;
    private Vector<int[]> mermiler;
    private static final int MERMIYON_SAG = 1;
    private static final int MERMIYON_SOL = -1;
    private static final int MERMI_UFO = 0;
    private static final int MERMI_SUIT = 1;
    private static final int MERMI_KEDI = 2;
    private static final int MERMI_X = 0;
    private static final int MERMI_Y = 1;
    private static final int MERMI_YON = 2;
    private static final int MERMI_CESIDI = 3;
    private int mermituruadedi = 3;
    private long mermiatilmazamani;
    private int mermigecikmezamani;
    private boolean kedioldu;
    private int alinanyol;
    private int toplananaltinlar;
    private int altinx, altiny;
    private long altinkaybolmazamani;
    private int altingecikmezamani;
    private boolean ekrandaaltinvar;
    private int altinsayisi;
    private float hizlanmaorani;
    private int arkaplanhizsabiti, dusmanhizsabiti, mermihizsabiti;
    private boolean kediozelgucaldi;
    private int ozelgucsuresi, kedialfarenk;
    private int alfaartismiktari;
    private long ozelgucalinmazamani;
    private long baslamazamani;
    private boolean oyunbitti;
    private boolean oyunsonlandi;
    private int toplamoyunsuresi;
    private int oyunsonubasari;
    private int basariseviyesi1, basariseviyesi2, basariseviyesi3;
    private int puan;
    private Paint puangolgerengi, oyunsonugolgerengi;



    public GameCanvas(NgApp ngApp) {
        super(ngApp);
    }

    public void setup() {
        baslamazamani = System.currentTimeMillis();
        mediaplayer = new NgMediaPlayer(root);
        mediaplayer.load("sounds/oyunmuzigi.mp3");
        mediaplayer.setLooping(true);
        mediaplayer.prepare();
        if (root.muzikacik) {
            mediaplayer.start();
        }
        try {
            patlamasesi = root.soundManager.load("sounds/se2.wav");
            dusmanatessesi = root.soundManager.load("sounds/dusmanates.wav");
            kediatessesi = root.soundManager.load("sounds/kediates.ogg");
            ozelgucalmasesi = root.soundManager.load("sounds/powerup.wav");
            ozelgucbitmesesi = root.soundManager.load("sounds/powerdown.wav");
            altinalmasesi = root.soundManager.load("sounds/collectcoin.wav");
            kaybetmesesi = root.soundManager.load("sounds/lose.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
        altinpanel = Utils.loadImage(root, "images/gui/altinpanel.png");
        altinpanelkaynak = new Rect(0, 0, altinpanel.getWidth(), altinpanel.getHeight());
        altinpanelhedef = new Rect(getWidth() - oranla(363), oranla(10), getWidth() - oranla(50), oranla(94));
        puanpanel = Utils.loadImage(root, "images/gui/puanpanel.png");
        puanpanelkaynak = new Rect(0, 0, puanpanel.getWidth(), puanpanel.getHeight());
        puanpanelhedef = new Rect(oranla(50), oranla(10), oranla(308), oranla(84));
        duraklatmamenusu = Utils.loadImage(root, "images/gui/pausemenu.png");
        duraklatmamenukaynak = new Rect(0, 0, duraklatmamenusu.getWidth(), duraklatmamenusu.getHeight());
        duraklatmamenuhedef = new Rect(getWidthHalf() - oranla(390),
                getHeightHalf() - oranla(178),
                getWidthHalf() + oranla(390),
                getHeightHalf() + oranla(178));
        baslamahedef = new Rect(duraklatmamenuhedef.left + oranla(120),
                duraklatmamenuhedef.top + oranla(148),
                duraklatmamenuhedef.left + oranla(264),
                duraklatmamenuhedef.top + oranla(292));
        yenidenbaslamahedef = new Rect(duraklatmamenuhedef.left + oranla(324),
                duraklatmamenuhedef.top + oranla(148),
                duraklatmamenuhedef.left + oranla(468),
                duraklatmamenuhedef.top + oranla(292));
        menubuttonhedef = new Rect(duraklatmamenuhedef.left + oranla(534),
                duraklatmamenuhedef.top + oranla(148),
                duraklatmamenuhedef.left + oranla(678),
                duraklatmamenuhedef.top + oranla(292));
        altinkareadedi = 10;
        altinkareno = 0;
        ozelguckareadedi = 4;
        arkaplan = Utils.loadImage(root, "images/night-city.png");
        kedi = new Bitmap[kedikareadedi];
        kedi[0] = Utils.loadImage(root, "images/yellow_idle_1.png");
        kedi[1] = Utils.loadImage(root, "images/yellow_idle_2.png");
        kedi[2] = Utils.loadImage(root, "images/yellow_dead_1.png");
        kedi[3] = Utils.loadImage(root, "images/yellow_dead_2.png");
        kedi[4] = Utils.loadImage(root, "images/yellow_dead_3.png");
        altin = new Bitmap[altinkareadedi];
        for (int i = 1; i < altinkareadedi + 1; i++) {
            altin[i - 1] = Utils.loadImage(root, "images/golds/" + i + ".png");
        }
        ozelguc = new Bitmap[ozelguckareadedi];
        ozelguc[0] = Utils.loadImage(root, "images/power_1.png");
        ozelguc[1] = Utils.loadImage(root, "images/power_2.png");
        ozelguc[2] = Utils.loadImage(root, "images/power_3.png");
        ozelguc[3] = Utils.loadImage(root, "images/power_4.png");
        mermiresmi = new Bitmap[mermituruadedi];
        mermiresmi[MERMI_UFO] = Utils.loadImage(root, "images/ufo_alien_shot_1.png");
        mermiresmi[MERMI_SUIT] = Utils.loadImage(root, "images/suit_alien_shot_1.png");
        mermiresmi[MERMI_KEDI] = Utils.loadImage(root, "images/cat_shot_1.png");
        kedikaynak = new Rect();
        kedihedef = new Rect();
        dusmankaynak = new Rect();
        dusmanhedef = new Rect();
        puanyazirengi = new Paint();
        kedigucrengi = new Paint();
        puanyazirengi.setARGB(255, 255, 255, 0);
        puanyazirengi.setTextSize(oranla(36));
        puanyazirengi.setTextAlign(Paint.Align.RIGHT);
        altinyazirengi = new Paint();
        altinyazirengi.setTextSize(oranla(36));
        altinyazirengi.setARGB(255, 255, 255, 0);
        altinyazirengi.setTextAlign(Paint.Align.RIGHT);
        puangolgerengi = new Paint();
        puangolgerengi.setARGB(128, 0, 0,0);
        puangolgerengi.setTextAlign(Paint.Align.RIGHT);
        puangolgerengi.setTextSize(oranla(36));
        oyunsonugolgerengi = new Paint();
        oyunsonugolgerengi.setARGB(128,0, 0, 0);
        oyunsonugolgerengi.setTextAlign(Paint.Align.RIGHT);
        oyunsonugolgerengi.setTextSize(oranla(80));

        tusadedi = 5;
        tushedefleri = new Rect[tusadedi];
        for (int i = 0; i < tusadedi; i++) {
            tushedefleri[i] = new Rect();
        }
        patlamakareadedi = 16;
        patlamaefektlerikaynak = new Rect[patlamakareadedi];
        for (int i = 0; i < patlamakareadedi; i++) {
            int satirno = i / 4;
            int sutunno = i % 4;
            patlamaefektlerikaynak[i] = new Rect(sutunno * 128, satirno * 128, (sutunno + 1) * 128, (satirno + 1) * 128);
        }
        patlamagenislik = oranla(250);
        patlamayukseklik = oranla(250);
        patlamaefektihedef = new Rect();
        patlamaefektikareno = 0;
        patlamavar = false;
        tushedefleri[TUS_SOL].set(20, getHeight() - 260, 140, getHeight() - 140);
        tushedefleri[TUS_SAG].set(260, getHeight() - 260, 380, getHeight() - 140);
        tushedefleri[TUS_YUKARI].set(oranla(40), getHeight() - oranla(340), oranla(160), getHeight() - oranla(220));
        tushedefleri[TUS_ASAGI].set(oranla(40), getHeight() - oranla(180), oranla(160), getHeight() - oranla(60));
        tushedefleri[TUS_ATES].set(getWidth() - oranla(260), getHeight() - oranla(260), getWidth() - oranla(80), getHeight() - oranla(80));
        tuslar = new Bitmap[tusadedi];
        tuslar[TUS_SOL] = Utils.loadImage(root, "images/buttons/shadedDark24.png");
        tuslar[TUS_SAG] = Utils.loadImage(root, "images/buttons/shadedDark25.png");
        tuslar[TUS_ASAGI] = Utils.loadImage(root, "images/buttons/shadedDark27.png");
        tuslar[TUS_YUKARI] = Utils.loadImage(root, "images/buttons/shadedDark26.png");
        tuslar[TUS_ATES] = Utils.loadImage(root, "images/buttons/shadedDark48.png");
        patlamaefekti = Utils.loadImage(root, "images/explosion2.png");
        oyunsonupaneli = new Bitmap[4];
        oyunsonupaneli[0] = Utils.loadImage(root, "images/gui/oyunsonu0.png");
        oyunsonupaneli[1] = Utils.loadImage(root, "images/gui/oyunsonu1.png");
        oyunsonupaneli[2] = Utils.loadImage(root, "images/gui/oyunsonu2.png");
        oyunsonupaneli[3] = Utils.loadImage(root, "images/gui/oyunsonu3.png");
        oyunsonupanelkaynak = new Rect(0, 0, oyunsonupaneli[0].getWidth(), oyunsonupaneli[0].getHeight());
        oyunsonupanelhedef = new Rect(getWidthHalf() - oranla(430),
                getHeightHalf() - oranla(491),
                getWidthHalf() + oranla(430),
                getHeightHalf() + oranla(491));
        oyunsonumenubuttonhedef = new Rect(
                oyunsonupanelhedef.left + oranla(493),
                oyunsonupanelhedef.top + oranla(862),
                oyunsonupanelhedef.left + oranla(614),
                oyunsonupanelhedef.top + oranla(983));
        oyunsonuyenidenbaslamahedef = new Rect(oyunsonupanelhedef.left + oranla(274),
                oyunsonupanelhedef.top + oranla(862),
                oyunsonupanelhedef.left + oranla(395),
                oyunsonupanelhedef.top + oranla(983));
        tuskaynagi = new Rect(0, 0, tuslar[0].getWidth(), tuslar[0].getHeight());
        dusman = new Bitmap[dusmanadedi][dusmananimasyonadedi][];
        dusman[KARAKTER_UFO_BLACK][ANIMASYON_IDLE] = new Bitmap[1];
        dusman[KARAKTER_UFO_GREEN][ANIMASYON_IDLE] = new Bitmap[1];
        dusman[KARAKTER_UFO_RED][ANIMASYON_IDLE] = new Bitmap[1];
        dusman[KARAKTER_SUIT_BLACK][ANIMASYON_IDLE] = new Bitmap[2];
        dusman[KARAKTER_SUIT_ORANGE][ANIMASYON_IDLE] = new Bitmap[2];
        dusman[KARAKTER_SUIT_PURPLE][ANIMASYON_IDLE] = new Bitmap[2];
        dusman[KARAKTER_UFO_BLACK][ANIMASYON_IDLE][0] = Utils.loadImage(root, "images/black_ufo_idle_1.png");
        dusman[KARAKTER_UFO_GREEN][ANIMASYON_IDLE][0] = Utils.loadImage(root, "images/green_ufo_idle_1.png");
        dusman[KARAKTER_UFO_RED][ANIMASYON_IDLE][0] = Utils.loadImage(root, "images/red_ufo_idle_1.png");
        dusman[KARAKTER_SUIT_BLACK][ANIMASYON_IDLE][0] = Utils.loadImage(root, "images/black_suit_idle_1.png");
        dusman[KARAKTER_SUIT_BLACK][ANIMASYON_IDLE][1] = Utils.loadImage(root, "images/black_suit_idle_2.png");
        dusman[KARAKTER_SUIT_ORANGE][ANIMASYON_IDLE][0] = Utils.loadImage(root, "images/orange_suit_idle_1.png");
        dusman[KARAKTER_SUIT_ORANGE][ANIMASYON_IDLE][1] = Utils.loadImage(root, "images/orange_suit_idle_2.png");
        dusman[KARAKTER_SUIT_PURPLE][ANIMASYON_IDLE][0] = Utils.loadImage(root, "images/purple_suit_idle_1.png");
        dusman[KARAKTER_SUIT_PURPLE][ANIMASYON_IDLE][1] = Utils.loadImage(root, "images/purple_suit_idle_2.png");
        rastgelesayiureticisi = new Random();
        arkaplankaynak = new Rect();
        arkaplanhedef = new Rect();
        arkaplankx = 0;
        ilerlemehizix = oranla(40);
        kedix = oranla(250);
        kediy = oranla(466);
        kedikareno = 0;
        kedikaresayaci = 0;
        kedikaresayaclimiti = 3;
        kedidikeyhiz = oranla(30);
        dikeyhiz = 0;
        ustlimit = oranla(150);
        altlimit = oranla(930);
        dusmanx = getWidth() * 2;
        dusmany = getHeightHalf() - oranla(101);
        dusmanhiz = -ilerlemehizix - oranla(10);
        ekrandadusmanvar = true;
        dusmanyasiyor = true;
        kediyasiyor = true;
        dusmananimasyoncesidi = ANIMASYON_IDLE;
        dusmananimasyonkareno = 0;
        dusmananimasyonkareadedi = new int[dusmanadedi][dusmananimasyonadedi];
        dusmananimasyonkareadedi[KARAKTER_UFO_BLACK][ANIMASYON_IDLE] = 1;
        dusmananimasyonkareadedi[KARAKTER_UFO_BLACK][ANIMASYON_SHOOT] = 2;
        dusmananimasyonkareadedi[KARAKTER_UFO_GREEN][ANIMASYON_IDLE] = 1;
        dusmananimasyonkareadedi[KARAKTER_UFO_GREEN][ANIMASYON_SHOOT] = 2;
        dusmananimasyonkareadedi[KARAKTER_UFO_RED][ANIMASYON_IDLE] = 1;
        dusmananimasyonkareadedi[KARAKTER_UFO_RED][ANIMASYON_SHOOT] = 2;
        dusmananimasyonkareadedi[KARAKTER_SUIT_BLACK][ANIMASYON_IDLE] = 2;
        dusmananimasyonkareadedi[KARAKTER_SUIT_BLACK][ANIMASYON_SHOOT] = 2;
        dusmananimasyonkareadedi[KARAKTER_SUIT_ORANGE][ANIMASYON_IDLE] = 2;
        dusmananimasyonkareadedi[KARAKTER_SUIT_ORANGE][ANIMASYON_SHOOT] = 2;
        dusmananimasyonkareadedi[KARAKTER_SUIT_PURPLE][ANIMASYON_IDLE] = 2;
        dusmananimasyonkareadedi[KARAKTER_SUIT_PURPLE][ANIMASYON_SHOOT] = 2;
        dusmancesidi = rastgelesayiureticisi.nextInt(dusmanadedi);
        dusmaniGuncelle();
        mermiler = new Vector<>();
        mermiilerlemehizi = oranla(70);
        mermieklenecek = false;
        mermiatilmazamani = System.currentTimeMillis();
        mermigecikmezamani = rastgelesayiureticisi.nextInt(3000) + 2000;
        mermihedef = new Rect();
        kedioldu = false;
        alinanyol = 0;
        toplananaltinlar = 0;
        altinx = -500;
        altiny = -500;
        altinhedef = new Rect(altinx, altiny, altinx + oranla(altin[0].getWidth()), altiny + oranla(altin[0].getHeight()));
        altinkaynak = new Rect(0, 0, altin[0].getWidth(), altin[0].getHeight());
        altingecikmezamani = rastgelesayiureticisi.nextInt(1000) + 1000;
        altinkaybolmazamani = System.currentTimeMillis() + 3000;
        ekrandaaltinvar = false;
        altinsayisi = 0;
        hizlanmaorani = 2.0f;
        arkaplanhizsabiti = oranla(40);
        dusmanhizsabiti = oranla(-50);
        mermihizsabiti = oranla(70);
        ozelguckaynak = new Rect(0, 0, ozelguc[0].getWidth(), ozelguc[0].getHeight());
        ozelgucx = 0;
        ozelgucy = 0;
        ozelguchedef = new Rect(ozelgucx, ozelgucy, ozelgucx + oranla(ozelguc[0].getWidth()), ozelgucy + oranla(ozelguc[0].getHeight()));
        ozelguckareno = 0;
        ozelgucyokolmazamani = System.currentTimeMillis() + 1000;
        ozelgucgecikmezamani = rastgelesayiureticisi.nextInt(20000) + 10000;
        ozelgucekrandavar = false;
        alfaartismiktari = 20;
        kediozelgucaldi = false;
        ozelgucsuresi = 6000;
        kedialfarenk = 255;
        oyunduraklatildi = false;
        oyunbitti = false;
        oyunsonlandi = false;
        oyunsonuskorrengi = new Paint();
        oyunsonuskorrengi.setARGB(255, 255, 255, 255);
        oyunsonuskorrengi.setTextSize(oranla(80));
        toplamcizgihedef = new Rect(oyunsonupanelhedef.left + oranla(30),
                oyunsonupanelhedef.top + oranla(730),
                oyunsonupanelhedef.right - oranla(30),
                oyunsonupanelhedef.top + oranla(740));  //TODO degiscek
        toplamoyunsuresi = 0;
        basariseviyesi1 = 1000;
        basariseviyesi2 = 3000;
        basariseviyesi3 = 5000;
        oyunsonubasari = 0;
        puan = 0;
        eniyipuanrengi = new Paint();
        eniyipuanrengi.setARGB(255, 255, 0, 0);
        eniyipuanrengi.setTextSize(oranla(80));
        eniyipuanrengi.setTextAlign(Paint.Align.CENTER);
    }


    private void dusmaniGuncelle() {
        dusmankaynak.set(0, 0, dusman[dusmancesidi][dusmananimasyoncesidi][dusmananimasyonkareno].getWidth(), dusman[dusmancesidi][dusmananimasyoncesidi][dusmananimasyonkareno].getHeight());
        dusmanhedef.set(dusmanx, dusmany, dusmanx + oranla(dusman[dusmancesidi][dusmananimasyoncesidi][dusmananimasyonkareno].getWidth()), dusmany + oranla(dusman[dusmancesidi][dusmananimasyoncesidi][dusmananimasyonkareno].getHeight()));

    }

    public void update() {
        if (oyunduraklatildi || oyunsonlandi) {
            return;
        }
        if (hizlanmaorani <= 3) {
            hizlanmaorani = 1 + alinanyol / (float) oranla(150000);
        }
        ilerlemehizix = (int) (arkaplanhizsabiti * hizlanmaorani);
        dusmanhiz = (int) (dusmanhizsabiti * hizlanmaorani);
        mermiilerlemehizi = (int) (mermihizsabiti * hizlanmaorani);
        arkaplaniOynat();
        kullaniciGirisiniKontrolEt();
        cakismalariKontrolEt();
        dusmanlariOynat();
        digerObjeleriOynat();
        animasyonlariOynat();


    }
    //oyunların kalbi - ana döngü

    public void draw(Canvas canvas) {
        grafikCizici = canvas;
        arkaplaniCiz();
        sahneyiCiz();
        kullaniciArayuzunuCiz();
        //ekrana çizdiriyoruz
    }

    private void arkaplaniOynat() {
        arkaplankx += ilerlemehizix;
        alinanyol += 40;
        if (arkaplankx >= getWidth())
            arkaplankx = 0;
    }

    private void kullaniciGirisiniKontrolEt() {
        kediy += dikeyhiz;
        if (kediy + oranla(kedi[0].getHeight()) >= altlimit || kediy <= ustlimit) {
            kediy -= dikeyhiz;
            dikeyhiz = 0;
        }

    }

    private void dusmanlariOynat() {
        dusmanx += dusmanhiz;
        if (dusmanx < oranla(-dusman[dusmancesidi][dusmananimasyoncesidi][0].getWidth()) - oranla(50) && ekrandadusmanvar) {
            ekrandadusmanvar = false;
            dusmanyasiyor = false;
            dusmaninyokolmazamani = System.currentTimeMillis();
            dusmangecikmezamani = rastgelesayiureticisi.nextInt(3000) + 2000;

        }
        if (System.currentTimeMillis() > dusmaninyokolmazamani + dusmangecikmezamani && !ekrandadusmanvar && !dusmanyasiyor) {
            dusmancesidi = rastgelesayiureticisi.nextInt(dusmanadedi);
            dusmanx = getWidth();
            dusmany = rastgelesayiureticisi.nextInt(altlimit - ustlimit - oranla(dusman[dusmancesidi][dusmananimasyoncesidi][0].getHeight())) + ustlimit;
            ekrandadusmanvar = true;
            dusmananimasyonkareno = 0;
            dusmanyasiyor = true;
        }
        dusmaniGuncelle();
        if (mermiatilmazamani + mermigecikmezamani < System.currentTimeMillis() && ekrandadusmanvar && dusmanyasiyor) {
            if (root.sesefektiacik) {
                root.soundManager.play(dusmanatessesi);
            }
            mermiEkle(MERMIYON_SOL);
            mermiatilmazamani = System.currentTimeMillis();
            mermigecikmezamani = rastgelesayiureticisi.nextInt(1000) + 1000;
        }
    }

    private void digerObjeleriOynat() {
        if (mermieklenecek && kediyasiyor) {
            if (root.sesefektiacik) {
                root.soundManager.play(kediatessesi);
            }
            mermiEkle(MERMIYON_SAG);
            mermieklenecek = false;
        }
        for (int[] mermi : mermiler) {
            mermi[MERMI_X] += mermi[MERMI_YON] * mermiilerlemehizi;
        }
        mermileriYoket();
        if (System.currentTimeMillis() > altinkaybolmazamani + altingecikmezamani && !ekrandaaltinvar) {
            altingecikmezamani = rastgelesayiureticisi.nextInt(1000) + 1000;
            altinx = getWidth() + oranla(100);
            altiny = rastgelesayiureticisi.nextInt(altlimit - ustlimit - oranla(altinkaynak.height())) + ustlimit;
            ekrandaaltinvar = true;
        }
        if (altinhedef.right < -oranla(50)) {
            if (ekrandaaltinvar) {
                altinkaybolmazamani = System.currentTimeMillis();
            }
            ekrandaaltinvar = false;

        }
        altinx -= ilerlemehizix;
        altinhedef.set(altinx, altiny, altinx + oranla(altinkaynak.width()), altiny + oranla(altinkaynak.height()));
        ozelgucx -= ilerlemehizix;
        if (System.currentTimeMillis() > ozelgucyokolmazamani + ozelgucgecikmezamani && !ozelgucekrandavar) {
            ozelgucgecikmezamani = rastgelesayiureticisi.nextInt(20000) + 10000;
            ozelgucx = getWidth() + oranla(100);
            ozelgucy = rastgelesayiureticisi.nextInt(altlimit - ustlimit - oranla(ozelguckaynak.height())) + ustlimit;
            ozelgucekrandavar = true;
        }
        ozelguchedef.set(ozelgucx, ozelgucy, ozelgucx + oranla(ozelguckaynak.width()), ozelgucy + oranla(ozelguckaynak.height()));
        if (ozelguchedef.right < -oranla(50)) {
            if (ozelgucekrandavar) {
                ozelgucyokolmazamani = System.currentTimeMillis();
                ozelgucekrandavar = false;
            }

        }
        if (kediozelgucaldi && ozelgucalinmazamani + ozelgucsuresi < System.currentTimeMillis()) {
            kediozelgucaldi = false;
            kedigucrengi.setAlpha(255);
            alfaartismiktari = 20;
            if (root.sesefektiacik) {
                root.soundManager.play(ozelgucbitmesesi);
            }
        }
    }


    private void animasyonlariOynat() {
        kedikaresayaci++;
        if (kedikaresayaci >= kedikaresayaclimiti) {
            dusmananimasyonkareno++;
            if (dusmananimasyonkareno >= dusmananimasyonkareadedi[dusmancesidi][dusmananimasyoncesidi]) {
                dusmananimasyonkareno = 0;
            }
            if (kediyasiyor || !oyunbitti) {
                kedikareno = 1 - kedikareno;
                puan = (int)(System.currentTimeMillis() - baslamazamani);
            } else {
                kedikareno++;
                dikeyhiz = kedidikeyhiz;
                if (kedikareno >= 4) {
                    kedikareno = 4;
                    kedioldu = true;
                    oyunsonlandi = true;
                    toplamoyunsuresi = (int) (System.currentTimeMillis() - baslamazamani);
                    if ((long)toplananaltinlar + (toplamoyunsuresi) / 10 > root.kayit.getLong("eniyiskor", 0L))
                        root.kayit.putLong("eniyiskor", (long)toplananaltinlar + (toplamoyunsuresi) / 10);
                    if (toplananaltinlar + (toplamoyunsuresi) / 10 >= basariseviyesi3) {
                        oyunsonubasari = 3;


                    } else if (toplananaltinlar + (toplamoyunsuresi) / 10 >= basariseviyesi2) {
                        oyunsonubasari = 2;

                    } else if (toplananaltinlar + (toplamoyunsuresi) / 10 >= basariseviyesi1) {
                        oyunsonubasari = 1;
                    }
                    else {
                        oyunsonubasari = 0;
                    }

                }

            }
            ozelguckareno++;
            if (ozelguckareno >= ozelguckareadedi) {
                ozelguckareno = 0;
            }
            kedikaresayaci = 0;
        }

        altinkareno++;
        if (altinkareno >= altinkareadedi)

        {
            altinkareno = 0;
        }
        if (patlamaefektikareno < patlamakareadedi && patlamavar)

        {
            patlamaefektikareno++;
            if (patlamaefektikareno == patlamakareadedi) {
                patlamavar = false;
                patlamaefektikareno = -1;
            }
        }
        if (kediozelgucaldi)

        {
            if (ozelgucalinmazamani + 4000 < System.currentTimeMillis()) {
                alfaartismiktari = 80 * alfaartismiktari / Math.abs(alfaartismiktari);
            } else if (ozelgucalinmazamani + 2000 < System.currentTimeMillis()) {
                alfaartismiktari = 50 * alfaartismiktari / Math.abs(alfaartismiktari);
            }
            kedialfarenk += alfaartismiktari;
            if (kedialfarenk > 255) {
                kedialfarenk -= alfaartismiktari;
                alfaartismiktari = -alfaartismiktari;
            }
            if (kedialfarenk < 0) {
                kedialfarenk -= alfaartismiktari;
                alfaartismiktari = -alfaartismiktari;
            }
            kedigucrengi.setAlpha(kedialfarenk);
        }


    }

    private void cakismalariKontrolEt(){
        if(!kediozelgucaldi && kediyasiyor && dusmanyasiyor && kedihedef.intersect(dusmanhedef)){
            kediyasiyor = false;
            dusmanyasiyor = false;
            hizlanmaorani = 0;
            dusmanhiz = 0;
            patlamaefektikareno = -1;
            patlamavar = true;
            oyunbitti = true ;
            if (root.sesefektiacik) {
                root.soundManager.play(kaybetmesesi);
                root.soundManager.play(patlamasesi);
            }
            dusmaninyokolmazamani = System.currentTimeMillis();
            dusmangecikmezamani = rastgelesayiureticisi.nextInt(3000) + 2000;
        }
        if (kediyasiyor && ekrandaaltinvar && altinhedef.intersect(kedihedef)) {
            ekrandaaltinvar = false;
            altinkaybolmazamani = System.currentTimeMillis();
            toplananaltinlar += 500;
            if (root.sesefektiacik){
                root.soundManager.play(altinalmasesi);
            }
        }
        for (int i = mermiler.size() - 1; i >= 0; i--) {
            mermihedef.set(mermiler.elementAt(i)[MERMI_X], mermiler.elementAt(i)[MERMI_Y],
                    mermiler.elementAt(i)[MERMI_X] + oranla(mermiresmi[mermiler.elementAt(i)[MERMI_CESIDI]].getWidth()),
                    mermiler.elementAt(i)[MERMI_Y] + oranla(mermiresmi[mermiler.elementAt(i)[MERMI_CESIDI]].getHeight()));
            if (dusmanyasiyor && mermiler.elementAt(i)[MERMI_YON] == MERMIYON_SAG && mermihedef.intersect(dusmanhedef) ) {
                dusmanyasiyor = false;
                mermiler.removeElementAt(i);
                ekrandadusmanvar = false;
                patlamavar = true;
                toplananaltinlar += 100;
                if (root.sesefektiacik) {
                    root.soundManager.play(patlamasesi);
                }
                dusmaninyokolmazamani = System.currentTimeMillis();
                dusmangecikmezamani = rastgelesayiureticisi.nextInt(3000) + 2000;
            }
            else if (!kediozelgucaldi && kediyasiyor && mermiler.elementAt(i)[MERMI_YON] == MERMIYON_SOL && mermihedef.intersect(kedihedef)) {
                oyunbitti = true ;
                kediyasiyor = false;
                hizlanmaorani = 0;
                dusmanhiz = oranla(-60);
                mermiilerlemehizi = -dusmanhiz + oranla(30);
                mermiler.removeElementAt(i);
                if (root.sesefektiacik){
                    root.soundManager.play(kaybetmesesi);
                }
            }
        }
        if (ozelgucekrandavar && kediyasiyor && ozelguchedef.intersect(kedihedef)){
            kediozelgucaldi = true;
            ozelgucekrandavar = false;
            ozelgucyokolmazamani = System.currentTimeMillis();
            ozelgucalinmazamani = ozelgucyokolmazamani;
            if (root.sesefektiacik){
                root.soundManager.play(ozelgucalmasesi);
            }
        }


    }

    private void arkaplaniCiz (){
        arkaplankaynak.set((int)(arkaplankx/oranla(1.0f)), 0, arkaplan.getWidth(), arkaplan.getHeight());
        arkaplanhedef.set(0, 0, getWidth()- arkaplankx, getHeight() );
        grafikCizici.drawBitmap(arkaplan, arkaplankaynak, arkaplanhedef, null);

        arkaplankaynak.set(0, 0, (int)(arkaplankx/oranla(1.0f)), arkaplan.getHeight());
        arkaplanhedef.set(getWidth() - arkaplankx, 0, getWidth(),getHeight());
        grafikCizici.drawBitmap(arkaplan, arkaplankaynak, arkaplanhedef, null);
    }

    private void sahneyiCiz(){
        if (dusmanyasiyor) {
            grafikCizici.drawBitmap(dusman[dusmancesidi][dusmananimasyoncesidi][0], dusmankaynak, dusmanhedef, null);
        }
        kedikaynak.set(0, 0, kedi[kedikareno].getWidth(), kedi[kedikareno].getHeight());
        kedihedef.set(kedix, kediy, kedix + oranla(kedi[kedikareno].getWidth()), kediy + oranla(kedi[kedikareno].getHeight()));
        if (!kedioldu) {
            grafikCizici.drawBitmap(kedi[kedikareno], kedikaynak, kedihedef, kedigucrengi);
        }
        if (patlamavar) {
            patlamaefektihedef.set(
                    dusmanhedef.left + (dusmanhedef.width() - patlamagenislik) / 2,
                    dusmanhedef.top + (dusmanhedef.height() - patlamayukseklik) / 2,
                    dusmanhedef.right - (dusmanhedef.width() - patlamagenislik) / 2 ,
                    dusmanhedef.bottom - (dusmanhedef.height() - patlamayukseklik) / 2);
            grafikCizici.drawBitmap(patlamaefekti, patlamaefektlerikaynak[patlamaefektikareno], patlamaefektihedef, null);
        }
        if (ekrandaaltinvar) {
            grafikCizici.drawBitmap(altin[altinkareno], altinkaynak, altinhedef, null);
        }
        if (ozelgucekrandavar) {
            // TODO hata buradan kaynakli
            grafikCizici.drawBitmap(ozelguc[ozelguckareno], ozelguckaynak, ozelguchedef, null);
        }
        for (int[] mermi : mermiler){
            grafikCizici.drawBitmap(mermiresmi[mermi[MERMI_CESIDI]], mermi[MERMI_X], mermi[MERMI_Y], null);
        }

    }

    private void kullaniciArayuzunuCiz(){
        for(int i = 2; i < tusadedi; i++){
            grafikCizici.drawBitmap(tuslar[i], tuskaynagi, tushedefleri[i], null);
        }
        grafikCizici.drawBitmap(altinpanel, altinpanelkaynak, altinpanelhedef, null);
        grafikCizici.drawBitmap(puanpanel, puanpanelkaynak, puanpanelhedef, null);
        grafikCizici.drawText(String.valueOf( puan / 100),
                oranla(292), oranla(72), puangolgerengi);
        grafikCizici.drawText(String.valueOf( puan / 100),
                oranla(290), oranla(70), puanyazirengi);
        grafikCizici.drawText(String.valueOf(toplananaltinlar), getWidth() - oranla(123),
                oranla(75), puangolgerengi);
        grafikCizici.drawText(String.valueOf(toplananaltinlar), getWidth() - oranla(125),
                oranla(73), altinyazirengi);
        if (oyunduraklatildi){
            grafikCizici.drawBitmap(duraklatmamenusu, duraklatmamenukaynak,duraklatmamenuhedef,null);
        }
        if (oyunsonlandi){
            grafikCizici.drawBitmap(oyunsonupaneli[oyunsonubasari], oyunsonupanelkaynak, oyunsonupanelhedef,null);
            oyunsonuskorrengi.setTextAlign(Paint.Align.LEFT);
            oyunsonugolgerengi.setTextAlign(Paint.Align.CENTER);
            grafikCizici.drawText("Rekor : " + String.valueOf(root.kayit.getLong("eniyiskor", 0L)),
                    getWidthHalf() + 5, oyunsonupanelhedef.top + oranla(455), oyunsonugolgerengi);
            grafikCizici.drawText("Rekor : " + String.valueOf(root.kayit.getLong("eniyiskor", 0L)),
                    getWidthHalf(), oyunsonupanelhedef.top + oranla(450), eniyipuanrengi);
            oyunsonugolgerengi.setTextAlign(Paint.Align.LEFT);
            grafikCizici.drawText("Puan:", oyunsonupanelhedef.left + oranla(55),
                    oyunsonupanelhedef.top + oranla(575),
                    oyunsonugolgerengi);
            grafikCizici.drawText("Puan:", oyunsonupanelhedef.left + oranla(50),
                    oyunsonupanelhedef.top + oranla(570),
                    oyunsonuskorrengi);
            grafikCizici.drawText("Süre:", oyunsonupanelhedef.left + oranla(55),
                    oyunsonupanelhedef.top + oranla(695),
                    oyunsonugolgerengi);
            grafikCizici.drawText("Süre:", oyunsonupanelhedef.left + oranla(50),
                    oyunsonupanelhedef.top + oranla(690),
                    oyunsonuskorrengi);
            grafikCizici.drawText("TOPLAM:", oyunsonupanelhedef.left + oranla(55),
                    oyunsonupanelhedef.top + oranla(845),
                    oyunsonugolgerengi);
            grafikCizici.drawText("TOPLAM:", oyunsonupanelhedef.left + oranla(50),
                    oyunsonupanelhedef.top + oranla(840),
                    oyunsonuskorrengi);
            oyunsonuskorrengi.setTextAlign(Paint.Align.RIGHT);
            oyunsonugolgerengi.setTextAlign(Paint.Align.RIGHT);
            grafikCizici.drawRect(toplamcizgihedef.left + 5, toplamcizgihedef.top + 5,
                    toplamcizgihedef.right + 5, toplamcizgihedef.bottom + 5, oyunsonugolgerengi);
            grafikCizici.drawRect(toplamcizgihedef , oyunsonuskorrengi);
            grafikCizici.drawText(String.valueOf(toplananaltinlar),
                    oyunsonupanelhedef.right - oranla(45),
                    oyunsonupanelhedef.top + oranla(575),
                    oyunsonugolgerengi);
            grafikCizici.drawText(String.valueOf(toplananaltinlar),
                    oyunsonupanelhedef.right - oranla(50),
                    oyunsonupanelhedef.top + oranla(570),
                    oyunsonuskorrengi);
            grafikCizici.drawText(sureyihesapla(toplamoyunsuresi),
                    oyunsonupanelhedef.right - oranla(45) ,
                    oyunsonupanelhedef.top + oranla(695),
                    oyunsonugolgerengi);
            grafikCizici.drawText(sureyihesapla(toplamoyunsuresi),
                    oyunsonupanelhedef.right - oranla(50) ,
                    oyunsonupanelhedef.top + oranla(690),
                    oyunsonuskorrengi);
            grafikCizici.drawText(String.valueOf(toplananaltinlar + (toplamoyunsuresi) / 10) ,
                    oyunsonupanelhedef.right - oranla(45) ,
                    oyunsonupanelhedef.top + oranla(845) ,
                    oyunsonugolgerengi);
            grafikCizici.drawText(String.valueOf(toplananaltinlar + (toplamoyunsuresi) / 10) ,
                    oyunsonupanelhedef.right - oranla(50) ,
                    oyunsonupanelhedef.top + oranla(840) ,
                    oyunsonuskorrengi);
        }

    }


    private String sureyihesapla(int msaniye){
        String sonuc = "" ;
        msaniye /= 1000 ;
        sonuc += msaniye/60 ;
        sonuc += ":" ;
        if (msaniye % 60 <10){
            sonuc += "0" + msaniye % 60 ;
        }
        else {
            sonuc += msaniye % 60 ;
        }
        return sonuc ;
    }



    public void keyPressed(int key) {

    }

    public void keyReleased(int key) {

    }

    public boolean backPressed() {
        oyunduraklatildi = true ;
        return true;
    }

    public void surfaceChanged(int width, int height) {

    }

    public void surfaceCreated() {

    }

    public void surfaceDestroyed() {

    }

    public void touchDown(int x, int y) {
        if (!oyunduraklatildi){
            if(tushedefleri[TUS_YUKARI].contains(x, y)) {
                dikeyhiz = -kedidikeyhiz;
            } else if(tushedefleri[TUS_ASAGI].contains(x, y)) {
                dikeyhiz = kedidikeyhiz;
            } else if(tushedefleri[TUS_ATES].contains(x, y)) {
                mermieklenecek = true;
            }
        }
        else {
            if (baslamahedef.contains(x,y)){
                oyunduraklatildi = false ;
            }
            else if (yenidenbaslamahedef.contains(x,y)){
                root.canvasManager.setCurrentCanvas(new GameCanvas(root));
            }
            else if (menubuttonhedef.contains(x,y)){
                root.canvasManager.setCurrentCanvas(new MenuCanvas(root));
            }

        }
        if (oyunsonlandi){
            if (oyunsonuyenidenbaslamahedef.contains(x,y)){
                root.canvasManager.setCurrentCanvas(new GameCanvas(root));
            }
            else if (oyunsonumenubuttonhedef.contains(x,y)){
                root.canvasManager.setCurrentCanvas(new MenuCanvas(root));
            }
        }

    }

    private void mermileriYoket(){
        for (int i = mermiler.size() - 1; i >= 0; i--) {
            if (mermiler.elementAt(i)[MERMI_X] > getWidth() + oranla(100) || mermiler.elementAt(i)[MERMI_X] < -oranla(100)) {
                mermiler.removeElementAt(i);
            }
        }
    }

    private void mermiEkle(int yon){
        int[] mermi = new int[4];
        if (yon == MERMIYON_SAG){
            mermi[MERMI_X] = kedihedef.right -oranla(80);
            mermi[MERMI_Y] = kedihedef.top + oranla(40) + (kedihedef.height() -oranla( mermiresmi[MERMI_KEDI].getHeight())) / 2;
            mermi[MERMI_CESIDI] = MERMI_KEDI;
        }
        else if (yon == MERMIYON_SOL){
            mermi[MERMI_X] = dusmanx - oranla(mermiresmi[MERMI_UFO].getWidth() + 40);
            mermi[MERMI_Y] = dusmanhedef.bottom - oranla(mermiresmi[MERMI_UFO].getHeight() - 10);
            mermi[MERMI_CESIDI] = MERMI_UFO;
            if (dusmancesidi >= 3 && dusmancesidi <= 5) {
                mermi[MERMI_X] = dusmanx - oranla(mermiresmi[MERMI_SUIT].getWidth() + 45);
                mermi[MERMI_Y] = dusmanhedef.top + (dusmanhedef.height() -oranla( mermiresmi[MERMI_SUIT].getHeight())) / 2 + oranla(25);
                mermi[MERMI_CESIDI] = MERMI_SUIT;
            }
        }
        mermi[MERMI_YON] = yon;
        mermiler.add(mermi);
    }

    public void touchMove(int x, int y) {

    }

    public void touchUp(int x, int y) {
        dikeyhiz = 0;
    }


    public void pause() {

    }


    public void resume() {

    }


    public void reloadTextures() {

    }


    public void showNotify() {
    }

    public void hideNotify() {
    }

}