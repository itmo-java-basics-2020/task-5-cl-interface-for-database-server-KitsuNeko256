package ru.andrey.kvstorage.console.commands;

import ru.andrey.kvstorage.console.CommandParams;
import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class CreateTableCommand implements DatabaseCommand {

    private final ExecutionEnvironment environment;
    private final String databaseName;
    private final String tableName;
    private final StringBuilder message;

    public CreateTableCommand(ExecutionEnvironment environment, CommandParams params) {
        this.environment = environment;
        this.databaseName = params.getParam(0);
        this.tableName = params.getParam(1);

        this.message = new StringBuilder();
        this.message.append("Create table ").append(this.tableName)
                .append(" in database ").append(this.databaseName).append(" : ");
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> database = this.environment.getDatabase(this.databaseName);
        return database.map(this::tryCreateTable)
                .orElse(DatabaseCommandResult.error(this.message.append("Failure! Database not found").toString()));
    }

    private DatabaseCommandResult tryCreateTable(Database database) {
        try {
            database.createTableIfNotExists(this.tableName);
            return DatabaseCommandResult.success(this.message.append("Success").toString());
        } catch (DatabaseException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }
}