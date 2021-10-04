package com.blue.project.modules.quartz.jobs;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldJob implements Job {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>
     * Called by the <code>{@link Scheduler}</code> when a <code>{@link Trigger}</code>
     * fires that is associated with the <code>Job</code>.
     * </p>
     *
     * <p>
     * The implementation may wish to set a
     * {@link org.quartz.JobExecutionContext#setResult(Object) result} object on the
     * {@link org.quartz.JobExecutionContext} before this method exits.  The result itself
     * is meaningless to Quartz, but may be informative to
     * <code>{@link JobListener}s</code> or
     * <code>{@link TriggerListener}s</code> that are watching the job's
     * execution.
     * </p>
     *
     * @param context
     * @throws org.quartz.JobExecutionException if there is an exception while executing the job.
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        this.logger.info("Hello world");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
        }
        this.logger.info("Good Bye world");
    }
}
