package test.sample;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class FundsBaseRetMsg extends FundsBaseMsg {
    LocalDate retDate;
    LocalTime retTime;

    @Builder
    public FundsBaseRetMsg(String token, long id, String bizType, String systemNo, LocalDate retDate, LocalTime retTime) {
        super(token, id, bizType, systemNo);
        this.retDate = retDate;
        this.retTime = retTime;
    }
}
