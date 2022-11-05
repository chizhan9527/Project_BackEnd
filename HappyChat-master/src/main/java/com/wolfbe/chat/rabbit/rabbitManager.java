package com.wolfbe.chat.rabbit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class rabbitManager {
    private static rabbit rab;
    static{
        try {
            rab=new rabbit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
    public static rabbit getRabbit(){return rab;}

}
