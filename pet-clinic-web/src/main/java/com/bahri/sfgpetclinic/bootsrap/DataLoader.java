package com.bahri.sfgpetclinic.bootsrap;

import com.bahri.sfgpetclinic.model.*;
import com.bahri.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;


    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType dogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType catPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("RadioLogy");

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");

        Speciality savedRadiology= specialityService.save(radiology);

        Speciality savedSurgey = specialityService.save(surgery);

        Owner owner1 = new Owner();
        owner1.setFirstName("Raed");
        owner1.setLastName("Bahri");
        owner1.setAddress("capzebib");
        owner1.setCity("Bizerte");
        owner1.setTelephone("23989478");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(dogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setLocalDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Amir");
        owner2.setLastName("Bahri");
        owner2.setAddress("el ghazela");
        owner2.setCity("Tunis");
        owner2.setTelephone("55555555");

        Pet fionasPet = new Pet();
        fionasPet.setPetType(catPetType);
        fionasPet.setOwner(owner2);
        fionasPet.setLocalDate(LocalDate.now());
        fionasPet.setName("Just Cat");
        System.out.println(catPetType.toString());
        owner2.getPets().add(fionasPet);

        ownerService.save(owner2);

        System.out.println("Saving Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Dad");
        vet1.setLastName("Bob");
        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Red");
        vet2.setLastName("Yell");
        vet2.getSpecialities().add(savedSurgey);

        vetService.save(vet2);


        Visit visit = new Visit();
        visit.setPet(fionasPet);
        visit.setDate(LocalDate.now());
        visit.setDescription("Sneezy Kitty");

        visitService.save(visit);
    }
}
