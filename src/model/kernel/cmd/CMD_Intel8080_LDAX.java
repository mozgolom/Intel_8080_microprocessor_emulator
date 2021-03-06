package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.RegisterPairs;
import model.kernel.Registers;

public class CMD_Intel8080_LDAX implements ICommand {

    private RegisterPairs registerPair;

    public CMD_Intel8080_LDAX(RegisterPairs registerPair) {
        this.registerPair = registerPair;
    }

    @Override
    public void setArgument(String arg) {}

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        int address = executeListener.requestOnGetValueFromRegisterPair(registerPair);
        int value = executeListener.requestOnGetValueFromMemoryByAddress(address);
        executeListener.requestOnSetValueInRegister(Registers.A, value);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "LDAX " + registerPair;
    }
}