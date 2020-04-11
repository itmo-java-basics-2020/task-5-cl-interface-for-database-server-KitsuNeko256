package ru.andrey.kvstorage.console.commands;

import ru.andrey.kvstorage.console.CommandParams;
import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;

public class CreateDatabaseCommand implements DatabaseCommand {

    private final ExecutionEnvironment environment;
    private final String databaseName;
    private final StringBuilder message;

    public CreateDatabaseCommand(ExecutionEnvironment environment, CommandParams params) {
        this.environment = environment;
        this.databaseName = params.getParam(0);

        this.message = new StringBuilder();
        this.message.append("Create database ").append(this.databaseName).append(" : ");
    }

    @Override
    public DatabaseCommandResult execute() {
        this.environment.addDatabase(null);
        return DatabaseCommandResult.success(this.message.append("Success").toString());
    }
}
