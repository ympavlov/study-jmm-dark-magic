package com.github.ympavlov.study.jmm.darkmagic.prosuming;

public class SynchronizedClean implements Holder
{
	private State value;

	@Override
	public synchronized void set(State v)
	{
	    value = v;
	}

	@Override
	public synchronized State get()
	{
		State v;
		v = value;
		return v;
	}
}
