package kernel.cmd;

import kernel.IMicroprocessor;
import kernel.IMicroprocessorCommandsAdapter;

public class CMD_NULL implements ICommand {
    @Override
    public void execute(IMicroprocessorCommandsAdapter microprocessor) {

    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "(no command)";
    }
}