package concurrency.code.confluentInterview.interview1;
import java.util.List;
import java.util.*;

class Function {
    public final List<String> argumentTypes; // e.g. ["Integer", "String", "PersonClass"]
    public final String name;
    public final boolean isVariadic;
    Function(String name, List<String> argumentTypes, boolean isVariadic) {
        this.name = name;
        this.argumentTypes = argumentTypes;
        this.isVariadic = isVariadic;
    }
    public String toString() {
        return this.name;
    }
}

class FunctionLibrary {
    List<Function> functionRegistry = new ArrayList<>();
    String generateKey(List<String> types){
        return String.join("-",types);
    }

    void register(Set<Function> functions) {
        functionRegistry.addAll(functions);
    }

    private static boolean isMatch(List<String> functionArgs, List<String> searchArgs, boolean veridict) {
        int funcArgSize = functionArgs.size();
        int searchArgSize = searchArgs.size();

        if (!veridict) {
            // Exact match required
            return funcArgSize == searchArgSize && functionArgs.equals(searchArgs);
        } else {
            // For veridict=true, the searchArgs should start with functionArgs except the last one
            if (searchArgSize < funcArgSize - 1) {
                return false; // Not enough arguments to match the non-repeating part
            }

            // Check if the initial arguments match
            for (int i = 0; i < funcArgSize - 1; i++) {
                if (!functionArgs.get(i).equals(searchArgs.get(i))) {
                    return false;  // Initial arguments don't match
                }
            }

            // Check if the remaining search arguments match the last function argument
            String lastArg = functionArgs.get(funcArgSize - 1);
            for (int i = funcArgSize - 1; i < searchArgSize; i++) {
                if (!lastArg.equals(searchArgs.get(i))) {
                    return false;  // Last argument doesn't repeat as expected
                }
            }

            return true;
        }
    }

    List<Function> findMatches(List<String> searchArgs) {
        // implement me HashMap : key , List<Functions>
        // linked hash map : key :
        // key : Boolean : funA
        // Integer : funA , funB , fun C
        // search argument : Integer", "Integer", "Integer"
        // funC = No of fun : Integer
        //
        List<Function> matches = new ArrayList<>();
        for(var function : functionRegistry){
            if(isMatch(function.argumentTypes , searchArgs, function.isVariadic)){
                matches.add(function);
            }
        }
        return matches;
    }
}
public class Solution {

    public static void main(String[] args) {
        FunctionLibrary library = new FunctionLibrary();
        Function funA = new Function("funA", List.of("Boolean", "Integer"), false);
        Function funB = new Function("funB", List.of("Integer", "Integer"), false);
        Function funC = new Function("funC", List.of("Integer"), true);
        library.register(new HashSet<Function>(List.of(funA, funB, funC)));
//        assertEquals(List.of(funA), library.findMatches(List.of("Boolean", "Integer")));
//        assertEquals(List.of(funB, funC), library.findMatches(List.of("Integer")));
        System.out.println(library.findMatches(List.of("Integer", "Integer")));
    }
}
