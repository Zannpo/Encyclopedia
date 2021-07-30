package pl.zan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProfessionApi {
    private List<Profession> listOfProfessions;

    public ProfessionApi() {

        listOfProfessions = new ArrayList<>();
        listOfProfessions.add(new Profession(1,"The Witcher","Najemnik wynajmowany do zabijania potworów"));
        listOfProfessions.add(new Profession(2,"Sorceress","Wyszkolony mag"));
        listOfProfessions.add(new Profession(3,"Bard","Zarabia na życie poprzez wykonywanie utworów"));

    }

    @PostMapping("/addProfession")
    public void addProfession(@RequestBody Profession profession) {
        listOfProfessions.add(profession);
    }


    @GetMapping("/getProfessions")
    public List<Profession> getProfessions() {
        return listOfProfessions;
    }
}
