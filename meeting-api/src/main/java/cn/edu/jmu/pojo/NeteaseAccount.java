package cn.edu.jmu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)
public class NeteaseAccount {

    private String accountName;
    private String addrRight;
    private String addrVisible;
    private String expTime;
    private String fwd;
    private String fwdauth;
    private String jobNo;
    private String mobile;
    private String nickname;
    private String passType;
    private String passchangeReq;
    private String password;
    private String resetpassMobile;
    private String resetpassGeneral;
    private String unitId;
    private String isNew;
    private String needChangeInfo;
    private String needChangeUnit;
    private String needChangePassword;
    private String creaded;
    private String synced;
    private String enabled;
    private String error;
    private String updated;
    private String lastUpdated;

    @Override
    public String toString() {
        return "account_name=" + accountName +
                "&addr_right=" + addrRight +
                "&addr_visible=" + addrVisible +
                "&exp_time=" + expTime +
                "&fwd=" + fwd +
                "&fwdauth=" + fwdauth +
                "&job_no=" + jobNo +
                "&mobile=" + mobile +
                "&nickname=" + nickname +
                "&pass_type=" + passType +
                "&passchange_req=" + passchangeReq +
                "&password=" + password +
                "&resetpass_mobile=" + resetpassMobile +
                "&resetpass_general=" + resetpassGeneral +
                "&unit_id=" + unitId +
                "&is_new=" + isNew +
                "&need_Change_Info=" + needChangeInfo +
                "&need_Change_Unit=" + needChangeUnit +
                "&need_Change_Password=" + needChangePassword +
                "&creaded=" + creaded +
                "&synced=" + synced +
                "&enabled=" + enabled +
                "&error=" + error;
    }
}
