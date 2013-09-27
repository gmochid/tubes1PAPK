package com.gmochid.tubes1papk.model;

public class Chest
{
    private String id;
    private String bssid;
    private int wifi;
    private Position chestPos;

    private double bearing;
    private double distance;

    public Chest(String id, String bssid, double distance, double degree, Position currentPos)
    {
        this.id = id;
        this.bssid = bssid;
        this.chestPos = Position.calculatePosisition(distance, degree, currentPos);
        this.wifi = 0;
        this.distance = distance;
        this.bearing = degree;
    }

    public void recalculateDistance(Position pos)
    {
        distance = Position.calculateDistance(pos, chestPos);
    }

    public void recalculateBearing(Position pos)
    {
        bearing = Position.calculateBearingHW(pos, chestPos);
    }

    public double getDistance()
    {
        return distance;
    }

    public double getBearing()
    {
        return bearing;
    }

    public String getId()
    {
        return id;
    }

    public String getBssid()
    {
        return bssid;
    }

    public int getWifi()
    {
        return wifi;
    }

    public Position getChestPos()
    {
        return chestPos;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setBssid(String bssid)
    {
        this.bssid = bssid;
    }

    public void setWifi(int wifi)
    {
        this.wifi = wifi;
    }

    public void setChestPos(Position position)
    {
        this.chestPos = position;
    }

    public String toString()
    {
        return "ID: " + id + " - " + chestPos.toString();
    }
}