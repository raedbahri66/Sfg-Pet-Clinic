package com.bahri.sfgpetclinic.bootsrap;

import com.bahri.sfgpetclinic.model.Owner;
import com.bahri.sfgpetclinic.model.Vet;
import com.bahri.sfgpetclinic.services.OwnerService;
import com.bahri.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;


    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Raed");
        owner1.setLastName("Bahri");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Amir");
        owner2.setLastName("Bahri");

        ownerService.save(owner2);

        System.out.println("Saving Owners....");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Dad");
        vet1.setLastName("Bob");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet1.setId(2L);
        vet1.setFirstName("Red");
        vet1.setLastName("Yell");
        vetService.save(vet2);
    }
}
