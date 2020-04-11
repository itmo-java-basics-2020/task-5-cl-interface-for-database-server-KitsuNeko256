package ru.andrey.kvstorage.console.commands;

import ru.andrey.kvstorage.console.CommandParams;
import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class UpdateKeyCommand implements DatabaseCommand {

    private final ExecutionEnvironment environment;
    private final String databaseName;
    private final String tableName;
    private final String key;
    private final String value;
    private final StringBuilder message;

    public UpdateKeyCommand(ExecutionEnvironment environment, CommandParams params) {
        this.environment = environment;
        this.databaseName = params.getParam(0);
        this.tableName = params.getParam(1);
        this.key = params.getParam(2);
        this.value = params.getParam(3);

        this.message = new StringBuilder();
        this.message.append("Set key ").append(this.key)
                .append(" to value ").append(this.value)
                .append(" in database ").append(this.databaseName)
                .append(" in table ").append(this.tableName).append(" : ");
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> database = this.environment.getDatabase(this.databaseName);
        return database.map(this::tryUpdateKey)
                .orElse(DatabaseCommandResult.error(this.message.append("Failure! Database not found").toString()));
    }

    private DatabaseCommandResult tryUpdateKey(Database database) {
        try {
            database.write(this.tableName, this.key, this.value);
            return DatabaseCommandResult.success(this.message.append("Success").toString());
        } catch (DatabaseException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }
}