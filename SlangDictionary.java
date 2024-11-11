import java.io.*;
import java.util.*;

public class SlangDictionary {
    private HashMap<String, String> slangMap;
    private TreeMap<String, List<String>> definitionMap;
    private List<String> searchHistory;
    private static final String DataFile = "slang.txt";

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
            for (Map.Entry<String, String> entry: slangMap.entrySet()) {
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

    public List<String> getSearchHistory() {
        return searchHistory;
    }

    // Search by slang word
    public String searchBySlangWord(String word) {
        return slangMap.get(word);
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
}
