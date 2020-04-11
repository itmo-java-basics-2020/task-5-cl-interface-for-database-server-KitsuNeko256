package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    static DatabaseCommandResult success(String message) {
        return success(message, null);
    }

    static DatabaseCommandResult success(String message, String value) {
        return new CommandResult(DatabaseCommandStatus.SUCCESS, message, value);
    }

    static DatabaseCommandResult error(String message) {
        return new CommandResult(DatabaseCommandStatus.FAILED, message, null);
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
        private final String message;
        private final String value;

        private CommandResult(DatabaseCommandStatus commandStatus, String message, String value) {
            this.commandStatus = commandStatus;
            this.message = message;
            this.value = value;
        }

        @Override
        public Optional<String> getResult() {
            if (!isSuccess() || value == null) {
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
            return message;
        }
    }
}