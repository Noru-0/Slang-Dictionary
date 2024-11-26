import java.io.*;
import java.util.*;

public class SlangDictionary {
    private HashMap<String, String> slangMap;
    private TreeMap<String, List<String>> definitionMap;
    private List<String> searchHistory;
    private static final String DataFile = "../resources/slang.txt";

    // Constructor
    public SlangDictionary() {
        slangMap = new HashMap<>();
        definitionMap = new TreeMap<>();
        searchHistory = new ArrayList<>();
        loadDictionary();
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

    // Save Data To File
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

    // Search by slang word
    public String searchBySlangWord(String word) {
        String result = slangMap.get(word);
        if (result != null) {
            addSearchHistory(word);
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
        // if (!result.isEmpty()) {
        // addSearchHistory(word);
        // }
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

    public void updateSlangWord(String slang, String definition) {
        slangMap.put(slang, definition);
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

    // Save search history
    public void addSearchHistory(String slangWord) {
        searchHistory.add(slangWord);
    }

    // Show search history
    public List<String> getSearchHistory() {
        return new ArrayList<>(searchHistory);
    }

    // Reset Dictionary
    public void resetDictionary() {
        try (BufferedReader br = new BufferedReader(new FileReader("../backups/slang_backup.txt"))) {
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
