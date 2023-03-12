package backend.exceptions.definition;

import openAPI.model.NotOkayMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

public class ApiException extends RuntimeException{
    @Value("${message.ApiException}")
    String messageGlobal;
    Integer statusCode = 500;
    private NotOkayMessage notOkayMessage = new NotOkayMessage();
    private Environment env;
    private Exception ex;



    public ApiException(Environment env) {
        processProperty(env);
    }
    public ApiException(Exception e) {
        ex = e;
        processProperty(env);
    }
    public ApiException() {
        notOkayMessage.setMessage(messageGlobal);
    }
    public void setEnv(Environment env) {
        this.env = env;
        processProperty(env);
    }

    public Integer getStatusCode() {
        return statusCode;
    }
    public NotOkayMessage getNotOkayMessage() {
        return notOkayMessage;
    }

    private void processProperty(Environment env) {
        Exception e = ex == null ? this : ex;

        try {
            String str = env.getProperty("message." + e.getClass().getSimpleName());
            System.out.println(">> requesting: message." + e.getClass().getSimpleName());
          //  e.printStackTrace();
            if(str.length() < 3) throw new Exception();
            int index = str.indexOf(',');
            if (index == -1)
                if(str.length() > 3) {
                    notOkayMessage.setMessage(str);
                    return;
                } else {
                    notOkayMessage.setMessage(messageGlobal);
                    return;
                }
            statusCode = Integer.parseInt(str.substring(0, index));
            if(statusCode > 599 || statusCode < 100)
                throw new Exception();
            notOkayMessage.setMessage(str.substring(index + 1).trim());
        } catch (Exception x) {
            notOkayMessage.setMessage(messageGlobal);
            statusCode = 500;
        }

    }
    public String toString() {
        return "{ \"message\": \"" + notOkayMessage.getMessage() + "\" }";
    }
}

