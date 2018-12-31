package com.example.avgor.myapplication;

import com.example.avgor.myapplication.Exceptions.WrongIntervalException;

public class ValidatorEditText {
    static public void gapValid(double x0, double xn ) throws  WrongIntervalException{
        if (x0>=xn) throw new WrongIntervalException();
    }
}
