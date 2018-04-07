package emulator;

import kernel.*;
import kernel.cmd.ICommand;
import translator.CommandsBuilder;
import translator.CommandsCodes;
import translator.ITranslator;
import translator.Intel8080Translator;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class EmulatorIntel8080 implements IEmulator {

    private IMicroprocessorEmulatorAdapter microprocessor;
    private IMicroprocessorPresenterAdapter microprocessorPresenterAdapter;
    private ITranslator translator;

    private ArrayList<Integer> breakpoints;

    public EmulatorIntel8080(IInputOutputSystem ioSystem) {
        IMicroprocessor mp = new Intel8080(new Memory(_DByte.MAX_VALUE));
        this.microprocessor = new MicroprocessorEmulatorAdapter(mp);
        this.microprocessor.setIOSystem(ioSystem);
        this.microprocessorPresenterAdapter = new MicroprocessorPresenterAdapter(mp);
        this.translator = new Intel8080Translator();
        this.breakpoints = new ArrayList<>();
    }

    @Override
    public void run() {
        while (step() && !breakpoints.contains(microprocessor.getValueByRegisterName("PC"))) {}
    }

    @Override
    public boolean step() {
        int address = microprocessor.getValueByRegisterName("PC");
        ICommand command = CommandsBuilder.getCommand(microprocessor.getMemory(), address);
        if (!command.getName().equals("HLT")) {
            microprocessor.executeCommand(command);
            return true;
        }
        return false;
    }

    @Override
    public void translation(String program) {
        IMemory memory = microprocessor.getMemory();
        String[] lexemes = translator.getLexemes(program);
        if (lexemes != null) {
            for (String lex : lexemes) {
                int[] code = translator.getCode(lex);
                int address = code[0];
                if (code[2] != CommandsCodes.SET) {
                    memory.setValueByIndex(address, code[2]);
                    if (code[3] >= 0) {
                        if (code[1] == 2) {
                            memory.setValueByIndex(address + 1, code[3]);
                        } else {
                            memory.setValueByIndex(address + 1, code[3] / 256);
                            memory.setValueByIndex(address + 2, code[3] % 256);
                        }
                    }
                } else {
                    memory.setValueByIndex(address, code[3]);
                }
            }
        }
    }

    @Override
    public String getTranslationResult() {
        return new Date().toString()
                + System.lineSeparator()
                + translator.getStatusString();
    }

    @Override
    public String[] getCommandsList() {
        ArrayList<String> commands = new ArrayList<>();
        int address = 0;
        while (address < 65536) {
            ICommand command
                    = CommandsBuilder.getCommand(microprocessor.getMemory(), address);
            commands.add(command.getName());
            for (int i = 1; i < command.getSize(); ++i) {
                address += 1;
                commands.add("***");
            }
            address += 1;
        }
        String[] output = new String[commands.size()];
        for (int i = 0; i < output.length; ++i) {
            output[i] = commands.get(i);
        }
        return output;
    }

    @Override
    public IMicroprocessorPresenterAdapter getMicroprocessor() {
        return microprocessorPresenterAdapter;
    }

    @Override
    public void resetRegisters() {
        microprocessor.resetRegisters();
    }

    @Override
    public void resetMemory() {
        microprocessor.resetMemory();
    }

    @Override
    public void setProgramCounter(int address) {
        microprocessor.setValueByRegisterName("PC", address);
    }

    @Override
    public void setBreakpoint(int address) {
        if (!breakpoints.contains(address)) {
            breakpoints.add(address);
        } else {
            breakpoints.remove((Integer) address);
        }
    }

    @Override
    public ArrayList<Integer> getBreakpoints() {
        return breakpoints;
    }

    @Override
    public void removeAllBreakpoints() {
        breakpoints.clear();
    }

    @Override
    public String loadProgramFromFile(String path) throws IOException {
        BufferedReader bufferedReader
                = new BufferedReader(new FileReader(new File(path)));
        StringBuilder program = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            program.append(line).append(System.lineSeparator());
        }
        return program.toString();
    }

    @Override
    public void saveProgramInFile(String path, String programText) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(programText);
        fileWriter.close();
    }
}