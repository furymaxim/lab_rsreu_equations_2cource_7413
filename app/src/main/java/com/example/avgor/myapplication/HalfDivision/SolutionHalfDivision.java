package com.example.avgor.myapplication.HalfDivision;

import com.example.avgor.myapplication.CheckWrongDecision;
import com.example.avgor.myapplication.Exceptions.WrongIntervalFindAnwerException;

import net.objecthunter.exp4j.Expression;

import java.util.ArrayList;

import static java.lang.StrictMath.abs;

public class SolutionHalfDivision {
    Expression function;
    double x0, xn;
    double error;
    public ArrayList<ColumnsHalfDivision> table;

//    Конструктор класса
    public SolutionHalfDivision(Expression function, double x0, double xn, double error ) throws WrongIntervalFindAnwerException{
        this.function = function;
        this.error = error;
        this.table= new ArrayList<>();
        this.table.add(new ColumnsHalfDivision(x0, xn));
        this.processFindAnswer();
    }

    private  double getResultFunction(double x ){
        this.function.setVariable("x", x);
        return function.evaluate();
    }

    private  void processFindAnswer()throws WrongIntervalFindAnwerException{
        int i = 0;
        double testError = Math.abs(this.table.get(i).a-this.table.get(i).b);
        while (testError>this.error){
            ColumnsHalfDivision col = new ColumnsHalfDivision(this.table.get(i));
            col.ab2 = (col.a+col.b)/2;
            col.fa = getResultFunction(col.a);
            col.fb = getResultFunction(col.b);
            col.fab2 = getResultFunction(col.ab2);
            if (i>1) check.checkWrongDecision(col.fb, col.fa );
            col.aMinusB = Math.abs(col.a-col.b);
            testError = col.aMinusB;
            this.table.set(i, new ColumnsHalfDivision(col));
            if (col.fab2 == 0){ break; }
            if (col.fa == 0 ){ col.fab2 = col.fa; this.table.set(i, new ColumnsHalfDivision(col)); break;}
            if (col.fb == 0 ){ col.fab2 = col.fb; this.table.set(i, new ColumnsHalfDivision(col)); break;}
            if (col.fa*col.fab2 > 0){col.a = col.ab2;} else {col.b = col.ab2;}
            if (Math.abs(this.table.get(i).a-this.table.get(i).b)>this.error){this.table.add(new ColumnsHalfDivision(col.a, col.b));}
            i++;

        }
    }


    public double getAnswer(){
        return table.get(table.size()-1).ab2;
    }


    CheckWrongDecision <Double> check  = (Double x,  Double y)  -> {
        if ((x*y)>0) throw new WrongIntervalFindAnwerException();
    };




}
