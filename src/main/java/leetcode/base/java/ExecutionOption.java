package leetcode.base.java;

/**
 * @author Ryoka Kujo chunxiang.huang@mail.hypers.com
 * @since 2020-10-10
 */
public class ExecutionOption {

    private boolean allowNullOutput = false;

    public boolean allowNullOutput() {
        return allowNullOutput;
    }

    public void allowNullOutput(boolean flag) {
        this.allowNullOutput = flag;
    }
}
