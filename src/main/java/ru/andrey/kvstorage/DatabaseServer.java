package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.*;

import java.util.Arrays;

public class DatabaseServer {

    private final ExecutionEnvironment env;

    public DatabaseServer(ExecutionEnvironment env) {
        this.env = env;
    }

    DatabaseCommandResult executeNextCommand(String commandText) {
        if (commandText == null || commandText.isEmpty()) {
            return DatabaseCommandResult.error("Input command is empty");
        }

        try {
            DatabaseCommand command = parseCommand(commandText);
            return command.execute();
        } catch (Exception e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }

    private DatabaseCommand parseCommand(String commandText) {
        String[] terms = commandText.split(" ");
        CommandParams params = new CommandParams(Arrays.copyOfRange(terms, 1, terms.length));

        DatabaseCommands databaseCommands;
        try {
            databaseCommands = DatabaseCommands.valueOf(terms[0]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Requested command " + commandText + "  not found");
        }

        return databaseCommands.getCommand(env, params);
    }
}
