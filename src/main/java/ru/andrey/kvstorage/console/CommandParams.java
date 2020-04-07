package ru.andrey.kvstorage.console;


public class CommandParams {
    private final String[] params;

    public CommandParams(String... params) {
        this.params = params;
    }

    public String getParam(int index) {
        if (index >= params.length) {
            throw new IllegalArgumentException("Invalid command parameter!");
        }
        return params[index];
    }
}