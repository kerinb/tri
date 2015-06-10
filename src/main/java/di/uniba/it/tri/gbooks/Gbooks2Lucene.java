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
package di.uniba.it.tri.gbooks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 *
 * @author pierpaolo
 */
public class Gbooks2Lucene {

    private IndexWriter writer;

    private static final Logger LOG = Logger.getLogger(Gbooks2Lucene.class.getName());

    private static final int MAX_LENGTH_WORD = 128;

    private static final int FILE_LIMIT = Integer.MAX_VALUE;

    private static final long MAX_FILE_SIZE = Long.MAX_VALUE;

    public Gbooks2Lucene(String indexdirname) throws IOException {
        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, new WhitespaceAnalyzer());
        writer = new IndexWriter(FSDirectory.open(new File(indexdirname)), config);
    }

    private GBLineResult processLine(String line) {
        String[] values = line.split("\t");
        String[] words = values[0].split(" ");
        List<String> tokens = new ArrayList<>(words.length);
        for (String word : words) {
            String[] split = word.split("_");
            if (split.length > 0 && split[0].length() > 0) {
                tokens.add(split[0].toLowerCase());
            }
        }
        return new GBLineResult(tokens.toArray(new String[tokens.size()]), Integer.parseInt(values[1]), Integer.parseInt(values[2]));
    }

    private void store(File dir) throws IOException {
        LOG.info("Indexing...");
        long c = 0;
        int fileCount = 0;
        File[] listFiles = dir.listFiles();
        for (int k = 0; k < listFiles.length && fileCount < FILE_LIMIT; k++) {
            if (listFiles[k].getName().startsWith("googlebooks-") && listFiles[k].getName().endsWith(".gz") && listFiles[k].length() < MAX_FILE_SIZE) {
                LOG.log(Level.INFO, "Working on file {0}", listFiles[k].getAbsolutePath());
                GZIPInputStream is = new GZIPInputStream(new FileInputStream(listFiles[k]));
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                while (reader.ready()) {
                    String line = reader.readLine();
                    GBLineResult gbres = null;
                    try {
                        gbres = processLine(line);
                    } catch (Exception ex) {
                        LOG.log(Level.SEVERE, "Line error: " + line, ex);
                    }
                    if (gbres != null) {
                        //check if all words in the ngram match regexp and max length
                        StringBuilder text = new StringBuilder();
                        for (String ngram : gbres.getNgram()) {
                            text.append(ngram).append(" ");
                        }
                        Document document = new Document();
                        document.add(new TextField("ngram", text.toString(), Field.Store.YES));
                        document.add(new IntField("year", gbres.getYear(), Field.Store.NO));
                        document.add(new IntField("count", gbres.getCount(), Field.Store.YES));
                        writer.addDocument(document);
                    }
                    c++;
                    if (c % 10000000 == 0) {
                        LOG.log(Level.INFO, "Processed lines: {0}", c);
                        writer.commit();
                    }
                }
                LOG.log(Level.INFO, "Close file {0} ({1}) (files {2})", new Object[]{listFiles[k].getAbsolutePath(), c, k + "/" + listFiles.length});
                reader.close();
                fileCount++;
            }
        }
        writer.close();
    }

    public void process(File startingDir) throws IOException, SQLException {
        store(startingDir);
    }

    public IndexWriter getWriter() {
        return writer;
    }

    static Options options;

    static CommandLineParser cmdParser = new BasicParser();

    static {
        options = new Options();
        options.addOption("i", true, "The corpus directory containing Google Books 2-grams dataset")
                .addOption("t", true, "Output directory where the index will be stored");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            CommandLine cmd = cmdParser.parse(options, args);
            if (cmd.hasOption("i") && cmd.hasOption("t")) {
                try {
                    Gbooks2Lucene gbp = new Gbooks2Lucene(cmd.getOptionValue("t"));
                    //attach a shutdown hook
                    Runtime.getRuntime().addShutdownHook(new Thread(new ShutdownIndex(gbp.getWriter())));
                    gbp.process(new File(cmd.getOptionValue("i")));
                } catch (IOException | SQLException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }
            } else {
                HelpFormatter helpFormatter = new HelpFormatter();
                helpFormatter.printHelp("Process Google Books 2-grams dataset and store in Lucene", options, true);
            }
        } catch (ParseException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    private class GBLineResult {

        private String[] ngram;

        private int year;

        private int count;

        public GBLineResult(String[] ngram, int year, int count) {
            this.ngram = ngram;
            this.year = year;
            this.count = count;
        }

        public String[] getNgram() {
            return ngram;
        }

        public void setNgram(String[] ngram) {
            this.ngram = ngram;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

    }

    private static class ShutdownIndex implements Runnable {

        private final IndexWriter writer;

        public ShutdownIndex(IndexWriter writer) {
            this.writer = writer;
        }

        @Override
        public void run() {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Gbooks2Lucene.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}