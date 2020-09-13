package leetcode.base.java;

import java.lang.reflect.Method;

import org.junit.platform.commons.util.StringUtils;

/**
 * @author Ryoka Kujo chunxiang.huang@mail.hypers.com
 * @since 2020-09-13
 */
class PrintExecution extends Execution {

    PrintExecution(Method method, Object[] args, DiffMode diffMode) {
        super(method, args, diffMode, false);
    }

    @Override
    public void assertions(Object methodOutput) {
        System.out.println(StringUtils.nullSafeToString(methodOutput));
    }

    @Override
    protected int argsLength() {
        return args.length;
    }
}
