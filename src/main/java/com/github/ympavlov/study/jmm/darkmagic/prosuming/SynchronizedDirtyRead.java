package com.github.ympavlov.study.jmm.darkmagic.prosuming;

public class SynchronizedDirtyRead implements Holder
{
	private State value;

	@Override
	public synchronized void set(State v)
	{
		value = v;
	}

	@Override
	public State get()
	{
		return value;
	}
}
