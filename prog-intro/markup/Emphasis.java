package markup;

import java.util.List;

public class Emphasis extends AbstractElement{
    public Emphasis (List<MDElement> el){
        super("*","<em>","</em>","\\emph{", el);
    }
}