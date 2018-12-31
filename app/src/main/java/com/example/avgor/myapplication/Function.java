package com.example.avgor.myapplication;

import android.widget.EditText;

import com.jjoe64.graphview.series.DataPoint;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;

public class Function {

    Expression function;

    ArrayList<Coordinate> tabF = new ArrayList();


    Function(String f ){
        this.function =   new ExpressionBuilder(f)
                .variables("x")
                .build();
    }

    private  double getResultFunction(double x ){
          this.function.setVariable("x", x);
          return function.evaluate();
    }

     void tab(double x0, double xh, double xn ) {
         double x = x0;
         while (x < xn) {
             tabF.add(new Coordinate(x,getResultFunction(x)));
             x = x+ xh;
         }
     }

     DataPoint[] getPoints(){
         DataPoint[] fCoordinates = new DataPoint[this.tabF.size()];
         for (int i = 0; i < fCoordinates.length; i++) {
             double x = this.tabF.get(i).x;
             double y = this.tabF.get(i).y;
             fCoordinates[i] = new DataPoint(x,y);
         }
         return fCoordinates;
     }

     double findMax(){
          double max = this.tabF.get(0).y;
          for (int i = 1; i<tabF.size(); i++){
              if (this.tabF.get(i).y> max) {
                  max = tabF.get(i).y;
              }
          }
          return  max;
     }

    double findMin(){
        double min = this.tabF.get(0).y;
        for (int i = 1; i<tabF.size(); i++){
            if (this.tabF.get(i).y< min) {
                min = tabF.get(i).y;
            }
        }
        return  min;
    }

//    Функция для отрисовки графика, расстояние между максимальным и минимальным значением y
//     и областью отрисовки графика

    public double scaleY(){
      if ( findMax()>findMin()){
          return findMax()*0.1;
        }  else {
          return findMin()*0.1;
      }
    }
}
