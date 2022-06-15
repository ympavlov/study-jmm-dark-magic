package com.github.ympavlov.study.jmm.darkmagic.prosuming;

public class PlainClean implements Holder
{
    private volatile State value;

    @Override
    public void set(State v)
    {
        value = v;
    }

    @Override
    public State get()
    {
        return value;
    }
}
