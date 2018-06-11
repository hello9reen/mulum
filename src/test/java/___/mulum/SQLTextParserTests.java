package ___.mulum;

import ___.mulum.parser.Context;
import ___.mulum.parser.IFContext;
import ___.mulum.parser.RootContext;
import ___.mulum.parser.SQLTextParser;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;

import static java.lang.System.out;

public class SQLTextParserTests {
    private static final String SQL =
            "SELECT ID, NAME, AGE \n" +
                    "  FROM USERS \n" +
                    " WHERE ID IS NOT NULL \n" +
                    "/*\n" +
                    "if (params.age != null && (params.age > 0 && params.age < 50)) {\n" +
                    "  if (param.age == 49) { AND GRADE = '{BIG_BRO}' }\n" +
                    "   AND AGE > :params.age\n" +
                    "}\n" +
                    "if (name != 'test' && name != 'checker') {\n" +
                    "  if (name == 'admin') {\n" +
                    "   AND GRP = 'ADMINISTRATOR' \n" +
                    "  }\n" +
                    "  else if (name == 'qualifier') { \n" +
                    "   AND (GRP = 'QA'\n" +
                    "        OR GRP = 'QC')\n" +
                    "  }\n" +
                    "  else { \n" +
                    "   AND GRP IS NULL\n" +
                    "  }\n" +
                    "}\n" +
                    "*/\n" +
                    "/*\n" +
                    "if (grades != null && grades.length > 0) { \n" +
                    "   AND GRADE IN ( for (i in grades join by ',') { if (i != null) { :i } } ) \n" +
                    "}\n" +
                    "*/\n" +
                    " ORDER BY ID DESC";

    @Test
    public void _01_findIfContext() {
        Matcher m = SQLTextParser.REGEXP_CONTEXT.matcher(SQL);

        int length = 0;
        while (m.find()) {
            length++;

            for (int i = 0; i < m.groupCount(); i++) {
                out.println(String.format("%02d-%02d: %s", length, i, m.group(i)));
            }
        }

        Assert.assertEquals(length, 9);
    }

    @Test
    public void _02_createContext() {
        Matcher m = SQLTextParser.REGEXP_CONTEXT.matcher(SQL);

        Context root = new RootContext();

        if (m.find()) {
            final String syntex = m.group(0);
            final String condition = m.group(1);

            Context ctx = null;

            if (syntex.trim().startsWith("if")) {
                ctx = new IFContext();
            }
        }

        while (m.find()) {

        }
    }
}
