package com.fshows.commons.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlipayBankcardInfo {

    //@NotBlank
    //@Length(max = 20)
    private String cardNo;
    //@Length(max = 64)
    private String cardName;

    public AlipayBankcardInfo() {
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public AlipayBankcardInfo(String cardNo, String cardName) {
        this.cardNo = cardNo;
        this.cardName = cardName;
    }
}
