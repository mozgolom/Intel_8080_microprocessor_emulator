package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.Flags;

public class CMD_Intel8080_JM extends CMD_Intel8080_JMP {

    public CMD_Intel8080_JM() {
        super();
    }

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        if (executeListener.requestOnGetValueFromFlag(Flags.S) == 1) {
            super.execute(executeListener);
        }
    }

    @Override
    public String getName() {
        return "JM " + arg;
    }
}