package be.kdg.magiwastebackend;

import be.kdg.magiwastebackend.domain.WasteBin;
import be.kdg.magiwastebackend.domain.WasteBinEvent;
import be.kdg.magiwastebackend.facade.ServiceFacade;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class MagiwasteBackendApplication implements CommandLineRunner {

    //TODO this is temporary to put two objects for testing purposes
    private final ServiceFacade serviceFacade;

    //TODO this is temporary to put two objects for testing purposes
    public MagiwasteBackendApplication(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    public static void main(String[] args) {
        SpringApplication.run(MagiwasteBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //TODO this is temporary to put two objects for testing purposes
        WasteBin bin1 = new WasteBin("Pothoekstraat 125, 2060 Antwerpen", 51.226082916679836, 4.436500484662087);
        bin1.setId(1L);
        WasteBinEvent binEvent1 = new WasteBinEvent(bin1, 0.6f, 40.0, false, LocalDate.now());
        serviceFacade.saveWasteBin(bin1);
        serviceFacade.saveWasteBinEvent(binEvent1);
    }
}
