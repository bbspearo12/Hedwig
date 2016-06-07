package com.groupware.hedwig.jobs;
/*
import org.apache.log4j.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;	
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class HelloWorldJob implements JobScheduler{

	
	 * public static void hello() { try { // // Grab the Scheduler instance from
	 * the Factory Scheduler scheduler =
	 * StdSchedulerFactory.getDefaultScheduler();
	 * 
	 * // // and start it off scheduler.start(); // // // // define the job and
	 * tie it to our HelloJob class JobDetail job =
	 * JobBuilder.newJob(HelloJob.class) .withIdentity("job1", "group1")
	 * .build();
	 * 
	 * // // Trigger the job to run now, and then repeat every 40 seconds
	 * Trigger trigger = newTrigger() .withIdentity("trigger1", "group1")
	 * .startNow() .withSchedule(simpleSchedule() .withIntervalInSeconds(40)
	 * .repeatForever()) .build();
	 * 
	 * // Tell quartz to schedule the job using our trigger
	 * scheduler.scheduleJob(job, trigger);
	 * 
	 * scheduler.shutdown(); // Thread.sleep(60000);
	 * 
	 * } catch (SchedulerException se) { se.printStackTrace(); } } }
	 
	
//	 Logger log = Logger.getLogger(HelloWorldJob.class);
		
	 public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {
	System.out.println("HelloWorldJob run successfully...");

	 }
	public static void main(String[] args) {

		try {

			// define the job and tie it to our HelloJob class
			JobDetail job = JobBuilder.newJob()
									  .withIdentity("testJob")
									  .build();

			// Trigger the job to run on the next round minute
			Trigger trigger = TriggerBuilder.newTrigger()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInSeconds(30)
							.repeatForever())
					        .build();

			// schedule the job
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sch = sf.getScheduler();
			sch.start();
			sch.scheduleJob(job, trigger);
			// sch.shutdown(true);
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
*/

import org.quartz.Job; 
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;   
/* This class defines a quartz job. * @author javawithease */ 

public class HelloWorldJob implements Job { 
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("Hello World.");
		} 
	} 






