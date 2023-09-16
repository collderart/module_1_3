package edu.crud;

import edu.crud.service.MainService;

public class Main {
    public static void main(String[] args) throws Exception {
        MainService.getInstance().run();
    }
}
