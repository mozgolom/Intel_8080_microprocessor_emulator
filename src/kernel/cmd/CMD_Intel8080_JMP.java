package kernel.cmd;

import kernel.IMicroprocessor;
import kernel.IMicroprocessorCommandsAdapter;

public class CMD_Intel8080_JMP implements ICommand {

    protected String arg;

    public CMD_Intel8080_JMP(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessorCommandsAdapter microprocessor) {
        int address = Integer.valueOf(arg, 16);
        microprocessor.setValueByRegisterName("PC", address);
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public String getName() {
        return "JMP " + arg;
    }
}