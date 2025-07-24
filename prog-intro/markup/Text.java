package markup;

public class Text implements MDElement{
    private String text;

    public Text(String s){
        text = s;
    }

    public void toMarkdown(StringBuilder s){
        s.append(text);
    }

    public void toHtml(StringBuilder s) {
        s.append(text);
    }

    @Override
    public void toTex(StringBuilder s) {
        s.append(text);
    }
}