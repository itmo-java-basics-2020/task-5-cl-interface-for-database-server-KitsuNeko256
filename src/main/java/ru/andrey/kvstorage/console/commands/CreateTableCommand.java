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
    private final CommandParams params;

    public CreateTableCommand(ExecutionEnvironment environment, CommandParams params) {
        this.environment = environment;
        this.params = params;
    }

    @Override
    public DatabaseCommandResult execute() {
        try {
            params.getParam(1);
        } catch (IllegalArgumentException error) {
            return DatabaseCommandResult.error(error.getMessage());
        }

        Optional<Database> database = environment.getDatabase(params.getParam(0));
        return database.map(this::tryCreateTable).orElse(DatabaseCommandResult.error("Failed to create a table!"));
    }

    private DatabaseCommandResult tryCreateTable(Database database) {
        try {
            database.createTableIfNotExists(params.getParam(1));
            return DatabaseCommandResult.success("");
        } catch (DatabaseException databaseException) {
            return DatabaseCommandResult.error(databaseException.getMessage());
        }
    }
}