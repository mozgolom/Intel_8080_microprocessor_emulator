package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.Registers;
import model.kernel._Byte;

public class CMD_Intel8080_ADI implements ICommand {

    private String arg;

    public CMD_Intel8080_ADI() {
        this.arg = "0x00";
    }

    @Override
    public void setArgument(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        int firstValue = Integer.valueOf(arg, 16);
        int secondValue = executeListener.requestOnGetValueFromRegister(Registers.A);
        firstValue += secondValue;
        executeListener.requestOnCheckByteForSetFlags(firstValue);
        firstValue = _Byte.getRoundedValue(firstValue);
        executeListener.requestOnSetValueInRegister(Registers.A, firstValue);
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public String getName() {
        return "ADI " + arg;
    }
}