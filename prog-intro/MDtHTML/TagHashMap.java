package MDtHTML;


import java.util.HashMap;

class TagHashMap {
    private HashMap<String, Integer> tags;

    TagHashMap() {
        tags = new HashMap<>();
        tags.put("*", 0);
        tags.put("_", 0);
        tags.put("**", 0);
        tags.put("__", 0);
        tags.put("++", 0);
        tags.put("--", 0);
        tags.put("`", 0);
        tags.put("~", 0);
    }

    void incVal(String tag) {
        int val = tags.get(tag);
        val++;
        tags.replace(tag, val);
    }

    private void decVal(String tag) {
        int val = tags.get(tag);
        val--;
        tags.replace(tag, val);
    }

    private void makeEven2Symbolic(String tag) {
        int emphasis = tags.get(tag);
        int strong = tags.get(tag + tag);
        emphasis = (emphasis + strong % 2);
        emphasis -= emphasis % 2;
        strong -= strong % 2;
        tags.replace(tag, emphasis);
        tags.replace(tag + tag, strong);
    }

    private void makeEven1Symbolic(String tag) {
        int val = tags.get(tag);
        val -= val % 2;
        tags.replace(tag, val);
    }

    void makeEvenAll() {
        makeEven2Symbolic("*");
        makeEven2Symbolic("_");
        makeEven1Symbolic("`");
        makeEven1Symbolic("++");
        makeEven1Symbolic("--");
        makeEven1Symbolic("~");
    }

    int getVal(String tag) {
        return tags.get(tag);
    }

    String toOpenHtml(String tag) {
        decVal(tag);
        if (tag.equals("++"))
            return "<u>";
        else if (tag.equals("--"))
            return "<s>";
        else if (tag.equals("`"))
            return "<code>";
        else if (tag.equals("~"))
            return "<mark>";
        else if (tag.length() == 1)
            return "<em>";
        else
            return "<strong>";
    }

    String toCloseHtml(String tag) {
        decVal(tag);
        if (tag.equals("++"))
            return "</u>";
        else if (tag.equals("--"))
            return "</s>";
        else if (tag.equals("`"))
            return "</code>";
        else if (tag.equals("~"))
            return  "</mark>";
        else if (tag.length() == 1)
            return "</em>";
        else
            return "</strong>";
    }
}
