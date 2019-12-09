package test.sample;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class FundsBaseReqMsg extends FundsBaseMsg {
    LocalDate reqDate;
    LocalTime reqTime;

    @Builder
    public FundsBaseReqMsg(String token, long id, String bizType, String systemNo, LocalDate reqDate, LocalTime reqTime) {
        super(token, id, bizType, systemNo);
        this.reqDate = reqDate;
        this.reqTime = reqTime;
    }
}
