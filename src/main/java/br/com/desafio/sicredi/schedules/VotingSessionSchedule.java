package br.com.desafio.sicredi.schedules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.desafio.sicredi.service.impl.PautaServiceImp;

@Component
@EnableScheduling
public class VotingSessionSchedule {

	@Autowired
	PautaServiceImp pautaService;
	
	@Bean
	@Scheduled(cron = "0 * * * * *")
	public void updateBase() {
		pautaService.updateCompletedPautas();
	}
}