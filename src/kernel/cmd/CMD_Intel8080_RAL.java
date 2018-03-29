package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_RAL implements ICommand {
    @Override
    public void execute(IMicroprocessor microprocessor) {
        int value = microprocessor.getValueByRegisterName("A");
        value = value << 1;
        if (microprocessor.getValueByFlagName("C") == 1) {
            value += 1;
        }
        if (value > 255) {
            microprocessor.setValueByFlagName("C", 1);
        } else {
            microprocessor.setValueByFlagName("C", 0);
        }
        value = microprocessor.getRoundedByte(value);
        microprocessor.setValueByRegisterName("A", value);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "RAL";
    }
}