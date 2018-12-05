package Screens;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.model.Attachment;
import com.github.seratch.jslack.api.webhook.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OlimpiaSlack {

  public void enviarMensagem(String mensagem) throws IOException{
   String url = "https://hooks.slack.com/services/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";
   
   Payload payload = Payload.builder()
  .channel("#random")
  .username("jSlack Bot")
  .iconEmoji(":smile_cat:") 
  .text(mensagem)
  .build();
Slack slack = Slack.getInstance();
WebhookResponse response = slack.send(url, payload);
  }
}