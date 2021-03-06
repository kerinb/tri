/**
 * Copyright (c) 2014, the Temporal Random Indexing AUTHORS.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the University of Bari nor the names of its contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * GNU GENERAL PUBLIC LICENSE - Version 3, 29 June 2007
 *
 */
package di.uniba.it.tri.aan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.io.FileUtils;

/**
 * Convert the ACL-AAN corpus in a single file for each paper with year metadata.
 * @author pierpaolo
 */
public class AAN2file {

    private static final int[] YEAR_DIRS = new int[]{2008, 2009, 2010, 2011, 2012, 2013};

    private String getValue(String line) {
        int s = line.indexOf("{");
        int e = line.indexOf("}");
        if (e < 0) {
            e = line.length();
        }
        if (e > s && s > -1 && e > -1) {
            return line.substring(s + 1, e);
        } else {
            Logger.getLogger(AAN2file.class.getName()).log(Level.WARNING, "No valid line: {0}", line);
            return null;
        }
    }

    private List<Paper> loadPaperList(File metadatafile, String paperdirname) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(metadatafile));
        String id = null;
        String year = null;
        List<Paper> list = new ArrayList<>();
        while (reader.ready()) {
            String line = reader.readLine();
            if (line.startsWith("id = {")) {
                id = getValue(line);
            } else if (line.startsWith("year = {")) {
                year = getValue(line);
            }
            if (line.length() == 0) {
                if (id != null && year != null) {
                    Paper paper = new Paper(id);
                    paper.setYear(Integer.parseInt(year));
                    paper.setFile(new File(paperdirname + "/" + id + ".txt"));
                    list.add(paper);
                }

            }
        }
        return list;
    }

    /**
     * Convert the ACL-AAN corpus in a single file for each paper with year metadata
     * @param anndirname The ACL-ANN corpus directory
     * @param outputdirname Output directory where files will be stored
     * @throws Exception
     */
    public void build(String anndirname, String outputdirname) throws Exception {
        for (int i = 0; i < YEAR_DIRS.length; i++) {
            File metadatafile = new File(anndirname + "/release/" + YEAR_DIRS[i] + "/acl-metadata.txt");
            List<Paper> list = loadPaperList(metadatafile, anndirname + "/papers_text/");
            for (Paper paper : list) {
                if (paper.getFile().exists()) {
                    File destFile = new File(outputdirname + "/" + paper.getId() + "_" + paper.getYear());
                    if (!destFile.exists()) {
                        FileUtils.copyFile(paper.getFile(), destFile);
                    }
                } else {
                    Logger.getLogger(AAN2file.class.getName()).log(Level.WARNING, "Paper not found: {0}", paper.getFile());
                }
            }
        }
    }
    
    static Options options;

    static CommandLineParser cmdParser = new BasicParser();

    static {
        options = new Options();
        options.addOption("c", true, "AAN corpus directory")
                .addOption("o", true, "Output directory where files will be stored");
    }

    /**
     * Convert the ACL-AAN corpus in a single file for each paper with year metadata.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            CommandLine cmd = cmdParser.parse(options, args);
            if (cmd.hasOption("c") && cmd.hasOption("o")) {
                AAN2file ann = new AAN2file();
                ann.build(cmd.getOptionValue("c"), cmd.getOptionValue("o"));
            } else {
                HelpFormatter helpFormatter = new HelpFormatter();
                helpFormatter.printHelp("Convert the ACL-AAN corpus in a single file for each paper with the year metadata", options, true);
            }
        } catch (Exception ex) {
            Logger.getLogger(AAN2file.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
