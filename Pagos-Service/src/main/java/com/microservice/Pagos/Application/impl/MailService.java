package com.microservice.Pagos.Application.impl;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Emailv31;
import com.microservice.Pagos.Domain.DTO.UserDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Service
public class MailService {


    @Async
    public CompletableFuture<String> sendMessage(UserDTO userDTO, double paymentPrice) {
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;
        client = new MailjetClient(("3063067a49da80604a51260743177d0f"),("8619a67cc3d294a4172b8d62262ba228"), new ClientOptions("v3.1")); //MIS KEY SECRESTS
        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "luisangelanonimo@gmail.com")
                                        .put("Name", "Carnicería"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", userDTO.email()) //CORREO DEL QUE RECIBE
                                                .put("Name", userDTO.name())))//NOMBRE DEL USER TBM
                                .put(Emailv31.Message.TEMPLATEID, 6105997)//PLANTILLA DEL HTML
                                .put(Emailv31.Message.TEMPLATELANGUAGE, true)
                                .put(Emailv31.Message.SUBJECT, "Compra Recibida!")
                                .put(Emailv31.Message.VARIABLES, new JSONObject()
                                        .put("date",new Date())
                                        .put("payment", paymentPrice)))); //SETEAR USER TBM
        try {
            response = client.post(request);
            return CompletableFuture.completedFuture("Mensaje creado con éxito"+response.getData().toString());
        } catch (Exception e) {
            return CompletableFuture.completedFuture("FALLO "+ e.getMessage());
        }
    }
    /* METODO ANTIGUO CON MAILSTRAP
    @Async
    public CompletableFuture<String> sendMessage() {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        String jsonBody = "{\"from\":{\"email\":\"mailtrap@demomailtrap.com\",\"name\":\"Mailtrap Test\"},\"to\":" +
                "[{\"email\":\"angeloxz.com@gmail.com\"}],\"template_uuid\":\"4f7ef8db-78d9-434b-85ac-47aa738c3a77\"," +
                "\"template_variables\":{\"user_name\":\"Test_User_name\",\"next_step_link\":\"Test_Next_step_link\"," +
                "\"get_started_link\":\"Test_Get_started_link\",\"onboarding_video_link\":\"Test_Onboarding_video_link\"}}";

        RequestBody body = RequestBody.create(jsonBody, mediaType);

        Request request = new Request.Builder()
                .url("https://send.api.mailtrap.io/api/send")
                .method("POST", body)
                .addHeader("Authorization", "Bearer "+mailToken)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return CompletableFuture.completedFuture("Mensaje creado con éxito");
            } else {
                return CompletableFuture.completedFuture("Error al enviar el mensaje: " + response.message());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage(), e);
        }
    }
*/
}
