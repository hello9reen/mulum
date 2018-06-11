package ___.mulum.parser;

import java.util.List;
import java.util.Map;

public interface Context {
    StringBuilder getSql();
    List<String> getParameterNames();
    Map<String, Object> getParameters();

    void addContext(Context context);
}
