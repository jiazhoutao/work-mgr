package cn.edu.jmu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)
public class NetcaseUnitLog {
    private String wid;
    private String unit_id;
    private String unit_name;
    private String operate;
    private Date operated;
    private String user_id;
    private String error;
}
