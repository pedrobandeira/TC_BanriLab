/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.jobs;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author Pedro
 */
@ManagedBean
@SessionScoped
public class SchedulerJob implements Job {

	@Override
	public void execute(JobExecutionContext context)
		throws JobExecutionException {
		
		System.out.println("Executando rotinas agendadas...");

	}


    
}
