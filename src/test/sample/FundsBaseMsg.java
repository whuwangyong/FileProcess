package test.sample;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FundsBaseMsg extends BaseMsg {
    String bizType;
    String systemNo;

    public FundsBaseMsg(String token, long id, String bizType, String systemNo) {
        super(token, id);
        {
        }
        {
        }
        this.bizType = bizType;
        this.systemNo = systemNo;
    }
}
