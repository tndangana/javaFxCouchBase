
package zw.co.abn.covid.model;

import lombok.Data;


public class Country extends BaseId{
   
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
