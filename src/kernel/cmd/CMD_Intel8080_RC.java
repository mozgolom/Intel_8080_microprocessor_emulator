package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_RC extends CMD_Intel8080_RET {
    @Override
    public void execute(IMicroprocessor microprocessor) {
        if (microprocessor.getValueByFlagName("C") == 1) {
            super.execute(microprocessor);
        }
    }

    @Override
    public String getName() {
        return "RC";
    }
}