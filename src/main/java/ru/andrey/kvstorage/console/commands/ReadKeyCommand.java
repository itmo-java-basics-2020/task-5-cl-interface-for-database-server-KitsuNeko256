package ru.andrey.kvstorage.console.commands;

import ru.andrey.kvstorage.console.CommandParams;
import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class ReadKeyCommand implements DatabaseCommand {

    private final ExecutionEnvironment environment;
    private final String databaseName;
    private final String tableName;
    private final String key;
    private final StringBuilder message;

    public ReadKeyCommand(ExecutionEnvironment environment, CommandParams params) {
        this.environment = environment;
        this.databaseName = params.getParam(0);
        this.tableName = params.getParam(1);
        this.key = params.getParam(2);

        this.message = new StringBuilder();
        this.message.append("Read key ").append(this.key)
                .append(" in database ").append(this.databaseName)
                .append(" in table ").append(this.tableName).append(" : ");
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> database = this.environment.getDatabase(this.databaseName);
        return database.map(this::tryReadKey)
                .orElse(DatabaseCommandResult.error(this.message.append("Failure! Database not found").toString()));
    }

    private DatabaseCommandResult tryReadKey(Database database) {
        try {
            String value = database.read(this.tableName, this.key);
            return DatabaseCommandResult.success(this.message.append("Success").toString(), value);
        } catch (DatabaseException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }
}