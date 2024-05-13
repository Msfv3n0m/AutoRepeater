package burp;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.handler.*;

import static burp.api.montoya.http.handler.RequestToBeSentAction.continueWith;
import static burp.api.montoya.http.handler.ResponseReceivedAction.continueWith;

class MyHttpHandler implements HttpHandler {
	
    private final MontoyaApi api;

    public MyHttpHandler(MontoyaApi api) {
        this.api = api;
    }

    @Override
    public RequestToBeSentAction handleHttpRequestToBeSent(HttpRequestToBeSent requestToBeSent) {
    	this.api.repeater().sendToRepeater(requestToBeSent);
    	return continueWith(requestToBeSent);
    }

    @Override
    public ResponseReceivedAction handleHttpResponseReceived(HttpResponseReceived responseReceived) {
        return continueWith(responseReceived);
    }
}