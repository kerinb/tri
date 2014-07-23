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
package di.uniba.it.tri.occ;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multiset.Entry;
import di.uniba.it.tri.extractor.Extractor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

/**
 *
 * @author pierpaolo
 */
public class BuildOccurrence {

    private int winsize = 5;

    private File outputDir = new File("./");

    private static final Logger logger = Logger.getLogger(BuildOccurrence.class.getName());
    
    private Extractor extractor;

    public BuildOccurrence() {
    }

    public int getWinsize() {
        return winsize;
    }

    public void setWinsize(int winsize) {
        this.winsize = winsize;
    }

    public File getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(File outputDir) {
        this.outputDir = outputDir;
    }

    public Extractor getExtractor() {
        return extractor;
    }

    public void setExtractor(Extractor extractor) {
        this.extractor = extractor;
    }

    private Map<String, Multiset<String>> count(File startingDir, int year) throws IOException {
        Map<String, Multiset<String>> map = new HashMap<>();
        File[] listFiles = startingDir.listFiles();
        for (File file : listFiles) {
            if (file.getName().endsWith("_" + String.valueOf(year))) {
                logger.log(Level.INFO, "Working file: {0}", file.getName());
                StringReader reader = extractor.extract(file);
                List<String> tokens = getTokens(reader);
                for (int i = 0; i < tokens.size(); i++) {
                    int start = Math.max(0, i - winsize);
                    int end = Math.min(tokens.size() - 1, i + winsize);
                    for (int j = start; j < end; j++) {
                        if (i != j) {
                            Multiset<String> multiset = map.get(tokens.get(i));
                            if (multiset == null) {
                                multiset = HashMultiset.create();
                                map.put(tokens.get(i), multiset);
                            }
                            multiset.add(tokens.get(j));
                        }
                    }
                }
            }
        }
        return map;
    }

    private List<String> getTokens(Reader reader) throws IOException {
        List<String> tokens = new ArrayList<>();
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
        TokenStream tokenStream = analyzer.tokenStream("text", reader);
        tokenStream.reset();
        CharTermAttribute cattr = tokenStream.addAttribute(CharTermAttribute.class);
        while (tokenStream.incrementToken()) {
            String token = cattr.toString();
            String[] split = token.split("'");
            if (split.length == 1) {
                tokens.add(token);
            } else {
                int max = 0;
                int index = 0;
                for (int i = 0; i < split.length; i++) {
                    if (split[i].length() > max) {
                        max = split[i].length();
                        index = i;
                    }
                }
                tokens.add(split[index]);
            }
        }
        tokenStream.end();
        return tokens;
    }

    public void process(File startingDir) throws Exception {
        logger.log(Level.INFO, "Starting dir: {0}", startingDir.getAbsolutePath());
        logger.log(Level.INFO, "Output dir: {0}", outputDir.getAbsolutePath());
        logger.log(Level.INFO, "Window size: {0}", winsize);
        File[] listFiles = startingDir.listFiles();
        int minYear = Integer.MAX_VALUE;
        int maxYear = -Integer.MAX_VALUE;
        for (File file : listFiles) {
            int i = file.getName().lastIndexOf("_");
            if (i > -1) {
                int year = Integer.parseInt(file.getName().substring(i + 1));
                if (year < minYear) {
                    minYear = year;
                }
                if (year > maxYear) {
                    maxYear = year;
                }
            }
        }
        logger.log(Level.INFO, "Form year: {0}", minYear);
        logger.log(Level.INFO, "To year: {0}", maxYear);
        for (int k = minYear; k <= maxYear; k++) {
            logger.log(Level.INFO, "Counting year: {0}", k);
            Map<String, Multiset<String>> count = count(startingDir, k);
            if (!count.isEmpty()) {
                save(count, k);
            }
        }
    }

    private void save(Map<String, Multiset<String>> count, int year) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputDir.getAbsolutePath() + "/count_" + year));
        Iterator<String> keys = count.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            writer.append(key);
            Set<Multiset.Entry<String>> entrySet = count.get(key).entrySet();
            for (Entry<String> entry : entrySet) {
                writer.append("\t").append(entry.getElement()).append("\t").append(String.valueOf(entry.getCount()));
            }
            writer.newLine();
        }
        writer.close();
    }

    /**
     * corpusDir outputDir windowSize extractor_class
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 4) {
            try {
                Extractor extractor=(Extractor) Class.forName(args[4]).newInstance();
                BuildOccurrence builder = new BuildOccurrence();
                builder.setOutputDir(new File(args[1]));
                builder.setWinsize(Integer.parseInt(args[2]));
                builder.setExtractor(extractor);
                builder.process(new File(args[0]));
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        } else {
            logger.warning("No valid arguments");
        }
    }

}