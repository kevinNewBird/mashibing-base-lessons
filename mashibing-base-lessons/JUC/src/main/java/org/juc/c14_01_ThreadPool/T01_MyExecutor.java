package org.juc.c14_01_ThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/22 14:43
 * @version: 1.0
 ***********************/
public class T01_MyExecutor implements Executor {

    public static void main(String[] args) {
        new T01_MyExecutor().execute(() -> System.out.println("hello executor!"));
    }


    /**
     * Executes the given command at some time in the future.  The command
     * may execute in a new thread, in a pooled thread, or in the calling
     * thread, at the discretion of the {@code Executor} implementation.
     *
     * @param command the runnable task
     * @throws RejectedExecutionException if this task cannot be
     *                                    accepted for execution
     * @throws NullPointerException       if command is null
     */
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
