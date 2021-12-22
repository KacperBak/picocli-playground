package de.kacperbak.picocli.playground.commands;

import de.kacperbak.picocli.playground.service.HelloService;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command( name = "hello", description = "Let's say something the UNIX way", mixinStandardHelpOptions = true, version = "1.0")
public class HelloCommand implements Callable<Integer> {

    static class ExclusiveSayOptions {
        @Option(names = {"-cw", "--cowSay"}, description = "Enable the Cow to say something")
        boolean cowSay;

        @Option(names = {"-tx", "--tuxSay"}, description = "Enable the Tux to say something")
        boolean tuxSay;
    }

    // Note: using Optional<ExclusiveSayOptions> type here results in a reflection exc
    @ArgGroup(exclusive = true, multiplicity = "0..1")
    ExclusiveSayOptions exclusiveSayOption;

    @Parameters( index = "0", description = "The message to say", defaultValue = "<your message here>")
    String message;

    @Override
    public Integer call() {
        if (exclusiveSayOption == null){
            return HelloService.unixSay(message);
        }
        if (exclusiveSayOption.cowSay){
            return HelloService.cowSay(message);
        }
        if (exclusiveSayOption.tuxSay){
            return HelloService.tuxSay(message);
        }
        return 1;
    }
}
