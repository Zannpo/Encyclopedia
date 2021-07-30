package pl.zan;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CharacterApi {
       private List<Character> listOfCharacters;

    public CharacterApi() {


        listOfCharacters = new ArrayList<>();

        listOfCharacters.add(new Character(1, "Geralt",1));
        listOfCharacters.add(new Character(2, "Vesemir",1));
        listOfCharacters.add(new Character(3, "Ciri",1));
        listOfCharacters.add(new Character(4, "Triss Merigold",2));
        listOfCharacters.add(new Character(5, "Yennefer",2));
        listOfCharacters.add(new Character(6, "Jaskier",3));
        listOfCharacters.add(new Character(7, "Lambert",1));


    }


    @PostMapping("/addCharacter")
    public void addCharacter(@RequestBody Character character) {
        listOfCharacters.add(character);
    }


    @GetMapping("/getCharacters")
    public List<Character> getCharacters() {
        return listOfCharacters;
    }
}