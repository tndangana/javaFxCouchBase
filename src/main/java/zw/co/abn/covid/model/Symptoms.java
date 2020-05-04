
package zw.co.abn.covid.model;


public class Symptoms extends BaseId{
    
    private String name;


    public Symptoms() {
    }

    public Symptoms(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
