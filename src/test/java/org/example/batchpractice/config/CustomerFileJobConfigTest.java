package org.example.batchpractice.config;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBatchTest
@SpringBootTest
class CustomerFileJobConfigTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobExplorer jobExplorer;

    @Autowired
    @Qualifier("customerFileJob") // 특정 Job을 명시적으로 주입
    private Job customerFileJob;

    @Test
    void customerFileJobTest() throws Exception {
        // Given
        JobParameters jobParameters = new JobParametersBuilder(jobExplorer)
                .addString("datetime", LocalDateTime.now().toString())
                .toJobParameters();
        jobLauncherTestUtils.setJob(customerFileJob);

        // When
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        // Then
        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
    }
}