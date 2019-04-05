package com.altarisnine.redstone.common.test;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class TestDiscord {

//    @Test to temporarily disable test
    public void testWebhook1() {
        try {
            Unirest.post("https://discordapp.com/api/webhooks/520599030242344960/Y37dttOZHtkXLUQYicSIpTnwIAsXGQUCg7c5fDnAbZayBwqakEOtGZe7lECphVM_iXFL")
                    .field("username", "Join")
                    .field("avatar_url", "https://minotar.net/avatar/Ryde__/128.png")
                    .field("content", "**Ryde__** has joined the world!")
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    /*@Test
    public void testREgion() {
        SerializedBy anno = BasicRegion.class.getAnnotation(SerializedBy.class);
        System.out.println(anno);
    }*/

}
