package ___.mulum.parser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SQLTextParser {
    public static final Pattern REGEXP_CONTEXT = Pattern.compile("(?:(?:(?:else[ ]+if|if|for)\\s*\\()(.*?)(\\))|else)\\s*\\{");

    private Context context;
    private CharSequence text;

    public void parse() {
    }
}