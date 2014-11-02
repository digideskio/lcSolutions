// a good solution from the web

class Solution {
    Map<String,List<String>> map;
    List<List<String>> results;
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        results= new ArrayList<List<String>>();
        if (dict.size() == 0)
            return results;

        int min=Integer.MAX_VALUE;

        Queue<String> queue= new ArrayDeque<String>();
        queue.add(start);

        map = new HashMap<String,List<String>>();
        dict.add(end);

        Map<String,Integer> ladder = new HashMap<String,Integer>();
        for (String string:dict)
            ladder.put(string, Integer.MAX_VALUE);
        ladder.put(start, 0);

        //BFS: Dijisktra search
        while (!queue.isEmpty()) {

            String word = queue.poll();

            int step = ladder.get(word)+1;//'step' indicates how many steps are needed to travel to one word.

            if (step>min) break;

            for (int i = 0; i < word.length(); i++){
                StringBuilder builder = new StringBuilder(word);
                for (char ch='a';  ch <= 'z'; ch++){
                    builder.setCharAt(i,ch);
                    String new_word=builder.toString();
                    if (ladder.containsKey(new_word)) {

                        if (step>ladder.get(new_word))//Check if it is the shortest path to one word.
                            continue;
                        else if (step<ladder.get(new_word)){
                            queue.add(new_word);
                            ladder.put(new_word, step);
                        }// It is a KEY line. If one word already appeared in one ladder,
                        // Do not insert the same word inside the queue twice. Otherwise it gets TLE.

                        if (map.containsKey(new_word)) //Build adjacent Graph
                            map.get(new_word).add(word);
                        else{
                            List<String> list= new LinkedList<String>();
                            list.add(word);
                            map.put(new_word,list);
                            //It is possible to write three lines in one:
                            //map.put(new_word,new LinkedList<String>(Arrays.asList(new String[]{word})));
                            //Which one is better?
                        }

                        if (new_word.equals(end))
                            min=step;

                    }//End if dict contains new_word
                }//End:Iteration from 'a' to 'z'
            }//End:Iteration from the first to the last
        }//End While

        //BackTracking
        LinkedList<String> result = new LinkedList<String>();
        backTrace(end,start,result);

        return results;
    }
    private void backTrace(String word,String start,List<String> list){
        if (word.equals(start)){
            list.add(0,start);
            results.add(new ArrayList<String>(list));
            list.remove(0);
            return;
        }
        list.add(0,word);
        if (map.get(word)!=null)
            for (String s:map.get(word))
                backTrace(s,start,list);
        list.remove(0);
    }
}


// Mine solution, still needs some work

class Solution {
    private void backTrace(String word,String start){
        if (word.equals(start)){
            list.add(0,start);
            results.add(new ArrayList<String>(list));
            list.remove(0);
            return;
        }
        list.add(0,word);
        if (tracking.get(word)!=null)
            for (String s:tracking.get(word))
                backTrace(s,start);
        list.remove(0);
    }

    List<List<String>> results;
    List<String> list = new LinkedList<String>();
    HashMap<String, Set<String>> tracking = new HashMap<String, Set<String>>();
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> lst = new LinkedList<List<String>>();
        if (start.equals(end)) return lst;
        Queue<String> queue = new LinkedList<String>();

        queue.add(start);
        dict.add(end);

        HashMap<String, Integer> steps = new HashMap<String, Integer>();
        steps.put(start, 0);
        int minStep = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            String w = queue.poll();
            if (steps.get(w) >= minStep) break;
            for (int i = 0; i < w.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    String word = w.substring(0, i) + c + w.substring(i + 1);
                    if (!word.equals(start) && !word.equals(w) && dict.contains(word)) {

                        Set<String> l = tracking.get(word);
                        if (l == null) {
                            l = new HashSet<String>();
                            queue.add(word);
                        }
                        l.add(w);
                        int step  = steps.get(w) + 1;
                        steps.put(word, step);
                        tracking.put(word, l);

                        if (word.equals(end)) minStep = step;
                    }

                }
            }
        }
        backTrace(end, start);
        return results;
    }
}