package com.example.avgor.myapplication;

import com.example.avgor.myapplication.Exceptions.WrongIntervalFindAnwerException;

@FunctionalInterface
public interface CheckWrongDecision <T> {
    void checkWrongDecision(T x, T y) throws WrongIntervalFindAnwerException;
}
