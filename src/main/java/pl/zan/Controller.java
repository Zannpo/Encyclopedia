package pl.zan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RestController;
import retrofit.Response;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@RestController
public class Controller {

    private Retrofit retrofit;

    @Autowired
    public Controller(Retrofit retrofit) {
        this.retrofit = retrofit;

    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        List<Profession> professions = getProfessions();
        List<Character> characters = getCharacters();
        Scanner scan = new Scanner(System.in);
        String decision;

        System.out.println("Wybierz z menu co chcesz zrobić.");

        OUTER:
        while (true) {
            System.out.println("A)Show everything");

            System.out.println("X)Close program");
            decision = scan.next();

            switch (decision) {
                case "X":
                    break OUTER;
                case "A":
                    System.out.println("Lista istniejacych profesji:");
                    System.out.println();

                    professions.stream().forEach(System.out::println);
                    System.out.println();
                    System.out.println("Lista istniejacych postaci:");

                    characters.stream().forEach(System.out::println);
                    System.out.println();
                    System.out.println("Koniec encyklopedii.");
                    break;

            }
        }
    }


    private ProfessionServices getProfessionsServiceImpl() {
        return retrofit.getRetrofit().create(ProfessionServices.class);
    }

    private void addProfession(Profession profession) {
        ProfessionServices service = getProfessionsServiceImpl();
        try {
            service.addProfession(profession).execute();
            System.out.println("Profesja dodana pomyślnie!");
        } catch (IOException e) {

            System.out.println("Coś poszło nie tak!");
            e.printStackTrace();
        }
    }

    private List<Profession> getProfessions() {
        ProfessionServices service = getProfessionsServiceImpl();
        Response<List<Profession>> response = null;
        try {
            response = service.getProfessions().execute();
        } catch (IOException e) {

            System.out.println("Coś poszło nie tak!");
            e.printStackTrace();
        }
        return response.body();
    }

    private CharacterServices getCharacterServiceImpl() {
        return retrofit.getRetrofit().create(CharacterServices.class);
    }

    private List<Character> getCharacters() {
        CharacterServices service = getCharacterServiceImpl();
        Response<List<Character>> response = null;
        try {
            response = service.getCharacter().execute();
        } catch (IOException e) {

            System.out.println("Coś poszło nie tak!");
            e.printStackTrace();
        }
        return response.body();
    }

    private void addCharacter(Character character) {
        CharacterServices service = getCharacterServiceImpl();
        try {
            service.addCharacter(character).execute();
            System.out.println("Postać dodana pomyślnie!");
        } catch (IOException e) {

            System.out.println("Coś poszło nie tak!");
            e.printStackTrace();
        }
    }
}
