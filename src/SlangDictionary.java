import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class SlangDictionary {
    private HashMap<String, String> slangMap;
    private TreeMap<String, List<String>> definitionMap;
    private List<Pair<String, String>> searchHistory;
    private static final String DataFile = System.getProperty("user.dir") + "\\resources\\slang.txt";
    private static final String HistoryFile = System.getProperty("user.dir") + "\\resources\\search_history.txt";

    // Constructor
    public SlangDictionary() {
        slangMap = new HashMap<>();
        definitionMap = new TreeMap<>();
        searchHistory = new ArrayList<>();
        loadDictionary();
        loadSearchHistory();
    }

    // Load Data From File
    private void loadDictionary() {
        try (BufferedReader br = new BufferedReader(new FileReader(DataFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("`");
                if (parts.length == 2) {
                    // Add to HashMap
                    slangMap.put(parts[0], parts[1]);

                    // Add to TreeMap
                    definitionMap.computeIfAbsent(parts[1], k -> new ArrayList<>()).add(parts[0]);
                }
            }
        } catch (IOException e) {
            System.out.println(("Error loading slang dictionary: ") + e.getMessage());
        }
    }

    // Load search history from file
    private void loadSearchHistory() {
        try (BufferedReader br = new BufferedReader(new FileReader(HistoryFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("`");
                if (parts.length == 2) {
                    searchHistory.add(new Pair<>(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading search history: " + e.getMessage());
        }
    }

    // Save data to file
    public void saveDictionary() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DataFile))) {
            for (Map.Entry<String, String> entry : slangMap.entrySet()) {
                bw.write(entry.getKey() + "`" + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving slang dictionary: " + e.getMessage());
        }
    }

    // Save search history to file
    public void saveSearchHistory() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HistoryFile))) {
            for (int i = 0; i < Math.min(30, searchHistory.size()); i++) {
                Pair<String, String> entry = searchHistory.get(i);
                writer.write(entry.getKey() + "`" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving search history: " + e.getMessage());
        }
    }

    // Getter
    public HashMap<String, String> getSlangMap() {
        return slangMap;
    }

    public boolean containsSlangWord(String word) {
        return slangMap.containsKey(word);
    }

    public TreeMap<String, List<String>> getDefinitionMap() {
        return definitionMap;
    }

    public List<Pair<String, String>> getSearchHistory() {
        return new ArrayList<>(searchHistory);
    }

    // Add search history entry
    public void addSearchHistory(String slangWord, boolean isQuiz) {
        if (isQuiz) {
            return;
        }
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        searchHistory.add(new Pair<>(slangWord, timestamp));
        if (searchHistory.size() > 30) {
            searchHistory.remove(0);
        }
        saveSearchHistory();
    }

    // Search by slang word
    public String searchBySlangWord(String word, boolean isQuiz) {
        String result = slangMap.get(word);
        if (result != null) {
            addSearchHistory(word, isQuiz);
            saveSearchHistory();
        }
        return result;
    }

    // Search by definition
    public List<String> searchByDefinition(String word) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : definitionMap.entrySet()) {
            if (entry.getKey().toLowerCase().contains(word.toLowerCase())) {
                result.addAll(entry.getValue());
            }
        }
        return result;
    }

    // Add slang word
    public boolean addSlangWord(String slang, String definition) {
        if (slangMap.containsKey(slang)) {
            return false;
        }
        slangMap.put(slang, definition);
        definitionMap.computeIfAbsent(definition, k -> new ArrayList<>()).add(slang);
        saveDictionary();
        return true;
    }

    // Edit slang word
    public boolean editSlangWord(String slang, String newDefinition) {
        if (slangMap.containsKey(slang)) {
            String oldDefinition = slangMap.put(slang, newDefinition);
            definitionMap.get(oldDefinition).remove(slang);
            if (definitionMap.get(oldDefinition).isEmpty()) {
                definitionMap.remove(oldDefinition);
            }
            definitionMap.computeIfAbsent(newDefinition, k -> new ArrayList<>()).add(slang);
            saveDictionary();
            return true;
        }
        return false;
    }

    // Delete slang word
    public boolean deleteSlangWord(String slang) {
        if (slangMap.containsKey(slang)) {
            String definition = slangMap.remove(slang);

            List<String> slangList = definitionMap.get(definition);
            if (slangList != null) {
                slangList.remove(slang);
                if (slangList.isEmpty()) {
                    definitionMap.remove(definition);
                }
            }
            saveDictionary();
            return true;
        } else {
            return false;
        }
    }

    // Reset Dictionary
    public void resetDictionary() {
        try (BufferedReader br = new BufferedReader(
                new FileReader(System.getProperty("user.dir") + "\\resources\\slang_backup.txt"))) {
            slangMap.clear();
            definitionMap.clear();

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("`");
                if (parts.length == 2) {
                    slangMap.put(parts[0], parts[1]);
                    definitionMap.computeIfAbsent(parts[1], k -> new ArrayList<>()).add(parts[0]);
                }
            }

            saveDictionary();
            System.out.println("Dictionary has been result to the original version.");
        } catch (IOException e) {
            System.out.println("Error resetting slang dictionary: " + e.getMessage());
        }
    }

    // Random Slang Word
    public String getRandomSlangWord() {
        List<String> keys = new ArrayList<>(slangMap.keySet());
        if (keys.isEmpty()) {
            return null;
        }
        Random random = new Random();
        String randomSlang = keys.get(random.nextInt(keys.size()));
        return randomSlang;
    }

    // Random Definition
    public String getRandomDefinition() {
        List<String> keys = new ArrayList<>(definitionMap.keySet());
        if (keys.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return keys.get(random.nextInt(keys.size()));
    }
}

// Pair Class
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return key + " : " + value;
    }
}