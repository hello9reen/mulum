package ___.mulum.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RootContext implements Context {
    protected List<Context> children = new ArrayList<Context>(1);

    @Override
    public void addChild(Context context) {
        children.add(context);
    }

    public String parse() {
        return null;
    }
}
