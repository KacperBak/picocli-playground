package de.kacperbak.picocli.playground.commands;

import de.kacperbak.picocli.playground.service.HelloService;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.Optional;
import java.util.concurrent.Callable;

@Command( name = "hello", description = "Let's say something the UNIX way", mixinStandardHelpOptions = true, version = "1.0")
public class HelloCommand implements Callable<Integer> {

    static class ExclusiveOptions {
        @Option(names = {"-cw", "--cowSay"}, description = "Enable the Cow to say something")
        boolean cowSay;

        @Option(names = {"-tx", "--tuxSay"}, description = "Enable the Tux to say something")
        boolean tuxSay;
    }

    // note: using Optional<ExclusiveOptions> type here results in a reflection exc
    @ArgGroup(exclusive = true, multiplicity = "0..1")
    ExclusiveOptions eoo;

    @Parameters( index = "0", description = "The message to say", defaultValue = "<your message here>")
    String message;

    @Override
    public Integer call() {
        if (eoo == null){
            return HelloService.unixSay(message);
        }
        if (eoo.cowSay){
            return HelloService.cowSay(message);
        }
        if (eoo.tuxSay){
            return HelloService.tuxSay(message);
        }
        return 1;
    }
}
