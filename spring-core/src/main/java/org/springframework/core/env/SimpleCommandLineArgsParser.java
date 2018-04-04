package org.springframework.core.env;

/**
 * 解析命令行参数的{@code String[]}用于填充一个{@link CommandLineArgs}对象
 *
 * <h3>处理选项参数</h3>
 * 选项参数一定要遵守下面的语法：
 * <pre class="code">--optName[=optValue]</pre>
 * 也就是，选项一定要以"{@code --}"作为前缀，只写不只定值都可以。
 * 如果一个值被指定，那么名字和值一定要由等于号("=")分割，且中间不带
 * <em>空格</em>
 *
 * <h4>有效的选项参数示例</h4>
 * <pre class="code">
 *     --foo
 *     --foo=bar
 *     --foo="bar then baz"
 *     --foo=bar,baz,biz
 * </pre>
 *
 * <h4>无效的选项参数示例</h4>
 * <pre class="code">
 *     -foo
 *     --foo bar
 *     --foo = bar
 *     --foo=bar --foo=baz --foo=biz
 * </pre>
 *
 * <h3>处理非选项参数</h3>
 * 在命令行出现的所有的不带"{@code --}"前缀的选项都被认为是"non-option arguments"且可以由
 * {@link CommandLineArgs#getNonOptionArgs()}方法获取。
 * @author jianglei
 * @since 2018/4/4
 */
class SimpleCommandLineArgsParser {

    /**
     * 基于上面{@linkplain SimpleCommandLineArgsParser}描述的规则解析给定的字符串，
     * 返回填充后的{@link CommandLineArgs}对象。
     * @param args 命令行参数，通常由{@code main()}获取
     */
    public CommandLineArgs parse(String... args) {
        CommandLineArgs commandLineArgs = new CommandLineArgs();
        for (String arg : args) {
            if (arg.startsWith("--")) {
                String optionText = arg.substring(2, arg.length());
                String optionName;
                String optionValue = null;
                if (optionText.contains("=")) {
                    optionName = optionText.substring(0, optionText.indexOf("="));
                    optionValue = optionText.substring(optionText.indexOf("=") + 1, optionText.length());
                } else {
                    optionName = optionText;
                }
                if (optionName.isEmpty() || (optionValue != null && optionValue.isEmpty())) {
                    throw new IllegalArgumentException("Invalid argument syntax: " + arg);
                }
                commandLineArgs.addOptionArg(optionName, optionValue);
            } else {
                commandLineArgs.addNonOptionArg(arg);
            }
        }

        return commandLineArgs;
    }

}
