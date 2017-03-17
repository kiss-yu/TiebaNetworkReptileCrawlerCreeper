package com.creeper.getshuju;

import com.creeper.model.TiebaInforModel;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 11723 on 2016/12/29.
 */

public class TiebaInfor extends TopPareant{
    private List<TiebaInforModel> sets=new ArrayList<>();
    private  TiebaInforModel model=new TiebaInforModel();
    private String tiebaName;
    public TiebaInfor(String tiebaName){
        this.tiebaName=tiebaName;
    }
    private TiebaInforModel getTiebaInforModel(){
        setPosts();
        setCreateTime();
        model.setTiebaName(tiebaName);
        return model;
    }
    public List<TiebaInforModel> getInforSets(){
        sets.add(getTiebaInforModel());
        return sets;
    }

    private void setPosts(){
        model.setPostModels(new Posts(tiebaName).getPostList());
    }
    private void setCreateTime() {
        setUserNumber();
        model.setCreateTime(new Date((new GregorianCalendar().getTimeInMillis())));
    }

    private void setUserNumber() {
        try {
            String header=getHeader();
            model.setTieba_user_number(getNumber(header, "关注"));
            setPostNumber(header);
        } catch (Exception e) {
            model.setTieba_user_number(0);
        }
    }

    private void setPostNumber(String header){
        try {
            model.setPost_number(getNumber(header, "贴子"));
        } catch (Exception e) {
            model.setPost_number(0);
        }
    }

    private String getHeader(){
        return ROOTDOC.getElementsByClass("header").first().toString();
    }

    private Integer getNumber(String content,String key){
        try {
            return getNum(content.split(key)[1]);
        } catch (Exception e) {
        }
        return 0;
    }



    private Integer getNum(String string){
        try {
            Pattern p = Pattern.compile("[[\\d]+[,|-]{1}]+[\\d]+");
            Matcher m = p.matcher(string);
            while(m.find()){
                return Integer.valueOf(m.group().replaceAll("[\\D]", ""));
            }
        } catch (Exception e) {
        }
        return 0;
    }
}
