package edu.asupoly.ser421.xmlex;

import java.io.*;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;

public class Transform {

    public static void main(String[] args) throws Exception {

    if (args.length != 3) {
        System.out.println("java <program> xml xsl outfile (- => stdout)");
        System.exit(1);
    }
    TransformerFactory tFactory = TransformerFactory.newInstance();
    //tFactory.setValidating(true);
    
    // Ask factory to create an appropriate transform object
    Transformer transformer = tFactory.newTransformer(new StreamSource(args[1]));

    // Process it
    transformer.transform(new StreamSource(args[0]),
                          new StreamResult(
                              args[2].equals("-")?
                                System.out:
                                new PrintStream(new FileOutputStream(args[2]))));
    }
}
