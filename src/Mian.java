import grammar.gen.STParser;
import grammar.gen.STScanner;
import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import tools.CLI;
import tools.CLI.Action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Mian {


    public static void run(String[] args ){
        try {
            CLI.parse(args, new String[0]);
            InputStream inputStream = args.length == 0 ?
                    System.in : new java.io.FileInputStream(CLI.infile);
            PrintStream outputStream = CLI.outfile == null ? System.out : new java.io.PrintStream(new java.io.FileOutputStream(CLI.outfile));
            if (CLI.target == Action.SCAN) {
                STScanner scanner =
                        new STScanner(new ANTLRInputStream(inputStream));
//        scanner.setTrace(CLI.debug);
                Token token;
                boolean done = false;
                while (!done) {
                    try {
                        for (token = scanner.nextToken(); token.getType() != STParser.EOF; token = scanner.nextToken()) {
                            String type = "";
                            String text = token.getText();

//              System.out.println("\n\n"+token.getType());
                            switch (token.getType()) {
                                // TODO: add strings for the other types here...
                                case STScanner.AS_OP:
                                    type = " AS_OP";
                                    break;
                                case STScanner.FromTo:
                                    type = " FromTo";
                                    break;
                                case STScanner.Decimal_literal:
                                    type = " Decimal_literal";
                                    break;
                                case STScanner.Static_string_literal:
                                    type = " STRINGLITERAL";
                                    break;
                                case STScanner.BOOL:
                                    type = " BOOLEANLITERAL";
                                    break;
                                case STScanner.ID:
                                    type = " IDENTIFIER";
                                    break;
                            }
                            outputStream.println(token.getLine()  + type + " " + text);
                        }
                        done = true;
                    } catch(Exception e) {
                        // print the error:
                        System.err.println(CLI.infile + " " + e);
//            scanner.consume();
                        scanner.skip(); // replaces
                    }
                }
            } else if (CLI.target == Action.PARSE ||
                    CLI.target == Action.DEFAULT) {
                STScanner scanner =
                        new STScanner(new ANTLRInputStream(inputStream));
                CommonTokenStream tokenStream = new CommonTokenStream(scanner); // added for Antlr4
                STParser parser = new STParser(tokenStream);
                parser.setTrace(CLI.debug);
                parser.pous();
//        if(parser.getError()) {
                if (parser.getNumberOfSyntaxErrors() > 0) {
                    System.exit(1);
                }
            }
        } catch(Exception e) {
            // print the error:
            System.err.println(CLI.infile+" "+e);
        }

    }

    public static void walkTree(String[] args){
        String prefix = "tests/";
        String inputFile = prefix + "test.txt";

        try{
            CharStream stream = CharStreams.fromFileName(inputFile);
            STScanner lexer = new STScanner(stream);
            TokenStream tokens = new CommonTokenStream(lexer);
            STParser parser = new STParser(tokens);
            ParseTree tree = parser.pous();
            ParseTreeWalker walker = new ParseTreeWalker();
            STListener listener = new STListener();
            walker.walk(listener,tree);
//            ArrayList<String> ruleNames = new ArrayList<>();
//            ruleNames.add("program");
            Trees.inspect(tree, parser);
            System.out.println(listener.pous.getProgramDeclsArrayList());
        }
        catch (IOException e){
            System.err.println("There was an error:\n" + e);
        }
    }

    public static void main(String[] args) throws IOException {
//        MyPrint myprint  = new MyPrint(true);
//        myprint.print(System.getProperty("user.home"));
        walkTree(args);



    }
}
