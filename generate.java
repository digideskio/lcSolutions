//Recursive solution, build new line based on the last one.

public class Solution {
    public List<List<Integer>> generate(int numRows) {
       List<List<Integer>> list = new ArrayList<List<Integer>>();
       if (numRows == 0) {
           return list;
       }
        if (numRows == 1) {
            
            List<Integer> first = new ArrayList<Integer>();
            first.add(1);
            list.add(first);
            return list;
        }
        
        List<List<Integer>> previous = generate(numRows - 1);
        List<Integer> lastLine = previous.get(previous.size() -1);
        List<Integer> newLine = new ArrayList<Integer>();
        newLine.add(1);
        for (int i = 0; i < lastLine.size() - 1; i++) {
            newLine.add(lastLine.get(i) + lastLine.get(i + 1));
        }
        newLine.add(1);
        previous.add(newLine);
        
        return previous;
    }
}