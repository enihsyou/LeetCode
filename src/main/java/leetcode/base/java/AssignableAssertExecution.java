package leetcode.base.java;

import java.lang.reflect.Method;

import org.assertj.core.util.Preconditions;

/**
 * @author Ryoka Kujo chunxiang.huang@mail.hypers.com
 * @since 2020-09-13
 */
public class AssignableAssertExecution extends AssertExecution {

    /** 函数期望的输出值在args中的位置 */
    private final int outputResides;

    AssignableAssertExecution(Method method, Object[] args, ExecutionOption option, int outputResides) {
        super(method, args, option);
        this.outputResides = outputResides;
    }

    @Override
    public void assertions(Object methodOutput) {
        super.assertions(args[outputResides]);
    }

    @Override
    protected void outputArgsPreconditions() {
        Preconditions.checkState(outputResides < method.getParameterCount(),
                                 "函数应该原地修改参数，" +
                                 "其他情况推荐使用AssertMode.exceptOutputMode");
        Preconditions.checkState(method.getReturnType() == void.class,
                                 "函数应该返回void，" +
                                 "其他情况推荐使用AssertMode.exceptOutputMode");
    }
}
