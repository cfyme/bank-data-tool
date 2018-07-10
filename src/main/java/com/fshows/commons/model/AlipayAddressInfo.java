package com.fshows.commons.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fshows.commons.util.MyStringUtil;

@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlipayAddressInfo {

    /**
     * 省代码
     */
    private String provinceCode;
    /**
     * 市代码
     */
    private String cityCode;
    /**
     * 区县代码
     */
    private String districtCode;
    /**
     * 经营地址
     */
    private String address;

    /**
     * 地址类型
     */
    private String type;

    public AlipayAddressInfo() {
    }

    public AlipayAddressInfo(String provinceCode, String cityCode, String districtCode,
                             String address) {
        this.provinceCode = provinceCode;
        this.cityCode = cityCode;
        this.districtCode = districtCode;
        this.address = address;
    }

    public AlipayAddressInfo(String provinceCode, String cityCode, String districtCode, String address, String type) {
        this.provinceCode = provinceCode;
        this.cityCode = cityCode;
        this.districtCode = districtCode;
        this.address = address;
        this.type = type;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getAddress() {

        return MyStringUtil.trim(address);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
