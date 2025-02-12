package org.example;
import org.json.*;

import javax.swing.*;
import java.io.*;
import java.net.*;


public class Back extends JFrame {

    String name;
    String convertfrom ;
    String convertto;

    Float countfrom;
    Float countto;

    Float courseRub;
    Float courseUSD;
    Float courseCNY;

    String result;

    Float currentRUB = 0F;
    Float currentUSD = 0F;
    Float currentCNY = 0F;
    Bd save = new Bd();
Back() throws Exception {

    addWindowListener(new java.awt.event.WindowAdapter() {
        public void windowClosing(java.awt.event.WindowEvent e) {
            save.closeConnection();
        }
    });

    save.openConnection();
    URL url = new URL("http://www.cbr.ru/scripts/XML_daily_eng.asp?");
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");

    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String xmltext = in.readLine();
    String jsonobj = XML.toJSONObject(xmltext).toString();
    JSONObject data = new JSONObject(jsonobj);
    JSONArray array = data.getJSONObject("ValCurs").getJSONArray("Valute");
    for(int i = 0;i<array.length();i++){
        if(array.getJSONObject(i).getString("CharCode").equals("USD")){
            this.courseUSD = Float.parseFloat(array.getJSONObject(i).getString("Value"). replace(',', '.'));
        }

        if(array.getJSONObject(i).getString("CharCode").equals("CNY")){
            this.courseCNY = Float.parseFloat(array.getJSONObject(i).getString("Value"). replace(',', '.'));
        }
    }

    this.name = "";
    this.convertfrom = "RUB";
    this.convertto = "RUB";

    this.countfrom = 0F;
    this.countto = 0F;

    this.courseRub = 1F;


    this.result="";

}


    public Float getCurrentRUB() {
        return currentRUB;
    }

    public Float getCurrentUSD() {
        return currentUSD;
    }

    public Float getCurrentCNY() {
        return currentCNY;
    }

    public String getName() {
        return name;
    }
    public String getConvertfrom(){
        return convertfrom;
    }

    public String getConvertto() {
        return convertto;
    }

    public Float getCountfrom() {
        return countfrom;
    }

    public Float getCountto() {
        return countto;
    }

    public void submitName(String name) {
       this.name = name;
    }

    public void setValuteFrom(String convertfrom){
        this.convertfrom = convertfrom;

    }

    public void setValuteTo(String convertto){
        this.convertto = convertto;
    }

    public String setCount(String count){
        this.countfrom = Float.valueOf(count);
        Float convfr=1F;
        Float convto=1F;
        switch(convertfrom){
            case "RUB":
                convfr = 1F;
                break;
            case "USD":
                convfr = this.courseUSD;
                break;
            case "CNY":
                convfr = this.courseCNY;
                break;
        }

        switch(convertto){
            case "RUB":
                convto = 1F;
                break;
            case "USD":
                convto = this.courseUSD;
                break;
            case "CNY":
                convto = this.courseCNY;
                break;
        }
        this.countto = Float.valueOf(count)*(convfr/convto);
        Float a =  Float.valueOf(count)*(convfr/convto);
        result = a.toString();
        return result;
    }

    public String RefrehRUB(){
    try {
        this.currentRUB = save.getValute(name,"countRUB");
        return currentRUB.toString();
    }
    catch(Exception e){
        e.printStackTrace();
        return "0";
    }
    }

    public String RefrehUSD(){
        try {
            this.currentUSD = save.getValute(name,"countUSD");
            return currentUSD.toString();
        }
        catch(Exception e){
            e.printStackTrace();
            return "0";
        }
    }

    public String RefrehCNY(){
        try {
            this.currentCNY = save.getValute(name,"countCNY");
            return currentCNY.toString();
        }
        catch(Exception e){
            e.printStackTrace();
            return "0";
        }
    }
    public void transaction(){
        switch (convertfrom){
            case("RUB"):
                if(currentRUB>=countfrom)
                    currentRUB-=countfrom;
                else
                    return;
                break;
            case("USD"):
                if(currentUSD>=countfrom)
                    currentUSD-=countfrom;
                else
                    return;
                break;
            case("CNY"):
                if(currentCNY>=countfrom)
                    currentCNY-=countfrom;
                else
                    return;
                break;
        }

        switch (convertto){
            case("RUB"):
                currentRUB+=countto;
                break;
            case("USD"):
                currentUSD+=countto;
                break;
            case("CNY"):
                currentCNY+=countto;
                break;
        }
        save.updateData(name,currentRUB,currentUSD,currentCNY);

    }


}
