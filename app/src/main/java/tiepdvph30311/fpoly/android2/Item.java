package tiepdvph30311.fpoly.android2;

public class Item {
    private long id;
    private String itemName;
    private int age;

    public Item(long id, String itemName, int age) {
        this.id = id;
        this.itemName = itemName;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public int getAge() {
        return age;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
