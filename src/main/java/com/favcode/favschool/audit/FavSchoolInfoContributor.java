package com.favcode.favschool.audit;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class FavSchoolInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> favMap = new HashMap<>();
        favMap.put("App Name", "Fav School");
        favMap.put("App Description", "Fav School Web Application for Students and Admin");
        favMap.put("App Version", "1.0.0");
        favMap.put("Contact Email", "info@favschool.com");
        favMap.put("Contact Mobile", "+1(414) 364 8567");
        builder.withDetail("favschool-info", favMap);
    }
}
