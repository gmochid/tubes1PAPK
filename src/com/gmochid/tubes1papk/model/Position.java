package com.gmochid.tubes1papk.model;

import android.location.Location;

public class Position {
	public static double ER = 6371000;

    private double latitude;
    private double longitude;

    public Position(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public static double degToRad(double deg)
    {
        return deg * (Math.PI / 180);
    }

    public static double radToDeg(double rad)
    {
        return rad * (180 / Math.PI);
    }

    public static Location createLocation(String provider, Position pos)
    {
        Location loc = new Location(provider);
        loc.setLatitude(pos.getLatitude());
        loc.setLongitude(pos.getLongitude());

        return loc;
    }

    public static Position calculatePosisition(double distance, double bearing, Position startPos)
    {
        double bearingRad = degToRad(bearing);
        double latA = degToRad(startPos.getLatitude());
        double lonA = degToRad(startPos.getLongitude());

        double latB = Math.asin(Math.sin(latA) * Math.cos(distance / ER) +
                Math.cos(latA) * Math.sin(distance / ER) * Math.cos(bearingRad));
        double lonB = lonA + Math.atan2(Math.sin(bearingRad) * Math.sin(distance / ER) * Math.cos(latA),
                Math.cos(distance / ER) - Math.sin(latA) * Math.sin(latB));

        return new Position(radToDeg(latB), radToDeg(lonB));
    }


    public static double calculateDistance(Position objectA, Position objectB)
    {
        Location locA = createLocation("A", objectA);
        Location locB = createLocation("B", objectB);

        return locA.distanceTo(locB);
    }

    public static double calculateBearing(Position start, Position target)
    {
        Location locA = createLocation("A", target);
        Location locB = createLocation("B", start);

        return locA.bearingTo(locB) + 180 % 360;
    }

    // Hard Ways

    public static double calculateDistanceHW(Position objectA, Position objectB)
    {
        double latA = degToRad(objectA.getLatitude());
        double lonA = degToRad(objectA.getLongitude());

        double latB = degToRad(objectB.getLatitude());
        double lonB = degToRad(objectB.getLongitude());

        double dLat = latB - latA;
        double dLon = lonB - lonA;

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) * Math.cos(latA) * Math.cos(latB);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return ER * c;
    }

    public static double calculateBearingHW(Position start, Position target)
    {
        double latA = degToRad(target.getLatitude());
        double lonA = degToRad(target.getLongitude());

        double latB = degToRad(start.getLatitude());
        double lonB = degToRad(start.getLongitude());

        double dLon = lonB - lonA;

        double y = Math.sin(dLon) * Math.cos(latB);
        double x = Math.cos(latA) * Math.sin(latB) -
                Math.sin(latA) * Math.cos(latB) * Math.cos(dLon);

        double bearing = Math.atan2(y, x);

        return radToDeg(bearing) + 180 % 360;
    }

    public static Position fromLocation(Location location)
    {
        return new Position(location.getLatitude(), location.getLongitude());
    }

    public String toString()
    {
        return "(" + latitude + ", " + longitude + ")";
    }
}
