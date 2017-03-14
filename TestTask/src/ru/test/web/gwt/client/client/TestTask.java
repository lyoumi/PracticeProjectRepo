package ru.test.web.gwt.client.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.*;

import java.util.Random;

/**
 * Created by lyoumi on 14.03.2017.
 */
public class TestTask implements EntryPoint {
    private Button button = new Button("lose");
    private boolean b = true;

    public void onModuleLoad() {

        VerticalPanel panel = new VerticalPanel();
        panel.setSpacing(10);
        panel.add(button);
        button.getElement().getStyle().setPosition(Style.Position.ABSOLUTE);
        button.getElement().getStyle().setTop(350, Style.Unit.PX);
        button.getElement().getStyle().setLeft((600), Style.Unit.PX);
        RootPanel.get().add(panel);
//        button.setStyleName("");
        button.addClickHandler(event -> {
//            int u = (int) (Math.random()*6);
            if (b){
                RootPanel.getBodyElement().getStyle().setBackgroundColor("RED");
            }else {
                RootPanel.getBodyElement().getStyle().setBackgroundColor("black");
            }
            b=!b;

        });
    }

    int randomizer(){
        System.out.println((int) (Math.random()*6));
        return (int) (Math.random()*6);
    }

}