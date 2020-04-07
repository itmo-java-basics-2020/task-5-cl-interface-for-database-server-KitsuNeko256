package ru.andrey.kvstorage.console.commands;

import ru.andrey.kvstorage.console.CommandParams;
import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;

public class CreateDatabaseCommand implements DatabaseCommand {

    private final ExecutionEnvironment environment;
    private final CommandParams param;

    public CreateDatabaseCommand(ExecutionEnvironment environment, CommandParams param) {
        this.environment = environment;
        this.param = param;
    }

    @Override
    public DatabaseCommandResult execute() {
        try {
            param.getParam(0);
        } catch (IllegalArgumentException error) {
            return DatabaseCommandResult.error(error.getMessage());
        }

        environment.addDatabase(null);
        return DatabaseCommandResult.success("");
    }
}
