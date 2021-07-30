package pl.zan;

public class Character {
    private long id;
    private String name;
    private long profession;


    public Character(long id, String name, long profession) {
        this.id = id;
        this.name = name;
        this.profession = profession;
    }

    public double getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getProfession() {
        return profession;
    }

    public void setProfession(long profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", profession=" + profession +
                '}';
    }
}
