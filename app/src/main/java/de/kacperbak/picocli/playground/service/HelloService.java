package de.kacperbak.picocli.playground.service;

import static de.kacperbak.picocli.playground.domain.AsciiArt.*;

public class HelloService {

    private static final String CAN_T_SAY_THIS = "Sorry, can't say this.";
    private static final int MAX_NUMBER_OF_CHARACTERS = 100;

    public static Integer unixSay(String message)
    {
        renderSpeakBubble(message);
        System.out.println("  .");
        System.out.println("   .");
        System.out.println("    .");
        return renderOutro(UNIX);
    }

    public static Integer cowSay(String message)
    {
        renderSpeakBubble(message);
        System.out.println("  .");
        System.out.println("   .");
        System.out.println("    says the Cow...");
        System.out.println("     .");
        System.out.println("      .");
        return renderOutro(COW);
    }

    public static Integer tuxSay(String message)
    {
        renderSpeakBubble(message);
        System.out.println("  .");
        System.out.println("   .");
        System.out.println("    says the Tux ...");
        System.out.println("     .");
        System.out.println("      .");
        return renderOutro(TUX);
    }

    private static void renderSpeakBubble(String message)
    {
        if (message.length() < 1 || message.length() > MAX_NUMBER_OF_CHARACTERS)
        {
            message = CAN_T_SAY_THIS;
        }
        String speakBubbleBar = message.replaceAll(".", "-");
        System.out.println(speakBubbleBar);
        System.out.println(message);
        System.out.println(speakBubbleBar);
    }

    private static Integer renderOutro(String asciiArt)
    {
        System.out.print(asciiArt);
        return 0;
    }
}
