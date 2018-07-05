package ___.mulum;

import ___.mulum.parser.*;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;

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
                    "*/ \n" +
                    "   AND GRP IS NULL\n" +
                    "   AND AGE > 0 \n" +
                    "/*\n" +
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
    public void _01_parse() {
        Context context = new RootContext();

        Matcher m = Context.REGEXP_DYNAMIC_CONTEXT_BEGIN.matcher(SQL);
        int cursor = 0;

        if (m.find()) {
            int start = m.start();
            String sql = SQL.substring(cursor, start);

            if (!StringUtils.isEmpty(sql)) {
                context.addChild(new PlainContext(context, sql));
                cursor = start;
            }

            do {
                final String type = m.group(1).trim().toLowerCase();
                final String condition = m.group(2).trim();
                final Context typeContext;

                if (type.startsWith("if")) {
                    typeContext = new IfContext(context, condition);
                    cursor = m.end(2);
                }


            }
            while (m.find(cursor));
        }
    }

    private int findContextEndpoint(Context parent, String sql, int cursor) {
        Matcher m = Context.REGEXP_DYNAMIC_CONTEXT_BEGIN.matcher(sql);

        if (m.find(cursor)) {
            Context context = null;

            final String type = m.group(1).trim().toLowerCase();
            switch (type) {
                case "if":
                case "else if":
                case "else":
                    context = new IfContext(parent, m.group(2));
                    break;
                case "for":
                    context = new ForContext(parent, m.group(2));
                    break;
                default:
                    throw new IllegalStateException("Unknown type: " + type);
            }

            parent.addChild(context);
            return findContextEndpoint(context, sql, cursor);
        }
    }
}
