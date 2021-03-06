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

        System.out.println("Wybierz z menu co chcesz zrobic.");

        OUTER:
        while (true) {
            System.out.println("A)Pokaz wszystko B)Pokaz liste profesji C) Pokaz liste postaci");
            System.out.println("D)Wyszukaj postac wedlug imienia");
            System.out.println("X)Zamknij menu");
            decision = scan.next();

            switch (decision) {
                case "X":
                    break OUTER;
                case "A":
                    try {

                        System.out.println("Lista istniejacych profesji:");
                        System.out.println();
                        professions.stream().forEach(System.out::println);
                        System.out.println();

                        System.out.println("Lista istniejacych postaci:");

                        characters.stream().forEach(System.out::println);
                        System.out.println();
                        System.out.println("Koniec encyklopedii.");
                    }
                    catch(Exception e)
                    {
                        System.out.println("Cos poszlo nie tak!");
                    }
                    break;
                case "B":
                    try{
                        System.out.println("Lista istniejacych profesji:");
                        System.out.println();
                        professions.stream().forEach(System.out::println);
                        System.out.println();
                    }
                    catch(Exception e)
                    {
                        System.out.println("Cos poszlo nie tak!");
                    }
                    break;
                case "C":
                    try{
                    System.out.println("Lista istniejacych postaci:");
                    characters.stream().forEach(System.out::println);
                    System.out.println();
                    }
                    catch(Exception e)
                {
                    System.out.println("Cos poszlo nie tak!");
                }
                    break;

                case "D":
                    try {
                        System.out.println("Podaj szukane imie");
                        String name = scan.next();
                        Character characterByName = getCharacterByName(name);
                        System.out.println(characterByName);
                        System.out.println(name);
                    }
                    catch(Exception e)
                {
                    System.out.println("Cos poszlo nie tak!");
                }
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
            System.out.println("Profesja dodana pomyslnie!");
        } catch (IOException e) {

            System.out.println("Cos poszlo nie tak!");
            e.printStackTrace();
        }
    }

    private List<Profession> getProfessions() {
        ProfessionServices service = getProfessionsServiceImpl();
        Response<List<Profession>> response = null;
        try {
            response = service.getProfessions().execute();
        } catch (IOException e) {

            System.out.println("Cos poszlo nie tak!");
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

            System.out.println("Cos poszlo nie tak!");
            e.printStackTrace();
        }
        return response.body();
    }

    private Character getCharacterByName(String name) {
        CharacterServices service = getCharacterServiceImpl();
        Response<Character> response = null;

        try {
            response = service.getCharacterByName(name).execute();
        } catch (IOException e) {

            System.out.println("Cos poszlo nie tak!");
            e.printStackTrace();
        }
        return response.body();
    }

    private void addCharacter(Character character) {
        CharacterServices service = getCharacterServiceImpl();
        try {
            service.addCharacter(character).execute();
            System.out.println("Postac dodana pomyslnie!");
        } catch (IOException e) {

            System.out.println("Cos poszlo nie tak!");
            e.printStackTrace();
        }
    }
}
