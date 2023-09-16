package edu.crud.controller;

import edu.crud.repository.LabelRepository;

import java.util.Scanner;

public class LabelControllerImpl implements CommonController {
    private final LabelRepository labelRepository;
    private final Scanner scanner;

    public LabelControllerImpl(Scanner scanner, LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
        this.scanner = scanner;
    }
}
