package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    static DatabaseCommandResult success(String value) {
        return new CommandResult(DatabaseCommandStatus.SUCCESS, value);
    }

    static DatabaseCommandResult error(String value) {
        return new CommandResult(DatabaseCommandStatus.FAILED, value);
    }

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    class CommandResult implements DatabaseCommandResult {

        private final DatabaseCommandStatus commandStatus;
        private final String value;

        private CommandResult(DatabaseCommandStatus commandStatus, String value) {
            this.commandStatus = commandStatus;
            this.value = value;
        }

        @Override
        public Optional<String> getResult() {
            if (!isSuccess()) {
                return Optional.empty();
            }
            return Optional.of(value);
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return commandStatus;
        }

        @Override
        public boolean isSuccess() {
            return commandStatus == DatabaseCommandStatus.SUCCESS;
        }

        @Override
        public String getErrorMessage() {
            if (isSuccess()) {
                return null;
            }
            return value;
        }
    }
}