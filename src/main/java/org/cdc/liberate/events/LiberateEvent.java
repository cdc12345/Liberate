package org.cdc.liberate.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.mcreator.plugin.MCREvent;
import org.cdc.liberate.utils.PluginLoaderUtils;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class LiberateEvent extends MCREvent {

    public static<T extends MCREvent> MCREvent eventSync(T event){
        PluginLoaderUtils.getJavaPlugins().forEach(javaPlugin -> PluginLoaderUtils.getListeners(javaPlugin).get(event.getClass())
                .forEach(listener -> listener.eventTriggered(event)));
        return event;
    }

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
