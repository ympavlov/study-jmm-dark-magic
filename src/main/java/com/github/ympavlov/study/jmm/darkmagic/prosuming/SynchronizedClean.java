package com.github.ympavlov.study.jmm.darkmagic.prosuming;

public class SynchronizedClean implements Holder
{
	private State value;

	@Override
	public void set(State v)
	{
		synchronized (this) {
			value = v;
		}
	}

	@Override
	public synchronized State get()
	{
		State v;
		synchronized (this) {
			v = value;
		}
		return v;
	}
}
