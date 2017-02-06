package com.tp1.inf8405.newapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnTouchListener{
    private Menu m = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imagegrille = (ImageView) findViewById(R.id.grid5x8);
        Bitmap bmp = BitmapFactory.decodeResource(this.getResources(),R.drawable.grid5x8);
        Bitmap tempBitmap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(tempBitmap);
        canvas.drawBitmap(bmp, 0, 0, null);

        float RayonCercle = tempBitmap.getHeight()/10 -10;  //Rayon d'un cercle
        float largeurCarre = (tempBitmap.getWidth()+7)/8;   //+7 et +4 c pour ajuster la largeur et la longeur d'un carré
        float longeurCarre = (tempBitmap.getHeight()+4)/5;
        int Cordx = tempBitmap.getWidth()/16;               //Coordonnées x et y du centre d'un carré
        int Cordy = tempBitmap.getHeight()/10;

        //Conteneur 2D des positions des cercles pour le niveau 1 du jeu
        Point PositionsNiv1[][]={
                {new Point(Cordx, Cordy),
                 new Point(Cordx + (int)(1*largeurCarre), Cordy),
                 new Point(Cordx + (int)(2*largeurCarre), Cordy),
                 new Point(Cordx + (int)(3*largeurCarre), Cordy),
                 new Point(Cordx + (int)(4*largeurCarre), Cordy),
                 new Point(Cordx + (int)(5*largeurCarre), Cordy),
                 new Point(Cordx + (int)(6*largeurCarre), Cordy),
                 new Point(Cordx + (int)(7*largeurCarre), Cordy)},

                {new Point(Cordx, Cordy + (int)longeurCarre),
                 new Point(Cordx + (int)(1*largeurCarre), Cordy + (int)longeurCarre),
                 new Point(Cordx + (int)(2*largeurCarre), Cordy + (int)longeurCarre),
                 new Point(Cordx + (int)(3*largeurCarre), Cordy + (int)longeurCarre),
                 new Point(Cordx + (int)(4*largeurCarre), Cordy + (int)longeurCarre),
                 new Point(Cordx + (int)(5*largeurCarre), Cordy + (int)longeurCarre),
                 new Point(Cordx + (int)(6*largeurCarre), Cordy + (int)longeurCarre),
                 new Point(Cordx + (int)(7*largeurCarre), Cordy + (int)longeurCarre) },

                {new Point(Cordx, Cordy + 2*(int)longeurCarre),
                new Point(Cordx + (int)(1*largeurCarre), Cordy + 2*(int)longeurCarre),
                new Point(Cordx + (int)(2*largeurCarre), Cordy + 2*(int)longeurCarre),
                new Point(Cordx + (int)(3*largeurCarre), Cordy + 2*(int)longeurCarre),
                new Point(Cordx + (int)(4*largeurCarre), Cordy + 2*(int)longeurCarre),
                new Point(Cordx + (int)(5*largeurCarre), Cordy + 2*(int)longeurCarre),
                new Point(Cordx + (int)(6*largeurCarre), Cordy + 2*(int)longeurCarre),
                new Point(Cordx + (int)(7*largeurCarre), Cordy + 2*(int)longeurCarre) },

                {new Point(Cordx, Cordy + 3*(int)longeurCarre),
                 new Point(Cordx + (int)(1*largeurCarre), Cordy + 3*(int)longeurCarre),
                 new Point(Cordx + (int)(2*largeurCarre), Cordy + 3*(int)longeurCarre),
                 new Point(Cordx + (int)(3*largeurCarre), Cordy + 3*(int)longeurCarre),
                 new Point(Cordx + (int)(4*largeurCarre), Cordy + 3*(int)longeurCarre),
                 new Point(Cordx + (int)(5*largeurCarre), Cordy + 3*(int)longeurCarre),
                 new Point(Cordx + (int)(6*largeurCarre), Cordy + 3*(int)longeurCarre),
                 new Point(Cordx + (int)(7*largeurCarre), Cordy + 3*(int)longeurCarre) },

                {       new Point(Cordx, Cordy + 4*(int)longeurCarre),
                        new Point(Cordx + (int)(1*largeurCarre), Cordy + 4*(int)longeurCarre),
                        new Point(Cordx + (int)(2*largeurCarre), Cordy + 4*(int)longeurCarre),
                        new Point(Cordx + (int)(3*largeurCarre), Cordy + 4*(int)longeurCarre),
                        new Point(Cordx + (int)(4*largeurCarre), Cordy + 4*(int)longeurCarre),
                        new Point(Cordx + (int)(5*largeurCarre), Cordy + 4*(int)longeurCarre),
                        new Point(Cordx + (int)(6*largeurCarre), Cordy + 4*(int)longeurCarre),
                        new Point(Cordx + (int)(7*largeurCarre), Cordy + 4*(int)longeurCarre) }

        };

        //Conteneur des couleurs des cercles Niveau 1
        int paintNiv1[][]= {
                {Color.YELLOW,Color.GREEN, Color.GREEN, Color.YELLOW, Color.GREEN,Color.YELLOW, 0xFF8B00FF,0xFF8B00FF},
                {Color.GREEN,0xFF8B00FF,0xFFFF7F00,Color.RED, Color.BLUE, Color.BLUE,0xFFFF7F00, Color.BLUE },
                {Color.BLUE,Color.RED,0xFFFF7F00,Color.RED,Color.BLUE,Color.GREEN,0xFFFF7F00,Color.RED},
                {0xFF8B00FF,0xFFFF7F00,0xFF8B00FF,Color.BLUE,0xFFFF7F00,Color.GREEN,Color.GREEN,0xFFFF7F00},
                {Color.YELLOW,Color.RED,Color.YELLOW,0xFFFF7F00,Color.BLUE,Color.RED,Color.GREEN,0xFF8B00FF}

                }; //0xFF8B00FF:purple, 0xFFFF7F00:orange


        Paint paint = new Paint();
        //On dessine les cercles sur le canvas        
        fonctions.dessinerCercles(PositionsNiv1,paintNiv1,paint,canvas,RayonCercle);
        imagegrille.setImageBitmap(tempBitmap);
    }

    @Override
    public boolean onTouch (View v,MotionEvent e) {
        int action = e.getAction();
        switch(action){
            case 1:
                Toast.makeText(this, "Action Down", Toast.LENGTH_SHORT).show();
            case 2 :
                Toast.makeText(this,"Action up",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        //R.menu.menu est l'id de notre menu
        inflater.inflate(R.layout.menu, menu);
        return true;
    }
}












