package ___.mulum.parser;

public class PlainContext implements Context {
    private final Context parent;
    private final String sql;

    public PlainContext(Context parent) {
        this.parent = parent;
        this.sql = "";
    }

    public PlainContext(Context parent, String sql) {
        this.parent = parent;
        this.sql = sql;
    }

    @Override
    public void addChild(Context context) {

    }
}
