package model.kernel;

import model.emulator.IIntraProgramIOEventsListener;

/**
 * Класс отслеживающий действия исполняемой команды и взаимодействующий с микропроцессором.
 * @author Maxim Rozhkov
 */
public class ExecutableCommandEventsListener implements IExecutableCommandEventsListener {

    private IMicroprocessor microprocessor;

    ExecutableCommandEventsListener(IMicroprocessor microprocessor) {
        this.microprocessor = microprocessor;
    }

    @Override
    public int requestOnGetValueFromRegister(Registers register) {
        return microprocessor.getValueFromRegister(register);
    }

    @Override
    public void requestOnSetValueInRegister(Registers register, int value) {
        microprocessor.setValueInRegister(register, value);
    }

    @Override
    public int requestOnGetValueFromFlag(Flags flag) {
        return microprocessor.getValueFromFlag(flag);
    }

    @Override
    public void requestOnSetValueInFlag(Flags flag, int value) {
        microprocessor.setValueInFlag(flag, value);
    }

    @Override
    public int requestOnGetValueFromRegisterPair(RegisterPairs registerPair) {
        return microprocessor.getValueFromRegisterPair(registerPair);
    }

    @Override
    public void requestOnSetValueInRegisterPair(RegisterPairs registerPair, int value) {
        microprocessor.setValueInRegisterPair(registerPair, value);
    }

    @Override
    public int requestOnGerValueFromFlagsRegister() {
        return microprocessor.getAllFlags();
    }

    @Override
    public void requestOnSetValueInFlagsRegister(int flags) {
        microprocessor.setAllFlags(flags);
    }

    @Override
    public int requestOnGetValueFromMemoryByAddress(int address) {
        return microprocessor.getValueFromMemoryByAddress(address);
    }

    @Override
    public void requestOnSetValueInMemoryByAddress(int address, int value) {
        microprocessor.setValueInMemoryByAddress(address, value);
    }

    @Override
    public IIntraProgramIOEventsListener requestOnGetInputOutputActionListener() {
        return microprocessor.getIntraProgramIOEventsListener();
    }

    @Override
    public void requestOnCheckByteForSetFlags(int value) {
        microprocessor.checkValueForSetFlags(value);
    }

    @Override
    public void returnFromInterrupt() {
        microprocessor.returnFromInterrupt();
    }
}