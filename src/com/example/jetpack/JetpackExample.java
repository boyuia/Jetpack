package com.example.jetpack;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JetpackExample {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java com.example.jetpack.JetpackExample <path_to_jetpack_file>");
            return;
        }

        try {
            // Read Jetpack code from file
            String code = new String(Files.readAllBytes(Paths.get(args[0])));

            // Tokenize the code
            List<Token> tokens = JetpackTokenizer.tokenize(code);

            // Parse the tokens
            JetpackParser parser = new JetpackParser(tokens);
            Element element = parser.parseElement();

            // Print or use the parsed Element
            System.out.println(element);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
