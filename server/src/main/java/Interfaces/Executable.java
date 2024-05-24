package Interfaces;

import data.src.exceptions.CommandDoesntExists;

import java.io.IOException;

public interface Executable {

    String ExecuteCommand(String... args) throws IOException, CommandDoesntExists;
}
