package _.mulum;

import org.junit.Test;
import org.springframework.util.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class SQLTextParserDoubleDashTests {
    private static final String SQL =
            "\nSELECT ID, NAME, AGE " +
            "\n  FROM USERS " +
            "\n WHERE ID NOT NULL " +
            "\n-- if (params.age != null && (params.age > 0 &&" +
            "\n params.age < 50)) { if (param.age == 49) { /* you are big bro! */ }" +
            "\n   AND AGE > :params.age" +
            "\n-- }" +
            "\n-- if (name != 'test' && name != 'checker') {" +
            "\n--   if (name == 'admin') {" +
            "\n   AND GRP = 'ADMINISTRATOR' " +
            "\n--   } else if (name == 'checker') { " +
            "\n   AND (GRP = 'QA'" +
            "\n        OR GRP = 'QC')" +
            "\n--   }" +
            "\n-- }" +

            "\n-- if (grades != null && grades.length > 0) { " +
            "\n   AND GRADE IN (" +
            " --    for (i in grades join by ',') { :i } " +
            "\n   )" +
            "\n-- }";

    @Test
    public void _01_findIfContext() {

        Matcher m = Pattern.compile("(?s)(?:--|/\\(.*)").matcher(SQL);

        while (m.find()) {
            for (int i = 0; i < m.groupCount(); i++) {
//                m.start(i);
                out.println(String.format("%2d: %s", i, m.group(i)));
            }

            out.println();
        }
    }
}
