# AutoComplete-Helper

A helpful way that auto-complete features can be used on a specific set of user data.

## Example setup
```java
AutoCompleteHelper helper = new AutoCompleteHelper();
help.addString("Batman");
help.addString("Battering-Ram");
help.addString("Trebuchet");

String exampleWord = "Bat";
help.findStrings(exampleWord);
if(list.isEmpty())
    System.out.println("No words containing " + exampleWord);
else {
    for(String word : results) {
        System.out.println(word);
    }
}
```

## Example usage
1. First example with "Bat"
   `help.findStrings("Bat");`
   output:
"Batman"
"Battering-Ram"

2. Second example with mis-spelt word, "Trebush"
   `help.findStrings("Trebush");`
   output:
"No words containing Trebush"