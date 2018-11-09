package com.buutcamp.main;

import com.buutcamp.database.HibernateMethods;
import com.buutcamp.database.MessageData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class SearchPage {

    @GetMapping(value = "/search")
    public String searchPageGET(Model model){
        SearchBar initialState = new SearchBar();
        initialState.setUsername(true);
        initialState.setDate(true);
        initialState.setMessage(true);
        model.addAttribute("searchBar", initialState);
        return "search-page";
    }

    @PostMapping(value = "/search")
    public String searchPagePOST(@ModelAttribute("searchBar") SearchBar bar, Model model){
        model.addAttribute("messagedataobjects", getSearchData(bar.getSearchValue(), bar.isUsername(), bar.isDate(), bar.isMessage()));
        model.addAttribute("searchBar", bar);
        return "search-page";
    }

    private List<MessageData> getSearchData(String string, boolean username, boolean date, boolean message){
        List<MessageData> messageDataList = HibernateMethods.searchMessages(string, username, date, message);
        Collections.reverse(messageDataList);
        for (MessageData m : messageDataList) {
            if (Utils.stringIsNullOrEmpty(m.getUsername())) {
                m.setUsername(Utils.getUnnamedPoster());
            }
        }
        return messageDataList;
    }

}
