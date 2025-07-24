package markup;

import java.util.List;

public class Strikeout extends AbstractElement{
    public Strikeout (List<MDElement> el){
        super("~","<s>","</s>","\\textst{", el);
    }
}