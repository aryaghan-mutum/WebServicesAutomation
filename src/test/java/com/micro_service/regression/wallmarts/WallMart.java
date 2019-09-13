package com.micro_service.regression.wallmarts;

import base.SuperClass;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WallMart extends SuperClass {
    
    public static void main(String... args) throws Exception {
        
        Stream<String> lines = Files.lines(Paths.get("files", "/Users/anuragmuthyam/Documents/dev/WebServicesAutomation/src/main/resources/data/csv/walmart.csv"));
        List<WallMartHelper> mcdos = lines.map(s -> {
            // -149.95038,61.13712,"McDonalds-Anchorage,AK","3828 W Dimond Blvd, Anchorage,AK, (907) 248-0597"
            // -72.84817,41.27988,"McDonalds-Branford,CT","424 W Main St, Branford,CT, (203) 488-9353"
            String[] strings = s.split(",");
            WallMartHelper mdo = new WallMartHelper();
            mdo.setLatitude(Double.parseDouble(strings[0]));
            mdo.setLongitude(Double.parseDouble(strings[1]));
            mdo.setName(strings[2].substring(1) + strings[3].substring(0, strings[3].length() - 1));
            mdo.setAddress(strings[4].substring(1));
            mdo.setCity(strings[5].trim());
            mdo.setState(strings[6].trim());
            if (mdo.state().endsWith("\"")) {
                mdo.setState(mdo.state().substring(0, mdo.state().length() - 1));
            }
            if (mdo.state().contains(" ")) {
                mdo.setState(mdo.state().substring(0, mdo.state().indexOf(" ")));
            }
            if (mdo.state().length() > 2) {
                mdo.setState(strings[7].trim());
            }
            return mdo;
        }).collect(Collectors.toList());
        
        System.out.println("# of McDos = " + mcdos.size());
        
        // The number of cities that have a McDonald
        long nTowns =
                mcdos.stream()
                        .map(WallMartHelper::city)
                        .collect(Collectors.toSet())
                        .size();
        System.out.println("The number of cities that have a McDonald : " + nTowns);
        
        // The city has the most MacDonald
        Map.Entry<String, Long> entry =
                mcdos.stream()
                        .collect(
                                Collectors.groupingBy(
                                        WallMartHelper::city,
                                        Collectors.counting()
                                )
                        )
                        .entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .get();
        System.out.println("The city has the most MacDonald : " + entry);
        
    }
}
