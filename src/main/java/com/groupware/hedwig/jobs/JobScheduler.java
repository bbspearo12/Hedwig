//package com.groupware.hedwig.jobs;
//
//import java.util.Date;
//import org.quartz.JobDetail; 
//import org.quartz.Scheduler; 
//import org.quartz.SimpleTrigger; 
//import org.quartz.impl.StdSchedulerFactory;  
///* This class is used for executing quartz job * using SimpleTrigger(Quartz 1.6.3). * @author javawithease */ 
//public class JobScheduler { 
//	public static void main(String args[]) { 
//		try{ 
//			//Set job details. 
//			JobDetail job = new JobDetail(); 
//			job.setName("HelloWorldJob"); 
//			job.setJobClass(HelloWorldJob.class);  
//			
//			//Set the scheduler timings. 
//			JobScheduler trigger = new JobScheduler(); 
//			trigger.setName("simpleTrigger");
//			trigger.setStartTime(new Date(System.currentTimeMillis())); 
//			
//			//Job will be executed 5 times. 
//			trigger.setRepeatCount(5); 
//			
//			//Execute the job after every 10 seconds.
//			trigger.setRepeatInterval(10000);  
//			
//			//Execute the job. 
//			Scheduler scheduler = new StdSchedulerFactory().getScheduler(); 
//			scheduler.start();
//			scheduler.scheduleJob(job, trigger); 
//			
//		}
//		 catch(Exception e) { 
//			 e.printStackTrace(); 
//			 }
//		} 
//}
//	} 
//}
//
