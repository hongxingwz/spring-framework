package org.springframework.core.env;

import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * @author jianglei
 * @since 2018/4/4
 */
public class SimpleCommandLineArgsParserTest {

    @Test
    public void testParse() {
        SimpleCommandLineArgsParser parser = new SimpleCommandLineArgsParser();
        CommandLineArgs parse =
                parser.parse("--foo=foo,foo2,foo3", "b", "c", "--bar=bar,bar2,bar3");
        List<String> nonOptionArgs = parse.getNonOptionArgs();
        Set<String> optionNames = parse.getOptionNames();
        System.out.println(nonOptionArgs);
        System.out.println(optionNames);
    }
}
