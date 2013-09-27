package com.gmochid.tubes1papk.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gmochid.tubes1papk.APIHelper;

public class TreasureHunt {
	// singleton class
    private static TreasureHunt instance = null;
    public static TreasureHunt getInstance() {
        if (instance == null) {
            instance = new TreasureHunt();
        }

        return instance;
    }

    private APIHelper api = new APIHelper();
    private Position currentPos = null;
    private List<Chest> chests = new ArrayList<Chest>();
    private Chest trackedChest = null;
    private int unachievedChestCount = 0;

    private TreasureHunt() {
        // TreasureHunt!
    }

    public Position getLastPos() {
        return currentPos;
    }

    public List<Chest> getChests() {
        return chests;
    }

    public Chest getTrackedChest() {
        return trackedChest;
    }

    public int getUnachievedChestCount() {
        return unachievedChestCount;
    }

    public APIHelper api() {
        return api;
    }

    // Setters
    public void setChestListFromJSON(JSONArray data) {
        chests = new ArrayList<Chest>();

        try {
            for (int i = 0; i < data.length(); i++) {
                JSONObject obj  = data.getJSONObject(i);
                String id       = obj.getString("id");
                String bssid    = obj.getString("bssid");
                double distance = obj.getDouble("distance");
                double degree   = obj.getDouble("degree");

                chests.add(new Chest(id, bssid, distance, degree, currentPos));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setUnachievedChestCountFromJSON(JSONObject json) {
        try {
            unachievedChestCount = json.getInt("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setLastPos(Position pos) {
        this.currentPos = pos;
    }

    public void setChests(List<Chest> chests) {
        this.chests = chests;
    }

    public void setTrackedChest(Chest chest) {
        this.trackedChest = chest;
    }

    public void setUnachievedChestCount(int unachievedChestCount) {
        this.unachievedChestCount = unachievedChestCount;
    }
}
