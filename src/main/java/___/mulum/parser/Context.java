package ___.mulum.parser;

import java.util.regex.Pattern;

public interface Context {
    Pattern REGEXP_DYNAMIC_CONTEXT_BEGIN = Pattern.compile("(?:(?:(?:else[ ]+if|if|for)\\s*\\()(.*?)(\\))|else)\\s*\\{");

    void addChild(Context context);
}
