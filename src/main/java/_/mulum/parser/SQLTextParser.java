package _.mulum.parser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SQLTextParser {
    private Context context;
    private CharSequence text;

    public void parse() {
    }
}