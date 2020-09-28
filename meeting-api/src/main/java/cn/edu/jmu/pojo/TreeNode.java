package cn.edu.jmu.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
@SuppressWarnings("serial")
@NoArgsConstructor
@Data
public class TreeNode  implements Serializable,Comparable<TreeNode> {
    private String key;
    private String title;
    private String pid;
    private Integer sequence;
    private Integer orgPropertyId;
    private String staffId;
    private List<TreeNode> children;
    private Boolean isLeaf;


    @Override
    public int compareTo(TreeNode o) {
        if(this.getSequence()==null){
            this.setSequence(0);
        }
        if(o.getSequence()==null){
            o.setSequence(0);
        }
        return this.getSequence()-o.getSequence();
    }
}
