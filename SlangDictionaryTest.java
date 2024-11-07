public class SlangDictionaryTest {
    public static void main(String[] args) {
        // Create object and load data
        SlangDictionary slangDictionary = new SlangDictionary();

        // Check size slangMap and definitionMap
        if (!slangDictionary.getSlangMap().isEmpty()) {
            System.out.println("SlangMap loaded successfully with " + slangDictionary.getSlangMap().size() + " slang word.");
        } else {
            System.out.println("Error: SlangMap doesn't have data.");
        }

        if (!slangDictionary.getDefinitionMap().isEmpty()) {
            System.out.println("DefinitionMap loaded successfully with " + slangDictionary.getDefinitionMap().size() + " definition.");
        } else {
            System.out.println("Error: DefinitionMap doesn't have data.");
        }

        // Check save data
        try {
            slangDictionary.saveDictionary();
            System.out.println("Saving data succesfully.");
        } catch (Exception e) {
            System.out.println("Error to save: " + e.getMessage());
        }

        // Check one word
        String testSlang = "YOLO";
        if (slangDictionary.getSlangMap().containsKey(testSlang)) {
            System.out.println("Slang word '" + testSlang + "' has definition: " + slangDictionary.getSlangMap().get(testSlang));
        } else {
            System.out.println("Slang word '" + testSlang + "' doesn't exist in dictionary.");
        }
    }
}
