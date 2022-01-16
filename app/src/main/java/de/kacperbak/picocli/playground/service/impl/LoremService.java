package de.kacperbak.picocli.playground.service.impl;

import de.kacperbak.picocli.playground.service.ILoremService;

public class LoremService implements ILoremService {

    private static final String LOREM_IPSUM_CONTENT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    @Override
    public String lorem(int count) {
        var delemiter = "\n\n";
        var result = "";
        for (int i = 0; i < count; i++) {
            result = result + LOREM_IPSUM_CONTENT;
            if ((i + 1) < count) {
                result = result + delemiter;
            }
        }
        return result;
    }
}
