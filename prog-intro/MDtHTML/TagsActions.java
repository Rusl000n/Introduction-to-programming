package MDtHTML;


class TagsActions {
    private static String heading(String text, int headingLvl) {
        if (headingLvl == 0)
            return "<p>" + text + "</p>";
        else
            return "<h" + headingLvl + ">" + text + "</h" + headingLvl + ">";
    }

    static String wrapParagraph(StringBuilder text, int headingLvl) {
        return "\n" + heading(
                text.substring(
                        headingLvl + ((headingLvl > 0) ? 1 : 0),
                        text.length()),
                headingLvl
        ) + '\n';
    }

    private static int insertingTextAndTag(StringBuilder answer, TagHashMap tagMap, String tag, StringBuilder textWithHeadings, int lastTextStart, int i) {
        answer.append(textWithHeadings.substring(lastTextStart, i));
        lastTextStart = i + tag.length();
        if (tagMap.getVal(tag) > 0) {
            if (tagMap.getVal(tag) % 2 == 1)
                answer.append(tagMap.toCloseHtml(tag));
            else
                answer.append(tagMap.toOpenHtml(tag));
        } else
            answer.append(tag);
        return lastTextStart;
    }

    static String parseInsideTags(StringBuilder textWithHeadings, TagHashMap tagMap) {
        StringBuilder answer = new StringBuilder();
        tagMap.makeEvenAll();
        int lastTextStart = 0;
        int i;
        int len = textWithHeadings.length();
        for (i = 0; i < len; i++) {
            char c = textWithHeadings.charAt(i);
            if (c == '`' || c == '~') {
                if (i == 0 || (i > 1 && textWithHeadings.charAt(i - 1) != '\\')) {
                    lastTextStart = insertingTextAndTag(answer, tagMap, "" + c, textWithHeadings, lastTextStart, i);
                }
            } else if (c == '+' || c == '-') {
                String thisTag = new String(new char[]{c, c});
                if (i < len - 1 && textWithHeadings.charAt(i + 1) == c) {
                    lastTextStart = insertingTextAndTag(answer, tagMap, thisTag, textWithHeadings, lastTextStart, i);
                }
            } else if (c == '*' || c == '_') {
                if (i < len - 1 && textWithHeadings.charAt(i + 1) == c) {
                    String thisTag = new String(new char[]{c, c});
                    lastTextStart = insertingTextAndTag(answer, tagMap, thisTag, textWithHeadings, lastTextStart, i);
                } else if (i > 0 && textWithHeadings.charAt(i - 1) != c) {
                    String thisTag = new String(new char[]{c});
                    if (textWithHeadings.charAt(i - 1) != '\\')
                        lastTextStart = insertingTextAndTag(answer, tagMap, thisTag, textWithHeadings, lastTextStart, i);
                }
            } else if (c == '\\') {
                answer.append(textWithHeadings.substring(lastTextStart, i));
                lastTextStart = i + 1;
            }

        }
        if (lastTextStart < textWithHeadings.length())
            answer.append(textWithHeadings.substring(lastTextStart, textWithHeadings.length()));
        answer.deleteCharAt(0);
        return answer.toString();
    }
}
