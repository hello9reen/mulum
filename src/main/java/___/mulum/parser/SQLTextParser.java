package ___.mulum.parser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SQLTextParser {
    private Context context;
    private CharSequence sql;

    public void parse() {
    }
}