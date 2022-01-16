package de.kacperbak.picocli.playground.commands;

import de.kacperbak.picocli.playground.service.ILoremService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "lorem", description = "Show lorem ipsum content", mixinStandardHelpOptions = true, version = "1.0")
public class LoremCommand implements Callable<Integer> {

    private ILoremService service;

    public LoremCommand(ILoremService service) {
        this.service = service;
    }

    @Option(names = {"-n", "--number"}, description = "number of how many times the lorem ipsum paragraph is going to be repeated")
    int number;

    @Override
    public Integer call() throws Exception {
        var content = (number > 0) ? service.lorem(number) : service.lorem(1);
        System.out.println(content);
        return 0;
    }
}
