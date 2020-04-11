package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.console.commands.*;

public enum DatabaseCommands {
    CREATE_DATABASE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, CommandParams params) {
            return new CreateDatabaseCommand(environment, params);
        }
    },
    CREATE_TABLE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, CommandParams params) {
            return new CreateTableCommand(environment, params);
        }
    },
    UPDATE_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, CommandParams params) {
            return new UpdateKeyCommand(environment, params);
        }
    },
    READ_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, CommandParams params) {
            return new ReadKeyCommand(environment, params);
        }
    };

    public abstract DatabaseCommand getCommand(ExecutionEnvironment environment, CommandParams params);
}