package burp;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;


//Burp will auto-detect and load any class that extends BurpExtension.
public class Main implements BurpExtension {
    @Override
    public void initialize(MontoyaApi api) {
        api.extension().setName("AutoRepeater");
        
        //Register the HTTP handler with Burp.
        api.http().registerHttpHandler(new MyHttpHandler(api));
    }
}
