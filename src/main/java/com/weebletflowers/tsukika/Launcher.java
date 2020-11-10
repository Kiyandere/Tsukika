package com.weebletflowers.tsukika;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.weebletflowers.tsukika.UserCommands.SelfRole;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Launcher extends ListenerAdapter
{
    private static CommandClientBuilder builder;

    public static void main(String[] args) throws LoginException
    {
        //readToken
        File tokenFile = new File("Tsukika Key.txt");
        String token = "";
        String ownerID = "";

        try
        {
            Scanner readToken = new Scanner(tokenFile);
            token = readToken.nextLine();
            ownerID = readToken.nextLine();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Don't see file");
            System.exit(-1);
        }

        //create jda
        JDA jda = JDABuilder.createDefault(token).build();

        //setting builder
        builder = new CommandClientBuilder();
        builder.setHelpWord("sendhelp");
        builder.setOwnerId(ownerID);
        builder.setPrefix("-");
        builder.setAlternativePrefix("$");

        //add commmands
        commands();

        //connect
        CommandClient client = builder.build();
        jda.addEventListener(client);
    }

    private static void commands()
    {
        builder.addCommands(new SelfRole());
    }
}
