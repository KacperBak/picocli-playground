package de.kacperbak.picocli.playground;

import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command( name = "greet", description = "Greeting from picocli", version = "1.0")
public class Greeting implements Callable<Integer> {

    @Override
    public Integer call(){
        System.out.println("Hello picocli !!!");
        return 0;
    }
}
