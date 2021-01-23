package com.jerome.quartz;

//import com.jerome.common.util.DateUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class TestJobService1 implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("TestJobService1 is work ..");
		test();
	}

	private boolean from;

	public TestJobService1() {
	}

	public TestJobService1(boolean from) {
		this.from = from;
	}

	public void test(){
		System.out.println(from);
//		System.out.println(DateUtils.formatDate(new Date(), DateUtils.YYYYMMDDHHMMSS));
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(from);
	}
}
