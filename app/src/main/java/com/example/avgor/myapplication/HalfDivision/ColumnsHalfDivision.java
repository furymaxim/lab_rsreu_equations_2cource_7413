package com.example.avgor.myapplication.HalfDivision;

public class ColumnsHalfDivision {
     public double ab2, fab2, a, b, fa,fb, aMinusB;

    ColumnsHalfDivision(double ab2, double fab2, double a, double b, double fa, double aMinusB){
        this.ab2 = ab2;
        this.fab2 = fab2;
        this.a = a;
        this.b = b;
        this.fa = fa;
        this.aMinusB = aMinusB;
    }

    ColumnsHalfDivision(double a, double b){
        this.ab2 = 0;
        this.fab2 =0;
        this.a = a;
        this.b = b;
        this.fa = 0;
        this.aMinusB = 0;
    }


    ColumnsHalfDivision(){}
    
    ColumnsHalfDivision(ColumnsHalfDivision col){
        this.ab2 = col.ab2;
        this.fab2 = col.fab2;
        this.a = col.a;
        this.b = col.b;
        this.fa = col.fa;
        this.fb = col.fb;
        this.aMinusB = col.aMinusB;
    }

    void setParameters(ColumnsHalfDivision col){
        this.ab2 = col.ab2;
        this.fab2 = col.fab2;
        this.a = col.a;
        this.b = col.b;
        this.fa = col.fa;
        this.fb = col.fb;
        this.aMinusB = col.aMinusB;
    }
}
