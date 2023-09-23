package com.example.fruitgrowth.object;
import com.example.fruitgrowth.DBOpener;

public class Variety {
    String varietyName;
    Integer treeCount;
    String treeType;

    public Variety (String name, Integer count, String type) {
        this.varietyName = name;
        this.treeCount = count;
        this.treeType = type;
    }

}
