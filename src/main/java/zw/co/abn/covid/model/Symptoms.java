
package zw.co.abn.covid.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Symptoms extends BaseId{
    
    private String name;


    public Symptoms(String name) {
        this.name = name;
    }

}
