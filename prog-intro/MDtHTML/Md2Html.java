package MDtHTML;


import java.io.*;

import static MDtHTML.TagsActions.*;

public class Md2Html {

    public static void main(String[] args) {
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(args[0]))));
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
            System.err.println(e.toString());
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        StringBuilder answer = new StringBuilder();
        try {
            boolean isNewIndent = true;
            String s = in.readLine();
            StringBuilder text = new StringBuilder();
            TagHashMap tagMap = new TagHashMap();
            int headingLvl = 0, i;
            while (s != null) {
                if (s.equals("")) {
                    isNewIndent = true;
                    if (text.length() > 0 && text.charAt(text.length() - 1) == '\n')
                        text.deleteCharAt(text.length() - 1);
                    if (text.length() > 0) {
                        StringBuilder thisPar = new StringBuilder(wrapParagraph(text, headingLvl));
                        answer.append(parseInsideTags(thisPar, tagMap));
                    }
                    headingLvl = 0;
                    text.delete(0, text.length());
                    tagMap = new TagHashMap();
                } else {
                    if (isNewIndent) {
                        i = 0;
                        while (s.charAt(i) == '#')
                            i++;
                        headingLvl = (s.charAt(i) == ' ') ? i : 0;
                        isNewIndent = false;
                    }
                    for (i = 0; i < s.length(); i++) {
                        char c = s.charAt(i);
                        if (i == 0 || s.charAt(i - 1) != '\\') {
                            if (c == '`' || c == '~')
                                tagMap.incVal(new String(new char[]{c}));
                            else if (c == '+' || c == '-') {
                                if (i < s.length() - 1 && s.charAt(i + 1) == c)
                                    tagMap.incVal(new String(new char[]{c, c}));
                            } else if (c == '*' || c == '_') {
                                if (i < s.length() - 1 && s.charAt(i + 1) == c)
                                    tagMap.incVal(new String(new char[]{c, c}));
                                else
                                    tagMap.incVal(new String(new char[]{c}));
                            }
                        }
                    }
                    s = s.replace("&", "&amp;")
                            .replace("<", "&lt;")
                            .replace(">", "&gt;");
                }
                text.append(s);
                if (s.length() > 0) text.append('\n');
                s = in.readLine();
            }
            text.deleteCharAt(text.length() - 1);
            StringBuilder thisPar = new StringBuilder(wrapParagraph(text, headingLvl));
            answer.append(parseInsideTags(thisPar, tagMap));
            in.close();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        try (PrintWriter out = new PrintWriter(new File(args[1]))) {
            out.print(answer);
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
            System.err.println(e.toString());
            System.out.println(answer);
        }
    }

}