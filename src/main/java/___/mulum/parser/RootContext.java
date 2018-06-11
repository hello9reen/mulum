package ___.mulum.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RootContext implements Context {
    protected List<Context> contexts = new ArrayList<Context>(1);
    protected List<String> parameterNames = new ArrayList<String>(1);
    protected Map<String, Object> parameters = new HashMap<String, Object>(1);

    @Override
    public StringBuilder getSql() {
        return null;
    }

    @Override
    public List<String> getParameterNames() {
        return parameterNames;
    }

    @Override
    public Map<String, Object> getParameters() {
        return parameters;
    }

    @Override
    public void addContext(Context context) {
        this.contexts.add(context);
    }

    public void parse(CharSequence text) {

    }
}
