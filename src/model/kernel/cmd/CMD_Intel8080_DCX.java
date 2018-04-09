package model.kernel.cmd;

import model.kernel.*;

public class CMD_Intel8080_DCX implements ICommand {

    private RegisterPairs registerPair;

    public CMD_Intel8080_DCX(RegisterPairs registerPair) {
        this.registerPair = registerPair;
    }

    @Override
    public void execute(ICommandExecuteEventsListener executeListener) {
        int value;
        if (registerPair != null) {
            value = executeListener.requestOnGetValueFromRegisterPair(registerPair);
        } else {
            value = executeListener.requestOnGetValueFromRegister(Registers.SP);
        }

        value -= 1;

        if (value < 0) {
            executeListener.requestOnSetValueInFlag(Flags.C, 1);
        } else {
            executeListener.requestOnSetValueInFlag(Flags.C, 0);
        }

        value = _DByte.getRoundedValue(value);

        if (!registerPair.equals("SP")) {
            executeListener.requestOnSetValueInRegisterPair(registerPair, value);
        } else {
            executeListener.requestOnSetValueInRegister(Registers.SP, value);
        }
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "DCX " + registerPair;
    }
}