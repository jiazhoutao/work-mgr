package cn.edu.jmu.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@SuppressWarnings("serial")
@NoArgsConstructor
@Data
@Accessors(chain=true)
public class Staff {
    private String id;
    private String name;

    public Staff(String id){
        this.id=id;
    }
}
