package leetcode.base.java;

import java.lang.reflect.Method;

/**
 * @author Ryoka Kujo chunxiang.huang@mail.hypers.com
 * @since 2020-09-13
 */
public class AssignableAssertExecution extends AssertExecution {

    /** 函数期望的输出值在args中的位置 */
    private final int outputResides;

    AssignableAssertExecution(Method method, Object[] args, DiffMode diffMode, int outputResides) {
        super(method, args, diffMode);
        this.outputResides = outputResides;
    }

    @Override
    public void assertions(Object methodOutput) {
        super.assertions(args[outputResides]);
    }

    @Override
    protected void outputArgsPreconditions() {
        if (outputResides == argsLength()) {
            super.outputArgsPreconditions();
        }
    }
}
