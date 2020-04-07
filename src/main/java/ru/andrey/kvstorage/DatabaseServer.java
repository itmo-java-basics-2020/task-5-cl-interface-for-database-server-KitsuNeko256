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
            return DatabaseCommandResult.error("Invalid input");
        }

        DatabaseCommand command = parseCommand(commandText);
        if (command == null) {
            return DatabaseCommandResult.error("Invalid command name");
        }

        try {
            return command.execute();
        } catch (Exception error) {
            return DatabaseCommandResult.error(error.getMessage());
        }
    }

    private DatabaseCommand parseCommand(String commandText) {
        String[] terms = commandText.split(" ");
        CommandParams params = new CommandParams(Arrays.copyOfRange(terms, 1, terms.length));

        DatabaseCommands databaseCommands;
        try {
            databaseCommands = DatabaseCommands.valueOf(terms[0]);
        } catch (IllegalArgumentException error) {
            return null;
        }

        return databaseCommands.getCommand(env, params);
    }
}
