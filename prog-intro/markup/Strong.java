package markup;

import java.util.List;

public class Strong extends AbstractElement{
    public Strong (List<MDElement> el){
        super("__","<strong>","</strong>","\\textbf{", el);
    }
}