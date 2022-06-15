package com.github.ympavlov.study.jmm.darkmagic.prosuming.stresstest;

import com.github.ympavlov.study.jmm.darkmagic.prosuming.*;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.I_Result;

abstract public class ProsumingTest<T extends Holder>
{
    /*
     * COMMON TEST LOGIC
     */

    private T holder;

    private ProsumingTest(Class<T> holderClass)
    {
        try {
            holder = holderClass.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        holder.set(new Holder.State(-1));
    }

    protected void setAction()
    {
        holder.set(new Holder.State(2));
    }

    protected int getAction()
    {
        Holder.State s = holder.get();
        return (s != null) ? s.get() : -1;
    }

    @Outcome(id = {"-4", "8"}, expect = Expect.ACCEPTABLE, desc = "Boring")
    //@Outcome(id = {"42", "456"}, expect = Expect.ACCEPTABLE, desc = "Boring")
    @Outcome(expect = Expect.FORBIDDEN, desc = "Forbidden")
    private abstract static class ProsumingTestCorrect<E extends Holder> extends ProsumingTest<E> {
        private ProsumingTestCorrect(Class<E> holderClass)
        {
            super(holderClass);
        }
    }

    @Outcome(id = {"-4", "8"}, expect = Expect.ACCEPTABLE, desc = "Boring")
    //@Outcome(id = {"42", "456"}, expect = Expect.ACCEPTABLE, desc = "Boring")
    @Outcome(expect = Expect.ACCEPTABLE_INTERESTING, desc = "RISCy")
    private abstract static class ProsumingTestRiscy<E extends Holder> extends ProsumingTest<E> {
        private ProsumingTestRiscy(Class<E> holderClass)
        {
            super(holderClass);
        }
    }

    /*
     * TEST CASES
     */

    /* inspired by org.openjdk.jcstress.samples.jmm.advanced.AdvancedJMM_07_SemiVolatile,
     *  org.openjdk.jcstress.samples.jmm.advanced.AdvancedJMM_05_MisplacedVolatile */

    @JCStressTest
    @State
    public static class PlainVolatileCleanTest extends ProsumingTestCorrect<PlainClean>
    {
        public PlainVolatileCleanTest()
        {
            super(PlainClean.class);
        }

        @Actor
        public void actor()
        {
            setAction();
        }

        @Actor
        public void observer(I_Result r)
        {
            r.r1 = getAction();
        }
    }

    @JCStressTest
    @State
    public static class PlainDirtyTest extends ProsumingTestRiscy<PlainDirty>
    {
        public PlainDirtyTest()
        {
            super(PlainDirty.class);
        }

        @Actor
        public void actor()
        {
            setAction();
        }

        @Actor
        public void observer(I_Result r)
        {
            r.r1 = getAction();
        }
    }

    /* inspired by org.openjdk.jcstress.samples.jmm.advanced.AdvancedJMM_06_SemiSynchronized */

    @JCStressTest
    @State
    public static class SynchronizedCleanTest extends ProsumingTestCorrect<SynchronizedClean>
    {
        public SynchronizedCleanTest()
        {
            super(SynchronizedClean.class);
        }

        @Actor
        public void actor()
        {
            setAction();
        }

        @Actor
        public void observer(I_Result r)
        {
            r.r1 = getAction();
        }
    }


    @JCStressTest
    @State
    public static class SynchronizedDirtyReadTest extends ProsumingTestRiscy<SynchronizedDirtyRead>
    {
        public SynchronizedDirtyReadTest()
        {
            super(SynchronizedDirtyRead.class);
        }

        @Actor
        public void actor()
        {
            setAction();
        }

        @Actor
        public void observer(I_Result r)
        {
            r.r1 = getAction();
        }
    }

    @JCStressTest
    @State
    public static class SynchronizedDirtyWriteTest extends ProsumingTestRiscy<SynchronizedDirtyWrite>
    {
        public SynchronizedDirtyWriteTest()
        {
            super(SynchronizedDirtyWrite.class);
        }

        @Actor
        public void actor()
        {
            setAction();
        }

        @Actor
        public void observer(I_Result r)
        {
            r.r1 = getAction();
        }
    }
}
