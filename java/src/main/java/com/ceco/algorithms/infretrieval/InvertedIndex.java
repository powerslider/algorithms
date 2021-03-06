package com.ceco.algorithms.infretrieval;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 17-Oct-2016
 */
public class InvertedIndex {

    private static final String RES_DIR = "src/main/resources/inverted-idx";
    private static final String INDEX_FILE = RES_DIR + "/hashmap.ser";
    private static final Path CORPUS_PATH = Paths.get(RES_DIR + "/corpus");

    private Map<String, Set<Pair>> index = new HashMap<>();
    private List<String> documents = new ArrayList<>();
    private final List<String> stopwords = loadStopwords(RES_DIR + "/stopwords.txt");

    private void indexFile(File file) throws IOException {
        int docId = documents.indexOf(file.getPath());
        if (docId == -1) {
            documents.add(file.getPath());
            docId = documents.size() - 1;
        }

        int pos = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                for (String w : line.split("\\W+")) {
                    String word = w.toLowerCase();
                    if (word.isEmpty()) continue;
                    pos++;
                    if (stopwords.contains(word))
                        continue;
                    Set<Pair> idx = Optional
                            .ofNullable(index.get(word))
                            .orElse(new LinkedHashSet<>());
                    if (idx.isEmpty()) {
                        index.put(word, idx);
                    }
                    idx.add(new Pair(docId, pos));
                }
            }
            System.out.println("indexed " + file.getPath() + " " + pos + " words");
        }
    }

    private void persist(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(index);
        } catch (IOException e) {
            System.out.println("Cannot save index to disk " + e);
        }
    }

    private void search(List<String> words) {
        for (String w : words) {
            Set<String> answer = new HashSet<>();
            String word = w.toLowerCase();
            Set<Pair> idx = index.get(word);
            if (idx != null) {
                for (Pair t : idx) {
                    answer.add(documents.get(t.docId));
                }
            }
            System.out.print(word);
            for (String f : answer) {
                System.out.print(" " + f);
            }
            System.out.println("");
        }
    }

    private List<String> loadStopwords(String fileName) {
        List<String> stopwords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                stopwords.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading stopwords " + e);
        }

        return stopwords;
    }


    private static class Pair implements Serializable {
        private int docId;
        private int position;

        Pair(int fileno, int position) {
            this.docId = fileno;
            this.position = position;
        }
    }

    public static void main(String[] args) throws IOException {
        InvertedIndex idx = new InvertedIndex();
        Stream<Path> list = Files.list(CORPUS_PATH);
        list.forEach((f) -> {
            try {
                idx.indexFile(f.toFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
//        idx.persist(INDEX_FILE);
        idx.search(Arrays.asList("mathew,darwin,evolution,tammy".split(",")));
    }
}
