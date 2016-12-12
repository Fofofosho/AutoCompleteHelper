import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class AutoCompleteHelper {

    private class TrieNode {

        protected String value;
        protected HashMap<Character, TrieNode> children;
        protected Boolean isWord;

        public TrieNode(String data) {
            value = data;
            children = new HashMap<Character, TrieNode>();
            isWord = false;
        }
    }
    public TrieNode root;

    public AutoCompleteHelper() {
        root = new TrieNode("");
    }

    // Can return NULL
    public ArrayList<String> findStrings(String input) {
        TrieNode current = root;
        ArrayList<String> contents = new ArrayList<>();
        char[] inputArray = input.toCharArray();
        // get to the very end of the input string
        for(char letter : inputArray) {
            if(current.children.containsKey(letter)) {
                current = current.children.get(letter);
            }
            else
                return null;
        }

        //visit and count all leaves from this current TrieNode
        contents = countEligibleLeaves(current, contents);
        return contents;
    }

    public void addString(String input) {
        TrieNode current = root;
        char[] inputArray = input.toCharArray();
        StringBuilder currentWord = new StringBuilder();
        for(char letter : inputArray) {
            if(current.children.containsKey(letter)) {
                currentWord.append(letter);
                current = current.children.get(letter);
            }
            else {
                currentWord.append(letter);

                // add new child
                current = addNode(current, currentWord.toString(), letter);
            }
        }

        current.isWord = true;
    }

    private TrieNode addNode(TrieNode current, String newNodeData, char newEdge) {
        if(current.children.containsKey(newEdge))
            return current.children.get(newEdge);

        TrieNode newNode = new TrieNode(newNodeData);
        current.children.put(newEdge, newNode);
        return newNode;
    }

    private ArrayList<String> countEligibleLeaves(TrieNode current, ArrayList<String> list) {
        Iterator<TrieNode> iter = current.children.values().iterator();
        if(current.children.size() > 0) {
            while(iter.hasNext()) {
                TrieNode node = iter.next();
                countEligibleLeaves(node, list);
            }
        }

        if(current.isWord) {
            list.add(current.value);
            return list;
        }
        else if(current.children.size() > 0 && !current.isWord) {
            return list;
        }

        return list;
    }
}