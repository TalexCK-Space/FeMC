package top.talexck.femc.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import static top.talexck.femc.config.configFile.ifcoloredmodmessage;

public class ChatUtils {
    public static void SendMessageinChat(String Message){
        MinecraftClient.getInstance().player.sendMessage(Text.literal(ColorMessage(Message)),false);
    }

    public static String ColorMessage(String Message){
        if(ifcoloredmodmessage){
            StringBuilder MessageEnd = new StringBuilder();
            int i = 0;
            while (i<Message.length()){
                if (Message.charAt(i) == '&'){
                    i = i+2;
                } else{
                    MessageEnd.append(Message.charAt(i));
                }

            }
            return String.valueOf(MessageEnd);
        }
        StringBuilder MessageEnd = new StringBuilder();
        int i = 0;
        while (i<Message.length()){
            if (Message.charAt(i) == '&'){
                MessageEnd.append("§");
            } else{
                MessageEnd.append(Message.charAt(i));
            }
            i = i+1;
        }
        return String.valueOf(MessageEnd);
    }
}
