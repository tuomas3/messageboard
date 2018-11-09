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
public class AdminPage {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPageGET(Model model){
        model.addAttribute("messagedataobjects", getAllData());
        return "admin-page";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String adminPagePOST(@RequestParam(name="deleteTheseIds",required = false) String ids,
                                @RequestParam(name="deleteClickedMessage", required=false) String id,
                                Model model){
        if(!Utils.stringIsNullOrEmpty(id)){
            deleteMessages(id);
        }
        else if(!Utils.stringIsNullOrEmpty(ids)){
            deleteMessages(ids);
        }
        model.addAttribute("messagedataobjects", getAllData());
        return "admin-page";
    }

    private List<MessageData> getAllData(){
        List<MessageData> messageDataList = HibernateMethods.getAllData();
        Collections.reverse(messageDataList);
        return messageDataList;
    }

    private void deleteMessages(String messageIds){
        if(messageIds.contains("-")){
            //range
            String[] parts = messageIds.split("-");
            if(parts.length == 2){
                Integer low = Utils.parseIntSafe(parts[0]);
                Integer high = Utils.parseIntSafe(parts[1]);
                if(low != null && high != null){
                    HibernateMethods.deleteRange(low, high);
                }
            }
        }else {
            String[] parts = messageIds.split(" ");
            if (parts.length > 1) {
                //Delete multiple messages.
                ArrayList<Integer> list = new ArrayList<Integer>();
                for (String s : parts) {
                    Integer id = Utils.parseIntSafe(s);
                    if (id != null) {
                        list.add(id);
                    }
                }
                HibernateMethods.deleteMessages(list);
            } else if (parts.length == 1) {
                //Delete single message.
                Integer id = Utils.parseIntSafe(messageIds);
                if (id != null) {
                    HibernateMethods.deleteMessage(id);
                }
            }
        }
    }
}
