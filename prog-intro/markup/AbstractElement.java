package markup;

import java.util.List;

public abstract class AbstractElement implements MDElement {
    private List<MDElement> el;
    private String sur;
    private String htmlSurOpen;
    private String htmlSurClose;
    private String texSur;

    AbstractElement(String MarkDown, String HtmlOpen, String HtmlClose, String TexOpen, List<MDElement> e) {
        sur = MarkDown;
        htmlSurOpen = HtmlOpen;
        htmlSurClose = HtmlClose;
        texSur = TexOpen;
        el = e;
    }

    public void toMarkdown(StringBuilder s) {
        s.append(sur);
        for (MDElement anEl : el) {
            anEl.toMarkdown(s);
        }
        s.append(sur);
    }

    public void toHtml(StringBuilder s) {
        s.append(htmlSurOpen);
        for (MDElement anEl : el) {
            anEl.toHtml(s);
        }
        s.append(htmlSurClose);
    }

    public void toTex(StringBuilder s) {
        s.append(texSur);
        for (MDElement anEl : el){
            anEl.toTex(s);
        }
        s.append("}");
    }
}