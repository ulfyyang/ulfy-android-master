package com.ulfy.master.domain.entity;

import java.io.Serializable;
import java.util.List;

public class CountryGroup implements Serializable {
    private static final long serialVersionUID = 7071029313047476654L;
    public String name;
    public List<CountryCode> countryCodeList;
}
