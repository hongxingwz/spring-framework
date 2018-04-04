package org.springframework.core.env;

import java.util.List;

/**
 * @author jianglei
 * @since 2018/4/4
 */
public class SimpleCommandLinePropertySource extends CommandLinePropertySource<CommandLineArgs> {

    public SimpleCommandLinePropertySource(String... args) {
        super(new SimpleCommandLineArgsParser().parse(args));
    }

    public SimpleCommandLinePropertySource(String name, String... args) {
        super(name, new SimpleCommandLineArgsParser().parse(args));
    }

    @Override
    public String[] getPropertyNames() {
        return source.getOptionNames().toArray(new String[source.getOptionNames().size()]);
    }


    @Override
    protected boolean containsOption(String name) {
        return this.source.containsOption(name);
    }

    @Override
    protected List<String> getOptionValues(String name) {
        return this.source.getOptionValues(name);
    }

    @Override
    protected List<String> getNonOptionArgs() {
        return this.source.getNonOptionArgs();
    }


}
