package edu.crud.view;

import edu.crud.ex.InvalidParamException;

import java.util.Scanner;

public interface CommonView<T> {
    void printMenu(Scanner scanner) throws InvalidParamException;
}
