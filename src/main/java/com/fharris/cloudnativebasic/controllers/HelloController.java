package com.fharris.cloudnativebasic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

@Controller
public class HelloController {
    String hostname;
    String ip;

    @GetMapping("/hello")
    public String sayHello(
            @RequestParam(defaultValue = "World", required = false) String name,
            Model model){
        model.addAttribute("user", name);

            try(final DatagramSocket socket = new DatagramSocket()){
                socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                ip = socket.getLocalAddress().getHostAddress();
                System.out.println("My ip is " + ip);
                model.addAttribute("ip", ip);
                socket.connect(InetAddress.getByName(InetAddress.getLocalHost().getHostName()), 10002);
                hostname = socket.getLocalAddress().getHostName();
                System.out.println("My hostname is " + hostname);
                model.addAttribute("hostname", hostname);
            } catch (SocketException | UnknownHostException e) {
                hostname = "something wrong happened";
                ip = "something wrong happened";
                e.printStackTrace();
            }

        return "hello";
    }
}
