package leetcode.base.java;

import java.lang.reflect.Method;

import org.junit.jupiter.api.function.Executable;

/**
 * 将函数输出与args的某一个位置的元素做对比
 * @author Ryoka Kujo chunxiang.huang@mail.hypers.com
 * @since 2020-09-13
 */
class ExceptArgumentAssertMode implements AssertMode {

    /** 对比的字段在入参的第几位 */
    private final int outputResides;

    ExceptArgumentAssertMode(int outputResides) {
        this.outputResides = outputResides;
    }

    @Override
    public Executable createExecutable(Method method, Object[] args, ExecutionOption option) {
        if (args.length <= method.getParameterCount()) {
            return () -> new PrintExecution(method, args, true, option).executeTestCase();
        } else {
            return () -> new AssignableAssertExecution(method, args, option,
                                                       outputResides).executeTestCase();
        }
    }
}
