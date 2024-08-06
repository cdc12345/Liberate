package org.cdc.liberate;

import org.cdc.liberate.transfer.AgentClassTransfer;

import java.lang.instrument.Instrumentation;

public class AgentMain {
    public static void premain(String args, Instrumentation instrumentation){
        Liberate liberate = Liberate.getInstance();
        liberate.setAgentMod(true);

        if ("safeMode".equalsIgnoreCase(args)){
            Liberate.getInstance().setSafeMod(true);
            return;
        }

        instrumentation.addTransformer(new AgentClassTransfer());
    }
}
