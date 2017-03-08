package sample;

import java.io.*;
import java.util.*;

public class WordDetector {
    private Map<String,Integer> wordCounts;

    public WordDetector() {
        wordCounts = new TreeMap<>();
    }

    public void processFile(File file) throws IOException {
        if (file.isDirectory()) {
            // process all of the files recursively
            File[] filesInDir = file.listFiles();
            for (int i = 0; i < filesInDir.length; i++) {
                processFile(filesInDir[i]);
            }
        } else if (file.exists()) {
            // load all of the data, and process it into words
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String word = scanner.next();
                if (isWord(word)) {
                    countWord(word);
                }
            }
        }
    }

    private void countWord(String word) {
        if (wordCounts.containsKey(word)) {
            int oldCount = wordCounts.get(word);
            wordCounts.put(word, oldCount + 1);
        } else {
            wordCounts.put(word, 1);
        }
    }

    private boolean isWord(String str){
        String pattern = "^[a-zA-Z]*$";
        if (str.matches(pattern)){
            return true;
        }
        return false;
    }

    public void printWordCounts(int minCount, File outputFile) throws FileNotFoundException {
        System.out.println("Saving word counts to " + outputFile.getAbsolutePath());
        if (!outputFile.exists() || outputFile.canWrite()) {
            PrintWriter fout = new PrintWriter(outputFile);

            Set<String> keys = wordCounts.keySet();
            Iterator<String> keyIterator = keys.iterator();

            while(keyIterator.hasNext()) {
                String key = keyIterator.next();
                int count = wordCounts.get(key);

                if (count >= minCount) {
                    //System.out.println(key + ": " + count);
                    fout.println(key + ": " + count);
                }
            }
            fout.close();
        } else {
            System.err.println("Cannot write to output file");
        }
    }

    public void frequency(String ham, String spam, WordDetector hamcounter, WordDetector spamcounter){
        Set<String> keys = wordCounts.keySet();
        Iterator<String> keyIterator = keys.iterator();
        int hamfiles = new File(ham).listFiles().length;
        int spamfiles = new File(spam).listFiles().length;
        while(keyIterator.hasNext()) {
            String key = keyIterator.next();
            int hamcount = hamcounter.wordCounts.get(key);
            int spamcount = spamcounter.wordCounts.get(key);
            float PrWS = spamcount/spamfiles;
            float PrWH = hamcount/hamfiles;
            float PrSW = PrWS / (PrWS + PrWH);

            System.out.println("key = " + key + "\nhamcount = " + hamcount + "\nspamcount = " + spamcount + "\nPrWS = " + PrWS + "\nPrWH = " + PrWH + "\nPrSW = " + PrSW);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ham Directory:");
        String ham = scanner.nextLine();
        WordDetector hamCounter = new WordDetector();
        //String ham = sample.Controller.getHam();
        File hamDir = new File(ham);
        System.out.println("Ham File: " + hamDir);

        System.out.println("Spam Directory:");
        String spam = scanner.nextLine();
        WordDetector spamCounter = new WordDetector();
        //String spam = sample.Controller.getSpam();
        File spamDir = new File(spam);
        System.out.println("Spam File: " + spamDir);


        try {
            hamCounter.processFile(hamDir);
            hamCounter.printWordCounts(2, new File("ham.txt"));

            spamCounter.processFile(hamDir);
            spamCounter.printWordCounts(2, new File("spam.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        spamCounter.frequency(ham, spam, hamCounter, spamCounter);
    }
}
