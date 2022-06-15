package com.github.ympavlov.study.jmm.darkmagic.prosuming;

public class PlainDirty implements Holder
{
	/* NO volatile ! */
	private State value;

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
