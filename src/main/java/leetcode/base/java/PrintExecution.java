package leetcode.base.java;

import java.lang.reflect.Method;

import org.junit.platform.commons.util.StringUtils;
import org.opentest4j.TestAbortedException;

/**
 * @author Ryoka Kujo chunxiang.huang@mail.hypers.com
 * @since 2020-09-13
 */
class PrintExecution extends Execution {

    /** 执行完以后标注错误 */
    private final boolean abortAtLast;

    PrintExecution(Method method, Object[] args, DiffMode diffMode, boolean abortAtLast) {
        super(method, args, diffMode);
        this.abortAtLast = abortAtLast;
    }

    @Override
    public void assertions(Object methodOutput) {
        System.out.println(StringUtils.nullSafeToString(methodOutput));
        if (abortAtLast) {
            throw new TestAbortedException("print output without assertion.");
        }
    }

    @Override
    protected int argsLength() {
        return args.length;
    }
}
