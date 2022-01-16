package de.kacperbak.picocli.playground;

import de.kacperbak.picocli.playground.commands.AsciiCommand;
import de.kacperbak.picocli.playground.commands.LoremCommand;
import de.kacperbak.picocli.playground.service.IAsciiService;
import de.kacperbak.picocli.playground.service.ILoremService;
import de.kacperbak.picocli.playground.service.impl.AsciiService;
import de.kacperbak.picocli.playground.service.impl.LoremService;
import picocli.CommandLine;

/**
 * Create all app dependencies, in default case a ctor without args is called
 * Documentation: https://picocli.info/#_custom_factory
 * Example implementation: github/picocli/picocli-shell-jline3/src/main/java/picocli/shell/jline3/PicocliCommands.java
 */
public class AppFactory implements CommandLine.IFactory {

    private IAsciiService helloService;
    private ILoremService loremService;

    public AppFactory() {
        this.helloService = new AsciiService();
        this.loremService = new LoremService();
    }

    @Override
    public <K> K create(Class<K> cls) throws Exception {
        if (cls == AsciiCommand.class) {
            return (K) createHelloCommand();
        }
        if (cls == LoremCommand.class) {
            return (K) createLoremCommand();
        }
        return CommandLine.defaultFactory().create(cls);
    }

    private AsciiCommand createHelloCommand() {
        return new AsciiCommand(this.helloService);
    }

    private LoremCommand createLoremCommand() {
        return new LoremCommand(this.loremService);
    }
}
