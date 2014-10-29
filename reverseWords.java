// two sols, the second of which used regular expression

class Solution {
    public String reverseWords(String s) {
        Stack<String> words = new Stack<String>();
        s = s.trim();
        while (s.length() > 0) {
            int index = 0;
            while (index < s.length() && s.charAt(index) != ' ') {
                index++;
            }
            words.add(s.substring(0, index));
            s = s.substring(index);
            s = s.trim();

        }
        StringBuffer str = new StringBuffer();
        while (!words.isEmpty()) {
            str.append(" " + words.pop());
        }
        return str.toString().trim();
    }
    public String reverseWordsAlt(String s) {
        String[] split = s.split("\\s+");
        StringBuffer words = new StringBuffer();
        for (String str : split) {
            if (str.length() > 0) {
                words.insert(0, str + " ");
            }
        }
        return words.toString().trim();
    }

}
