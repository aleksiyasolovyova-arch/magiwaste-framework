package be.kdg.magiwastebackend.facade;

import be.kdg.magiwastebackend.service.RawDataLogService;
import be.kdg.magiwastebackend.service.WasteBinEventService;
import be.kdg.magiwastebackend.service.WasteBinService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FacadeConfig {

    private final WasteBinService wasteBinService;
    private final WasteBinEventService wasteBinEventService;
    private final RawDataLogService rawDataLogService;

    public FacadeConfig(WasteBinService wasteBinService, WasteBinEventService wasteBinEventService, RawDataLogService rawDataLogService) {
        this.wasteBinService = wasteBinService;
        this.wasteBinEventService = wasteBinEventService;
        this.rawDataLogService = rawDataLogService;
    }

    @Bean
    public ServiceFacade unitTestContext() {
        return new ServiceFacadeImplementation(wasteBinService, wasteBinEventService, rawDataLogService);
    }
}
