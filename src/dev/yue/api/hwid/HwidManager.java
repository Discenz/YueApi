package dev.yue.api.hwid;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import dev.yue.api.manager.Manager;

public class HwidManager extends Manager<String> {

    private URL url;
    
    public HwidManager (String id, String url) {
        super(id);

        if (this.id == null) this.id = "api_hwid";

        try {
            this.url = new URL(url);

            Scanner scanner = new Scanner(this.url.openStream(), "UTF-8");

            while (scanner.hasNextLine()) {

                String hwid = scanner.nextLine();

                list.add(hwid);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public boolean contains (String hwid) {
        for (String h: list) {
            if (hwid.equals(h)) return true;
        }
        return false;
    }

}
