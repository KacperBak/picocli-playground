/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package de.kacperbak.picocli.playground;

import de.kacperbak.picocli.playground.commands.AsciiCommand;
import de.kacperbak.picocli.playground.service.impl.AsciiService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * More details on testing picocli:
 * https://picocli.info/#_testing_the_output
 */
class AsciiCommandTest {

    final PrintStream originalOut = System.out;
    final PrintStream originalErr = System.err;
    final ByteArrayOutputStream out = new ByteArrayOutputStream();
    final ByteArrayOutputStream err = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        out.reset();
        err.reset();
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }


    /**
     * Calling: ./app/bin/app
     * Results in:
     * -------------------
     * <your message here>
     * -------------------
     *   .
     *    .
     *     .
     *     __  __   __  __   ______  __   __
     *    /\ \/\ \ /\ \/\ \ /\__  _\/\ \ /\ \
     *    \ \ \ \ \\ \ `\\ \\/_/\ \/\ `\`\/'/'
     *     \ \ \ \ \\ \ , ` \  \ \ \ `\/ > <
     *      \ \ \_\ \\ \ \`\ \  \_\ \__ \/'/\`\
     *       \ \_____\\ \_\ \_\ /\_____\/\_\\ \_\
     *        \/_____/ \/_/\/_/ \/_____/\/_/ \/_/
     */
    @Test
    void test_NoOptions_NoParams() {

        // given
        String expectedDefaultMessage = "<your message here>";

        // when
        String[] args = new String [0];
        new CommandLine(new AsciiCommand(new AsciiService())).execute(args);

        // then
        assertTrue(out.toString().contains(expectedDefaultMessage));
        assertEquals("", err.toString());
    }

    /**
     * Calling: ./app/bin/app 'asdf'
     * Results in:
     * ----
     * asdf
     * ----
     *   .
     *    .
     *     .
     *     __  __   __  __   ______  __   __
     *    /\ \/\ \ /\ \/\ \ /\__  _\/\ \ /\ \
     *    \ \ \ \ \\ \ `\\ \\/_/\ \/\ `\`\/'/'
     *     \ \ \ \ \\ \ , ` \  \ \ \ `\/ > <
     *      \ \ \_\ \\ \ \`\ \  \_\ \__ \/'/\`\
     *       \ \_____\\ \_\ \_\ /\_____\/\_\\ \_\
     *        \/_____/ \/_/\/_/ \/_____/\/_/ \/_/
     */
    @Test
    void test_NoOptions_OneMessageParam() {

        // given
        String expectedMessage = "asdf";

        // when
        String[] args = {"asdf"};
        new CommandLine(new AsciiCommand(new AsciiService())).execute(args);

        // then
        assertTrue(out.toString().contains(expectedMessage));
        assertEquals("", err.toString());
    }

    /**
     * Calling: ./app/bin/app -cw 'asdf'
     * Results in:
     * ----
     * asdf
     * ----
     *   .
     *    .
     *     says the Cow...
     *      .
     *       .
     *        ^__^
     *        (oo)\_______
     *        (__)\       )\/\
     *            ||----w |
     *            ||     ||
     */
    @Test
    void test_CwOption_OneMessageParam() {

        // given
        String expectedMessage = "asdf";
        String expectedSaying = "says the Cow";

        // when
        String[] args = {"-cw", "asdf"};
        new CommandLine(new AsciiCommand(new AsciiService())).execute(args);

        // then
        assertTrue(out.toString().contains(expectedMessage));
        assertTrue(out.toString().contains(expectedSaying));
        assertEquals("", err.toString());
    }

    /**
     * Calling: ./app/bin/app -tx 'asdf'
     * Results in:
     * ----
     * asdf
     * ----
     *   .
     *    .
     *     says the Tux ...
     *      .
     *       .
     *           .--.
     *          |o_o |
     *          |:_/ |
     *         //   \ \
     *        (|     | )
     *       /'\_   _/`\
     *       \___)=(___/
     */
    @Test
    void test_TxOption_OneMessageParam() {

        // given
        String expectedMessage = "asdf";
        String expectedSaying = "says the Tux";

        // when
        String[] args = {"-tx", "asdf"};
        new CommandLine(new AsciiCommand(new AsciiService())).execute(args);

        // then
        assertTrue(out.toString().contains(expectedMessage));
        assertTrue(out.toString().contains(expectedSaying));
        assertEquals("", err.toString());
    }

    /**
     * Calling: ./app/bin/app -aa 'asdf'
     * Results in:
     * Unknown option: '-aa'
     * Usage: hello [-hV] [-cw | -tx] <message>
     * Let's say something the UNIX way
     *       <message>       The message to say
     *       -cw, --cowSay   Enable the Cow to say something
     *   -h, --help          Show this help message and exit.
     *       -tx, --tuxSay   Enable the Tux to say something
     *   -V, --version       Print version information and exit
     */
    @Test
    void test_WrongOption_OneMessageParam() {

        // given
        String expectedError = "Unknown option: '-aa'";
        int expectedErrorStatusCode = 2;

        // when
        String[] args = {"-aa", "asdf"};
        int actualExitStatusCode = new CommandLine(new AsciiCommand(new AsciiService())).execute(args);

        // then
        assertEquals(expectedErrorStatusCode, actualExitStatusCode);
        assertTrue(err.toString().contains(expectedError));
    }
}
