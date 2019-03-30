package com.rishabh.customrecyclerviewtabs.DataModels;

public class TabItem {

  private String text;
  private int icon;
  private boolean status;

  public TabItem(String text, int icon, boolean status) {
    this.text = text;
    this.icon = icon;
    this.status = status;
  }


  public String getText() {
    return text;
  }


  public void setText(String text) {
    this.text = text;
  }


  public int getIcon() {
    return icon;
  }


  public void setIcon(int icon) {
    this.icon = icon;
  }


  public boolean isStatus() {
    return status;
  }


  public void setStatus(boolean status) {
    this.status = status;
  }


}
