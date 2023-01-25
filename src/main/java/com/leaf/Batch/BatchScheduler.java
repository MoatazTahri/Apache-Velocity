package com.leaf.Batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

@Component
@EnableScheduling
@Slf4j
public class BatchScheduler {
    @Autowired
    private Job job;

    @Autowired
    private JobLauncher launcher;

    @Scheduled(fixedDelay = 30, timeUnit = TimeUnit.SECONDS)
    public void repeat() {
        // schedule recupere le fichier de conf
        Calendar date = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        log.info("job executed at " + sdf.format(date.getTime()));
        JobParameters parameters = new JobParametersBuilder().addLong("start", System.currentTimeMillis()).toJobParameters();
        try {
            launcher.run(job, parameters);
        } catch (JobExecutionException e) {
            e.printStackTrace();
        }
    }
}
