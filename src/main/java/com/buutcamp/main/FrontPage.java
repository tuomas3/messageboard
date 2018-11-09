package com.buutcamp.main;

import com.buutcamp.database.HibernateMethods;
import com.buutcamp.database.MessageData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class FrontPage {

    private ArrayList<MessageData> messages;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String frontPageGET(Model model){
        model.addAttribute("messagedataobjects", getAllData());
        return "front-page";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String frontPagePOST(@RequestParam(name="textareacontent",required = false) String textareacontent,
                                @RequestParam(name="username",required = false) String username,
                                Model model){
        if(Utils.stringIsNullOrEmpty(textareacontent)){
            model.addAttribute("alert", "Viestikenttä ei voi olla tyhjä.");
        } else {
            String date = Utils.getDateStr();
            addToDatabase(username, date, textareacontent);
        }
        model.addAttribute("messagedataobjects", getAllData());
        return "front-page";
    }

    private void addToDatabase(String username, String date, String message){
        HibernateMethods.saveMessage(new MessageData(username, date, message));
    }


    private List<MessageData> getAllData() {
        List<MessageData> messageDataList = HibernateMethods.getAllData();
        Collections.reverse(messageDataList);
        for (MessageData m : messageDataList) {
            if (Utils.stringIsNullOrEmpty(m.getUsername())) {
                m.setUsername(Utils.getUnnamedPoster());
            }
        }
        return messageDataList;
    }
}
