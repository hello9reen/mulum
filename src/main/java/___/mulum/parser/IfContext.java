package ___.mulum.parser;

import java.util.ArrayList;
import java.util.List;

public class IfContext implements Context {
    private final Context parent;
    private final String condition;

    private final List<Context> children = new ArrayList<Context>(1);

    private List<Context> elseIf = new ArrayList<Context>(1);


    public IfContext(Context parent) {
        this.parent = parent;
        this.condition = null;
    }

    public IfContext(Context parent, String condition) {
        this.parent = parent;
        this.condition = condition;
    }

    public void addElse(Context _else) {
        this.elseIf.add(_else);
    }

    @Override
    public void addChild(Context context) {

    }
}
