package com.coreservlets.networking;

import java.util.HashMap;
import java.util.Map;

public class LoanInputs {
    private Map<String,String> mInputMap;

    public LoanInputs(String amount, String rate, String months) {
        mInputMap = new HashMap<String,String>();
        mInputMap.put("amount", amount);
        mInputMap.put("rate", rate);
        mInputMap.put("months", months);
    }

    public Map<String,String> getInputMap() {
        return(mInputMap);
    }
}
