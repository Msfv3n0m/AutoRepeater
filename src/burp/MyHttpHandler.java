package burp;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.handler.*;

import static burp.api.montoya.http.handler.RequestToBeSentAction.continueWith;
import static burp.api.montoya.http.handler.ResponseReceivedAction.continueWith;

class MyHttpHandler implements HttpHandler {
	
    private final MontoyaApi api;
    private boolean status = false;
    public MyHttpHandler(MontoyaApi api) {
        this.api = api;
    }

    
    public boolean getStatus() {
    	return this.status;
    }
    public void toggleStatus() {
    	if (this.status == true) {
    		this.status = false;
    	}
    	else {
    		this.status = true;
    	}
    }
    @Override
    public RequestToBeSentAction handleHttpRequestToBeSent(HttpRequestToBeSent requestToBeSent) {
    	if (this.status) {
        	this.api.repeater().sendToRepeater(requestToBeSent);
    	}
    	return continueWith(requestToBeSent);
    }

    @Override
    public ResponseReceivedAction handleHttpResponseReceived(HttpResponseReceived responseReceived) {
        return continueWith(responseReceived);
    }
}