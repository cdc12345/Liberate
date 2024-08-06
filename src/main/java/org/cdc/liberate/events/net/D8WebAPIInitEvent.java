package org.cdc.liberate.events.net;

import lombok.Getter;
import net.mcreator.io.net.api.D8WebAPI;
import org.apache.logging.log4j.Logger;
import org.cdc.liberate.events.LiberateEvent;
@Getter
public class D8WebAPIInitEvent extends LiberateEvent {
    private final Logger logger;

    public D8WebAPIInitEvent(D8WebAPI parent, Logger logger){
        super(parent);
        this.logger = logger;
    }

    @Override
    public boolean canCancelable() {
        return true;
    }
}
