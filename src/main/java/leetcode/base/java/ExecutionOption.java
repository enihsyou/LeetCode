package leetcode.base.java;

/**
 * @author Ryoka Kujo chunxiang.huang@mail.hypers.com
 * @since 2020-10-10
 */
@SuppressWarnings("UnusedReturnValue")
public class ExecutionOption {

    /** 禁用校验输出为null */
    private boolean allowNullOutput = false;

    /** 定义结果比较模式 */
    private DiffMode diffMode = DiffMode.EXACTLY;

    /** 定义结果判断模式 */
    private AssertMode assertMode = AssertMode.exceptOutputMode();

    public boolean allowNullOutput() {
        return allowNullOutput;
    }

    public ExecutionOption allowNullOutput(boolean flag) {
        this.allowNullOutput = flag;
        return this;
    }

    public DiffMode diffMode() {
        return diffMode;
    }

    public ExecutionOption diffMode(DiffMode diffMode) {
        this.diffMode = diffMode;
        return this;
    }

    public AssertMode assertMode() {
        return assertMode;
    }

    public ExecutionOption assertMode(AssertMode assertMode) {
        this.assertMode = assertMode;
        return this;
    }
}
