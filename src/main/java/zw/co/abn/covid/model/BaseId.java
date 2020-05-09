
package zw.co.abn.covid.model;

import java.time.LocalDate;
import lombok.Data;

@Data
public abstract class BaseId {
    
    private String id;
    private LocalDate createdDate = LocalDate.now();
    private LocalDate updatedDate = LocalDate.now();
}
