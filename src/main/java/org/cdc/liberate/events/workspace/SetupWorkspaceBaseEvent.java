package org.cdc.liberate.events.workspace;

import lombok.Getter;
import net.mcreator.workspace.Workspace;
import org.apache.logging.log4j.Logger;
import org.cdc.liberate.events.LiberateEvent;

@Getter
public class SetupWorkspaceBaseEvent extends LiberateEvent {

    private Logger LOG;
    private Workspace workspace;

    public SetupWorkspaceBaseEvent(Object parent, Workspace workspace, Logger LOG) {
        super(parent);
        this.LOG = LOG;
        this.workspace = workspace;
    }
}
