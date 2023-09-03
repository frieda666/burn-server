package edu.uci.banerjee.burnserver.model;

import java.io.Serializable;

public class Statistics implements Serializable {
  private Integer numFires;
  private Double avgSize;
  private Integer minYear;
  private Integer maxYear;
  private Double minSize;
  private Double maxSize;
  private Integer minMonth;
  private Integer maxMonth;

  public Statistics(
      Integer numFires,
      Double avgSize,
      Integer minYear,
      Integer maxYear,
      Double minSize,
      Double maxSize,
      Integer minMonth,
      Integer maxMonth) {
    this.numFires = numFires;
    this.avgSize = avgSize;
    this.minYear = minYear;
    this.maxYear = maxYear;
    this.minSize = minSize;
    this.maxSize = maxSize;
    this.minMonth = minMonth;
    this.maxMonth = maxMonth;
  }


  public Integer getNumFires() {
    return numFires;
  }


  public void setNumFires(Integer numFires) {
    this.numFires = numFires;
  }


  public Double getAvgSize() {
    return avgSize;
  }


  public void setAvgSize(Double avgSize) {
    this.avgSize = avgSize;
  }


  public Integer getMinYear() {
    return minYear;
  }


  public void setMinYear(Integer minYear) {
    this.minYear = minYear;
  }


  public Integer getMaxYear() {
    return maxYear;
  }


  public void setMaxYear(Integer maxYear) {
    this.maxYear = maxYear;
  }


  public Double getMinSize() {
    return minSize;
  }


  public void setMinSize(Double minSize) {
    this.minSize = minSize;
  }


  public Double getMaxSize() {
    return maxSize;
  }


  public void setMaxSize(Double maxSize) {
    this.maxSize = maxSize;
  }


  public Integer getMinMonth(){
    return minMonth;
  }


  public void setMinMonth(Integer minMonth){
    this.minMonth = minMonth;
  }


  public Integer getMaxMonth(){
    return maxMonth;
  }


  public void setMaxMonth(Integer maxMonth){
    this.maxMonth = maxMonth;
  }


  @Override
  public String toString() {
    return "Statistics{"
        + "numFires="
        + numFires
        + ", avgSize="
        + avgSize
        + ", minYear="
        + minYear
        + ", maxYear="
        + maxYear
        + '}';
  }
}
