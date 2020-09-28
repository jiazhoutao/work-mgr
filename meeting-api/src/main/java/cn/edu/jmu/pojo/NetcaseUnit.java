package cn.edu.jmu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)
public class NetcaseUnit {
    private String id;
    private String name;
    private String parentId;
    private String neteaseId;
    private String synced;
    private String error;
    private String updated;
    private String lastUpdated;

    @Override
    public String toString() {
        return "id=" + id +
                "&name=" + name +
                "&parent_id=" + parentId +
                "&synced=" + synced +
                "&error=" + error +
                "&updated=" + updated +
                "&last_updated=" + lastUpdated;
    }
}
