package org.springframework.core.env;

import java.util.*;

/**
 * 一个带表命令行参数的类，分成"选项参数"和"非选项参数"
 * @author jianglei
 * @since 2018/4/3
 */
class CommandLineArgs {

    private final Map<String, List<String>> optionArgs = new HashMap<>();
    private final List<String> nonOptionArgs = new ArrayList<>();


    /**
     * 添加一个选项参数名并把相关联的值添加到与此选项名关联的列表中（此列表可能含有0个或多个值）。
     * 给定的值可以为{@code null}，表示选项被指定但没有指定值（e.g. "--foo" vs. "--foo=bar")。
     */
    public void addOptionArg(String optionName, String optionValue) {
        if (!this.optionArgs.containsKey(optionName)) {
            this.optionArgs.put(optionName, new ArrayList<String>());
        }

        if (optionValue != null) {
            this.optionArgs.get(optionName).add(optionValue);
        }
    }

    /**
     * 返回出现在命令行的选项参数的集合。
     */
    public Set<String> getOptionNames() {
        return Collections.unmodifiableSet(this.optionArgs.keySet());
    }

    /**
     * 返回是否指定的选项名出现在命令行。
     */
    public boolean containsOption(String optionName) {
        return this.optionArgs.containsKey(optionName);
    }

    /**
     * 返回给定选项相关的值的列表。如果选项不存在则返回{@code null}。
     * 如果没有值与此选项相关联则返回空列表。
     */
    public List<String> getOptionValues(String optionName) {
        return this.optionArgs.get(optionName);
    }

    /**
     * 把给定的非选项参数添加到列表。
     */
    public void addNonOptionArg(String value) {
        this.nonOptionArgs.add(value);
    }

    /**
     * 返回在命令行指定的非选项参数列表。
     */
    public List<String> getNonOptionArgs() {
        return Collections.unmodifiableList(this.nonOptionArgs);
    }
}
