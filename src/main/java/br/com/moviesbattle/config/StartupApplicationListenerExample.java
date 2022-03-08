package br.com.moviesbattle.config;

import br.com.moviesbattle.service.FilmeService;
import br.com.moviesbattle.service.Impl.FilmeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupApplicationListenerExample implements
        ApplicationListener<ContextRefreshedEvent> {

    private final Logger log = LoggerFactory.getLogger(StartupApplicationListenerExample.class);

    @Autowired
    private FilmeService filmeService;

    public static int counter;

    @Override public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Iniciando dados da aplicação");
        filmeService.inserirDadosBD();
    }
}

