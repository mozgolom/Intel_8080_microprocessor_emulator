package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;

public class CMD_Intel8080_CALL implements ICommand {

    protected String arg;

    public CMD_Intel8080_CALL(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int callAddress = Integer.valueOf(arg, 16);
        int pushAddress = microprocessor.getValueByRegisterName("PC");
        CMD_Intel8080_PUSH.push(microprocessor, pushAddress);
        microprocessor.setValueByRegisterName("PC", callAddress);
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public String getName() {
        return "CALL " + arg;
    }
}