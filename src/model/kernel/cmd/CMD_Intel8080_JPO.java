package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.Flags;

public class CMD_Intel8080_JPO extends CMD_Intel8080_JMP {

    public CMD_Intel8080_JPO() {
        super();
    }

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        if (executeListener.requestOnGetValueFromFlag(Flags.P) == 0) {
            super.execute(executeListener);
        }
    }

    @Override
    public String getName() {
        return "JPO " + arg;
    }
}