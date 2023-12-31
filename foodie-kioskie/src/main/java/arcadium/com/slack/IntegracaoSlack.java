package arcadium.com.slack;

import arcadium.com.models.Log;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;

import java.io.IOException;
// import org.json.JSONObject;


public class IntegracaoSlack {

    public String token = "xoxp-6150995821093-6156376214244-6189266014066-97c53bd703da36ac3fa4cc173b6236e2";
    public MethodsClient methods;

    public IntegracaoSlack(){
        Slack slack = Slack.getInstance();
        methods = slack.methods(token);
    }

    public void sendMessage(String message){
        Log logger = new Log();

        try{
            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                    .channel("#arcadium")
                    .text(message)
                    .build();

            ChatPostMessageResponse response = methods.chatPostMessage(request);
        } catch (SlackApiException requestFailure) {
            requestFailure.fillInStackTrace();
            logger.log("Houve um erro ao tentar enviar mensagem no slack.");
            logger.log("Exceção => " + requestFailure);
        } catch (IOException connectivityIssue) {
            connectivityIssue.fillInStackTrace();
            logger.log("Houve um erro ao tentar enviar mensagem no slack.");
            logger.log("Exceção => " + connectivityIssue);
        }
    }

}