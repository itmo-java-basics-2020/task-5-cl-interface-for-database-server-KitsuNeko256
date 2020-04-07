package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.console.commands.*;
public enum DatabaseCommands {
    CREATE_DATABASE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, CommandParams args) {
            return new CreateDatabaseCommand(environment, args);
        }
    }, CREATE_TABLE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, CommandParams args) {
            return new CreateTableCommand(environment, args);
        }
    }, UPDATE_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, CommandParams args) {
            return new UpdateKeyCommand(environment, args);
        }
    }, READ_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, CommandParams args) {
            return new ReadKeyCommand(environment, args);
        }
    };

    public abstract DatabaseCommand getCommand(ExecutionEnvironment environment, CommandParams args);
}