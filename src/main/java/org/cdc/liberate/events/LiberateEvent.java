package org.cdc.liberate.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.mcreator.plugin.MCREvent;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class LiberateEvent extends MCREvent {

    private Object parent;
    private boolean canceled;
    private Object value;

    public LiberateEvent(Object parent){
        this.parent = parent;
    }

    public boolean canCancelable() {
        return false;
    }

    public boolean canSetValue(){
        return false;
    }

}
