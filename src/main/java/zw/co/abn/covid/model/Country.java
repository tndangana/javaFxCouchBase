
package zw.co.abn.covid.model;


import lombok.Data;

//Model which holds attributes for country extended to BaseId
@Data
//@Data annotation generates getter and setters
public class Country extends BaseId{

    //attribute
    private String name;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }



}
