package ___.mulum.parser;

import java.util.ArrayList;
import java.util.List;

public class ForContext implements Context {
    private final Context parent;
    private final String loopExpression;

    private final List<Context> children = new ArrayList<Context>(1);

    public ForContext(Context parent, String loopExpression) {
        this.parent = parent;
        this.loopExpression = loopExpression;
    }

    @Override
    public void addChild(Context context) {
        children.add(context);
    }
}
