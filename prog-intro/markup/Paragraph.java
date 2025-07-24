package markup;

import java.util.List;

public class Paragraph implements MDElement {
    private List<MDElement> el;

    public Paragraph(List<MDElement> e){
        el = e;
    }

    public void toMarkdown(StringBuilder s){
        for (int i = 0; i < el.size(); i++)
            el.get(i).toMarkdown(s);
    }

    public void toHtml(StringBuilder s) {
        for (int i = 0; i < el.size(); i++)
            el.get(i).toHtml(s);
    }

    public void toTex(StringBuilder s) {
        for (int i = 0; i < el.size(); i++)
            el.get(i).toTex(s);
    }
}