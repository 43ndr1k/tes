package de.syntela;



import java.io.File;

import org.apache.log4j.Logger;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/**
 * Diese Klasse verarbeitet die CommandLine Argumente.
 */
public class CommandLineValues {

    final static Logger logger = Logger.getLogger(CommandLineValues.class);

    /**
     * Argument
     */
    @Option(name = "-o", aliases = { "--outputFileName" }, required = false,
            usage = "outputFile Name ")
    private String outputFileName;

    /**
     * Argument
     */
    @Option(name = "-x", aliases = { "--xmlFile" }, required = true,
            usage = "outputFile Name ")
    private File xmlFile;

    /**
     * Argument
     */
    @Option(name = "-j", aliases = { "--jasperFile" }, required = true,
            usage = "input Jasper file ")
    private String jasperFile;

    /**
     * Argument
     */
    @Option(name = "-f", aliases = { "--format" }, required = false,
            usage = "Output File Format ")
    private String format="";

    private boolean errorFree = false;

    /**
     * Die CommandLine Werte werden geparst und den richtigen Argument Variablen zugeordnet.
     * @param args CommandLine Argumente
     */
    public CommandLineValues(String... args) {
        CmdLineParser parser = new CmdLineParser(this);
        parser.setUsageWidth(80);
        try {
            parser.parseArgument(args);

            if (!getXmlFile().isFile()) {
                throw new CmdLineException(parser,
                        "--xmlFile is no valid input file.");
            }

            if (!getJasperFile().endsWith(".jasper")) {
                throw new CmdLineException(parser,
                        "--jasperFile is no valid input file.");
            }

            errorFree = true;
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
            logger.error(e.getMessage());
        }
    }

    /**
     * Gibt ein fehler zurück wenn das Parsen gescheitert ist.
     *
     * @return true if no error occurred.
     */
    public boolean isErrorFree() {
        return errorFree;
    }

    /**
     * Gibt den xml File Pfad zurück
     * @return xmlFile
     */
    public File getXmlFile() {
        return xmlFile;
    }

    /**
     * Gibt den Jasper File Pfad zurück.
     * @return jasperFile
     */
    public String getJasperFile() {
        return jasperFile;
    }

    /**
     * Gibt den Output File Namen zurück.
     * @return outputFileName
     */
    public String getOutputFileName() {
        return outputFileName;
    }

    /**
     * Gibt das Ausgabeformat zurück also z.b. pdf
     * @return
     */
    public String getFormat() {
        return format;
    }
}

