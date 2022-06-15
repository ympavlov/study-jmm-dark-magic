package com.github.ympavlov.study.jmm.darkmagic.prosuming;

public class SynchronizedDirtyWrite implements Holder
{
	private State value;

	@Override
	public void set(State v)
	{
		value = v;
	}

	@Override
	public synchronized State get()
	{
		return value;
	}
}
