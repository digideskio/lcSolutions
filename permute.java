// Recursive solution
// Be careful with init of Lists!

public class Solution {
    public List<List<Integer>> permute(int[] num) {
        return permute(num, 0);
    }

    public List<List<Integer>> permute(int[] num, int index) {
        List<List<Integer>> list = new LinkedList<List<Integer>>();
     
        if (index == num.length) {
            LinkedList<Integer> l = new LinkedList<Integer>();
            list.add(l);
            return list;
        }

        List<List<Integer>> previous = permute(num, index + 1);
        for (int i = 0; i < previous.size(); i++) {
            List<Integer> item = previous.get(i);
            List<List<Integer>> items = getList((LinkedList<Integer>) item, num[index]);
            list.addAll(items);
        }

        return list;
    }

    public List<List<Integer>> getList(LinkedList<Integer> item, int number) {
        List<List<Integer>> list = new LinkedList<List<Integer>>();
        for (int i = 0; i < item.size(); i++) {
            List<Integer> newItem = (List<Integer>)item.clone();
            newItem.add(i, number);
            list.add(newItem);
        }
        List<Integer> newItem = (List<Integer>)item.clone();
        newItem.add(number);
        list.add(newItem);
        return list;
    }
}