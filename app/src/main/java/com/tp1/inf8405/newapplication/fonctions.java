package com.tp1.inf8405.newapplication;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;

/**
 * Created by Jean on 02/02/2017.
 */

public class fonctions {

    public void deplacer(int posX,int posY) {

        //Vérifier que on déplace pas de plus d'une position

        //Déplacer les deux
    }

    /* methode permettant de dessiner les cercles sur la grille*/
    public static void dessinerCercles (Point PositionsNiv [][], int paintNiv[][], Paint paint,Canvas canvas,float RayonCercle){
        for (int i=0;i<PositionsNiv.length;i++)
            for (int j=0;j<PositionsNiv[0].length;j++){
                paint.setColor(paintNiv[i][j]);
                canvas.drawCircle(PositionsNiv[i][j].x, PositionsNiv[i][j].y, RayonCercle, paint);
            }
    }

}
